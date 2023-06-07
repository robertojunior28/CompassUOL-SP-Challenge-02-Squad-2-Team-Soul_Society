package br.com.compassuol.pb.challenge.ecommerce.entities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

 @DataJpaTest
 class CustomersTest {

     @Test
     public void ValidCustomer(){
         Customer customer = new Customer();
         customer.setName("Customer A");
         customer.setCpf("000.000.000-00");
         customer.setEmail("estagio@compass.com");
         customer.setActive(true);

         System.out.println(customer.getCustomerId());
     }
}
