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
  private String cpf;
  private LocalDate birthDate;
  private MaritalStatus maritalStatus;
  private Sex sex;
}
