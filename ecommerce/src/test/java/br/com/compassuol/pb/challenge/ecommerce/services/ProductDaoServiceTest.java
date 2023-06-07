package br.com.compassuol.pb.challenge.ecommerce.services;

import br.com.compassuol.pb.challenge.ecommerce.entities.Product;
import br.com.compassuol.pb.challenge.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductDaoServiceTest {

    @Mock
    private ProductRepository productRepo;

    @InjectMocks
    private ProductDaoService productDaoService;

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

        List<Product> theProducts = productDaoService.findAll();

        assertEquals(allProductsExpected.size(), theProducts.size());
        assertTrue(theProducts.containsAll(allProductsExpected));
        verify(productRepo, times(1)).findAll();
    }

    @Test
    void testSave(){
        Product product1 = new Product(1, "Product 1", BigDecimal.valueOf(10.0), "Description 1");
        when(productRepo.save(product1)).thenReturn(product1);

        Product productSaved = productDaoService.save(product1);

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

        Product foundedProduct = productDaoService.findById(id);

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

        assertThrows(NoSuchElementException.class, () -> productDaoService.findById(id));
        verify(productRepo, times(1)).findById(id);

    }
}