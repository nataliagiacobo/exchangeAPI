package br.ada.exchangeAPI.controller.dto;

import br.ada.exchangeAPI.model.enums.MaritalStatus;
import br.ada.exchangeAPI.model.enums.Sex;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse {
  private Integer id;
  private String name;
  private LocalDate birthDate;
  private MaritalStatus maritalStatus;
  private Sex sex;
}
