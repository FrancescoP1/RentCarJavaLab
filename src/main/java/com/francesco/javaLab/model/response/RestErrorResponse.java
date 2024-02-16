package com.francesco.javaLab.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;

@Builder
@Getter
@Setter
public class RestErrorResponse {
  private HttpStatus httpStatus;
  private Integer httpStatusCode;
  private String message;
  private OffsetDateTime timeStamp;
}
