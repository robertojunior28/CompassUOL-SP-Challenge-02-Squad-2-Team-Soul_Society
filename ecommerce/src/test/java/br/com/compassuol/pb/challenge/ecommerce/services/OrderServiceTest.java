package br.com.compassuol.pb.challenge.ecommerce.services;

import br.com.compassuol.pb.challenge.ecommerce.entities.Customer;
import br.com.compassuol.pb.challenge.ecommerce.entities.Order;
import br.com.compassuol.pb.challenge.ecommerce.enums.OrderStatus;
import br.com.compassuol.pb.challenge.ecommerce.exceptions.CustomerNotFoundException;
import br.com.compassuol.pb.challenge.ecommerce.repository.CustomerRepository;
import br.com.compassuol.pb.challenge.ecommerce.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Order order1 = new Order(1);
        Order order2 = new Order(1);
        List<Order> allOrdersExpected = Arrays.asList(order1, order2);

        when(orderRepository.findAll()).thenReturn(allOrdersExpected);

        List<Order> theOrders = orderService.findAll();

        assertEquals(allOrdersExpected.size(), theOrders.size());
        assertTrue(theOrders.containsAll(allOrdersExpected));
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void testSaveIfCustomerIsPresent() {
        Integer id = 1;
        Customer customer = new Customer("Customer", "094.038.120-60", "customer@gmail.com");
        customer.setCustomerId(id);
        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        Order order = new Order(customer.getCustomerId());
        order.setCustomer(customer);
        order.setDate(order.getDate());
        order.setStatus(Collections.singletonList(OrderStatus.CREATED));

        when(orderRepository.save(order)).thenReturn(order);

        Order orderSaved = orderService.save(order);

        assertNotNull(orderSaved);
        assertEquals(order.getCustomerId(), orderSaved.getCustomerId());
        assertEquals(order.getDate(), orderSaved.getDate());
        assertEquals(order.getCustomer(), orderSaved.getCustomer());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void testFindByCustomerIfCustomerExist() {
        Order order1 = new Order(1);
        Order order2 = new Order(1);
        List<Order> allOrdersExpected = Arrays.asList(order1, order2);

        Customer customer = new Customer("Customer", "094.038.120-60", "customer@gamil.com");
        customer.setCustomerId(1);

        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        when(orderRepository.findAllByCustomer(Optional.of(customer))).thenReturn(allOrdersExpected);

        List<Order> theOrders = orderService.findAllByCustomer(1);

        assertEquals(allOrdersExpected.size(), theOrders.size());
        assertTrue(theOrders.containsAll(allOrdersExpected));
        verify(orderRepository, times(1)).findAllByCustomer(Optional.of(customer));
        verify(customerRepository, times(1)).findById(1);

    }

    @Test
    void testFindByCustomerIfCustomerNotExist() {
        Integer customerId = 1;
        Order order1 = new Order(customerId);
        Order order2 = new Order(customerId);
        List<Order> allOrdersExpected = Arrays.asList(order1, order2);

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () ->
                orderService.findAllByCustomer(customerId));

        verify(customerRepository, times(1)).findById(customerId);

    }

}