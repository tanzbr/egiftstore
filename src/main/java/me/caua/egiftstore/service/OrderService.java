package me.caua.egiftstore.service;

import jakarta.validation.Valid;
import me.caua.egiftstore.dto.in.OrderDTO;
import me.caua.egiftstore.dto.out.OrderResponseDTO;

import java.util.List;

public interface OrderService {

    OrderResponseDTO create(@Valid OrderDTO orderDTO);
    OrderResponseDTO findById(Long id);
    List<OrderResponseDTO> findAll();
    List<OrderResponseDTO> findByCustomerId(Long orderId);
    void delete(Long orderId);

}
