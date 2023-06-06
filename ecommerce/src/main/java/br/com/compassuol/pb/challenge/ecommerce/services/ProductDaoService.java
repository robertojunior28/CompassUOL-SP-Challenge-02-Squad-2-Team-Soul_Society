package br.com.compassuol.pb.challenge.ecommerce.services;

import br.com.compassuol.pb.challenge.ecommerce.entities.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

@Component
public class ProductDaoService {

    private static List<Product> products = new ArrayList<>();

    private static int productsCount = 0;

    static {
        products.add(new Product(++productsCount, "Mussarela", new BigDecimal("8.99") ,"8,99 O kg de mussarela"));
        products.add(new Product(++productsCount, "Mussarela", new BigDecimal("8.99") ,"8,99 O kg de mussarela"));
        products.add(new Product(++productsCount, "Mussarela", new BigDecimal("8.99") ,"8,99 O kg de mussarela"));
    }

    public List<Product> findAll() {
        return products;
    }

    public Product save(Product product) {
        product.setProductId(++productsCount);
        products.add(product);
        return product;
    }

    public Product findById(Integer id){
        for (Product product : products){
            if (product.getProductId().equals(id)){
                return product;
            }
        }
        throw new NoSuchElementException("Product not found with ID: " + id);
    }

    public Product updateProduct(Integer id,Product updatedProduct){
        for (Product product : products){
            if (product.getProductId().equals(id)){
                product.setName(updatedProduct.getName());
                product.setPrice(updatedProduct.getPrice());
                product.setDescription(updatedProduct.getDescription());
                return product;
            }
        }
        throw new NoSuchElementException("Product not found with ID: " + id);
    }

    public Product save(Product product) {
        if (product.getProductId() == null){
            product.setProductId(++productsCount);
            products.add(product);
        }else{
            for (int i = 0; i < products.size(); i ++) {
                if (products.get(i).getProductId().equals(product.getProductId())){
                    products.set(i, product);
                    return product;
                }
            }
            throw new NoSuchElementException("Product not found with ID: " + product.getProductId());
        }
        return product;
    }


    public void deleteById(int id) {
        Predicate<? super Product> predicate = user -> user.getProductId().equals(id);
        products.removeIf(predicate);
    }
}
