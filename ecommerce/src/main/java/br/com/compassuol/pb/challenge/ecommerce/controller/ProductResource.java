package br.com.compassuol.pb.challenge.ecommerce.controller;

import br.com.compassuol.pb.challenge.ecommerce.entities.Product;
import br.com.compassuol.pb.challenge.ecommerce.services.ProductDaoService;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
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

    @GetMapping("/v1/products/{id}")
    public Product retrieveProductById(@PathVariable Integer id){
        return service.findById(id);
    }

    @PutMapping("/v1/products/{id}")
    public Product updateProduct(@PathVariable Integer id, @RequestBody Product updatedProduct){
        Product existingProduct = service.findById(id);

        if (existingProduct == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");

        }

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setDescription(updatedProduct.getDescription());


        return service.save(existingProduct);
    }

    @DeleteMapping("/v1/products/{id}")
    public void deleteUser(@PathVariable int id) {
        service.deleteById(id);
    }

    @PostMapping("/v1/products")
    public ResponseEntity<Product> createUser(@Valid @RequestBody Product product) {
        Product savedProduct = service.save(product);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProduct.getProductId())
                .toUri();
        return ResponseEntity.created(location).body(savedProduct);
    }
}