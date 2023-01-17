package br.com.validation.domain.services;

import br.com.validation.domain.entities.Password;
import br.com.validation.domain.repositories.PasswordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class PasswordServiceTest {

    PasswordService passwordService;

    @MockBean
    private PasswordRepository passwordRepository;

    @BeforeEach
    void setUp() {
        this.passwordService = new PasswordService(passwordRepository);
    }

    @Test
    @DisplayName("Must save the password")
    void saveTest() {
        Password password = Password.builder()
                .password("AbTpX!fok")
                .isValid(false)
                .build();

        this.passwordService.save(password);

        Mockito.verify(this.passwordRepository, Mockito.times(1)).save(password);
    }

}