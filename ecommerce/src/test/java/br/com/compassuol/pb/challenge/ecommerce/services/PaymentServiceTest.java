package br.com.compassuol.pb.challenge.ecommerce.services;

import br.com.compassuol.pb.challenge.ecommerce.entities.Order;
import br.com.compassuol.pb.challenge.ecommerce.entities.Payment;
import br.com.compassuol.pb.challenge.ecommerce.enums.PaymentMethod;
import br.com.compassuol.pb.challenge.ecommerce.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void save() {
        var payment = new Payment();
        payment.setPaymentId(1);
        payment.setPaymentDate(new Date());
        payment.setPaymentMethod(PaymentMethod.CASH);
        payment.setOrder(new Order());
        var result = paymentRepository.save(payment)
        assertTrue(result != null);

    }
}