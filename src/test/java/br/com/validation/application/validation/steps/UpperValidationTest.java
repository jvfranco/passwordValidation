package br.com.validation.application.validation.steps;

import br.com.validation.application.exceptions.ValidatorException;
import br.com.validation.domain.entities.Password;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class UpperValidationTest {

    UpperValidation upperValidation;

    @MockBean
    ValidationStep validationStep;

    @BeforeEach
    void setUp() {
        this.upperValidation = new UpperValidation(this.validationStep);
    }

    @Test
    @DisplayName("Must contain uppercase letters")
    void executeSuccessTest() {
        Password password = Password.builder()
                .password("AbTp9!fok")
                .isValid(false)
                .build();

        this.upperValidation.execute(password);

        Mockito.verify(this.validationStep, Mockito.times(1)).execute(password);
    }

    @Test
    @DisplayName("Must throw exception when validating password")
    void mustThrowExceptionTest() {
        Password password = Password.builder()
                .password("abtp9!fok")
                .isValid(false)
                .build();

        assertThrows(ValidatorException.class, () -> this.upperValidation.execute(password));

        Mockito.verify(this.validationStep, Mockito.never()).execute(password);
    }

}