package br.com.compassuol.pb.challenge.ecommerce.services;

import br.com.compassuol.pb.challenge.ecommerce.entities.Customer;
import br.com.compassuol.pb.challenge.ecommerce.entities.Order;
import br.com.compassuol.pb.challenge.ecommerce.enums.OrderStatus;
import br.com.compassuol.pb.challenge.ecommerce.exceptions.CustomerNotFoundException;
import br.com.compassuol.pb.challenge.ecommerce.repository.CustomerRepository;
import br.com.compassuol.pb.challenge.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<Order> findAllByCustomer(Integer customerId){
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isEmpty()){
            throw new CustomerNotFoundException("Customer not found with ID:" + customerId);
        }
        return orderRepository.findAllByCustomer(customer);
    }
}
