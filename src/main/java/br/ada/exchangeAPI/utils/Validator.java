package br.ada.exchangeAPI.utils;

import br.ada.exchangeAPI.controller.exception.CpfNotFoundError;
import br.ada.exchangeAPI.model.Customer;
import br.ada.exchangeAPI.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Validator {


    CustomerRepository customerRepository;

    public static Boolean cpfValidate(String cpf){
        return cpf.matches("^\\d{11}$");
    }

    public Customer cpfExists(String cpf) throws CpfNotFoundError {
        Customer customerExist = customerRepository.findCustomerByCpf(cpf);
        if (customerExist == null) throw new CpfNotFoundError("CPF not found");
        return customerExist;
    }

    public Validator(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
}
