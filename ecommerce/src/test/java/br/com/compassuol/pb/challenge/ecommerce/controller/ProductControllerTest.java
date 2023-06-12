package br.com.compassuol.pb.challenge.ecommerce.controller;

import br.com.compassuol.pb.challenge.ecommerce.entities.Product;
import br.com.compassuol.pb.challenge.ecommerce.exceptions.ProductNotFoundException;
import br.com.compassuol.pb.challenge.ecommerce.repository.ProductRepository;
import br.com.compassuol.pb.challenge.ecommerce.services.ProductService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testRetrieveAllProducts() {

        List<Product> productList = List.of(
                new Product(1, "Product 1", BigDecimal.valueOf(10.0), "Description 1"),
                new Product(2, "Product 2", BigDecimal.valueOf(15.0), "Description 2")
        );
        given(productService.findAll()).willReturn(productList);


        List<Product> retrievedProducts = productController.retrieveAllProducts();


        assertThat(retrievedProducts).isEqualTo(productList);
        verify(productService, times(1)).findAll();
    }

    @Test
    void testRetrieveProductById_ExistingId() {

        Integer id = 1;
        Product product = new Product(id, "Product 1", BigDecimal.valueOf(10.0), "Description 1");
        given(productRepository.findById(id)).willReturn(Optional.of(product));


        EntityModel<Product> entityModel = productController.retrieveProductById(id);


        assertThat(entityModel.getContent()).isEqualTo(product);

        verify(productRepository, times(1)).findById(id);
    }



    @Test
    void testUpdateProduct() {

        Integer id = 1;
        Product product = new Product(id, "Product 1", BigDecimal.valueOf(10.0), "Description 1");
        given(productService.updateById(eq(id), any(Product.class))).willReturn(product);


        Product updatedProduct = productController.updateProduct(id, product);


        assertThat(updatedProduct).isEqualTo(product);
        verify(productService, times(1)).updateById(id, product);
    }

    @Test
    void testDeleteProduct() {

        Integer id = 1;


        productController.deleteProduct(id);


        verify(productService, times(1)).deleteById(id);
    }

    @Test
    public void testCreateProduct() throws Exception {

        Product savedProduct = new Product();
        savedProduct.setProductId(1);
        when(productService.save(any(Product.class))).thenReturn(savedProduct);


        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Product 1\",\"price\":10.0}"))
                .andExpect(status().isCreated());
    }
}
