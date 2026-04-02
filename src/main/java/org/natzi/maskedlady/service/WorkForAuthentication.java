package org.natzi.maskedlady.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.natzi.maskedlady.config.exception.InvalidPersistenceException;
import org.natzi.maskedlady.entity.Account;
import org.natzi.maskedlady.entity.OneTimeTokenLogin;
import org.natzi.maskedlady.entity.EmailConfirmationUUID;
import org.natzi.maskedlady.entity.AccountType;
import org.natzi.maskedlady.repository.AccountRepository;
import org.natzi.maskedlady.repository.OneTimeTokenRepository;
import org.natzi.maskedlady.repository.EmailConfirmationRepository;
import org.natzi.maskedlady.utils.dto.CreateAccountDTO;
import org.natzi.maskedlady.utils.dto.HasAToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class WorkForAuthentication {

    private final AccountRepository repositoryAuth;
    private final EmailConfirmationRepository repositoryOttRegister;
    private final OneTimeTokenRepository repositoryOttLogin;
    private final EntityManager em;

    public WorkForAuthentication(AccountRepository repositoryAuth, EmailConfirmationRepository repositoryOttRegister,
                                 OneTimeTokenRepository repositoryOttLogin, EntityManager em) {
        this.repositoryAuth = repositoryAuth;
        this.repositoryOttRegister = repositoryOttRegister;
        this.repositoryOttLogin = repositoryOttLogin;
        this.em = em;
    }

    public void ensureAccountDoesNotExist(String username) {
        if (repositoryAuth.findByUsername(username).isPresent()) {
            throw new InvalidPersistenceException("El username ya está en uso");
        }
    }

    public Account ensureAccountExists(String username) {
        return repositoryAuth.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("El username no existe"));
    }


    @Transactional
    public Account createAccount(CreateAccountDTO accountDTO) {
        Account account = new Account();
        account.setUsername(accountDTO.username());

        account.setEmail(accountDTO.email());
        AccountType accountType = searchType(accountDTO.type());
        account.setAccountType(accountType);

        return repositoryAuth.save(account);
    }

    @Transactional
    public EmailConfirmationUUID validateEmailBeforeSaving(HasAToken hasAToken, String email) {
        EmailConfirmationUUID emailConfirmation = new EmailConfirmationUUID();
        emailConfirmation.setUsername(hasAToken.username());
        emailConfirmation.setCodConfirmation(hasAToken.ott());
        emailConfirmation.setIssuedAt(LocalDateTime.now());
        emailConfirmation.setExpiresAt(hasAToken.expiresAt());

        return repositoryOttRegister.save(emailConfirmation);
    }

    public void saveLoginToken(HasAToken hasAToken) {
        OneTimeTokenLogin token = new OneTimeTokenLogin();
        token.setUsername(hasAToken.username());
        token.setIssuedAt(LocalDateTime.now());
        token.setExpiresAt(hasAToken.expiresAt());
        token.setOtt(hasAToken.ott());
        repositoryOttLogin.save(token);
    }

    @Transactional
    public void saveAsVerifiedAccount(String username) {
        Account tempAccount = repositoryAuth.findByUsername(username)
                .orElseThrow(() -> new InvalidPersistenceException("Hubo un error al persistir la informacion"));

        tempAccount.setActive(true);
        repositoryAuth.save(tempAccount);
    }

    public EmailConfirmationUUID searchAccountRegistrationOtt(String ott) {
        return repositoryOttRegister.findByCodConfirmation(ott)
                .orElseThrow(() -> new EntityNotFoundException("No existe ese token ingresado"));
    }

    public AccountType searchType(int id) {
        return Optional.ofNullable(em.find(AccountType.class, id))
                .orElseThrow(() -> new EntityNotFoundException("Patron de creación incorrecta"));
    }

    public OneTimeTokenLogin searchLoginOtt(String ott) {
        return repositoryOttLogin.findByOtt(ott)
                .orElseThrow(() -> new EntityNotFoundException("No existe ese token ingresado"));
    }

    public void emailIsPresent(String email) {
        Optional<Account> account = repositoryAuth.findByEmail(email);
        if (account.isPresent()) {
            throw new InvalidPersistenceException("El email ya esta en uso");
        }
    }

}
