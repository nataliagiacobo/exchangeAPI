package br.ada.exchangeAPI.repository;

import br.ada.exchangeAPI.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT * FROM orders WHERE customer_id = :id", nativeQuery = true)
    List<Order> findOrdersByCustomer(Integer id);
}
