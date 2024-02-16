package com.francesco.javaLab.constants;

public interface ValidationMessages {
  String ID_NOT_NULL = "ID cannot be null";
  String NAME_NOT_BLANK = "Name cannot be blank";
  String NAME_SIZE = "Name must be between 3 and 50 characters";
  String IDENTIFICATION_NUMBER_NOT_BLANK = "Identification number cannot be blank";

  String IDENTIFICATION_NUMBER_SIZE = "Identification number must be between 5 and 15 characters";

  String VEHICLE_BRAND_NOT_BLANK = "Brand cannot be blank";
  String VEHICLE_BRAND_SIZE = "Brand must be between 2 and 50 characters";

  String VEHICLE_MODEL_NOT_BLANK = "Model cannot be blank";
  String VEHICLE_MODEL_SIZE = "Model must be between 2 and 50 characters";

  String VEHICLE_MODEL_YEAR_NOT_BLANK = "Model year cannot be blank";
  String VEHICLE_MODEL_YEAR_PATTERN = "Invalid model year format";

  String VEHICLE_VIN_NOT_BLANK = "VIN cannot be blank";
  String VEHICLE_VIN_SIZE = "VIN must be 17 characters long";

  String VEHICLE_ODOMETER_READING_NOT_NULL = "Odometer reading cannot be null";
  String VEHICLE_LOCATION_ID_NOT_NULL = "Location ID cannot be null";

  String EMPLOYEE_COMPANY_ROLE_NOT_BLANK = "The employee's company role cannot be blank";
  String EMPLOYEE_COMPANY_ROLE_SIZE = "The employee's company role must be between 2 and 30 characters";
  String EMPLOYEE_LOCATION_ID_NOT_NULL = "The employee's location id must not be null!";

  String LOCATION_LOCATION_NAME_NOT_BLANK = "Location name cannot be blank";
  String LOCATION_LOCATION_NAME_SIZE = "Location name must be between 2 and 50 characters";

  String LOCATION_CITY_NOT_BLANK = "City cannot be blank";
  String LOCATION_CITY_SIZE = "City must be between 2 and 50 characters";

  String RENTAL_START_DATE_NOT_NULL = "Start date cannot be null";
  String RENTAL_START_DATE_PAST_OR_PRESENT = "Start date must be in the past or present";

  String RENTAL_END_DATE_NOT_NULL = "End date cannot be null";
  String RENTAL_END_DATE_FUTURE_OR_PRESENT = "End date must be in the future or present";

  String RENTAL_VEHICLE_ID_NOT_NULL = "Vehicle ID cannot be null";
  String RENTAL_VEHICLE_ID_POSITIVE = "Vehicle ID must be a positive number";

  String RENTAL_CLIENT_ID_NOT_NULL = "Client ID cannot be null";
  String RENTAL_CLIENT_ID_POSITIVE = "Client ID must be a positive number";

  String REVIEW_COMMENT_NOT_BLANK = "Comment cannot be blank";
  String REVIEW_COMMENT_SIZE = "Comment must not exceed 1000 characters";

  String REVIEW_RATING_NOT_NULL = "Rating cannot be null";
  String REVIEW_RATING_POSITIVE = "Rating must be a positive number";

  String REVIEW_RENTAL_ID_NOT_NULL = "Rental ID cannot be null";
  String REVIEW_RENTAL_ID_POSITIVE = "Rental ID must be a positive number";

}
