package me.caua.egiftstore.service;

import jakarta.validation.Valid;
import me.caua.egiftstore.dto.order.OrderDTO;
import me.caua.egiftstore.dto.order.OrderResponseDTO;

import java.util.List;

public interface OrderService {

    OrderResponseDTO create(@Valid OrderDTO orderDTO);
    OrderResponseDTO findById(Long id);
    List<OrderResponseDTO> findAll();
    List<OrderResponseDTO> findByCustomerId(Long orderId);
    void delete(Long orderId);

}
