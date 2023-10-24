package br.ada.exchangeAPI.controller;

import br.ada.exchangeAPI.controller.dto.OrderRequest;
import br.ada.exchangeAPI.controller.dto.OrderResponse;
import br.ada.exchangeAPI.controller.exception.CpfNotFoundError;
import br.ada.exchangeAPI.controller.exception.CurrencyException;
import br.ada.exchangeAPI.service.OrderService;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> saveOrder(@Valid @RequestBody OrderRequest orderDTO) throws CurrencyException, CpfNotFoundError {
        OrderResponse order =  orderService.saveOrder(orderDTO);
        return ResponseEntity.created(URI.create("/order/" + order.getId())).body(order);
    }  

    @GetMapping("/cpf/{cpf}")
	public ResponseEntity<List<OrderResponse>> getOrdersByCpf(@PathVariable String cpf) {
		return ResponseEntity.ok(orderService.getOrdersByCpf(cpf));
	}

}
