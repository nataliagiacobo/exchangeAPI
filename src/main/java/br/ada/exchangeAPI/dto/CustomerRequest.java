package br.ada.exchangeAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomerRequest {
  private String name;
  private String cpf;
  private String dataNascimento;
  private String estadoCivil;
  private String sexo;
}
