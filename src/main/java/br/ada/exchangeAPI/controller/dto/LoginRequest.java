package br.ada.exchangeAPI.controller.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
public class LoginRequest {
    @CPF
    private String cpf;
    private String password;
}
