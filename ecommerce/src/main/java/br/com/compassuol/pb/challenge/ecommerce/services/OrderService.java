package br.com.compassuol.pb.challenge.ecommerce.services;

import br.com.compassuol.pb.challenge.ecommerce.entities.Customer;
import br.com.compassuol.pb.challenge.ecommerce.entities.Order;
import br.com.compassuol.pb.challenge.ecommerce.enums.OrderStatus;
import br.com.compassuol.pb.challenge.ecommerce.repository.CustomerRepository;
import br.com.compassuol.pb.challenge.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;

    private Customer customer;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository){
        this.orderRepository=orderRepository;
        this.customerRepository = customerRepository;
    }


    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order save(Order order){
        Optional<Customer> customer = customerRepository.findById(order.getCustomerId());
        if(customer.isPresent()){
            order.setCustomer(customer.get());
            order.setDate(new Date());
            order.setStatus(Collections.singletonList(OrderStatus.CREATED));
        }
        return orderRepository.save(order);
    }

    public List<Order> findAllByCustomer(Customer customer){
        return orderRepository.findAllByCustomer(customer);
    }
}
