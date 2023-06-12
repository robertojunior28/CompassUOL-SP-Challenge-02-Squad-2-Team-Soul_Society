package br.com.compassuol.pb.challenge.ecommerce.controller;

import br.com.compassuol.pb.challenge.ecommerce.entities.Order;
import br.com.compassuol.pb.challenge.ecommerce.entities.Payment;
import br.com.compassuol.pb.challenge.ecommerce.enums.PaymentMethod;
import br.com.compassuol.pb.challenge.ecommerce.repository.PaymentRepository;
import br.com.compassuol.pb.challenge.ecommerce.services.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PaymentControllerTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;
    @Mock
    private PaymentController paymentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAttributes() {
        var payment = new Payment(PaymentMethod.CASH, new Order());
        assertEquals(PaymentMethod.CASH, payment.getPaymentMethod());
        assertNotNull(payment.getOrder());
        assertNotNull(payment.getPaymentDate());
    }

    @Test
    void testCreatePayment() {
        var payment = new Payment(PaymentMethod.CASH, new Order());
        var paymentCreate = when(paymentController.createPayment(payment)).thenReturn(payment);
        assertNotNull(paymentCreate);
        assertEquals(PaymentMethod.CASH, payment.getPaymentMethod());
    }
}