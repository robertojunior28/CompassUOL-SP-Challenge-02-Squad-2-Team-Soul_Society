package br.com.compassuol.pb.challenge.ecommerce.services;

import br.com.compassuol.pb.challenge.ecommerce.entities.Customer;

import br.com.compassuol.pb.challenge.ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerDaoService {

    private CustomerRepository customerRepository;

    public  CustomerDaoService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }


    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(Integer id){
        return customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found with ID: " + id));
    }

    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }


}
