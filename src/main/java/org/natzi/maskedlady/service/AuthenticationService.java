package org.natzi.maskedlady.service;

import org.natzi.maskedlady.utils.dto.*;

public interface AuthenticationService {

    SendEmailDTO createAccount(CreateAccountDTO accountDTO);
    void verifyAccountRegistration(String ott);
    void login(AccountLoginDTO login, String uri);
    String verifyLoginOtt(String ott);

}
