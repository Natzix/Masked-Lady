package org.natzi.maskedlady.service;

import com.nimbusds.jose.JOSEException;
import jakarta.persistence.EntityManager;
import org.natzi.maskedlady.config.exception.NimbusJoseException;
import org.natzi.maskedlady.entity.Account;
import org.natzi.maskedlady.entity.AccountType;
import org.natzi.maskedlady.entity.OneTimeTokenLogin;
import org.natzi.maskedlady.entity.EmailConfirmationUUID;
import org.natzi.maskedlady.repository.AccountRepository;
import org.natzi.maskedlady.service.email.EmailService;
import org.natzi.maskedlady.service.ott.OneTimeTokenService;
import org.natzi.maskedlady.service.ott.OneTimeTokenValidation;
import org.natzi.maskedlady.utils.dto.*;
import org.natzi.maskedlady.utils.jwt.JoseJwtSetting;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final WorkForAuthentication serviceWorkAuth;
    private final OneTimeTokenValidation serviceValidationOtt;
    private final OneTimeTokenService serviceOtt;
    private final EmailService serviceEmail;
    private final JoseJwtSetting jwt;
    private final AccountRepository accountRepository;

    public AuthenticationServiceImpl(WorkForAuthentication serviceWorkAuth, OneTimeTokenValidation serviceValidationOtt,
                                     OneTimeTokenService serviceOtt, EmailService serviceEmail, JoseJwtSetting jwt,
                                     AccountRepository accountRepository) {
        this.serviceWorkAuth = serviceWorkAuth;
        this.serviceValidationOtt = serviceValidationOtt;
        this.serviceOtt = serviceOtt;
        this.serviceEmail = serviceEmail;
        this.jwt = jwt;
        this.accountRepository = accountRepository;
    }

    @Override
    public SendEmailDTO createAccount(CreateAccountDTO accountDTO) {
        String username = accountDTO.username();
        String email = accountDTO.email();

        serviceWorkAuth.ensureAccountDoesNotExist(username);
        serviceWorkAuth.searchType(accountDTO.type());
        serviceWorkAuth.emailIsPresent(email);

        HasAToken hasAToken = serviceOtt.generate(username);

        EmailConfirmationUUID emailConfirmation = serviceWorkAuth
                .validateEmailBeforeSaving(hasAToken, email);

        accountRepository.save(new Account(username, email, false, new AccountType(1, "")));

        return new SendEmailDTO(
                emailConfirmation.getId(),
                "Validación de Correo Electronico",
                username,
                email,
                hasAToken.ott()
        );
    }

    @Override
    public void login(AccountLoginDTO login, String uri) {
        Account account = serviceWorkAuth.ensureAccountExists(login.username());
        HasAToken hasAToken = serviceOtt.generate(login.username());

        serviceWorkAuth.saveLoginToken(hasAToken);

        serviceEmail.sendValidationEmail(new SendEmailDTO(
                account.getId(),
                "Inicio de sesion",
                login.username(),
                account.getEmail(),
                hasAToken.ott()
        ) ,uri);
    }

    @Override
    public void verifyAccountRegistration(String ott) {
        EmailConfirmationUUID ottTime = serviceWorkAuth.searchAccountRegistrationOtt(ott);
        serviceValidationOtt.consume(ottTime);

        serviceWorkAuth.saveAsVerifiedAccount(ottTime.getUsername());

    }

    @Override
    public String verifyLoginOtt(String ott) {
        OneTimeTokenLogin token = serviceWorkAuth.searchLoginOtt(ott);
        serviceOtt.consume(token);

        try {
            return jwt.buildJWT(token);
        } catch (JOSEException ex) {
            throw new NimbusJoseException("Error al construir el JWT", ex);
        }
    }
}