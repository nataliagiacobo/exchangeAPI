package br.ada.exchangeAPI.service;

import br.ada.exchangeAPI.controller.dto.OrderRequest;
import br.ada.exchangeAPI.controller.dto.OrderResponse;
import br.ada.exchangeAPI.controller.exception.CurrencyException;
import br.ada.exchangeAPI.model.Customer;
import br.ada.exchangeAPI.model.Order;
import br.ada.exchangeAPI.repository.CustomerRepository;
import br.ada.exchangeAPI.repository.OrderRepository;
import br.ada.exchangeAPI.utils.OrderConvert;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    QuotationService quotationService;

    public OrderResponse saveOrder(OrderRequest orderDTO) throws CurrencyException {
        
        Customer customerExist = customerRepository.findCustomerByCpf(orderDTO.getCpf());
        if (customerExist == null) throw new RuntimeException("CPF not found");

        Order order = OrderConvert.toEntity(orderDTO, customerExist);    

        BigDecimal exchangeRate = bidValue(order.getCurrency()); 
        order.setQuotation(exchangeRate); 

        order.setOperationCost(operationCostValue(order.getExchangeAmount(), exchangeRate));

        order.setOrderTimestamp(LocalDateTime.now());
        
        return OrderConvert.toResponse(orderRepository.save(order));
    }

    private BigDecimal bidValue(String currency) throws CurrencyException {
        if ("USD".equals(currency) || "EUR".equals(currency)) {
            return QuotationService.getBid(currency);
        } else {
            throw new CurrencyException("Currency must be USD or EUR");
        }
    }

    private BigDecimal operationCostValue(BigDecimal exchangeAmount, BigDecimal exchangeRate) {
        BigDecimal cost = exchangeAmount.multiply(exchangeRate);
        return cost;
    }

    public List<OrderResponse> getOrdersByCpf(String cpf) {
        Customer customerExist = customerRepository.findCustomerByCpf(cpf);
        if (customerExist == null) throw new RuntimeException("CPF not found");

        List<Order> ordersLs = orderRepository.findOrdersByCustomer(customerExist.getId());

        if (ordersLs.isEmpty()) return new ArrayList<>();;
        
        return OrderConvert.toResponseList(ordersLs);
    }

}
