package br.com.compassuol.pb.challenge.ecommerce.controller;

import br.com.compassuol.pb.challenge.ecommerce.entities.Product;
import br.com.compassuol.pb.challenge.ecommerce.exceptions.ProductNotFoundException;
import br.com.compassuol.pb.challenge.ecommerce.repository.ProductRepository;
import br.com.compassuol.pb.challenge.ecommerce.services.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.hateoas.EntityModel;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    private final ProductService service;

    public ProductController(ProductRepository productRepository, ProductService service) {
        this.productRepository = productRepository;
        this.service = service;
    }

    //Get /v1/products
    @GetMapping
    public List<Product> retrieveAllProducts() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<Product> retrieveProductById(@PathVariable Integer id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty())
            throw new ProductNotFoundException("id:" + id);

        EntityModel<Product> entityModel = EntityModel.of(product.get());

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllProducts());
        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }


    @PutMapping("/{id}")
    @Transactional
    public Product updateProduct(@PathVariable Integer id, @Valid @RequestBody Product product){
        return service.updateById(id, product);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteProduct(@PathVariable int id) {
        service.deleteById(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product savedProduct = service.save(product);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProduct.getProductId())
                .toUri();
        return ResponseEntity.created(location).body(savedProduct);
    }
}