package org.natzi.maskedlady.service;

import org.natzi.maskedlady.entity.Account;
import org.natzi.maskedlady.entity.OneTimeToken;
import org.natzi.maskedlady.entity.OttTimeTable;
import org.natzi.maskedlady.service.email.EmailService;
import org.natzi.maskedlady.service.ott.OneTimeTokenService;
import org.natzi.maskedlady.service.ott.OneTimeTokenValidation;
import org.natzi.maskedlady.utils.dto.*;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final WorkForAuthentication serviceWorkAuth;
    private final OneTimeTokenValidation serviceValidationOtt;
    private final OneTimeTokenService serviceOtt;
    private final EmailService serviceEmail;

    public AuthenticationServiceImpl(WorkForAuthentication serviceWorkAuth, OneTimeTokenValidation serviceValidationOtt,
                                     OneTimeTokenService serviceOtt, EmailService serviceEmail) {
        this.serviceWorkAuth = serviceWorkAuth;
        this.serviceValidationOtt = serviceValidationOtt;
        this.serviceOtt = serviceOtt;
        this.serviceEmail = serviceEmail;
    }

    @Override
    public SendEmailDTO createAccount(CreateAccountDTO accountDTO) {
        String username = accountDTO.username();
        String email = accountDTO.email();

        serviceWorkAuth.ensureAccountDoesNotExist(username);
        serviceWorkAuth.searchRole(accountDTO.role());
        serviceWorkAuth.emailIsPresent(email);

        HasAToken hasAToken = serviceOtt.generate(username);

        OttTimeTable ottTimeTable = serviceWorkAuth
                .validateEmailBeforeSaving(hasAToken, email);

        return new SendEmailDTO(
                ottTimeTable.getId(),
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
    public AccountCreatedDTO verifyAccountRegistration(String ott) {
        OttTimeTable ottTime = serviceWorkAuth.searchAccountRegistrationOtt(ott);
        serviceValidationOtt.consume(ottTime);

        String username = ottTime.getUsername();
        String email = ottTime.getEmail();

        CreateAccountDTO accountDTO = new CreateAccountDTO(
                username,
                email,
                1
        );
        Account account = serviceWorkAuth.createAccount(accountDTO);

        return new AccountCreatedDTO(account.getId(), username, email);
    }

    @Override
    public void verifyLoginOtt(String ott) {
        OneTimeToken token = serviceWorkAuth.searchLoginOtt(ott);
        serviceOtt.consume(token);
    }
}