package com.substring.foodie.exception;

import com.substring.foodie.playload.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@RestController
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException(NullPointerException ex) {
        logger.error(ex.getMessage());
        ex.printStackTrace();
        return ex.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        //fetch all errors list from BindingResult
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        //iterate all errors and put the error to map
        allErrors.forEach(error -> {
            //error: we have to fetch the field
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errorMap.put(fieldName, message);
        });
        logger.info(errorMap.toString());
        return errorMap;
    }


    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ApiResponse> handleSqlException(SQLIntegrityConstraintViolationException exception) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(false);
        apiResponse.setHttpStatus(HttpStatus.BAD_REQUEST);

        if (exception.getMessage().contains("Duplicate entry")) {
            apiResponse.setMessage(("user already exist"));
        } else {
            apiResponse.setMessage(exception.getMessage());
        }

        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);

    }

}
