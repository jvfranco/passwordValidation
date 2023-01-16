package br.com.validation.application.models;

import br.com.validation.domain.entities.Password;

public record PasswordRequest(String password) {

    public Password convert() {
        return Password.builder()
                .password(this.password)
                .isValid(false)
                .build();
    }
}
