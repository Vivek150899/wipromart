package com.wipro.wipromart.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.wipro.wipromart.entity.Product;
import com.wipro.wipromart.service.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {


	    @InjectMocks
	    private ProductController productController;

	    @Mock
	    private ProductService productService;

	    @Test
	    public void testSaveProduct() {
	        
	        Product product = new Product();
	        product.setProductName("MyProduct");
	        product.setProductPrice(5000);
	        product.setMfd(LocalDate.of(2024, 1, 10));
	        product.setCategory("dummy");

	        Product savedProduct = new Product();
	        savedProduct.setProductName("MyProduct");
	        savedProduct.setProductPrice(5000);
	        savedProduct.setMfd(LocalDate.of(2024, 1, 10));
	        savedProduct.setCategory("dummy");

	        Mockito.when(productService.saveProduct(Mockito.any(Product.class))).thenReturn(savedProduct);

	        
	        ResponseEntity<Product> responseEntity = productController.addProduct(product);

	        
	        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	        assertThat(responseEntity.getBody()).isNotNull();
	        assertThat(responseEntity.getBody().getProductName()).isEqualTo("MyProduct");
	        assertThat(responseEntity.getBody().getProductPrice()).isEqualTo(5000);
	        assertThat(responseEntity.getBody().getMfd()).isEqualTo(LocalDate.of(2024, 1, 10));
	        assertThat(responseEntity.getBody().getCategory()).isEqualTo("dummy");
	}
	    
	    @Test
	    public void testFindAllProducts() {
	        // Arrange
	        Product product1 = new Product();
	        product1.setProductId(1L);
	        product1.setProductName("Product1");
	        product1.setProductPrice(100.0);
	        product1.setMfd(LocalDate.of(2024, 1, 1));
	        product1.setCategory("Category1");

	        Product product2 = new Product();
	        product2.setProductId(2L);
	        product2.setProductName("Product2");
	        product2.setProductPrice(200.0);
	        product2.setMfd(LocalDate.of(2024, 1, 2));
	        product2.setCategory("Category2");

	        List<Product> productList = Arrays.asList(product1, product2);

	        Mockito.when(productService.getAllProducts()).thenReturn(productList);

	        // Act
	        ResponseEntity<List<Product>> responseEntity = productController.fetchAllProducts();

	        // Assert
	        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	        assertThat(responseEntity.getBody()).isNotNull();
	        assertThat(responseEntity.getBody().size()).isEqualTo(2);

	        // Validate the first product
	        Product resultProduct1 = responseEntity.getBody().get(0);
	        assertThat(resultProduct1.getProductId()).isEqualTo(1L);
	        assertThat(resultProduct1.getProductName()).isEqualTo("Product1");
	        assertThat(resultProduct1.getProductPrice()).isEqualTo(100.0);
	        assertThat(resultProduct1.getMfd()).isEqualTo(LocalDate.of(2024, 1, 1));
	        assertThat(resultProduct1.getCategory()).isEqualTo("Category1");

	        // Validate the second product
	        Product resultProduct2 = responseEntity.getBody().get(1);
	        assertThat(resultProduct2.getProductId()).isEqualTo(2L);
	        assertThat(resultProduct2.getProductName()).isEqualTo("Product2");
	        assertThat(resultProduct2.getProductPrice()).isEqualTo(200.0);
	        assertThat(resultProduct2.getMfd()).isEqualTo(LocalDate.of(2024, 1, 2));
	        assertThat(resultProduct2.getCategory()).isEqualTo("Category2");
	    }

	    
	}