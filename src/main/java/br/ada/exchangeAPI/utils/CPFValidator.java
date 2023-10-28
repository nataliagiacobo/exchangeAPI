package br.ada.exchangeAPI.utils;

public class CPFValidator {

    public static Boolean cpfValidate(String cpf){

        return cpf.matches("^\\d{11}$");
    }

}
