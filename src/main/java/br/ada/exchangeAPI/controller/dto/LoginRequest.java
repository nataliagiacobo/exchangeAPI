package br.ada.exchangeAPI.controller.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
public class LoginRequest {
    @CPF
    private String cpf;
    private String password;
}
