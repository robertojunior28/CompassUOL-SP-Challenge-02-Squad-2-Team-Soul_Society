package br.com.compassuol.pb.challenge.ecommerce.entities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class OrderTest {

    @Test
    public void testValidOrder(){
        Order order = new Order();
        order.setStatus("Status A");
        order.setCustomer("Customer A");
        order.setDate("2023-06-08");

        String status = order.getStatus();
        String customer = order.getCustomer();

        System.out.println(order.getId());

        assertEquals("Status A", status);

    }
}