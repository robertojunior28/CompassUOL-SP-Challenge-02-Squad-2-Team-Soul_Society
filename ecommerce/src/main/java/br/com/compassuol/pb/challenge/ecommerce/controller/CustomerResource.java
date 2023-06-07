package br.com.compassuol.pb.challenge.ecommerce.controller;

import br.com.compassuol.pb.challenge.ecommerce.entities.Customer;
import br.com.compassuol.pb.challenge.ecommerce.entities.Product;
import br.com.compassuol.pb.challenge.ecommerce.services.CustomerDaoService;
import br.com.compassuol.pb.challenge.ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/v1/products/{id}")
    public Product retrieveProductById(@PathVariable Integer id){
        return service.findById(id);
    }

    @PostMapping("v1/customers")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }

    @PutMapping("v1/customers/{id}")
    public Customer updateCustomer(@PathVariable Integer id, @RequestBody Customer customer ){
        var customerOptional = customerRepository.findById(id);
        if(customerOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return customerRepository.save(customer);
    }

}
