package br.ada.exchangeAPI.controller.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderRequest {

    private String cpf;
    
    private String currency;

    private BigDecimal exchangeAmount;

    @Size(min=4, max=4)
    private String bankBranch; 
    
}
