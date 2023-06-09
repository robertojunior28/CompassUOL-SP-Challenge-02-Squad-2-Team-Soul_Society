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
<<<<<<< HEAD
    public Customer updateCustomer(@PathVariable Integer id, @RequestBody Customer customer ){
        var existingCustomer = service.findById(id);
        if(existingCustomer == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        existingCustomer.setName(customer.getName());
        existingCustomer.setCpf(customer.getCpf());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setActive(customer.getActive());
        return service.save(customer);
=======
    public Customer updateCustomer(@PathVariable Integer id, @Valid @RequestBody Customer customer ){
        return service.updateById(id, customer);
>>>>>>> f7f1516c208eca4828412586e500c3fa29685425
    }

}
