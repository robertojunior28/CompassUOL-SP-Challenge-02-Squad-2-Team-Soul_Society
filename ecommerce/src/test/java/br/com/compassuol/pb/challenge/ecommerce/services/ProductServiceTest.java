package br.com.compassuol.pb.challenge.ecommerce.services;

import br.com.compassuol.pb.challenge.ecommerce.entities.Product;
import br.com.compassuol.pb.challenge.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepo;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll(){
        Product product1 = new Product(1, "Product 1", BigDecimal.valueOf(10.0), "Description 1");
        Product product2 = new Product(2, "Product 2", BigDecimal.valueOf(15.0), "Description 2");
        List<Product> allProductsExpected = Arrays.asList(product1, product2);

        when(productRepo.findAll()).thenReturn(allProductsExpected);

        List<Product> theProducts = productService.findAll();

        assertEquals(allProductsExpected.size(), theProducts.size());
        assertTrue(theProducts.containsAll(allProductsExpected));
        verify(productRepo, times(1)).findAll();
    }

    @Test
    void testSave(){
        Product product1 = new Product(1, "Product 1", BigDecimal.valueOf(10.0), "Description 1");
        when(productRepo.save(product1)).thenReturn(product1);

        Product productSaved = productService.save(product1);

        assertNotNull(productSaved);
        assertEquals(product1.getName(), productSaved.getName());
        assertEquals(product1.getPrice(), productSaved.getPrice());
        assertEquals(product1.getDescription(), productSaved.getDescription());
        verify(productRepo, times(1)).save(product1);

    }

    @Test
    void testFindByIDIfIdExist(){
        Product product1 = new Product(1, "Product 1", BigDecimal.valueOf(10.0), "Description 1");
        Integer id = product1.getProductId();

        when(productRepo.findById(id)).thenReturn(Optional.of(product1));

        Product foundedProduct = productService.findById(id);

        assertNotNull(foundedProduct);
        assertEquals(id, foundedProduct.getProductId());
        assertEquals(product1.getName(), foundedProduct.getName());
        assertEquals(product1.getPrice(), foundedProduct.getPrice());
        assertEquals(product1.getDescription(), foundedProduct.getDescription());
        verify(productRepo, times(1)).findById(id);
    }

    @Test
    void testFindByIdIfIdNonExist(){
        Integer id = 444;
        when(productRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> productService.findById(id));
        verify(productRepo, times(1)).findById(id);

    }

    @Test
    void testUpdateProduct_ExistingId(){
        Integer id = 454;
        Product existingProduct = new Product(id, "Product one", BigDecimal.valueOf(10.0), "Cool Description");
        Product updatedProduct = new Product(id, "Product new", BigDecimal.valueOf(20.0), "Cool Description");

        when(productRepo.findById(id)).thenReturn(Optional.of(existingProduct));
        when(productRepo.save(existingProduct)).thenReturn(existingProduct);


        Product result = productService.updateById(id, updatedProduct);


        assertNotNull(result);
        assertEquals(id, result.getProductId());
        assertEquals(updatedProduct.getName(), result.getName());
        assertEquals(updatedProduct.getPrice(), result.getPrice());
        assertEquals(updatedProduct.getDescription(), result.getDescription());
        verify(productRepo, times(1)).findById(id);
        verify(productRepo, times(1)).save(existingProduct);
    }

    @Test
    void testUpdateProduct_NonexistentId() {

        Integer id = 450;
        Product updatedProduct = new Product(id, "new Product", BigDecimal.valueOf(20.0), "new Description");

        when(productRepo.findById(id)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> productService.updateById(id, updatedProduct));
        assertEquals("Product not found with ID: " + id, exception.getMessage());
        verify(productRepo, times(1)).findById(id);
        verify(productRepo, never()).save(any(Product.class));
    }

    @Test
    void testDeleteById() {

        int id = 1;
        productService.deleteById(id);
        verify(productRepo, times(1)).deleteById(id);
    }
}