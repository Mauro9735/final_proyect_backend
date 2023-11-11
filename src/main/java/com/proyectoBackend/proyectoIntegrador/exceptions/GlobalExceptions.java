package com.proyectoBackend.proyectoIntegrador.exceptions;


import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {

    private static final Logger LOGGER = Logger.getLogger(GlobalExceptions.class);

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> tratamientoResourceNotFoundException(
            ResourceNotFoundException rnfe){
        LOGGER.error("Ocurrió un error: "+rnfe.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
    }


    @ExceptionHandler({ResourceBadRequestException.class})
    public ResponseEntity<String> tratamientoResourceBadRequestException(
            ResourceBadRequestException rbre){
        LOGGER.error("Ocurrió un error: "+rbre.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rbre.getMessage());
    }


}
