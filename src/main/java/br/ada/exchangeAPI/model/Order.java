package br.ada.exchangeAPI.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne()
    private Customer customer;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "exchange_amount", precision = 16, scale = 2, nullable = false)
    private BigDecimal exchangeAmount;

    @Column(name = "quotation", precision = 5, scale = 4)
    private BigDecimal quotation;

    @Column(name = "operation_cost", precision = 16, scale = 2)
    private BigDecimal operationCost;

    @Column(name = "bank_branch", nullable = false)
    private String bankBranch;

    @Column(name = "order_date")
    @CreatedDate
    private LocalDateTime orderTimestamp;

}
