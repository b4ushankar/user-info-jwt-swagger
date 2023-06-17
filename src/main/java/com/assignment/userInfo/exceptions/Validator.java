package com.assignment.userInfo.exceptions;

import java.util.HashMap;
import java.util.Map;


import com.assignment.userInfo.dto.ErrorMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Validator {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ErrorMessageDto> handleUserNotFoundException(UserNotFoundException ex){
        ErrorMessageDto errorMessageDto = new ErrorMessageDto("UserNotFound", ex.getMessage());
        return new ResponseEntity<ErrorMessageDto>(errorMessageDto,HttpStatus.BAD_REQUEST);
    }

   /* @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<String> handleUserNotFoundException(MissingServletRequestParameterException ex){
        return new ResponseEntity<String>("UserId parameter is mandatory",HttpStatus.BAD_REQUEST);
    }*/

}