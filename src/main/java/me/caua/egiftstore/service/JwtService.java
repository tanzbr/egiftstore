package me.caua.egiftstore.service;

import me.caua.egiftstore.dto.out.UserResponseDTO;

public interface JwtService {
    String generateJwt(UserResponseDTO user);
}
