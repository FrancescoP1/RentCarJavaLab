# RentCarJavaLab
 Project for Java Web Programming course 2023-2024.
### PDF Documents

- [Business Requirements](BusinessRequirements.pdf)
- [Technical Documentation](TechnicalDocumentation.pdf)
# Rent Car Spring Boot API

Rent Car is an API designed to offer the main features necessary for managing a rent car company. The app’s main purpose is to accommodate the needs of small to medium rent car companies by offering them a solid infrastructure that would allow them to manage their vehicle fleets, employees, clients, and rentals. Rent Car API is a robust backend solution, implemented using Java and the Spring ecosystem.

## Business Requirements:

1. **User Authentication and Authorization:**
    - Users should be able to sign up and log in as either employees or clients.
    - Employee accounts should have different access levels for administrative tasks.
    - Clients should only be able to view and manage their own rental history and details.

2. **Vehicle Management:**
    - The system should allow employees to add, update, and delete vehicle information, including make, model, year, and availability status.
    - Normal users should be able to see the details of the various vehicles.

3. **Rental Management:**
    - Employees should be able to create, modify, and delete rental bookings.
    - Clients should be able to view available vehicles, select a rental period, and place a booking.

4. **Employee Management:**
    - Administrators should have the ability to add, edit, and remove employee accounts.
    - Employee accounts should include information such as name, contact details, and role within the company.

5. **Client Management:**
    - Clients should be able to create, update, and delete their accounts.
    - The system should store client information securely, including contact details and payment preferences.

6. **Payment Processing:**
    - The application should support secure payment transactions for rental bookings.
    - Clients should have the option to save payment methods for future use.

7. **Error Handling:**
    - The application should provide a robust error handling mechanism that allows users to easily understand that an error has occurred.
    - Users shall receive different error messages (i.e., administrators should be able to see more detailed error messages than normal users).

8. **Reservation Confirmation and Notifications:**
    - Clients should receive confirmation emails or notifications upon successful rental bookings.
    - Employees should be notified of new bookings and changes to existing reservations.

9. **Availability Tracking:**
    - The application should track vehicle availability in real-time to prevent double bookings.
    - Employees should be able to mark vehicles as unavailable during maintenance or repairs.

10. **Feedback & Review System:**
    - Clients should have the option to leave reviews and ratings for both vehicles and service quality.
    - Employees should be able to respond to client feedback and address any issues promptly.

## MVP Features:

### 1. Basic CRUD Operations for Vehicles:
- Implement endpoints for viewing, updating, deleting, and adding vehicles.
- The system should automatically validate a newly added vehicle.

### 2. Rental Management:
- Implement endpoints for creating, updating, and canceling rentals.
- The system should automatically check if a car can be rented or not (if it’s available).

### 3. Employee Management:
- Implement endpoints for viewing, adding, and removing employees.
- The system should automatically check and validate the newly added employees.

### 4. Client Management:
- Implement endpoints for viewing, adding, and removing clients.
- The system should automatically check and validate the newly added clients.

### 5. Error Handling:
- Implement robust error handling for API requests, providing clear error messages to users.
- A global error handling mechanism shall be implemented.

