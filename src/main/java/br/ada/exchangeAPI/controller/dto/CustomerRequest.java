package br.ada.exchangeAPI.controller.dto;

import br.ada.exchangeAPI.model.enums.MaritalStatus;
import br.ada.exchangeAPI.model.enums.Sex;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@AllArgsConstructor
public class CustomerRequest {
  private String name;

//  @Pattern(regexp = "^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$")

  private String cpf;
  private LocalDate birthDate;
  private MaritalStatus maritalStatus;
  private Sex sex;
  private String password;
}
