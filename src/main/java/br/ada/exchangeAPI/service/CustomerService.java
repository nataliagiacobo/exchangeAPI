package br.ada.exchangeAPI.service;

import br.ada.exchangeAPI.controller.dto.CustomerRequest;
import br.ada.exchangeAPI.controller.exception.CpfNotFoundError;
import br.ada.exchangeAPI.controller.exception.CpfValidationError;
import br.ada.exchangeAPI.controller.exception.CustomerNotActiveError;
import br.ada.exchangeAPI.model.Customer;
import br.ada.exchangeAPI.controller.exception.CustomerValidationError;
import java.util.List;

import br.ada.exchangeAPI.utils.CustomerConvert;
import br.ada.exchangeAPI.utils.CPFValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.ada.exchangeAPI.controller.dto.CustomerResponse;
import br.ada.exchangeAPI.repository.CustomerRepository;

@Service
public class CustomerService extends CustomerValidationError {
	
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public CustomerResponse getCustomerByCpf(String customerCpf) throws CpfNotFoundError, CustomerNotActiveError {
		Customer customerExist = customerRepository.findCustomerByCpf(customerCpf);
        if (customerExist == null) throw new CpfNotFoundError("CPF not found");
		if (!customerExist.getActive()) throw new CustomerNotActiveError("Customer not active anymore");
		return CustomerConvert.toResponse(customerExist);
	}

	public Customer findCustomerById(Integer customerId) {
		return customerRepository.findCustomerById(customerId);
	}


	public CustomerResponse saveNewCustomer(CustomerRequest customerRequest) throws CpfValidationError {
		Customer customer = CustomerConvert.toEntity(customerRequest);
		if(!CPFValidator.cpfValidate(customer.getCpf())) throw new CpfValidationError("Invalid CPF");
		validateName(customer.getName());
		validateBirthDate(customer.getBirthDate());
		validateMaritalStatus(customer.getMaritalStatus());
		validateSex(customer.getSex());
		validatePassword(customer.getPassword());
		customer.setPassword(passwordEncoder.encode(customerRequest.getPassword()));
		customer.setActive(true);
		return CustomerConvert.toResponse(customerRepository.save(customer));
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
		customer.setPassword(passwordEncoder.encode(customerRequest.getPassword()));
		customer.setActive(true);
		return CustomerConvert.toResponse(customerRepository.save(customer));
	}
}
