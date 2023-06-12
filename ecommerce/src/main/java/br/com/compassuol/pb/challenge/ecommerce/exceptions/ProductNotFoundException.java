package br.com.compassuol.pb.challenge.ecommerce.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Integer id) {
        super(String.format("Product with id %s not found", id));
    }
}
