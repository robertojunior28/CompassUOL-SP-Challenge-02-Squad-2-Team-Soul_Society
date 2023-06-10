package br.com.compassuol.pb.challenge.ecommerce.services;

import br.com.compassuol.pb.challenge.ecommerce.entities.Customer;
import br.com.compassuol.pb.challenge.ecommerce.entities.Product;
import br.com.compassuol.pb.challenge.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepo;

    @Autowired
    public ProductService(ProductRepository productRepo){
        this.productRepo = productRepo;
    }

    private static int productsCount = 0;

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public Product save(Product product) {
        return productRepo.save(product);
    }

    public Product findById(Integer id){
        return productRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found with ID: " + id));
    }

    public Product updateById(Integer id, Product product) {

        Optional<Product> optional = productRepo.findById(id);

        if (optional.isPresent()) {
            Product updatedProduct = optional.get();
            if (product.getName() != null) {
                updatedProduct.setName(product.getName());
            }
            if (product.getPrice() != null) {
                updatedProduct.setPrice(product.getPrice());
            }
            if (product.getDescription() != null) {
                updatedProduct.setDescription(product.getDescription());
            }


            return productRepo.save(updatedProduct);
        }
        return null;
    }


    public void deleteById(int id) {
        productRepo.deleteById(id);
    }
}
