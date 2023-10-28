package br.ada.exchangeAPI.controller.config;

import br.ada.exchangeAPI.controller.exception.CpfNotFoundError;
import br.ada.exchangeAPI.controller.exception.CpfValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.ada.exchangeAPI.controller.exception.CurrencyException;

@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CurrencyException.class)
    public String handlerCurrency(CurrencyException exception){
        return exception.getDescription();
    }


    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CpfValidationError.class)
    public String handlerCpf(CpfValidationError exception){
        return exception.getDescription();
    }
    
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(CpfNotFoundError.class)
    public String handlerCpfNotFound(CpfNotFoundError exception){
        return exception.getDescription();
    }
}
