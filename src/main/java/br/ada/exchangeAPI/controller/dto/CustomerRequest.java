package br.ada.exchangeAPI.controller.dto;

import br.ada.exchangeAPI.model.enums.MaritalStatus;
import br.ada.exchangeAPI.model.enums.Sex;
import java.time.LocalDate;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
public class CustomerRequest {

  @NotBlank
  @Length(min = 3, max = 35)
  private String name;

  private String cpf;

  @NotNull(message = "Birth date is required")
  @PastOrPresent(message = "Birth date must be in the past or present")
  private LocalDate birthDate;

  @NotNull(message = "Marital status is required")
  @Enumerated(EnumType.STRING)
  private MaritalStatus maritalStatus;

  @NotNull(message = "Sex is required")
  @Enumerated(EnumType.STRING)
  private Sex sex;

  private String password;
}
