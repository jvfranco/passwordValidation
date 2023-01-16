package br.com.validation.application.validation.steps;

import br.com.validation.domain.entities.Password;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@AllArgsConstructor
@Slf4j
public class QuantityValidation extends ValidationStep {

    private ValidationStep next;

    @Override
    public void execute(Password password) {
        log.info("Step 1 - Senha deve possuir mais de 9 caracteres e não possuir espaços em branco");

        Pattern pattern = Pattern.compile("[^\\p{Space}]{9,}");

        if (!pattern.matcher(password.getPassword()).find()) {
            log.info("Quantidade de caracteres menor que 9 ou possui espaços em branco.");
            throw new RuntimeException("Quantidade de caracteres menor que 9.");
        }

        if (this.next != null) {
            this.next.execute(password);
        }
    }
}
