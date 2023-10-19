package br.ada.exchangeAPI.utils;

import br.ada.exchangeAPI.controller.dto.CustomerRequest;
import br.ada.exchangeAPI.controller.dto.CustomerResponse;
import br.ada.exchangeAPI.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerConvert {

  public static Customer toEntity(CustomerRequest customerRequest) {
    Customer customer = new Customer();
    customer.setName(customerRequest.getName());
    customer.setCpf(customerRequest.getCpf());
    customer.setBirthDate(customerRequest.getBirthDate());
    customer.setMaritalStatus(customerRequest.getMaritalStatus());
    customer.setSex(customerRequest.getSex());

    return customer;
  }

  public static CustomerResponse toResponse(Customer customer) {
    CustomerResponse customerResponse = new CustomerResponse();
    customerResponse.setId(customerResponse.getId());

    return customerResponse;
  }

  public static List<CustomerResponse> toResponseList(List<Customer> customers) {
    List<CustomerResponse> customerResponses = new ArrayList<>();
    for (Customer customer : customers) {
      CustomerResponse customerResponse = CustomerConvert.toResponse(customer);
      customerResponses.add(customerResponse);
    }
    return customerResponses;
  }

}