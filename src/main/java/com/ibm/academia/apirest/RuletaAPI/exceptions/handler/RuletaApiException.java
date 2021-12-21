package com.ibm.academia.apirest.RuletaAPI.exceptions.handler;

import com.ibm.academia.apirest.RuletaAPI.exceptions.BadRequestException;
import com.ibm.academia.apirest.RuletaAPI.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RuletaApiException {
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> formatoInvalidoException(BadRequestException exception){
        Map<String,Object> respuesta = new HashMap<>();
        respuesta.put("error",exception.getMessage());
        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> noExisteException(BadRequestException exception){
        Map<String,Object> respuesta = new HashMap<>();
        respuesta.put("error",exception.getMessage());
        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }
}
