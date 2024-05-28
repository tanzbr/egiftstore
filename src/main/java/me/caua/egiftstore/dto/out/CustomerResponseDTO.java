package me.caua.egiftstore.dto.out;

import jakarta.validation.constraints.NotNull;
import me.caua.egiftstore.dto.in.UserDTO;
import me.caua.egiftstore.model.Customer;
import me.caua.egiftstore.model.Employee;

public record CustomerResponseDTO(
        @NotNull(message = "acceptMarketing cannot be null.")
        Boolean acceptMarketing,
        @NotNull(message = "User cannot be null.")
        UserResponseDTO userResponseDTO
) {
        public static CustomerResponseDTO valueOf(Customer customer) {
                return new CustomerResponseDTO(
                        customer.getAcceptMarketing(),
                        UserResponseDTO.valueOf(customer.getUser()));
        }
}
