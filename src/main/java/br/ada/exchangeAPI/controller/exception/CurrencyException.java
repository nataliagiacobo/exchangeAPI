package br.ada.exchangeAPI.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CurrencyException extends Exception {
	
	private String description;

}
