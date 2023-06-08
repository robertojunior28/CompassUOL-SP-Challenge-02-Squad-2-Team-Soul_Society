package br.com.compassuol.pb.challenge.ecommerce.controller;

import br.com.compassuol.pb.challenge.ecommerce.entities.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    private final OrderDaoService service;

    public OrderController(OrderDaoService service) {
        this.service = service;}

    @GetMapping("/v1/orders")
    public List<Order> retrieveAllOrders() {return service.findAll();}
}
