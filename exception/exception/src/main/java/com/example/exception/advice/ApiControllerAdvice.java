package com.example.exception.advice;

import com.example.exception.controller.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.Field;

//@RestControllerAdvice
//@RestControllerAdvice(basePackages = "com.example.exception.controller")
@RestControllerAdvice(basePackageClasses = ApiController.class)
public class ApiControllerAdvice {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception e){
        System.out.println(e.getClass().getName());
        /*System.out.println("-------------------");
        System.out.println(e.getLocalizedMessage());
        System.out.println("-------------------");*/
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e){
        System.out.println("advice");
        BindingResult bindingResult=e.getBindingResult();
        bindingResult.getAllErrors().forEach(error->{
            FieldError field=(FieldError)error;
            String fieldName=field.getField();
            String message=field.getDefaultMessage();
            String value=field.getRejectedValue().toString();  //어떠한 값이 잘못되었는지 알려준다.

            System.out.println("----------------");
            System.out.println(fieldName);
            System.out.println(message);
            System.out.println(value);

        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity constraintViolationException(ConstraintViolationException e){
        //ConstraintViolationException 은 어떠한 필드가 잘못되었는지에 대한 정보를 담고 있다.
        e.getConstraintViolations().forEach(error->{
            String field=error.getPropertyPath().spliterator();
            String message=error.getMessage();
            String invalidValue=error.getInvalidValue().toString();

            System.out.println("----------------");
            System.out.println(field);
            System.out.println(message);
            System.out.println(invalidValue);

        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity missingServletRequestParameterException(MissingServletRequestParameterException e){
        //System.out.println(e.getClass().getName());
        String fieldName=e.getParameterName();
        String fieldType=e.getParameterType();
        String invalidValue=e.getMessage();

        System.out.println(fieldName);
        System.out.println(fieldType);
        System.out.println(invalidValue);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
