package me.caua.egiftstore.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import me.caua.egiftstore.dto.in.EmployeeDTO;
import me.caua.egiftstore.dto.out.EmployeeResponseDTO;

import java.util.List;

@ApplicationScoped
public interface EmployeeService {

    EmployeeResponseDTO create(@Valid EmployeeDTO employeeDTO);
    void update(Long id, @Valid EmployeeDTO employeeDTO);
    void delete(Long id);
    EmployeeResponseDTO findById(Long id);
    List<EmployeeResponseDTO> findByName(String name);
    List<EmployeeResponseDTO> findAll();

}
