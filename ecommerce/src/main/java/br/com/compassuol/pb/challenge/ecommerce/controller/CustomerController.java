package br.com.compassuol.pb.challenge.ecommerce.controller;

import br.com.compassuol.pb.challenge.ecommerce.entities.Customer;
import br.com.compassuol.pb.challenge.ecommerce.services.CustomerDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
public class CustomerController {

    private CustomerDaoService service;

    @Autowired
    public CustomerController(CustomerDaoService service){
        this.service=service;
    }


    @GetMapping("/v1/customers/{id}")
    public Customer retrieveCustomerById(@PathVariable Integer id){
        return service.findById(id);
    }
    
    @PostMapping("/v1/customers")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customer){
        return service.save(customer);
    }

    @PutMapping("/v1/customers/{id}")
    public Customer updateCustomer(@PathVariable Integer id, @RequestBody Customer customer ){
        var existingCustomer = service.findById(id);
        if(existingCustomer == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        existingCustomer.setName(customer.getName());
        //existingCustomer.setCpf(customer.getCpf());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setActive(customer.getActive());
        return service.save(customer);
    }

}
