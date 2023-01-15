package br.com.validation.application.controllers;

import br.com.validation.application.models.PasswordRequest;
import br.com.validation.application.models.PasswordResponse;
import br.com.validation.domain.entities.Password;
import br.com.validation.domain.services.EncryptService;
import br.com.validation.domain.services.PasswordService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/password")
@AllArgsConstructor
@Slf4j
public class PasswordController {

    private final EncryptService encryptService;
    private final PasswordService passwordService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PasswordResponse validation(@RequestBody PasswordRequest passwordRequest) {
        log.info(passwordRequest.password());
        String passwordEncrypt = this.encryptService.encrypt(passwordRequest.password());
        log.info(passwordEncrypt);

        Password pass = Password.builder()
                .password(passwordEncrypt)
                .isValid(Boolean.TRUE)
                .build();

        this.passwordService.save(pass);

        return new PasswordResponse(pass.getIsValid());
    }
}
