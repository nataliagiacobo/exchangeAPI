package br.ada.exchangeAPI.service;

import br.ada.exchangeAPI.controller.dto.OrderRequest;
import br.ada.exchangeAPI.controller.dto.OrderResponse;
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

    public OrderResponse saveOrder(OrderRequest orderDTO) {
        Customer customerExist = customerRepository.findCustomerByCpf(orderDTO.getCpf());
        if (customerExist == null) throw new RuntimeException("CPF not found");

        Order order = OrderConvert.toEntity(orderDTO, customerExist);
        order.setOrderTimestamp(LocalDateTime.now());

        String currency = order.getCurrency();
        if (!"USD".equals(currency) && !"EUR".equals(currency)) {
            throw new RuntimeException("Currency must be USD or EUR");
        }

        //BigDecimal quotation = quotationService.getBid();
        

        //verificar se cpf existe - ok
        //verificar se é usd ou eur - ok
        //imprimir horário - ok
        //objeto correto - ok
        //consultar cotação do dia e armazenar
        //conta: cotação * valor da compra
        
        return OrderConvert.toResponse(orderRepository.save(order));
    }

    private BigDecimal convertValue() {
        return BigDecimal.ONE;
    }

}
