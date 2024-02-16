package com.francesco.javaLab.exception;

import com.francesco.javaLab.model.response.RestErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<RestErrorResponse> handleResourceNotFoundException(
      ResourceNotFoundException resourceNotFoundException) {
    RestErrorResponse restErrorResponse =
        RestErrorResponse.builder()
            .httpStatusCode(404)
            .httpStatus(HttpStatus.NOT_FOUND)
            .message(resourceNotFoundException.getMessage())
            .build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restErrorResponse);
  }

  @ExceptionHandler(ResourceNotAvailableException.class)
  public ResponseEntity<RestErrorResponse> handleResourceNotAvailableException(
      ResourceNotAvailableException resourceNotAvailableException) {
    RestErrorResponse restErrorResponse =
        RestErrorResponse.builder()
            .httpStatusCode(406)
            .httpStatus(HttpStatus.NOT_ACCEPTABLE)
            .message(resourceNotAvailableException.getMessage())
            .build();
    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(restErrorResponse);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<RestErrorResponse> handleGenericException() {
    RestErrorResponse restErrorResponse =
        RestErrorResponse.builder()
            .httpStatusCode(500)
            .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
            .message("")
            .build();
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(restErrorResponse);
  }
}
