package br.com.compassuol.pb.challenge.ecommerce.entities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class OrderTest {

    Order order = new Order();

    @Test
    void setId(){
        order.setId(2);
        assertEquals(2, order.getId());
    }

//    @Test
//    void setStatus() {
//        order.setStatus();
//        assertEquals(, order.getStatus());
//    }
//
//    @Test
//    void setCustomer() {
//        order.setCustomer();
//        assertEquals(, order.getCustomer());
//    }
//
//    @Test
//    void setDate(){
//        order.setDate(new Date());
//        assertEquals(new Date(), order.getDate());
//    }
}