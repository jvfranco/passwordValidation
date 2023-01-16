package br.com.validation.application.controllers;

import br.com.validation.application.models.PasswordRequest;
import br.com.validation.application.models.PasswordResponse;
import br.com.validation.application.validation.services.ValidationStepService;
import br.com.validation.domain.entities.Password;
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
    private final PasswordService passwordService;

    private final ValidationStepService validationStepService;


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public PasswordResponse validation(@RequestBody PasswordRequest passwordRequest) {
        log.info(passwordRequest.password());

        Password pass = Password.builder()
                .password(passwordRequest.password())
                .build();

        this.validationStepService.execute(pass);

        log.info(pass.encrypt().getPassword());

        this.passwordService.save(pass);

        return new PasswordResponse(pass.getIsValid());
    }
}
