package me.caua.egiftstore.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import me.caua.egiftstore.dto.in.CustomerDTO;
import me.caua.egiftstore.dto.in.EmployeeDTO;
import me.caua.egiftstore.dto.out.CustomerResponseDTO;
import me.caua.egiftstore.dto.out.EmployeeResponseDTO;
import me.caua.egiftstore.dto.out.UserResponseDTO;

import java.util.List;

@ApplicationScoped
public interface CustomerService {

    CustomerResponseDTO create(@Valid CustomerDTO customerDTO);
    void update(Long id, @Valid CustomerDTO customerDTO);
    void delete(Long id);
    CustomerResponseDTO findById(Long id);
    List<CustomerResponseDTO> findByName(String name);
    List<CustomerResponseDTO> findAll();
    UserResponseDTO login(String email, String password);

}
