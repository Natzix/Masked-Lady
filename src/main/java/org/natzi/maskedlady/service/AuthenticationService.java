package org.natzi.maskedlady.service;

import org.natzi.maskedlady.utils.dto.*;

public interface AuthenticationService {

    SendEmailDTO createAccount(CreateAccountDTO accountDTO);
    AccountCreatedDTO verifyAccountRegistration(String ott);
    void login(AccountLoginDTO login, String uri);
    void verifyLoginOtt(String ott);

}
