package com.francesco.javaLab.exception;

public interface ExceptionConstants {
  String VEHICLE_NOT_FOUND = "The requested vehicle does not exist in our system!";
  String VEHICLE_NOT_AVAILABLE = "The requested vehicle is currently not available!";
  String VEHICLE_ALREADY_RENTED = "The requested vehicle is rented by another person!";
  String CLIENT_NOT_FOUND = "The requested client does not exist in our system!";
  String CLIENT_ALREADY_EXISTS = "A client with the provided identification number already exists!";
  String LOCATION_NOT_FOUND = "The requested location does not exist in our system!";
  String EMPLOYEE_NOT_FOUND = "The requested employee does not exist in out system!";

  String RENTAL_NOT_FOUND = "The requested vehicle rental does not exist in our system!";
  String RENTAL_ALREADY_FINISHED = "The requested rental has already been finished!";
}
