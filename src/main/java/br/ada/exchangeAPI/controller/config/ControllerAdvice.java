package br.ada.exchangeAPI.controller.config;

import br.ada.exchangeAPI.controller.exception.CpfNotFoundError;
import br.ada.exchangeAPI.controller.exception.CpfValidationError;
import br.ada.exchangeAPI.controller.exception.ValidationError;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.ada.exchangeAPI.controller.exception.CurrencyException;

@RestControllerAdvice
public class ControllerAdvice {
    @Autowired
    private MessageSource messageSource;

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

    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    @ExceptionHandler(IllegalArgumentException.class)
    public String handlerIllegalArgument(IllegalArgumentException exception){
        return exception.getMessage();
    }

    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handlerMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        List<ValidationError> errors = new ArrayList<>();
        List<FieldError> fieldErros = exception.getBindingResult().getFieldErrors();

        fieldErros.forEach(e -> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ValidationError validationError = new ValidationError(e.getField(), message);
            errors.add(validationError);
        });

        return errors.toString();
    }
}
