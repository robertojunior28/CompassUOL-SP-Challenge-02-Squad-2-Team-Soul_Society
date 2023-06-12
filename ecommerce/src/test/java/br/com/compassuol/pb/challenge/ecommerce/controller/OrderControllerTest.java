package br.com.compassuol.pb.challenge.ecommerce.controller;

import br.com.compassuol.pb.challenge.ecommerce.entities.Customer;
import br.com.compassuol.pb.challenge.ecommerce.entities.Order;
import br.com.compassuol.pb.challenge.ecommerce.services.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    OrderService orderService;

    @InjectMocks
    OrderController orderController;

    @Test
    public void createOrder(){
        //given
        Customer customer = new Customer("Customer", "132.080.480-25", "customer@gmail.com");
        Order order = new Order(customer.getCustomerId());
        given(orderService.save(any())).willReturn(order);

        //when
        Order viewOrder = orderController.createOrder(order);

        //then
        assertThat(viewOrder).isEqualTo(order);
    }

    @Test
    public void retrieveAllOrders(){
        //given
        Customer customer = new Customer("Customer", "132.080.480-25", "customer@gmail.com");
        Order order1 = new Order(customer.getCustomerId());
        Order order2 = new Order(customer.getCustomerId());
        List<Order> listOrders = Arrays.asList(order1, order2);
        given(orderService.findAll()).willReturn(listOrders);

        //when
        List<Order> viewOrders = orderController.retrieveAllOrders();

        //then
        assertThat(viewOrders).isEqualTo(listOrders);
    }

    @Test
    public void retrievelAllOrdersByCustomer(){
        //given
        Customer customer = new Customer("Customer", "132.080.480-25", "customer@gmail.com");
        Order order1 = new Order(customer.getCustomerId());
        Order order2 = new Order(customer.getCustomerId());
        List<Order> listOrders = Arrays.asList(order1, order2);
        given(orderService.findAllByCustomer(anyInt())).willReturn(listOrders);

        //when
        List<Order> viewOrders = orderController.retrieveAllOrdersByCustomer(anyInt());

        //then
        assertThat(viewOrders).isEqualTo(listOrders);
    }

}