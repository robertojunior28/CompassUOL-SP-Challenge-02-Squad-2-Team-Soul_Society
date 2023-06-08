package br.com.compassuol.pb.challenge.ecommerce.controller;

import br.com.compassuol.pb.challenge.ecommerce.entities.Customer;
import br.com.compassuol.pb.challenge.ecommerce.entities.Order;
import br.com.compassuol.pb.challenge.ecommerce.services.OrderDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    private OrderDaoService service;

    @Autowired
    public OrderController(OrderDaoService service) {
        this.service = service;
    }

    @GetMapping("/v1/orders")
    public List<Order> retrieveAllOrders() {
        return service.findAll();
    }

    @PostMapping("/v1/orders")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Order createOrder(@RequestBody Order order) {
        return service.save(order);
    }

    @GetMapping("/v1/orders/customers/{customer}")
    public List<Order> retrieveAllOrdersByCustumer(Customer customer) {
        return service.findAllByCustomerId(customer);
    }

}
