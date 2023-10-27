package br.ada.exchangeAPI.service;

import br.ada.exchangeAPI.controller.dto.CustomerResponse;
import br.ada.exchangeAPI.controller.dto.OrderRequest;
import br.ada.exchangeAPI.controller.dto.OrderResponse;
import br.ada.exchangeAPI.controller.exception.CpfNotFoundError;
import br.ada.exchangeAPI.controller.exception.CurrencyException;
import br.ada.exchangeAPI.model.Customer;
import br.ada.exchangeAPI.model.Order;
import br.ada.exchangeAPI.repository.CustomerRepository;
import br.ada.exchangeAPI.repository.OrderRepository;
import br.ada.exchangeAPI.utils.CustomerConvert;
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

//    public OrderResponse saveOrder(OrderRequest orderDTO) throws CurrencyException, CpfNotFoundError {
////        Customer customerExist = customerRepository.findCustomerByCpf(orderDTO.getCpf());
////        if (customerExist == null) throw new CpfNotFoundError("CPF not found");
//        CustomerResponse existingCustomer = customerService.getCustomerByCpf(orderDTO.getCpf());
//        System.out.println("CustomerResponse ID: " + existingCustomer.getId());
////        System.out.println("cpf do registro de compra: " + orderDTO.getCpf());
////        System.out.println("dados do customerResponse com esse cpf (existingCustomer): " + existingCustomer.getName());
////        System.out.println("dados do customer convertido do existingCustomer:" + CustomerConvert.toEntity(existingCustomer).getName() + "  /  " + CustomerConvert.toEntity(existingCustomer).getSex());
//        Order order = OrderConvert.toEntity(orderDTO, CustomerConvert.toEntity(existingCustomer));
//        System.out.println("dados do order: " + order.getExchangeAmount() + "  /  " + order.getCustomer() + "  /  " + order.getCurrency());
//
//        BigDecimal exchangeRate = OrderCalculate.bidValue(order.getCurrency());
//        System.out.println("teste passando pelo exchangeRate: " + exchangeRate);
//
//        order.setQuotation(exchangeRate);
//        order.setOperationCost(OrderCalculate.operationCostValue(order.getExchangeAmount(), exchangeRate));
//        order.setOrderTimestamp(LocalDateTime.now());
//
//        System.out.println("teste definição valores finais do order: " + order.getQuotation() + "," + order.getOperationCost() + "," + order.getOrderTimestamp());
//
//        OrderResponse orderResponse = OrderConvert.toResponse(orderRepository.save(order));
//        System.out.println("teste se passou pelo salvamento no banco" + orderResponse);
//        return orderResponse;
//    }


    public OrderResponse saveOrder(OrderRequest orderDTO) throws CurrencyException, CpfNotFoundError {
//        Customer customerExist = customerRepository.findCustomerByCpf(orderDTO.getCpf());
//        if (customerExist == null) throw new CpfNotFoundError("CPF not found");
        CustomerResponse existingCustomer = customerService.getCustomerByCpf(orderDTO.getCpf());
        System.out.println("CustomerResponse ID: " + existingCustomer.getId());
        Customer customer = customerService.findCustomerById(existingCustomer.getId());
        System.out.println("customer id: " + customer.getId());
//        System.out.println("cpf do registro de compra: " + orderDTO.getCpf());
//        System.out.println("dados do customerResponse com esse cpf (existingCustomer): " + existingCustomer.getName());
//        System.out.println("dados do customer convertido do existingCustomer:" + CustomerConvert.toEntity(existingCustomer).getName() + "  /  " + CustomerConvert.toEntity(existingCustomer).getSex());
        Order order = OrderConvert.toEntity(orderDTO, customer);
        System.out.println("dados do order: " + order.getExchangeAmount() + "  /  " + order.getCustomer() + "  /  " + order.getCurrency());

        BigDecimal exchangeRate = OrderCalculate.bidValue(order.getCurrency());
        System.out.println("teste passando pelo exchangeRate: " + exchangeRate);

        order.setQuotation(exchangeRate);
        order.setOperationCost(OrderCalculate.operationCostValue(order.getExchangeAmount(), exchangeRate));
        order.setOrderTimestamp(LocalDateTime.now());

        System.out.println("teste definição valores finais do order: " + order.getQuotation() + "," + order.getOperationCost() + "," + order.getOrderTimestamp());

        OrderResponse orderResponse = OrderConvert.toResponse(orderRepository.save(order));
        System.out.println("teste se passou pelo salvamento no banco" + orderResponse);
        return orderResponse;
    }





    public List<OrderResponse> getOrdersByCpf(String cpf) throws CpfNotFoundError {
//        Customer customerExist = customerRepository.findCustomerByCpf(cpf);
//        if (customerExist == null) throw new CpfNotFoundError("CPF not found");

        CustomerResponse existingCustomer = customerService.getCustomerByCpf(cpf);

        List<Order> ordersLs = orderRepository.findOrdersByCustomer(existingCustomer.getId());

        if (ordersLs.isEmpty()) return new ArrayList<>();
        return OrderConvert.toResponseList(ordersLs);
    }

}
