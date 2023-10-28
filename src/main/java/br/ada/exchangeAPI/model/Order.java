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
    private Integer id; //generated

    @ManyToOne() //(cascade=CascadeType.PERSIST)
    private Customer customer; //related to customers table -> cpf

    @Column(name = "currency", nullable = false)
    private String currency; //input

    @Column(name = "exchange_amount", precision = 16, scale = 2, nullable = false)
    private BigDecimal exchangeAmount; //input

    @Column(name = "quotation", precision = 5, scale = 4)
    private BigDecimal quotation; //comes from external API - QuotationService

    @Column(name = "operation_cost", precision = 16, scale = 2)
    private BigDecimal operationCost; //calculated

    @Column(name = "bank_branch", nullable = false)
    private String bankBranch; //input

    @Column(name = "order_date")
    @CreatedDate
    private LocalDateTime orderTimestamp; //generated

}
