package br.com.compassuol.pb.challenge.ecommerce.controller;

import br.com.compassuol.pb.challenge.ecommerce.entities.Customer;
import br.com.compassuol.pb.challenge.ecommerce.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    @Test
    public void createCustomer(){
        var customer = new Customer("Customer", "132.080.480-25", "customer@gmail.com");
        given(customerService.save(any())).willReturn(customer);

        var viewCustomer = customerController.createCustomer(customer);

        assertThat(viewCustomer).isEqualTo(customer);
    }

    @Test
    public void retrieveCustomerById(){
        var customer = new Customer("Customer", "132.080.480-25", "customer@gmail.com");
        given(customerService.findById(anyInt())).willReturn(customer);

        var viewCustomer = customerController.retrieveCustomerById(anyInt());

        assertThat(viewCustomer).isEqualTo(customer);
    }

    @Test
    public void updateCustomer(){
        var customer = new Customer("Customer", "132.080.480-25", "customer@gmail.com");
        given(customerService.updateById(anyInt(),any())).willReturn(customer);

        var viewCustomer = customerController.updateCustomer(anyInt(),any());

        assertThat(viewCustomer).isEqualTo(customer);
    }

}