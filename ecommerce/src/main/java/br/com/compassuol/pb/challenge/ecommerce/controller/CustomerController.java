package br.com.compassuol.pb.challenge.ecommerce.controller;

import br.com.compassuol.pb.challenge.ecommerce.entities.Customer;
import br.com.compassuol.pb.challenge.ecommerce.services.CustomerService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService service;

    @Autowired
    public CustomerController(CustomerService service){
        this.service=service;
    }


    @GetMapping("/{id}")
    public Customer retrieveCustomerById(@PathVariable Integer id){
        return service.findById(id);
    }
    
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Transactional
    public Customer createCustomer(@Valid @RequestBody Customer customer){
        return service.save(customer);
    }

    @PutMapping("/{id}")
    @Transactional

    public Customer updateCustomer(@PathVariable Integer id, @Valid @RequestBody Customer customer ){
        return service.updateById(id, customer);
    }

}
