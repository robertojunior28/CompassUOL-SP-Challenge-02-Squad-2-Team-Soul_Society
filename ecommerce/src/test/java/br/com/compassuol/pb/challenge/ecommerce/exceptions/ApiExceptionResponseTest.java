package br.com.compassuol.pb.challenge.ecommerce.exceptions;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiExceptionResponseTest {

    @Test
    void createApiExceptionResponse_WithValidData_ReturnsCorrectValues() {
        Integer code = 404;
        String status = "Not Found";
        LocalDateTime timestamp = LocalDateTime.of(2023, 6, 12, 15, 30, 0);
        String message = "Customer not found";
        List<String> details = Arrays.asList("Error 1", "Error 2");

        ApiExceptionResponse response = ApiExceptionResponse.builder()
                .code(code)
                .status(status)
                .timestamp(timestamp)
                .message(message)
                .details(details)
                .build();

        assertEquals(code, response.getCode());
        assertEquals(status, response.getStatus());
        assertEquals(timestamp, response.getTimestamp());
        assertEquals(message, response.getMessage());
        assertEquals(details, response.getDetails());
    }

}
