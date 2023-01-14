package br.com.validation.domain.repositories;

import br.com.validation.domain.entities.Password;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PasswordRepository extends JpaRepository<Password, UUID> {
}
