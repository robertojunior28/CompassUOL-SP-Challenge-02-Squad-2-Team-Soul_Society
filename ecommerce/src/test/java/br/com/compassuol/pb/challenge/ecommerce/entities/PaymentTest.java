package br.com.compassuol.pb.challenge.ecommerce.entities;

import br.com.compassuol.pb.challenge.ecommerce.enums.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    Payment payment = new Payment();

    @BeforeEach
    void setUp() {
    }

    @Test
    void setPaymentId() {
        payment.setPaymentId(1);
        assertEquals(1, payment.getPaymentId());
    }

    @Test
    void setPaymentMethod() {
        payment.setPaymentMethod(PaymentMethod.PIX);
        assertEquals(PaymentMethod.PIX, payment.getPaymentMethod());
    }

    @Test
    void setPaymentDate() {
        payment.setPaymentDate(new Date());
        assertEquals(new Date(), payment.getPaymentDate());
    }

    @Test
    void setOrder() {
        var order = new Order();
        order.setId(1);
        payment.setOrder(order);
        assertEquals(1, payment.getOrder().getId());
    }
}