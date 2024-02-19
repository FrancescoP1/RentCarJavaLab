package com.francesco.javaLab.exception;

public class ActionNotAllowedException extends RuntimeException {
  public ActionNotAllowedException(String message) {
    super(message);
  }
}
