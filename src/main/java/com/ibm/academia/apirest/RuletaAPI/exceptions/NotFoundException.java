package com.ibm.academia.apirest.RuletaAPI.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super((message));
    }
}
