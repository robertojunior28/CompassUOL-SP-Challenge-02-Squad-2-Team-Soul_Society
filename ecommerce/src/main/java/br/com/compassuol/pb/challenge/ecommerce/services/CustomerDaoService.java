package br.com.compassuol.pb.challenge.ecommerce.services;

import br.com.compassuol.pb.challenge.ecommerce.entities.Customer;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerDaoService {

    private static List<Customer> customers = new ArrayList<>();

    private static int customersCount = 0;

    static {
        customers.add(new Customer("Fulano","111.222.333-44", "email@mail.com" ,true));
    }

    public List<Customer> findAll() {
        return customers;
    }

}
