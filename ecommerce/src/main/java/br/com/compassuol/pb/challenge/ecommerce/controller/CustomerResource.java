package br.com.compassuol.pb.challenge.ecommerce.controller;

import br.com.compassuol.pb.challenge.ecommerce.services.CustomerDaoService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerResource {

    private final CustomerDaoService service;

    public CustomerResource(CustomerDaoService service) {this.service = service; }


}
