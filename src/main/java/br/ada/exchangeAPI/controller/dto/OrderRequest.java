package br.ada.exchangeAPI.controller.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderRequest {

    private String cpf;
    
    private String currency;

    @Positive(message = "Exchange amount must be a number greater than zero. ")
    private BigDecimal exchangeAmount;

    @Size(min=4, max=4, message = "Bank branch must have exactly 4 numbers. ")
    private String bankBranch; 
    
}
