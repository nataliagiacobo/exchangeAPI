package br.ada.exchangeAPI.dto;

import br.ada.exchangeAPI.model.enums.MaritalStatus;
import br.ada.exchangeAPI.model.enums.Sex;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomerRequest {
  private String name;
  private String cpf;
  private LocalDate birthDate;
  private MaritalStatus maritalStatus;
  private Sex sex;
}
