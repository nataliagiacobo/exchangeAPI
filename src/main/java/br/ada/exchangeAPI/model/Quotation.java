package br.ada.exchangeAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Quotation {
    private String code;
    private String codein;
    private String name;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal varBid;
    private BigDecimal pctChange;
    private BigDecimal bid;
    private BigDecimal ask;
    private Date timestamp;
}
