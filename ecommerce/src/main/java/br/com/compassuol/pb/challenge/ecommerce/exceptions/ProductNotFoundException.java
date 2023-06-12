package br.com.compassuol.pb.challenge.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Integer id) {
        super(String.format("Product with id %s not found", id));
    }
}
