package br.ada.exchangeAPI.service;

import br.ada.exchangeAPI.controller.dto.CustomerResponse;
import br.ada.exchangeAPI.controller.dto.OrderRequest;
import br.ada.exchangeAPI.controller.dto.OrderResponse;
import br.ada.exchangeAPI.controller.exception.CpfNotFoundError;
import br.ada.exchangeAPI.controller.exception.CurrencyException;
import br.ada.exchangeAPI.controller.exception.CustomerNotActiveError;
import br.ada.exchangeAPI.model.Customer;
import br.ada.exchangeAPI.model.Order;
import br.ada.exchangeAPI.repository.CustomerRepository;
import br.ada.exchangeAPI.repository.OrderRepository;

import br.ada.exchangeAPI.utils.OrderCalculate;
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
    @Autowired
    CustomerService customerService;

    public OrderResponse saveOrder(OrderRequest orderDTO) throws CurrencyException, CpfNotFoundError, CustomerNotActiveError {

        CustomerResponse existingCustomer = customerService.getCustomerByCpf(orderDTO.getCpf());

        Customer customer = customerService.findCustomerById(existingCustomer.getId());

        Order order = OrderConvert.toEntity(orderDTO, customer);

        BigDecimal exchangeRate = OrderCalculate.bidValue(order.getCurrency());

        order.setQuotation(exchangeRate);
        order.setOperationCost(OrderCalculate.operationCostValue(order.getExchangeAmount(), exchangeRate));
        order.setOrderTimestamp(LocalDateTime.now());

        OrderResponse orderResponse = OrderConvert.toResponse(orderRepository.save(order));
        return orderResponse;
    }



    public List<OrderResponse> getOrdersByCpf(String cpf) throws CpfNotFoundError, CustomerNotActiveError {

        CustomerResponse existingCustomer = customerService.getCustomerByCpf(cpf);

        List<Order> ordersLs = orderRepository.findOrdersByCustomer(existingCustomer.getId());

        if (ordersLs.isEmpty()) return new ArrayList<>();
        return OrderConvert.toResponseList(ordersLs);
    }

}
