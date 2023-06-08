package br.com.compassuol.pb.challenge.ecommerce.controller;

import br.com.compassuol.pb.challenge.ecommerce.entities.Payment;
import br.com.compassuol.pb.challenge.ecommerce.services.PaymentDaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class PaymentController {

    private PaymentDaoService paymentDaoService;

    @Autowired
    public PaymentController(PaymentDaoService paymentDaoService) {
        this.paymentDaoService = paymentDaoService;
    }

//    @GetMapping("/v1/payments")
//    public List<Payment> retrieveAllPayments() {return paymentDaoService.retrieveAllPayment();}

    @PostMapping("/v1/products")
    public ResponseEntity<Payment> createUser(@Valid @RequestBody Payment payment) {
        Payment savedPayment = paymentDaoService.save(payment);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPayment.getPaymentId())
                .toUri();
        return ResponseEntity.created(location).body(savedPayment);
    }
}
