package br.com.validation.domain.services;

import br.com.validation.domain.entities.Password;
import br.com.validation.domain.repositories.PasswordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PasswordService {

    private final PasswordRepository repository;

    public void save(Password password) {
        this.repository.save(password);
    }
}
