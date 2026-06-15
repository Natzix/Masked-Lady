package org.natzi.maskedlady.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.natzi.maskedlady.service.auth.AuthenticationService;
import org.natzi.maskedlady.service.email.EmailService;
import org.natzi.maskedlady.utils.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.logging.Logger;

@Validated
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
    public ResponseEntity<ResponseEntityDTO> login(@RequestBody @Valid AccountLoginDTO dtoLogin, HttpServletRequest request) {
        serviceAuth.login(dtoLogin, request.getRequestURI());

        return ResponseEntity.ok(
                new ResponseEntityDTO("Enlace de inicio de sesion enviado", HttpStatus.OK.value())
        );
    }

    @GetMapping("/login/verify")
    public ResponseEntity<ResponseEntityDTO> loginProcess(@NotBlank @RequestParam(name = "ott") String ott) {
        String jwt = serviceAuth.verifyLoginOtt(ott);

        return  ResponseEntity.ok(
                new ResponseEntityDTO(jwt, HttpStatus.OK.value())
        );
    }

    @PostMapping("/create-account")
    public ResponseEntity<SendEmailDTO> createAccount(@RequestBody @Valid CreateAccountDTO dtoCreateAccount, HttpServletRequest request) {
        SendEmailDTO contentEmail = serviceAuth.createAccount(dtoCreateAccount);

        emailService.sendValidationEmail(contentEmail, request.getRequestURI());

        int id = contentEmail.id();
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri)
                .body(contentEmail);
    }

    @GetMapping("/create-account/verify")
    public ResponseEntity<String> registrationProcess(@NotBlank @RequestParam(name = "ott") String ott) {
        serviceAuth.verifyAccountRegistration(ott);

        return ResponseEntity.ok()
                .body("Tu cuenta se creo satisfactoriamente");
    }

    @GetMapping("/delete-user")
    public ResponseEntity<ResponseEntityDTO> deleteUser(@NotBlank @RequestParam(name = "username") String username) {
        AccountDTO accountDeletedDTO = serviceAuth.deleteUser(username);

        String message = "The user with the username " + accountDeletedDTO.username() + " has been deleted";

        return ResponseEntity.ok(
          new ResponseEntityDTO(message,
                  HttpStatus.OK.value())
        );
    }
}
