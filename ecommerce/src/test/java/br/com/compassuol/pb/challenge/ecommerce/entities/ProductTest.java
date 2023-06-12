package br.com.compassuol.pb.challenge.ecommerce.entities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class ProductTest {

    @Test
    public void testValidProduct() {
        Product product = new Product();
        product.setName("Product A");
        product.setPrice(new BigDecimal("9.99"));
        product.setDescription("Description A");

        System.out.println(product.getProductId());

        String name = product.getName();
        BigDecimal price = product.getPrice();
        String description = product.getDescription();

        assertEquals("Product A", name);
        assertEquals(9.99, price);
        assertEquals("Description A", description);
    }
}