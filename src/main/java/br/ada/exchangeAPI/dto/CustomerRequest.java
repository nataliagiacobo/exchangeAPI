package br.ada.exchangeAPI.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomerRequest {
  private String name;
  private String cpf;
  private LocalDate birthDate;
  private String maritalStatus;
  private String sex;
}
