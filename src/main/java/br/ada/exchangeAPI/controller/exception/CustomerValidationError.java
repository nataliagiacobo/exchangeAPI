package br.ada.exchangeAPI.controller.exception;

import br.ada.exchangeAPI.interfaces.IValidationAttributes;
import br.ada.exchangeAPI.model.enums.MaritalStatus;
import br.ada.exchangeAPI.model.enums.Sex;
import br.ada.exchangeAPI.utils.CPFValidator;

import java.time.LocalDate;

public class CustomerValidationError implements IValidationAttributes {

  @Override
  public void validateName(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name is required");
    }
  }

  @Override
  public void validateCpf(String cpf) {
    if (cpf == null || cpf.isEmpty())
      throw new IllegalArgumentException("CPF is required");
    if(!CPFValidator.cpfValidate(cpf))
      try {
        throw new CpfValidationError("Invalid CPF");
      } catch (CpfValidationError e) {
        throw new RuntimeException(e);
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
    if (maritalStatus == null) {
      throw new IllegalArgumentException("Marital status is required");
    }
  }

  @Override
  public void validateSex(Sex sex) {
    if (sex == null) {
      throw new IllegalArgumentException("Sex is required");
    }
  }

  @Override
  public void validatePassword(String password) {
    if (password == null || password.isEmpty()) {
      throw new IllegalArgumentException("Password is required");
    }
  }
}
