package br.ada.exchangeAPI.service;

import br.ada.exchangeAPI.controller.dto.CustomerRequest;
import br.ada.exchangeAPI.model.Customer;
import java.util.List;

import br.ada.exchangeAPI.utils.CustomerConvert;
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

	public CustomerResponse saveNewCustomer(CustomerRequest customerRequest) {
		Customer customerEntity = customerRepository.save(CustomerConvert.toEntity(customerRequest));
		customerEntity.setActive(true);
		return CustomerConvert.toResponse(customerEntity);
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
