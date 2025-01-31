package com.wipro.wipromart.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.wipro.wipromart.entity.Product;
import com.wipro.wipromart.exception.ResourceNotFoundException;
import com.wipro.wipromart.repository.ProductRepository;

@SpringBootTest
public class ProductServiceTest {
	
	@InjectMocks
	private ProductService productService=new ProductServiceImpl();
	
	@Mock
	private ProductRepository productRepository;
	
	
	
	@Test
	void testGetProductById() {
		
		Product product = new Product();
		
		product.setProductId(200);
		product.setProductName("MyProduct");
		product.setProductPrice(5000);
		product.setMfd(LocalDate.of(2024,12, 24));
		product.setCategory("dummy");
		
		Optional<Product> optionalProduct=Optional.of(product);
		
		when(productRepository.findById(200L)).thenReturn(optionalProduct);
		
		Product actualProduct=productService.getProductById(200);
		
		assertEquals("MyProduct", actualProduct.getProductName());
		assertEquals(5000, actualProduct.getProductPrice());
		
	}
	
	@Test
	void testGetProductByIdWithException() {	
		when(productRepository.findById(200L)).thenThrow(ResourceNotFoundException.class);
		
		assertThrows(ResourceNotFoundException.class,()->productService.getProductById(200));
		
  }
	
	@Test
	void testSaveProduct() {
		
        Product product = new Product();
		
		product.setProductId(200);
		product.setProductName("MyProduct");
		product.setProductPrice(5000);
		product.setMfd(LocalDate.of(2024,12, 24));
		product.setCategory("dummy");
		
		when(productRepository.save(product)).thenReturn(product);
		
		Product newProduct=productService.saveProduct(product);
		
		assertEquals(200, newProduct.getProductId());
		assertEquals("MyProduct", newProduct.getProductName());
		assertEquals(5000, newProduct.getProductPrice());
		assertEquals(5000, newProduct.getProductPrice());
		assertEquals("2024-12-24", newProduct.getMfd().toString());
		
	    }
	
	    @Test
	    void testGetAllProducts() {
		
        Product product1 = new Product();
		
		product1.setProductId(200);
		product1.setProductName("MyProduct1");
		product1.setProductPrice(5000);
		product1.setMfd(LocalDate.of(2022,12, 24));
		product1.setCategory("dummy1");
		
        Product product2 = new Product();
		
		product2.setProductId(200);
		product2.setProductName("MyProduct2");
		product2.setProductPrice(6000);
		product2.setMfd(LocalDate.of(2023,12, 24));
		product2.setCategory("dummy2");
		
       Product product3 = new Product();
		
		product3.setProductId(400);
		product3.setProductName("MyProduct3");
		product3.setProductPrice(7000);
		product3.setMfd(LocalDate.of(2024,12, 24));
		product3.setCategory("dummy3");
		
		List <Product> myProducts=new ArrayList<>();
		myProducts.add(product1);
		myProducts.add(product2);
		myProducts.add(product3);
		
		when(productRepository.findAll()).thenReturn(myProducts);
		
		List<Product> productList=productService.getAllProducts();
		
		assertEquals(myProducts.size(),productList.size());
	}
	    @Test
	    void testDeleteProduct() {
	    	
	    	Product product = new Product();
			
			product.setProductId(200);
			product.setProductName("MyProduct");
			product.setProductPrice(5000);
			product.setMfd(LocalDate.of(2024,12, 24));
			product.setCategory("dummy");
			
			Optional<Product> optionalProduct=Optional.of(product);
			
			when(productRepository.findById(200L)).thenReturn(optionalProduct);
			
			//when(productRepository.delete(optionalProduct.get()))
			
			doNothing().when(productRepository).delete(optionalProduct.get());
			
			productService.deleteProduct(200); //return type is void 
			
			verify(productRepository,times(1)).findById(200L);
			
			verify(productRepository,times(1)).delete(product);
	    	
	    }

}
