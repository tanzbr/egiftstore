package me.caua.egiftstore.service;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import me.caua.egiftstore.dto.out.UserResponseDTO;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class JwtServiceImpl implements JwtService {

    private static final Duration EXPIRATION_TIME = Duration.ofHours(24);

    @Override
    public String generateJwt(UserResponseDTO user) {
        Instant now = Instant.now();
        Instant expiryDate = now.plus(EXPIRATION_TIME);

        Set<String> roles = new HashSet<>();
        roles.add("Funcionario");

        return Jwt.issuer("unitins-jwt")
                .subject(user.email())
                .groups(roles)
                .expiresAt(expiryDate)
                .sign();
    }
}
