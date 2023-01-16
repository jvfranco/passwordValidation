package br.com.validation.application.validation.services;

import br.com.validation.application.validation.steps.*;
import br.com.validation.domain.entities.Password;
import org.springframework.stereotype.Service;

@Service
public class ValidationStepService {

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

    public void execute(Password password) {
        this.step.execute(password);
    }
}
