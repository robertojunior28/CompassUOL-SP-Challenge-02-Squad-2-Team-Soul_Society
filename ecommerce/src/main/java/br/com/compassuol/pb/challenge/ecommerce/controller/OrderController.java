package br.com.compassuol.pb.challenge.ecommerce.controller;

import br.com.compassuol.pb.challenge.ecommerce.entities.Customer;
import br.com.compassuol.pb.challenge.ecommerce.entities.Order;
import br.com.compassuol.pb.challenge.ecommerce.enums.OrderStatus;
import br.com.compassuol.pb.challenge.ecommerce.services.CustomerService;
import br.com.compassuol.pb.challenge.ecommerce.services.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService service;
    private CustomerService customerService;

    @Autowired
    public OrderController(OrderService service, CustomerService customerService) {
        this.service = service;
        this.customerService = customerService;
    }

    @GetMapping
    public List<Order> retrieveAllOrders() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        Order savedOrder = service.save(order);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedOrder.getId())
                .toUri();
        return  ResponseEntity.created(location).body(savedOrder);
    }

    @GetMapping("/customers/{customerId}")
    public List<Order> retrieveAllOrdersByCustomer(@PathVariable Integer customerId) {
        return service.findAllByCustomer(customerId);
    }

}
