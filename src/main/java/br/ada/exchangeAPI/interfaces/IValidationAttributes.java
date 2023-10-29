package br.ada.exchangeAPI.interfaces;

import br.ada.exchangeAPI.model.enums.MaritalStatus;
import br.ada.exchangeAPI.model.enums.Sex;
import java.time.LocalDate;

public interface IValidationAttributes {

  void validateName(String name);

  void validateBirthDate(LocalDate birthDate);

  void validateMaritalStatus(MaritalStatus maritalStatus);

  void validateSex(Sex sex);

  void validatePassword(String password);
}
