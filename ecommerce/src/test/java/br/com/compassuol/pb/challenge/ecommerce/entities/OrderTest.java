package br.com.compassuol.pb.challenge.ecommerce.entities;

import br.com.compassuol.pb.challenge.ecommerce.enums.OrderStatus;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderTest {

    Order order = new Order();

    @Test
    void setId(){
        order.setId(2);
        assertEquals(2, order.getId());
    }

    @Test
    void setStatus() {
        order.setStatus(Collections.singletonList(OrderStatus.CREATED));
        assertEquals(Collections.singletonList(OrderStatus.CREATED), order.getStatus());
    }

    @Test
    void setCustomer() {
        Customer customer = new Customer("Customer", "633.459.650-03", "customer@gmail.com");
        order.setCustomer(customer);
        assertEquals(customer, order.getCustomer());
    }

    @Test
    void setDate(){
        order.setDate(new Date());
        assertEquals(new Date(), order.getDate());
    }

    @Test
    void setCustomerId(){
        order.setCustomerId(1);
        assertEquals(1, order.getCustomerId());
    }
    @Test
    void constructor(){
        Customer customer = new Customer("Customer", "633.459.650-03", "customer@gmail.com");
        Integer customerId = customer.getCustomerId();
        Order newOrder = new Order(customerId);
        newOrder.setCustomer(customer);

        assertEquals(customer.getCustomerId(), newOrder.getCustomer().getCustomerId());
    }
}