package br.ada.exchangeAPI.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderResponse {
    
    private Integer id;
    private String cpf;
    private String currency; 
    private BigDecimal exchangeAmount;
    private BigDecimal quotation;
    private BigDecimal operationCost;
    private String bankBranch;
    private LocalDateTime orderTimestamp;

}
