package com.ibm.academia.apirest.RuletaAPI.exceptions;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String mensaje){
        super(mensaje);
    }
}
