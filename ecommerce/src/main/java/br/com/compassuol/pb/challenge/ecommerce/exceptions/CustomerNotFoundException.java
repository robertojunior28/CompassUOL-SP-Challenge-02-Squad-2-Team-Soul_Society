package br.com.compassuol.pb.challenge.ecommerce.exceptions;

import java.util.NoSuchElementException;

public class CustomerNotFoundException extends NoSuchElementException {

    public CustomerNotFoundException(Integer id) {
        super(String.format("Customer with id %s not found", id));
    }
}
