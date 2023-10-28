package br.ada.exchangeAPI.utils;

import java.util.ArrayList;
import java.util.List;

import br.ada.exchangeAPI.controller.dto.OrderRequest;
import br.ada.exchangeAPI.controller.dto.OrderResponse;
import br.ada.exchangeAPI.model.Customer;
import br.ada.exchangeAPI.model.Order;

public class OrderConvert {
    
    public static Order toEntity(OrderRequest orderDTO, Customer customer) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setCurrency(orderDTO.getCurrency());
        order.setExchangeAmount(orderDTO.getExchangeAmount());
        order.setBankBranch(orderDTO.getBankBranch());
        return order;
    }

//    public static Order toEntity(OrderRequest orderDTO, CustomerResponse customerResponse) {
//        Order order = new Order();
//        order.setCustomer(CustomerConvert.toEntity(customerResponse));
//        order.setCurrency(orderDTO.getCurrency());
//        order.setExchangeAmount(orderDTO.getExchangeAmount());
//        order.setBankBranch(orderDTO.getBankBranch());
//        return order;
//    }

    public static OrderResponse toResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderId(order.getId());
        orderResponse.setCustomerId(order.getCustomer().getId());
        orderResponse.setCpf(order.getCustomer().getCpf());
        orderResponse.setOrderTimestamp(order.getOrderTimestamp());
        orderResponse.setCurrency(order.getCurrency());
        orderResponse.setExchangeAmount(order.getExchangeAmount());
        orderResponse.setQuotation(order.getQuotation());
        orderResponse.setOperationCost(order.getOperationCost());
        orderResponse.setBankBranch(order.getBankBranch());
        return orderResponse;
    }

    public static List<OrderResponse> toResponseList(List<Order> orders) {
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order : orders) {
            OrderResponse orderResponse = OrderConvert.toResponse(order);
            orderResponses.add(orderResponse);
        }
        return orderResponses;
    }
    
}
