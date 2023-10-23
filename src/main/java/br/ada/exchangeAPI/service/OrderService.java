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
        order.setOrderTimestamp(LocalDateTime.now());

        String currency = order.getCurrency();        

        BigDecimal exchangeRate = bidValue(currency); //retorna cotação
        order.setQuotation(exchangeRate); // salva o valor da cotação no atributo da order

        BigDecimal operationCost = operationCostComputation(order.getExchangeAmount(), exchangeRate); // ... (valor em 'currencyX', cotação)
        order.setOperationCost(operationCost);
        
        return OrderConvert.toResponse(orderRepository.save(order));
    }

    private BigDecimal bidValue(String currency) throws CurrencyException {

        if ("USD".equals(currency) || "EUR".equals(currency)) {
            return QuotationService.getBid(currency);
        } else {
            throw new CurrencyException("Currency must be USD or EUR");
        }

    }

    private BigDecimal operationCostComputation(BigDecimal exchangeAmount, BigDecimal exchangeRate) {
        BigDecimal cost = exchangeAmount.multiply(exchangeRate);
        return cost;
    }

}
