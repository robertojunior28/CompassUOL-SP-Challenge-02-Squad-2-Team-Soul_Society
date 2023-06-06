package br.com.compassuol.pb.challenge.ecommerce.services;

import br.com.compassuol.pb.challenge.ecommerce.entities.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDaoService {

    private static List<Product> products = new ArrayList<>();

    private static int productsCount = 0;

    static {
        products.add(new Product(++productsCount, "Mussarela", new BigDecimal("8.99") ,"8,99 O kg de mussarela"));
    }

    public List<Product> findAll() {
        return products;
    }
}
