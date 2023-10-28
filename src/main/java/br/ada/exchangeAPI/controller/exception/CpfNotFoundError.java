package br.ada.exchangeAPI.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CpfNotFoundError extends Exception{

	private String description;

}
