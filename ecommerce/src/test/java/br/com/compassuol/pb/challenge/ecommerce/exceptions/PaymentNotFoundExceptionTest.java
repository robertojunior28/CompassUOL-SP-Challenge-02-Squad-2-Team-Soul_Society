package br.com.compassuol.pb.challenge.ecommerce.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentNotFoundExceptionTest {

    @Test
    void createCustomerNotFoundException_WithValidId_ReturnsCorrectMessage() {
        Integer id = 123;
        String expectedMessage = "Customer with id 123 not found";

        var exception = new PaymentNotFoundException(id);

        assertEquals(expectedMessage, exception.getMessage());
    }

}