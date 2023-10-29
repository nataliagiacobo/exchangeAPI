package br.ada.exchangeAPI.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomerNotActiveError extends Exception{

	private String description;

}
