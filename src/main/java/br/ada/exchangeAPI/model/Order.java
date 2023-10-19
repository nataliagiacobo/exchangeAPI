package br.ada.exchangeAPI.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //generated

    @ManyToOne()
    private Customer cpf; //related to customers table - cpf

    @Column(name = "currency", nullable = false)
    private String currency; //input

    @Column(name = "exchange_amount", nullable = false)
    private BigDecimal exchangeAmount; //input

    @Column(name = "quotation")
    private BigDecimal quotation; //comes from external API - QuotationService

    @Column(name = "operation_cost")
    private BigDecimal operationCost; //calculated

    @Column(name = "bank_branch", nullable = false)
    private Integer bankBranch; //input

    @Column(name = "order_date")
    @CreatedDate
    private Instant orderTimestamp; //generated


//    Response body da ordem de compra:
//    {
//        "id_compra": 1,
//        "cpf_cliente": "43488428095",
//        "dataSolicitacao": "2021-08-27T16:11:23.866",
//        "tipo_moeda": "EUR",
//        "valor_moeda_estrangeira": 100.0,
//        "valor_cotacao": 6.5857,
//        "valor_total_operacao": 658.57,
//        "numero_agencia_retirada": "7057"
//    }

}
