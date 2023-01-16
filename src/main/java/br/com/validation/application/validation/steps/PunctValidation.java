package br.com.validation.application.validation.steps;

import br.com.validation.application.exceptions.ValidatorException;
import br.com.validation.domain.entities.Password;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@AllArgsConstructor
@Slf4j
public class PunctValidation extends ValidationStep {

    private ValidationStep next;

    @Override
    public void execute(Password password) {
        log.info("Step 5 - Deve possuir caracteres especiais");

        Pattern pattern = Pattern.compile("(?=.*[!@#$%^&*()-+])");

        if (!pattern.matcher(password.getPassword()).find()) {
            log.error("A senha não possui caracteres especiais.");
            throw new ValidatorException("A senha não possui caracteres especiais.");
        }

        log.info("Step 5 - OK");

        if (this.next != null) {
            this.next.execute(password);
        }
    }
}
