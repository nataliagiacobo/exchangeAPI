package br.ada.exchangeAPI.repository;

import br.ada.exchangeAPI.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Override
    @Query(value = "SELECT * FORM CUSTOMERS WHERE ACTIVE = TRUE", nativeQuery = true)
    Page<Customer> findAll (Pageable pageable);


    //IMPLEMENTAR NA CUSTOMERSERVICE
//    List<Customer> findAllByName(String name);

//    @Query(value = "SELECT * FROM CUSTOMERS WHERE CPF = :customerCpf", nativeQuery = true)
//    Customer findCustomerByCpf(String customerCpf);



}
