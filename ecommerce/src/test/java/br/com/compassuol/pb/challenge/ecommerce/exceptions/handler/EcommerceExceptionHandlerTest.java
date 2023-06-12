package br.com.compassuol.pb.challenge.ecommerce.exceptions.handler;

import br.com.compassuol.pb.challenge.ecommerce.exceptions.ApiExceptionResponse;
import br.com.compassuol.pb.challenge.ecommerce.exceptions.CustomerNotFoundException;
import br.com.compassuol.pb.challenge.ecommerce.exceptions.PaymentNotFoundException;
import br.com.compassuol.pb.challenge.ecommerce.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EcommerceExceptionHandlerTest {

    @Mock
    private CustomerNotFoundException customerNotFoundException;

    @Mock
    private ProductNotFoundException productNotFoundException;

    @Mock
    private PaymentNotFoundException paymentNotFoundException;

    @InjectMocks
    private EcommerceExceptionHandler ecommerceExceptionHandler;

    @Test
    void handleCustomerNotFoundException_ReturnsNotFoundResponse() {
        var customerNotFoundException = mock(CustomerNotFoundException.class);
        when(customerNotFoundException.getMessage()).thenReturn("Customer not found");

        var ecommerceExceptionHandler = new EcommerceExceptionHandler();
        var exceptionHandler = ecommerceExceptionHandler;
        ResponseEntity<Object> response = exceptionHandler.handleNotFoundException(customerNotFoundException);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Customer not found", ((ApiExceptionResponse) response.getBody()).getMessage());
        assertEquals(Collections.singletonList("Customer not found"), ((ApiExceptionResponse) response.getBody()).getDetails());
    }

}