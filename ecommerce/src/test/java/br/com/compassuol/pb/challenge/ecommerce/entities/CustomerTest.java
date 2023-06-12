package br.com.compassuol.pb.challenge.ecommerce.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerTest {

     @Test
     public void testValidCustomer(){
         var customer = new Customer();
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

     @Test
     public void testConstructor(){
         var customer = new Customer("Customer", "633.459.650-03", "customer@gmail.com");
         customer.setCustomerId(1);

         assertEquals(1, customer.getCustomerId());
         assertEquals("Customer", customer.getName());
         assertEquals("633.459.650-03", customer.getCpf());
         assertEquals("customer@gmail.com", customer.getEmail());
         assertEquals(true, customer.getActive());
     }
}
