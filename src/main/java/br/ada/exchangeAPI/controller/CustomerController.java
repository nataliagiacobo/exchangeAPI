package br.ada.exchangeAPI.controller;

import br.ada.exchangeAPI.controller.dto.CustomerRequest;
import br.ada.exchangeAPI.controller.dto.CustomerResponse;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ada.exchangeAPI.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<CustomerResponse> getCustomerByCpf(@PathVariable String cpf){
		return ResponseEntity.ok(customerService.getCustomerByCpf(cpf));
	}

	@PostMapping()
	public ResponseEntity<CustomerResponse> saveNewCustomer(
			@RequestBody CustomerRequest customerRequest
	){
		CustomerResponse customer = customerService.saveNewCustomer(customerRequest);
		return ResponseEntity.created(URI.create("/customer/"+customer.getId())).body(customer);
	}

}
