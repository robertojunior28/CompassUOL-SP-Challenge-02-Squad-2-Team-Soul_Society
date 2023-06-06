package br.com.compassuol.pb.challenge.ecommerce.entities;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import javax.xml.validation.Validator;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductTest {

    @Test
    public void testValidProduct() {
        Product product = new Product();
        product.setName("Product A");
        product.setPrice(new BigDecimal("9.99"));
        product.setDescription("Description A");

        System.out.println(product.getProductId());

    }
}