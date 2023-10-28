package br.ada.exchangeAPI.utils;

//@Component
public class CPFValidator {


//    CustomerRepository customerRepository;

    public static Boolean cpfValidate(String cpf){

        return cpf.matches("^\\d{11}$");
    }

//    public Customer cpfExists(String cpf) throws CpfNotFoundError {
//        Customer customerExist = customerRepository.findCustomerByCpf(cpf);
//        if (customerExist == null) throw new CpfNotFoundError("CPF not found");
//        return customerExist;
//    }
//
//    public Validator(CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }
}
