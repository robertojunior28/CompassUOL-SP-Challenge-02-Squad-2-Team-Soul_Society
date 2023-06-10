package br.com.compassuol.pb.challenge.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends NoSuchElementException {

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
