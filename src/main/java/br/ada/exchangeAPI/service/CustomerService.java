package br.ada.exchangeAPI.service;

import br.ada.exchangeAPI.controller.dto.CustomerRequest;
import br.ada.exchangeAPI.controller.exception.CpfValidationError;
import br.ada.exchangeAPI.model.Customer;
import java.util.List;

import br.ada.exchangeAPI.utils.CustomerConvert;
import br.ada.exchangeAPI.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ada.exchangeAPI.controller.dto.CustomerResponse;
import br.ada.exchangeAPI.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;

	public CustomerResponse getCustomerByCpf(String customerCpf) {
		return CustomerConvert.toResponse(customerRepository.findCustomerByCpf(customerCpf));
	}

	public CustomerResponse saveNewCustomer(CustomerRequest customerRequest) throws CpfValidationError {
		Customer customer = CustomerConvert.toEntity(customerRequest);
		if(!Validator.cpfValidate(customer.getCpf())) throw new CpfValidationError("Cpf inválido");
		Customer customerEntity = customerRepository.save(customer);

		customerEntity.setActive(true);
		return CustomerConvert.toResponse(customerRepository.save(customerEntity));
	}

	public List<CustomerResponse> getAllCustomers() {
		List<Customer> customers = customerRepository.findAll();
		return CustomerConvert.toResponseList(customers);
	}

	public void deleteCustomer(Integer id) {
		Customer customer = customerRepository.findById(id).orElseThrow();
		customer.setActive(false);
		customerRepository.save(customer);
	}
	
	public CustomerResponse updateCustomer(CustomerRequest customerRequest, Integer id) {
		Customer customer = CustomerConvert.toEntity(customerRequest);
		customer.setId(id);
		customer.setActive(true);
		return CustomerConvert.toResponse(customerRepository.save(customer));
	}
}
