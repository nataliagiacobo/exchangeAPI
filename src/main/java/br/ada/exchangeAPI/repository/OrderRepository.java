package br.ada.exchangeAPI.repository;

import br.ada.exchangeAPI.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    // List orders by cpf:
    @Query(value = "SELECT * FROM ORDERS WHERE CUSTOMER = :customerCpf", nativeQuery = true)
    List<Order> findOrdersByCpfIs(String customerCpf);

    // List orders by currency:
    @Query(value = "SELECT * FROM ORDERS WHERE CURRENCY = :currencyCode", nativeQuery = true)
    List<Order> findOrdersByCurrencyEqualsIgnoreCase(String currencyCode);
}
