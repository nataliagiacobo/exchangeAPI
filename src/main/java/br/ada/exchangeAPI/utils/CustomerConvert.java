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

  // pra ter um objeto Customer que o programa entenda que já está no banco de dados
//  public static Customer toEntity(CustomerResponse customerResponse) {
//    Integer id = customerResponse.getId();
//    Customer customer = customerService.findCustomerById(id);

////    customer.equals(customerResponse);

////    customer.setName(customerResponse.getName());
////    customer.setCpf(customerResponse.getCpf());
////    customer.setBirthDate(customerResponse.getBirthDate());
////    customer.setMaritalStatus(customerResponse.getMaritalStatus());
////    customer.setSex(customerResponse.getSex());
//
//    return customer;
//  }

  public static CustomerResponse toResponse(Customer customer) {
    CustomerResponse customerResponse = new CustomerResponse();
    customerResponse.setId(customer.getId());
    customerResponse.setName(customer.getName());
    customerResponse.setCpf(customer.getCpf());
    customerResponse.setBirthDate(customer.getBirthDate());
    customerResponse.setMaritalStatus(customer.getMaritalStatus());
    customerResponse.setSex(customer.getSex());

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
