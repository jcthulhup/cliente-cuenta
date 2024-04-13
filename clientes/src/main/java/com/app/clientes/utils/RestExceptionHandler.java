package com.app.clientes.utils;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleException(NoSuchElementException exc) {
        //En service, Cuando se quiere buscar algun entity en BD, validar si optional.isEmpty() y retornar
        //throw new NoSuchElementException("No existe el cliente con el id: " + id);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return buildResponseEntity(httpStatus, exc);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleException(DuplicateKeyException exc) {
        //En service, Cuando se verifica por el repository si es que existe ese entity con algun valor en especifico,
        //Cuando se crea una nueva entity en repository (.save())
        //  Cliente clienteVerif = new Cliente(cliente.getCliContra());
        //	  if (usuarioRepository.exists(Example.of(clienteVerif))) {
        //	    throw new DuplicateKeyException("Ya existe cliente con contraseña: " + cliente.getCliContra());
        //	  }
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return buildResponseEntity(httpStatus, exc);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleException(IllegalArgumentException exc) {
        //En service, Cuando al validar un dato no es correcto, usar:
        //throw new IllegalArgumentException("Saldo inicial no puede ser negativo");
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return buildResponseEntity(httpStatus, exc);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleException(InvalidDataException exc) {
        //En el controller, si el BindingResult result.hasErrors()
        //retornar throw new InvalidDataException(result); para mostrar excepcion con errores
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        List<String> errors = exc.getResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        return buildResponseEntity(httpStatus, new RuntimeException("Data enviada es invalida"), errors);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleException(MethodArgumentTypeMismatchException exc) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        // Aplica cuando en el URL se envia un argumento invalido, por ejemplo String
        // por Integer
        return buildResponseEntity(httpStatus, new RuntimeException("Tipo de Argumento invalido"));
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleException(Exception exc) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        return buildResponseEntity(httpStatus, new RuntimeException("Lo sentimos, hubo un problema. Por favor, vuelva a intentarlo mas tarde"));
    }

    private ResponseEntity<ErrorResponse> buildResponseEntity(HttpStatus httpStatus, Exception exc) {
        return buildResponseEntity(httpStatus, exc, null);
    }

    private ResponseEntity<ErrorResponse> buildResponseEntity(HttpStatus httpStatus, Exception exc, List<String> errors) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage("Mensaje -> " + exc.getMessage());
        error.setStatus(httpStatus.value());
        error.setTimestamp(new Date());
        error.setErrors(errors);
        return new ResponseEntity<>(error, httpStatus);
    }
}
