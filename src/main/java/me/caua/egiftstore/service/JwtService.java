package me.caua.egiftstore.service;

import me.caua.egiftstore.dto.UserResponseDTO;

public interface JwtService {
    String generateJwt(UserResponseDTO user);
}
