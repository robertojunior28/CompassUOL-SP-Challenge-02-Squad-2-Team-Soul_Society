package br.com.compassuol.pb.challenge.ecommerce.controller;

import br.com.compassuol.pb.challenge.ecommerce.entities.Customer;
import br.com.compassuol.pb.challenge.ecommerce.entities.Order;
import br.com.compassuol.pb.challenge.ecommerce.entities.Payment;
import br.com.compassuol.pb.challenge.ecommerce.enums.PaymentMethod;
import br.com.compassuol.pb.challenge.ecommerce.repository.PaymentRepository;
import br.com.compassuol.pb.challenge.ecommerce.services.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentControllerTest {

    @Mock
    private PaymentService paymentService;
    @InjectMocks
    private PaymentController paymentController;

    @Test
    void createPayment() {
        //given
        var customer = new Customer("Customer", "132.080.480-25", "customer@gmail.com");
        var order = new Order(customer.getCustomerId());
        var payment = new Payment(PaymentMethod.CASH,order);
        given(paymentService.save(any())).willReturn(payment);

        //when
        var viewPayment = paymentController.createPayment(payment);

        //then
        assertThat(viewPayment).isEqualTo(payment);
    }
}