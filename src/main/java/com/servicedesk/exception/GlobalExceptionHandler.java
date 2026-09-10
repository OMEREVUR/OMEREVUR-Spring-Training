package com.servicedesk.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.servicedesk.response.CustomerResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<CustomerResponse<Object>> handleBaseException(BaseException ex) {
        return buildResponse(ex.getMessageType().getHttpStatus(), ex.getMessage(), null);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<CustomerResponse<Object>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        MessageType type = MessageType.DUPLICATE_RECORD;
        return buildResponse(type.getHttpStatus(), type.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomerResponse<Object>> handleUnexpectedException(Exception ex) {
        log.error("Beklenmeyen hata", ex);
        MessageType type = MessageType.GENERAL_EXCEPTION;
        return buildResponse(type.getHttpStatus(), type.getMessage(), null);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new LinkedHashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.putIfAbsent(fieldError.getField(), fieldError.getDefaultMessage());
        }

        MessageType type = MessageType.VALIDATION_ERROR;
        return ResponseEntity.status(type.getHttpStatus())
                .body(new CustomerResponse<>(false, type.getHttpStatus().value(), type.getMessage(), errors));
    }

    // Spring'in standart hataları (bozuk JSON, yanlış URL, desteklenmeyen metot...) da aynı formatta dönsün
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
            HttpStatusCode statusCode, WebRequest request) {
        String message = body instanceof ProblemDetail problemDetail && problemDetail.getDetail() != null
                ? problemDetail.getDetail()
                : ex.getMessage();

        return ResponseEntity.status(statusCode)
                .headers(headers)
                .body(new CustomerResponse<>(false, statusCode.value(), message, null));
    }

    private <T> ResponseEntity<CustomerResponse<T>> buildResponse(HttpStatusCode status, String message, T variable) {
        return ResponseEntity.status(status)
                .body(new CustomerResponse<>(false, status.value(), message, variable));
    }
}
