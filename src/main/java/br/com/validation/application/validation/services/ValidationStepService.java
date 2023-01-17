package br.com.validation.application.validation.services;

import br.com.validation.application.validation.steps.*;
import br.com.validation.domain.entities.Password;
import br.com.validation.domain.services.PasswordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ValidationStepService {

    private PasswordService passwordService;

    private ValidationStep step;

    public ValidationStepService(PasswordService passwordService) {
        this.passwordService = passwordService;
        this.step = new QuantityValidation(
                new DigitsValidation(
                        new LowerValidation(
                                new UpperValidation(
                                        new PunctValidation(
                                                new DuplicatedCharsValidation(null)
                                        )
                                )
                        )
                ));
    }

    public boolean execute(Password password) {
        try {
            this.step.execute(password);
        } catch (Exception ex) {
            log.error(String.format("Erros: %s", ex.getMessage()));
            return false;
        }
        password.encrypt();
        password.setIsValid(true);
        this.passwordService.save(password);
        return true;
    }
}
