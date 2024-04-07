# Spring JWT Security Project

This project is a simple implementation of JWT (JSON Web Token) authentication using Spring Boot.

## Technologies Used

- Java 17
- Spring Boot
- Spring Security
- PostgreSQL
- Gradle

## Features

- User registration and authentication
- JWT generation for authenticated users
- JWT validation for subsequent requests
- Password hashing for user passwords

## Project Structure

The project contains the following main components:

- `AppConfig`: This is the main configuration class for the Spring Boot application with all the necessary beans.
- `SecurityConfig`: This class configures the security filter chain for the application.
- `AuthenticationController`: This is the REST controller that handles the `/api/v1/auth` endpoints for user registration and authentication.
- `AuthenticationService`: This service handles the business logic for user registration and authentication, including JWT generation.
- `JwtService`: This service provides utility methods for JWT generation and validation.
- `RegisterRequest`: This is the data transfer object (DTO) for user registration requests.
- `AuthenticationRequest`: This is the DTO for user authentication requests.

## Setup and Running

To run this project, you need to have Java and Gradle installed on your machine.

1. Clone the repository to your local machine.
2. Navigate to the project directory in your terminal.
3. Run the command `gradle bootRun` to start the application. (or use your IDE to run the application)

The application will start and listen for HTTP requests on port 8080.

## Endpoints

- `POST /api/v1/auth/register`: Register a new user. The request body should be a JSON object with `firstName`, `lastName`, `email`, and `password` fields.
- `POST /api/v1/auth/authentication`: Authenticate a user. The request body should be a JSON object with `email` and `password` fields.

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License

[MIT](https://choosealicense.com/licenses/mit/)