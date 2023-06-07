package br.com.compassuol.pb.challenge.ecommerce.entities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

 @DataJpaTest
 class CustomersTest {

     @Test
     public void ValidCustomer(){
         Customer customer = new Customer();
         customer.setName();
         customer.setCpf();
         customer.setEmail();
         customer.setActive();
     }
}
