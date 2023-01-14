package br.com.validation.application.controllers;

import br.com.validation.application.models.PasswordRequest;
import br.com.validation.application.models.PasswordResponse;
import br.com.validation.domain.services.EncryptService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/password")
@AllArgsConstructor
public class PasswordController {

    private final EncryptService encryptService;

    @PostMapping
    public PasswordResponse validation(@RequestBody PasswordRequest password) {
        System.out.println(password);
        System.out.println(this.encryptService.encrypt(password.password()));

        return new PasswordResponse(Boolean.TRUE);
    }
}
