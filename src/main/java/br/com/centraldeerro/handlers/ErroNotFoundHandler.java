package br.com.centraldeerro.handlers;

import br.com.centraldeerro.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = {"br.com.centraldeerro.controllers"})
public class ErroNotFoundHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> exception(ResourceNotFoundException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
