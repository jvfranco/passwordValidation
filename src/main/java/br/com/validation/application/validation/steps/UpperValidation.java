package br.com.validation.application.validation.steps;

import br.com.validation.domain.entities.Password;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@AllArgsConstructor
@Slf4j
public class UpperValidation extends ValidationStep {

    private ValidationStep next;

    @Override
    public void execute(Password password) {
        log.info("Step 4 - Deve possuir caracteres maiúsculos");

        Pattern pattern = Pattern.compile("\\p{Upper}+");

        if (!pattern.matcher(password.getPassword()).find()) {
            log.info("A senha não possui caracteres maiúsculos.");
            throw new RuntimeException("A senha não possui caracteres maiúsculos.");
        }

        if (this.next != null) {
            this.next.execute(password);
        }
    }
}
