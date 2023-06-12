package br.com.compassuol.pb.challenge.ecommerce.services;

import br.com.compassuol.pb.challenge.ecommerce.entities.Customer;
import br.com.compassuol.pb.challenge.ecommerce.exceptions.CustomerNotFoundException;
import br.com.compassuol.pb.challenge.ecommerce.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testSave(){
        Customer customer = new Customer("Customer", "094.038.120-60","customer@gmail.com");

        when(customerRepository.save(customer)).thenReturn(customer);

        Customer customerSaved = customerService.save(customer);

        assertNotNull(customerSaved);
        assertEquals(customer.getName(), customerSaved.getName());
        assertEquals(customer.getCpf(), customerSaved.getCpf());
        assertEquals(customer.getEmail(), customerSaved.getEmail());
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void testFindByIdIfIdExist(){
        Customer customer = new Customer("Customer", "094.038.120-60","customer@gmail.com");
        Integer id = customer.getCustomerId();

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));

        Customer foundedCustomer = customerService.findById(id);

        assertNotNull(foundedCustomer);
        assertEquals(id, foundedCustomer.getCustomerId());
        assertEquals(customer.getName(), foundedCustomer.getName());
        assertEquals(customer.getCpf(), foundedCustomer.getCpf());
        assertEquals(customer.getEmail(), foundedCustomer.getEmail());
        verify(customerRepository, times(1)).findById(id);
    }

    @Test
    void testFindByIdIfIdNotExist(){
        Integer id = 666;

        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.findById(id));
        verify(customerRepository, times(1)).findById(id);
    }

    @Test
    void testUpdateByIdIfIdExist(){
        Customer customer = new Customer("Customer", "094.038.120-60","customer@gmail.com");

        Integer id = customer.getCustomerId();

        Customer updateCustomer = new Customer("New Customer", "442.128.860-81","customer@outlook.com");
        updateCustomer.setCustomerId(id);

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer result = customerService.updateById(id,updateCustomer);

        assertNotNull(result);
        assertEquals(id, result.getCustomerId());
        assertEquals(updateCustomer.getName(), result.getName());
        assertEquals(updateCustomer.getCpf(), result.getCpf());
        assertEquals(updateCustomer.getEmail(), result.getEmail());
        verify(customerRepository, times(1)).findById(id);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void testUpdateByIdIfIdNotExist(){
        Integer id = 999;
        Customer customer = new Customer("Customer", "094.038.120-60","customer@gmail.com");

        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class,
                () ->customerService.updateById(id, customer));
        assertEquals("Customer not found with ID: " + id, exception.getMessage());
        verify(customerRepository, times(1)).findById(id);
        verify(customerRepository, never()).save(any(Customer.class));
    }
}
