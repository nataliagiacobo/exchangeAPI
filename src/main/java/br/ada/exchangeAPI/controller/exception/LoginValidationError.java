package br.ada.exchangeAPI.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@Getter
@Setter
public class LoginValidationError extends Exception{
  private String field;
  private String message;
}
