package br.ada.exchangeAPI.controller;

import br.ada.exchangeAPI.controller.dto.CustomerRequest;
import br.ada.exchangeAPI.controller.dto.CustomerResponse;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable Integer id){
		customerService.deleteCustomer(id);
	}

}
