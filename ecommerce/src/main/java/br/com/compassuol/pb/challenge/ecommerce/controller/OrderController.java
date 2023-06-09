package br.com.compassuol.pb.challenge.ecommerce.controller;

import br.com.compassuol.pb.challenge.ecommerce.entities.Customer;
import br.com.compassuol.pb.challenge.ecommerce.entities.Order;
import br.com.compassuol.pb.challenge.ecommerce.services.OrderDaoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderDaoService service;

    @Autowired
    public OrderController(OrderDaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Order> retrieveAllOrders() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Transactional
    public Order createOrder(@RequestBody Order order) {
        return service.save(order);
    }

    @GetMapping("/{customer}")
    public List<Order> retrieveAllOrdersByCustumer(Customer customer) {
        return service.findAllByCustomerId(customer);
    }

}
