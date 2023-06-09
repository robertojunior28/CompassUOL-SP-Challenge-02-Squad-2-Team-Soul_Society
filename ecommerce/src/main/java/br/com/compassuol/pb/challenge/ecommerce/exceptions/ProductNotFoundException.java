package br.com.compassuol.pb.challenge.ecommerce.exceptions;

import br.com.compassuol.pb.challenge.ecommerce.entities.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String message) {
        super(message);
    }
}
