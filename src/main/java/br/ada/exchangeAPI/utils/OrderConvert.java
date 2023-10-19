package br.ada.exchangeAPI.utils;

import br.ada.exchangeAPI.controller.dto.OrderResponse;
import br.ada.exchangeAPI.model.Order;

public class OrderConvert {
    
    public static Order toEntity(Order orderDTO) {
        Order order = new Order();
        order.setCustomer(orderDTO.getCustomer());
        order.setCurrency(orderDTO.getCurrency());
        order.setExchangeAmount(orderDTO.getExchangeAmount());
        order.setQuotation(orderDTO.getQuotation());
        order.setOperationCost(orderDTO.getOperationCost());
        order.setBankBranch(orderDTO.getBankBranch());
        return order;
    }

    public static OrderResponse toResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setCpf(order.getCustomer().getCpf());
        orderResponse.setOrderTimestamp(order.getOrderTimestamp());
        orderResponse.setCurrency(order.getCurrency());
        orderResponse.setExchangeAmount(order.getExchangeAmount());
        orderResponse.setQuotation(order.getQuotation());
        orderResponse.setOperationCost(order.getOperationCost());
        orderResponse.setBankBranch(order.getBankBranch());
        return orderResponse;
    }
}
