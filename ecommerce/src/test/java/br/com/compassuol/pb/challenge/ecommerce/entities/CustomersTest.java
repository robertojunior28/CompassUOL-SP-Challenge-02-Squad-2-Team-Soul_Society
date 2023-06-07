package br.com.compassuol.pb.challenge.ecommerce.entities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
 class CustomersTest {

     @Test
     public void testValidCustomer(){
         Customer customer = new Customer();
         customer.setName("Customer A");
         customer.setCpf("000.000.000-00");
         customer.setEmail("estagio@compass.com");
         customer.setActive(true);

         String name = customer.getName();
         String cpf = customer.getCpf();
         String email = customer.getEmail();
         boolean active = customer.getActive();

         System.out.println(customer.getCustomerId());

         assertEquals("Customer A", name);
         assertEquals("000.000.000-00", cpf);
         assertEquals("estagio@compass.com", email);
         assertTrue(active);
     }
}
