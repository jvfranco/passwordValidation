package br.com.validation.application.validation.services;

import br.com.validation.domain.entities.Password;
import br.com.validation.domain.repositories.PasswordRepository;
import br.com.validation.domain.services.PasswordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
class ValidationStepServiceTest {

    ValidationStepService validationStepService;

    @MockBean
    private PasswordService passwordService;

    @MockBean
    private PasswordRepository passwordRepository;

    @BeforeEach
    void setUp() {
        this.passwordService = new PasswordService(passwordRepository);
        this.validationStepService = new ValidationStepService(passwordService);
    }

    @Test
    @DisplayName("Must validate the password")
    void executeSuccessTest() {
        Password password = Password.builder()
                .password("AbTp9!fok")
                .isValid(false)
                .build();

        boolean execute = this.validationStepService.execute(password);

        assertTrue(password.getIsValid());
        assertTrue(!password.getPassword().equals("AbTp9!fok"));
        assertTrue(execute);
    }

    @Test
    @DisplayName("Must not validate the password")
    void mustThrowExceptionTest() {
        Password password = Password.builder()
                .password("AbTp9 fok")
                .isValid(false)
                .build();

        boolean execute = this.validationStepService.execute(password);

        assertFalse(password.getIsValid());
        assertTrue(password.getPassword().equals("AbTp9 fok"));
        assertFalse(execute);
    }

}