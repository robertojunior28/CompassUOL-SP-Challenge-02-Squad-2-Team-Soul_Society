package br.com.compassuol.pb.challenge.ecommerce.services;

import br.com.compassuol.pb.challenge.ecommerce.entities.Product;
import br.com.compassuol.pb.challenge.ecommerce.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

@Service
@Transactional
@Component
public class ProductDaoService {




    private final ProductRepository productRepo;

    @Autowired
    public ProductDaoService(ProductRepository productRepo){
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

    public Product updateProduct(Integer id,Product updatedProduct){
        Product existingProduct = productRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found with ID: " + id));

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setDescription(updatedProduct.getDescription());

        return productRepo.save(existingProduct);
    }

    //public Product savebyId(Product product) {
     //   if (product.getProductId() == null){
         //   product.setProductId(++productsCount);
       //     products.add(product);
        //}else{
          //  for (int i = 0; i < products.size(); i ++) {
            //    if (products.get(i).getProductId().equals(product.getProductId())){
              //      products.set(i, product);
                //    return product;
                //}
            //}
            //throw new NoSuchElementException("Product not found with ID: " + product.getProductId());
        //}
        //return product;
    //}


    public void deleteById(int id) {
        productRepo.deleteById(id);
    }
}
