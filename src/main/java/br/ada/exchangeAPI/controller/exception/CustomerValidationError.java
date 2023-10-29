package br.ada.exchangeAPI.controller.exception;

import br.ada.exchangeAPI.interfaces.IValidationAttributes;
import br.ada.exchangeAPI.model.enums.MaritalStatus;
import br.ada.exchangeAPI.model.enums.Sex;
import java.time.LocalDate;
import java.util.Arrays;

public class CustomerValidationError implements IValidationAttributes {

  @Override
  public void validateName(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name is required");
    }
  }

  @Override
  public void validateBirthDate(LocalDate birthDate) {
    if (birthDate == null) {
      throw new IllegalArgumentException("Birth date is required");
    } else if (birthDate.isAfter(LocalDate.now())) {
      throw new IllegalArgumentException("Invalid format for birth date");
    }
  }

  @Override
  public void validateMaritalStatus(MaritalStatus maritalStatus) {
    if (!Arrays.asList(MaritalStatus.values()).contains(maritalStatus)) {
      throw new IllegalArgumentException("Invalid marital status. Please, choose one of the following: SINGLE, MARRIED, DIVORCED, WIDOWED");
    }
  }

  @Override
  public void validateSex(Sex sex) {
    if (sex == null) {
      throw new IllegalArgumentException("Sex is required");
    }
    if (!Arrays.asList(Sex.values()).contains(sex)) {
      throw new IllegalArgumentException("Invalid sex. Please, choose one of the following: MALE, FEMALE, OTHER");
    }
  }

  @Override
  public void validatePassword(String password) {
    if (password == null || password.isEmpty()) {
      throw new IllegalArgumentException("Password is required");
    }
  }
}
