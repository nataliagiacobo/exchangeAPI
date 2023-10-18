package br.ada.exchangeAPI.repository;

import br.ada.exchangeAPI.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
//    IMPLEMENTAR NA CUSTOMERSERVICE
//    @Override
//    @Query(value = "SELECT * FORM CUSTOMERS WHERE ACTIVE = TRUE", nativeQuery = true)
//    Page<Customer> findAll (Pageable pageable);



//    List<Customer> findAllByName(String name);

//    @Query(value = "SELECT * FROM CUSTOMERS WHERE CPF = :customerCpf", nativeQuery = true)
//    Customer findCustomerByCpf(String customerCpf);



}
