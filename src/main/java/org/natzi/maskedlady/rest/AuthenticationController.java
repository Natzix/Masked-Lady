package org.natzi.maskedlady.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.natzi.maskedlady.service.AuthenticationService;
import org.natzi.maskedlady.service.email.EmailService;
import org.natzi.maskedlady.utils.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.logging.Logger;

@RestController
@RequestMapping("/masked-auth")
public class AuthenticationController {
    private final AuthenticationService serviceAuth;
    private final EmailService emailService;
    private final Logger lg = Logger.getLogger(AuthenticationController.class.getName());

    public AuthenticationController(AuthenticationService serviceAuth, EmailService emailService) {
        this.serviceAuth = serviceAuth;
        this.emailService = emailService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseEntityDTO> login(@RequestBody @Valid AccountLoginDTO login, HttpServletRequest request) {
        serviceAuth.login(login, request.getRequestURI());

        return ResponseEntity.ok(
                new ResponseEntityDTO("Enlace de inicio de sesion enviado", HttpStatus.OK.value())
        );
    }

    @GetMapping("/login/verify")
    public ResponseEntity<ResponseEntityDTO> loginProcess(@RequestParam(name = "ott") String ott) {
        serviceAuth.verifyLoginOtt(ott);

        return  ResponseEntity.ok(
                new ResponseEntityDTO("Bienvenido", HttpStatus.OK.value())
        );
    }

    @PostMapping("/create-account")
    public ResponseEntity<SendEmailDTO> createAccount(@RequestBody @Valid CreateAccountDTO createAccountDTO, HttpServletRequest request) {
        SendEmailDTO validateEmail = serviceAuth.createAccount(createAccountDTO);

        emailService.sendValidationEmail(validateEmail, request.getRequestURI());

        int id = validateEmail.id();

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri)
                .body(validateEmail);
    }

    @GetMapping("/create-account/verify")
    public ResponseEntity<AccountCreatedDTO> registrationProcess(@RequestParam(name = "ott") String ott) {

        AccountCreatedDTO account = serviceAuth.verifyAccountRegistration(ott);

        int id = account.id();
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri)
                .body(account);
    }
}
