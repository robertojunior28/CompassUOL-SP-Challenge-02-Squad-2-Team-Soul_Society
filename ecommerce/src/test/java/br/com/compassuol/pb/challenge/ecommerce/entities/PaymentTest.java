package br.com.compassuol.pb.challenge.ecommerce.entities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class PaymentTest {

    @Test
    public void testValidPayment(){
        Payment payment = new Payment();
        payment.setPaymentMethod("PIX");
        payment.setPaymentDate("2023-06-09");
        payment.setOrder("Order A");

        String paymentMethod = String.valueOf(payment.getPaymentMethod());
        String paymentDate = String.valueOf(payment.getPaymentDate());
        String order = payment.getOrder().toString();

        System.out.println(payment.getPaymentId());

        assertEquals("PIX", paymentMethod);
        assertEquals("2023-06-09", paymentDate);
        assertEquals("estagio@compass.com", order);
    }
}
