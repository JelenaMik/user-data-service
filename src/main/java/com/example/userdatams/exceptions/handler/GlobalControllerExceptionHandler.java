package com.example.userdatams.exceptions.handler;

import com.example.userdatams.exceptions.FavoriteProviderAlreadyExistsException;
import com.example.userdatams.exceptions.UserDataNotFoundException;
import com.example.userdatams.repository.model.FavoriteProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity handleBindErrors(MethodArgumentNotValidException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {

        List errorList = exception.getAllErrors().stream()
                .map(fieldError -> {
                    Map<String, String > errorMap = new HashMap<>();
                    errorMap.put( fieldError.getObjectName(), fieldError.getDefaultMessage());
                    return errorMap;
                }).toList();
        response.sendError(HttpStatus.BAD_REQUEST.value(), errorList.toString());

        ErrorModel errorModel = ErrorModel.builder()
                .timestamp(LocalDate.now())
                .status(HttpStatus.BAD_REQUEST)
                .message(errorList.toString())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserDataNotFoundException.class)
    ResponseEntity handleBindErrors(UserDataNotFoundException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(),"User data was not found");

        ErrorModel errorModel = ErrorModel.builder()
                .timestamp(LocalDate.now())
                .status(HttpStatus.NOT_FOUND)
                .message("User data was not found")
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FavoriteProviderAlreadyExistsException.class)
    ResponseEntity handleBindErrors(FavoriteProviderAlreadyExistsException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(),"It is already your favorite provider");

        ErrorModel errorModel = ErrorModel.builder()
                .timestamp(LocalDate.now())
                .status(HttpStatus.BAD_REQUEST)
                .message("It is already your favorite provider")
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
    }
}
