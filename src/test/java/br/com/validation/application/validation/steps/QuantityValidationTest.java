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
class QuantityValidationTest {

    QuantityValidation quantityValidation;

    @MockBean
    ValidationStep validationStep;

    @BeforeEach
    void setUp() {
        this.quantityValidation = new QuantityValidation(this.validationStep);
    }

    @Test
    @DisplayName("Must contain nine or more characters with no whitespace")
    void executeSuccessTest() {
        Password password = Password.builder()
                .password("AbTp9!fok")
                .isValid(false)
                .build();

        this.quantityValidation.execute(password);

        Mockito.verify(this.validationStep, Mockito.times(1)).execute(password);
    }

    @Test
    @DisplayName("Must throw exception when validating password with whitespace")
    void mustThrowExceptionWhitespaceTest() {
        Password password = Password.builder()
                .password("AbTp9! ok")
                .isValid(false)
                .build();

        assertThrows(ValidatorException.class, () -> this.quantityValidation.execute(password));

        Mockito.verify(this.validationStep, Mockito.never()).execute(password);
    }

    @Test
    @DisplayName("Must throw exception when validating password with eight characters")
    void mustThrowExceptionTest() {
        Password password = Password.builder()
                .password("AbTp9!ok")
                .isValid(false)
                .build();

        assertThrows(ValidatorException.class, () -> this.quantityValidation.execute(password));

        Mockito.verify(this.validationStep, Mockito.never()).execute(password);
    }

}