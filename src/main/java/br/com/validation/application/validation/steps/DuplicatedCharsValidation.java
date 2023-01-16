package br.com.validation.application.validation.steps;

import br.com.validation.application.exceptions.ValidatorException;
import br.com.validation.domain.entities.Password;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashSet;

@AllArgsConstructor
@Slf4j
public class DuplicatedCharsValidation extends ValidationStep {

    private ValidationStep next;

    @Override
    public void execute(Password password) {
        log.info("Step 6 - Não deve possuir caracteres repetidos");

        String[] letters = password.getPassword().split("");
        HashSet<String> lettersSet = new HashSet<>();
        lettersSet.addAll(Arrays.asList(letters));

        if (password.getPassword().length() != lettersSet.size()) {
            log.error("A senha não deve possuir caracteres repetidos.");
            throw new ValidatorException("A senha não deve possuir caracteres repetidos.");
        }

        log.info("Step 6 - OK");

        if (this.next != null) {
            this.next.execute(password);
        }
    }
}
