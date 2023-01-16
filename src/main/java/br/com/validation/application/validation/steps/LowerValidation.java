package br.com.validation.application.validation.steps;

import br.com.validation.application.exceptions.ValidatorException;
import br.com.validation.domain.entities.Password;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@AllArgsConstructor
@Slf4j
public class LowerValidation extends ValidationStep {

    private ValidationStep next;

    @Override
    public void execute(Password password) {
        log.info("Step 3 - Deve possuir caracteres minúsculos");

        Pattern pattern = Pattern.compile("\\p{Lower}+");

        if (!pattern.matcher(password.getPassword()).find()) {
            log.error("A senha não possui caracteres minúsculos.");
            throw new ValidatorException("A senha não possui caracteres minúsculos.");
        }

        log.info("Step 3 - OK");

        if (this.next != null) {
            this.next.execute(password);
        }
    }
}
