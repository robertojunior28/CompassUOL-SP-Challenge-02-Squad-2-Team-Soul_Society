package br.com.compassuol.pb.challenge.ecommerce.entities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ProductTest {

    @Test
    public void testValidProduct() {
        Product product = new Product();
        product.setProductId(1);
        product.setName("Product A");
        product.setPrice(new BigDecimal("9.99"));
        product.setDescription("Description A");

        Integer id = product.getProductId();
        String name = product.getName();
        BigDecimal price = product.getPrice();
        String description = product.getDescription();


        System.out.println(product.getProductId());


        assertEquals(1, id);
        assertEquals("Product A", name);
        assertEquals(new BigDecimal("9.99"), price);
        assertEquals("Description A", description);
    }

    @Test
    public void testConstructor() {
        Product product = new Product(1, "Product", BigDecimal.valueOf(10.0), "Description");


        assertEquals(1, product.getProductId());
        assertEquals("Product", product.getName());
        assertEquals(BigDecimal.valueOf(10.0), product.getPrice());
        assertEquals("Description", product.getDescription());
    }
}