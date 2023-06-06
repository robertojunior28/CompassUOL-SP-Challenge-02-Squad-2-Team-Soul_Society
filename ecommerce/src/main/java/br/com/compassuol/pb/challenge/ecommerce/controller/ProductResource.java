package br.com.compassuol.pb.challenge.ecommerce.controller;

import br.com.compassuol.pb.challenge.ecommerce.entities.Product;
import br.com.compassuol.pb.challenge.ecommerce.services.ProductDaoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductResource {

    private final ProductDaoService service;

    public ProductResource(ProductDaoService service) {
        this.service = service;
    }

    //Get /v1/products
    @GetMapping("/v1/products")
    public List<Product> retrieveAllProducts() {
        return service.findAll();
    }
}
