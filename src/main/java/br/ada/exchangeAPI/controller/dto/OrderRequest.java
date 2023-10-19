package br.ada.exchangeAPI.controller.dto;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderRequest {

    private Integer customer_id;
    
    private String currency;

    private BigDecimal exchangeAmount;

    private BigDecimal quotation; 

    private BigDecimal operationCost;

    @Length(min=4, max=5)
    private Integer bankBranch; 
    
}
