package br.com.compassuol.pb.challenge.ecommerce.controller;

import br.com.compassuol.pb.challenge.ecommerce.entities.Customer;
import br.com.compassuol.pb.challenge.ecommerce.services.CustomerDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerDaoService service;


    @GetMapping("/v1/products/{id}")
    public Optional<Customer> retrieveCustomerById(@PathVariable Integer id){
        return service.findById(id);
    }
    
    @PostMapping("v1/customers")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Optional<Customer> createCustomer(@RequestBody Customer customer){
        return service.save(customer);
    }

    @PutMapping("/v1/customers/{id}")
    public Optional<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer customer ){
        var customerOptional = service.findById(id);
        if(customerOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return service.save(customer);
    }

}
