package br.com.compassuol.pb.challenge.ecommerce.controller;

import br.com.compassuol.pb.challenge.ecommerce.entities.Product;
import br.com.compassuol.pb.challenge.ecommerce.services.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    //Get /v1/products
    @GetMapping
    public List<Product> retrieveAllProducts() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Product retrieveProductById(@PathVariable Integer id){
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @Transactional
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

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteUser(@PathVariable int id) {
        service.deleteById(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Product> createUser(@Valid @RequestBody Product product) {
        Product savedProduct = service.save(product);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProduct.getProductId())
                .toUri();
        return ResponseEntity.created(location).body(savedProduct);
    }
}