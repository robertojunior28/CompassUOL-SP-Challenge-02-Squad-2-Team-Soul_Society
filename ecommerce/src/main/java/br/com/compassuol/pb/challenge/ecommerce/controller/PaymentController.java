package br.com.compassuol.pb.challenge.ecommerce.controller;

import br.com.compassuol.pb.challenge.ecommerce.entities.Payment;
import br.com.compassuol.pb.challenge.ecommerce.services.PaymentDaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
=======
import org.springframework.web.bind.annotation.RequestMapping;
>>>>>>> ffb19e2d667af941073e62bc68fbd4668c897811
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private PaymentDaoService paymentDaoService;

    @Autowired
    public PaymentController(PaymentDaoService paymentDaoService) {
        this.paymentDaoService = paymentDaoService;
    }

    @PostMapping
    public Payment createPayment(@Valid @RequestBody Payment payment) {
//        Payment savedPayment = paymentDaoService.save(payment);
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(savedPayment.getPaymentId())
//                .toUri();
//        return ResponseEntity.created(location).body(savedPayment);
        return paymentDaoService.save(payment);
    }
}
