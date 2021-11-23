package com.example.heroku.advice;

import com.example.heroku.model.ErrorMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handle(Throwable ex, HttpServletRequest request) {
        Throwable rootException = getRootException(ex);
        if (rootException instanceof EntityNotFoundException) {
            return handle((EntityNotFoundException) rootException, request);
        }
        if (rootException instanceof IllegalArgumentException) {
            return handle((IllegalArgumentException) rootException, request);
        }
        log.error(rootException.getMessage(), ex);
        return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error. " + rootException.getMessage(),
                request.getServletPath());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handle(EntityNotFoundException ex, HttpServletRequest request) {
        log.debug(ex.getMessage(), ex);
        return createResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request.getServletPath());
    }

    @ExceptionHandler({
            IllegalArgumentException.class,
            MethodArgumentTypeMismatchException.class,
            HttpMediaTypeNotSupportedException.class,
            MissingServletRequestParameterException.class,
            HttpMessageNotReadableException.class,
            MissingServletRequestPartException.class
    })
    public ResponseEntity<ErrorMessage> handle(Exception ex, HttpServletRequest request) {
        log.info(ex.getMessage(), ex);
        return createResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getServletPath());
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<ErrorMessage> handle(HttpMediaTypeNotAcceptableException ex, HttpServletRequest request) {
        log.debug(ex.getMessage(), ex);
        return createResponse(HttpStatus.NOT_ACCEPTABLE, ex.getMessage(), request.getServletPath());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorMessage> handle(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        log.debug(ex.getMessage(), ex);
        return createResponse(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage(), request.getServletPath());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handle(MethodArgumentNotValidException ex, HttpServletRequest request) {
        log.debug(ex.getMessage(), ex);

        final List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        if (CollectionUtils.isNotEmpty(errors)) {
            String message = errors.stream().map(err -> err.getField() + ": " + err.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            return createResponse(HttpStatus.BAD_REQUEST, message, request.getServletPath());
        }
        return createResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getServletPath());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> handle(ConstraintViolationException ex, HttpServletRequest request) {
        log.debug(ex.getMessage(), ex);

        final Set<ConstraintViolation<?>> errors = ex.getConstraintViolations();
        if (CollectionUtils.isNotEmpty(errors)) {
            String message = errors.stream().map(constraintViolation -> constraintViolation.getPropertyPath() + ": " + constraintViolation.getMessage())
                    .collect(Collectors.joining(", "));
            return createResponse(HttpStatus.BAD_REQUEST, message, request.getServletPath());
        }
        return createResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getServletPath());
    }

    private ResponseEntity<ErrorMessage> createResponse(HttpStatus httpStatus, String body, String path) {
        return new ResponseEntity<>(ErrorMessage.builder()
                .status(httpStatus.value())
                .error(httpStatus.getReasonPhrase())
                .message(body)
                .path(path)
                .timestamp(ZonedDateTime.now())
                .build(), httpStatus);
    }

    public static Throwable getRootException(Throwable exception) {
        Throwable rootException = exception;
        while (rootException.getCause() != null) {
            rootException = rootException.getCause();
        }
        return rootException;
    }
}