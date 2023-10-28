package br.ada.exchangeAPI.controller;

import br.ada.exchangeAPI.controller.dto.CustomerRequest;
import br.ada.exchangeAPI.controller.dto.CustomerResponse;
import java.net.URI;
import java.util.List;

import br.ada.exchangeAPI.controller.exception.CpfNotFoundError;
import br.ada.exchangeAPI.controller.exception.CpfValidationError;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.br.CPF;
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
	public ResponseEntity<CustomerResponse> getCustomerByCpf(@PathVariable String cpf) throws CpfNotFoundError{
		return ResponseEntity.ok(customerService.getCustomerByCpf(cpf));
	}

	@PostMapping()
	public ResponseEntity<CustomerResponse> saveNewCustomer(
			@Valid @CPF @RequestBody CustomerRequest customerRequest
	) throws CpfValidationError {
		CustomerResponse customer = customerService.saveNewCustomer(customerRequest);
		return ResponseEntity.created(URI.create("/customer/"+customer.getId())).body(customer);
	}

	@GetMapping()
	public ResponseEntity<List<CustomerResponse>> getAllCustomers(){
		return ResponseEntity.ok(customerService.getAllCustomers());
	}

	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable Integer id){
		customerService.deleteCustomer(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CustomerResponse> updateCustomer(
			@RequestBody CustomerRequest customerRequest,
			@PathVariable Integer id
			){
		return ResponseEntity.ok(customerService.updateCustomer(customerRequest, id));
	}

}
