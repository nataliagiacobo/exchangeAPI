package br.ada.exchangeAPI.service;

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

	public CustomerResponse getCustomerByCpf(String customerCpf){
        return CustomerConvert.toResponse(customerRepository.findCustomerByCpf(customerCpf));
    }

}
