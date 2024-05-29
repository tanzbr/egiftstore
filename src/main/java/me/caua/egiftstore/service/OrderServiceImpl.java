package me.caua.egiftstore.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import me.caua.egiftstore.dto.in.OrderDTO;
import me.caua.egiftstore.dto.in.OrderItemDTO;
import me.caua.egiftstore.dto.out.OrderResponseDTO;
import me.caua.egiftstore.enums.GiftState;
import me.caua.egiftstore.model.*;
import me.caua.egiftstore.repository.CustomerRepository;
import me.caua.egiftstore.repository.GiftCardRepository;
import me.caua.egiftstore.repository.OrderRepository;
import me.caua.egiftstore.validation.ValidationException;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class OrderServiceImpl implements OrderService {

    @Inject
    public OrderRepository orderRepository;
    @Inject
    public CustomerRepository customerRepository;
    @Inject
    public GiftCardRepository giftCardRepository;

    @Transactional
    @Override
    public OrderResponseDTO create(OrderDTO orderDTO) {
        validateCustomerExists(orderDTO.customerId());

        Order order = new Order();
        double totalprice = 0.0;

        order.setOrderDate(orderDTO.orderDate());
        order.setCustomer(customerRepository.findById(orderDTO.customerId()));
        order.setPayment(new Payment(orderDTO.paymentDTO().totalPrice(), orderDTO.paymentDTO().paymentStatus()));

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemDTO orderItemDTO : orderDTO.items()) {
            validateGiftCardExists(orderItemDTO.giftcardId());
            OrderItem orderItem = new OrderItem();

            GiftCard giftCard = giftCardRepository.findById(orderItemDTO.giftcardId());

            for (GiftCode code : giftCard.getGiftCodes()) {
                if (code.getGiftState() == GiftState.AVAILABLE) {
                    code.setGiftState(GiftState.DELIVERED);
                    orderItem.setGiftCode(code);
                    break;
                }
            }

            orderItem.setPrice(orderItemDTO.price());
            orderItem.setQuantity(orderItemDTO.quantity());
            orderItem.setDiscount(orderItemDTO.discount());
            orderItem.setGiftCard(giftCard);
            orderItems.add(orderItem);
            totalprice+= orderItemDTO.price() - (orderItemDTO.price() * orderItemDTO.discount());
        }

        order.setTotalPrice(totalprice);
        order.setItems(orderItems);

        orderRepository.persist(order);

        return OrderResponseDTO.valueOf(order);
    }

    @Override
    public OrderResponseDTO findById(Long id) {
        return OrderResponseDTO.valueOf(orderRepository.findById(id));
    }

    @Override
    public List<OrderResponseDTO> findAll() {
        return orderRepository.findAll().stream().map(OrderResponseDTO::valueOf).toList();
    }

    @Override
    public List<OrderResponseDTO> findByCustomerId(Long customerId) {
        return orderRepository.findByCustomer(customerId).stream().map(OrderResponseDTO::valueOf).toList();
    }

    @Override
    @Transactional
    public void delete(Long orderId) {
        validateOrderExists(orderId);
        orderRepository.deleteById(orderId);
    }

    public void validateOrderExists(Long id) {
        if (orderRepository.findById(id) == null)
            throw new ValidationException("id", "order not found.");
    }

    public void validateCustomerExists(Long id) {
        if (customerRepository.findById(id) == null)
            throw new ValidationException("id", "customer not found.");
    }

    public void validateGiftCardExists(Long id) {
        if (giftCardRepository.findById(id) == null)
            throw new ValidationException("id", "giftcard not found.");
    }
}
