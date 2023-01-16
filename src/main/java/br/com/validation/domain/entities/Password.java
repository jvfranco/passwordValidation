package br.com.validation.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String password;

    @Column(name = "valid", length = 5)
    @Setter
    private Boolean isValid;

    public Password(String password) {
        this.password = password;
    }

    public Password encrypt() {
        return new Password(new BCryptPasswordEncoder().encode(this.getPassword()));
    }
}
