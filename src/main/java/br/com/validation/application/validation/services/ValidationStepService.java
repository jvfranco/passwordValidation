package br.com.validation.application.validation.services;

import br.com.validation.application.validation.steps.*;
import br.com.validation.domain.entities.Password;
import br.com.validation.domain.services.PasswordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ValidationStepService {

    @Autowired
    private PasswordService passwordService;

    private ValidationStep step;

    public ValidationStepService() {
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

    public ValidationStepService(ValidationStep... steps) {
        for(int index = 0; index < steps.length -1; index++) {
            ValidationStep currentStep = steps[index];
            currentStep.setNext(steps[index + 1]);
        }
        this.step = steps[0];
    }

    public boolean execute(Password password) {
        try {
            this.step.execute(password);
        } catch (Exception ex) {
            log.error(String.format("Mensagens de Erro: %s", ex.getMessage()));
            return false;
        }
        password.encrypt();
        password.setIsValid(true);
        this.passwordService.save(password);
        return true;
    }
}
