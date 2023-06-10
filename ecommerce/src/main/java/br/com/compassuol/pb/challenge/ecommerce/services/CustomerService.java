package br.com.compassuol.pb.challenge.ecommerce.services;

import br.com.compassuol.pb.challenge.ecommerce.entities.Customer;

import br.com.compassuol.pb.challenge.ecommerce.exceptions.CustomerNotFoundException;
import br.com.compassuol.pb.challenge.ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }


    public Customer findById(Integer id){
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + id));
    }

    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer updateById(Integer id, Customer customer) {

        Optional<Customer> optional = customerRepository.findById(id);

        if (optional.isPresent()) {
            Customer updatedCustomer = optional.get();
            if (customer.getName() != null) {
                updatedCustomer.setName(customer.getName());
            }
            if (customer.getCpf() != null) {
                updatedCustomer.setCpf(customer.getCpf());
            }
            if (customer.getEmail() != null) {
                updatedCustomer.setEmail(customer.getEmail());
            }
            if (customer.getActive() != null) {
                updatedCustomer.setActive(customer.getActive());
            }

            return customerRepository.save(updatedCustomer);
        }

        throw new CustomerNotFoundException("Customer not found with ID: " + id);
    }
}
