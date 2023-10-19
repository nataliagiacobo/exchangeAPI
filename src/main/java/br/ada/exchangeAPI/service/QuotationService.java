package br.ada.exchangeAPI.service;

import br.ada.exchangeAPI.model.Quotation;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class QuotationService {

    private static final String URI = "https://economia.awesomeapi.com.br/{currency}";

    public static Quotation getQuotation(String currency){
        RestTemplate restTemplate = new RestTemplate();
        Quotation[] list = restTemplate.getForObject(URI,  Quotation[].class, currency);

        if(list != null && list.length > 0)
            return list[0];
        else
            return null;
    }

    public static BigDecimal getBid(String currency){
        Quotation quotation = getQuotation(currency);
        return quotation != null ? quotation.getBid() : null;
    }
}
