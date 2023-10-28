package br.ada.exchangeAPI.repository;

import br.ada.exchangeAPI.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value = "SELECT * FROM CUSTOMERS WHERE CPF = :customerCpf", nativeQuery = true)
    Customer findCustomerByCpf(String customerCpf);

    UserDetails findByCpf(String cpf);

}
