package br.com.compassuol.pb.challenge.ecommerce.services;

import br.com.compassuol.pb.challenge.ecommerce.entities.Customer;
import br.com.compassuol.pb.challenge.ecommerce.exceptions.CustomerNotFoundException;
import br.com.compassuol.pb.challenge.ecommerce.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
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

    }
}
