package org.natzi.maskedlady.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.natzi.maskedlady.config.exception.InvalidPersistenceException;
import org.natzi.maskedlady.entity.Account;
import org.natzi.maskedlady.entity.OneTimeToken;
import org.natzi.maskedlady.entity.OttTimeTable;
import org.natzi.maskedlady.entity.Role;
import org.natzi.maskedlady.repository.AuthenticationRepository;
import org.natzi.maskedlady.repository.OneTimeTokenRepository;
import org.natzi.maskedlady.repository.OttTimeTableRepository;
import org.natzi.maskedlady.utils.dto.CreateAccountDTO;
import org.natzi.maskedlady.utils.dto.HasAToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class WorkForAuthentication {

    private final AuthenticationRepository repositoryAuth;
    private final OttTimeTableRepository repositoryOttRegister;
    private final OneTimeTokenRepository repositoryOttLogin;
    private final EntityManager em;

    public WorkForAuthentication(AuthenticationRepository repositoryAuth, OttTimeTableRepository repositoryOttRegister,
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
        Role role = searchRole(accountDTO.role());
        account.setRoleId(role);

        return repositoryAuth.save(account);
    }

    @Transactional
    public OttTimeTable validateEmailBeforeSaving(HasAToken hasAToken, String email) {
        OttTimeTable ottTimeTable = new OttTimeTable();
        ottTimeTable.setUsername(hasAToken.username());
        ottTimeTable.setEmail(email);
        ottTimeTable.setOtt(hasAToken.ott());
        ottTimeTable.setIssuedAt(LocalDateTime.now());
        ottTimeTable.setExpiresAt(hasAToken.expiresAt());

        return repositoryOttRegister.save(ottTimeTable);
    }

    public void saveLoginToken(HasAToken hasAToken) {
        OneTimeToken token = new OneTimeToken();
        token.setUsername(hasAToken.username());
        token.setOtt(hasAToken.ott());
        token.setIssuedAt(LocalDateTime.now());
        token.setExpiresAt(hasAToken.expiresAt());
        
        repositoryOttLogin.save(token);
    }

    public OttTimeTable searchAccountRegistrationOtt(String ott) {
        return repositoryOttRegister.findByOtt(ott)
                .orElseThrow(() -> new EntityNotFoundException("No existe ese token ingresado"));
    }

    public Role searchRole(int id) {
        return Optional.of(em.find(Role.class, id))
                .orElseThrow(() -> new EntityNotFoundException("Patron de creación incorrecta"));
    }

    public OneTimeToken searchLoginOtt(String ott) {
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
