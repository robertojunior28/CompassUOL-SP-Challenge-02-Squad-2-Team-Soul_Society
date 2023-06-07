package br.com.compassuol.pb.challenge.ecommerce.controller;

import br.com.compassuol.pb.challenge.ecommerce.entities.Payment;
import br.com.compassuol.pb.challenge.ecommerce.services.PaymentDaoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaymentController {

    private PaymentDaoService paymentDaoService;

    public PaymentController(PaymentDaoService paymentDaoService) {
        this.paymentDaoService = paymentDaoService;}

    @GetMapping("/v1/payments")
    public List<Payment> retrieveAllPayments() {return paymentDaoService.findAll();}

}
