package br.com.validation.application.validation.steps;

import br.com.validation.domain.entities.Password;

public abstract class ValidationStep {

    protected ValidationStep nextStep;

    public abstract void execute(Password password);
    public void setNext(ValidationStep next) {
        this.nextStep = next;
    }

}
