package br.com.emanuel.emprestai.application.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderService {
    private final BCryptPasswordEncoder encoder;

    public PasswordEncoderService() {
        this.encoder = new BCryptPasswordEncoder();
    }

    /**
     * Criptografa uma senha usando BCrypt
     */
    public String encode(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    /**
     * Valida se uma senha corresponde ao hash criptografado
     */
    public boolean matches(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}

