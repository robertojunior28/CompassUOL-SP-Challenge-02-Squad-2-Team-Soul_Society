package br.com.compassuol.pb.challenge.ecommerce.exceptions;

public class PaymentNotFoundException extends RuntimeException{
    public PaymentNotFoundException(Integer id) {
        super(String.format("Payment with id %s not found", id));
    }

}
