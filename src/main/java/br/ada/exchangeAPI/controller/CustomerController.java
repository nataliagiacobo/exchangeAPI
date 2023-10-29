package br.ada.exchangeAPI.controller;

import br.ada.exchangeAPI.controller.dto.CustomerRequest;
import br.ada.exchangeAPI.controller.dto.CustomerResponse;
import br.ada.exchangeAPI.controller.exception.CpfNotFoundError;
import br.ada.exchangeAPI.controller.exception.CpfValidationError;
import br.ada.exchangeAPI.service.CustomerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/cpf/{cpf}")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<CustomerResponse> getCustomerByCpf(@PathVariable String cpf) throws CpfNotFoundError{
		return ResponseEntity.ok(customerService.getCustomerByCpf(cpf));
	}

	@PostMapping()
	public ResponseEntity<CustomerResponse> saveNewCustomer(
			@Valid @RequestBody CustomerRequest customerRequest
	) throws CpfValidationError {
		CustomerResponse customer = customerService.saveNewCustomer(customerRequest);
		return ResponseEntity.created(URI.create("/customer/"+customer.getId())).body(customer);
	}

	@GetMapping()
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<List<CustomerResponse>> getAllCustomers(){
		return ResponseEntity.ok(customerService.getAllCustomers());
	}

	@DeleteMapping("/{id}")
	@SecurityRequirement(name = "bearerAuth")
	public void deleteCustomer(@PathVariable Integer id){
		customerService.deleteCustomer(id);
	}
	
	@PutMapping("/{id}")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<CustomerResponse> updateCustomer(
			@RequestBody CustomerRequest customerRequest,
			@PathVariable Integer id
			){
		return ResponseEntity.ok(customerService.updateCustomer(customerRequest, id));
	}

}
