package br.com.compassuol.pb.challenge.ecommerce.exceptions;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Integer id) {
        super(String.format("Customer with id %s not found", id));
    }
}
