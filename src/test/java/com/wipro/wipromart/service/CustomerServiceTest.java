package com.wipro.wipromart.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.wipro.wipromart.entity.Customer;
import com.wipro.wipromart.exception.ResourceNotFoundException;
import com.wipro.wipromart.repository.CustomerRepository;

@SpringBootTest
public class CustomerServiceTest {
	
	@InjectMocks
	private CustomerService customerService=new CustomerServiceImpl();
	
	@Mock
	private CustomerRepository customerRepository;
	
	@Test
	void getCustomerById() {
		
		Customer customer =new Customer();
		customer.setCustomerId(20L);
		customer.setFirstName("Achal");
		customer.setLastName("Shukla");
		customer.setEmail("Achal@gmail.com");
		customer.setMobile("9574829473");
		customer.setCity("Lucknow");
		
		Optional <Customer> optionalCustomer=Optional.of(customer);
		
		when(customerRepository.findById(20L)).thenReturn(optionalCustomer);
		
        Customer actualCustomer=customerService.fetchCustomerById(20);
		
		assertEquals(20, actualCustomer.getCustomerId());
		assertEquals("Achal", actualCustomer.getFirstName());
		assertEquals("Shukla", actualCustomer.getLastName());
		assertEquals("Achal@gmail.com", actualCustomer.getEmail());
		assertEquals("9574829473", actualCustomer.getMobile());
		assertEquals("Lucknow", actualCustomer.getCity());
		
	}
	
	@Test
	void testGetProductByIdWithException() {	
		when(customerRepository.findById(200L)).thenThrow(ResourceNotFoundException.class);
		
		assertThrows(ResourceNotFoundException.class,()->customerService.fetchCustomerById(200));
		
  }
	
	@Test
	void testSaveProduct() {
		
		Customer customer =new Customer();
		customer.setCustomerId(20L);
		customer.setFirstName("Achal");
		customer.setLastName("Shukla");
		customer.setEmail("Achal@gmail.com");
		customer.setMobile("9574829473");
		customer.setCity("Lucknow");
		
		when(customerRepository.save(customer)).thenReturn(customer);
		
		Customer newCustomer=customerRepository.save(customer);
		
		assertEquals(20L, newCustomer.getCustomerId());
		assertEquals("Achal", newCustomer.getFirstName());
		assertEquals("Shukla", newCustomer.getLastName());
		assertEquals("Achal@gmail.com", newCustomer.getEmail());
		assertEquals("9574829473", newCustomer.getMobile());
		assertEquals("Lucknow", newCustomer.getCity());
		
	    }
	
	    @Test
	    void testGetAllProducts() {
		
	    	Customer customer1 =new Customer();
			customer1.setCustomerId(20L);
			customer1.setFirstName("Achal");
			customer1.setLastName("Shukla");
			customer1.setEmail("Achal@gmail.com");
			customer1.setMobile("9574829473");
			customer1.setCity("Lucknow");
		
			Customer customer2 =new Customer();
			customer2.setCustomerId(30L);
			customer2.setFirstName("Aman");
			customer2.setLastName("Shukla");
			customer2.setEmail("Aman@gmail.com");
			customer2.setMobile("9575936384");
			customer2.setCity("Delhi");
		
			Customer customer3 =new Customer();
			customer3.setCustomerId(40L);
			customer3.setFirstName("Abhishek");
			customer3.setLastName("Shukla");
			customer3.setEmail("Abhishek@gmail.com");
			customer3.setMobile("9527936384");
			customer3.setCity("Kanpur");
		
		List <Customer> myCustomers=new ArrayList<>();
		myCustomers.add(customer1);
		myCustomers.add(customer2);
		myCustomers.add(customer3);
		
		when(customerRepository.findAll()).thenReturn(myCustomers);
		
		List<Customer> customerList=customerService.getAllCustomers();
		
		assertEquals(myCustomers.size(),customerList.size());
	}
	    
	    
	    @Test
	    void testDeleteProduct() {
	    	
	    	Customer customer =new Customer();
			customer.setCustomerId(20L);
			customer.setFirstName("Achal");
			customer.setLastName("Shukla");
			customer.setEmail("Achal@gmail.com");
			customer.setMobile("9574829473");
			customer.setCity("Lucknow");
			
			Optional<Customer> optionalCustomer=Optional.of(customer);
			
			when(customerRepository.findById(200L)).thenReturn(optionalCustomer);
			
			//when(productRepository.delete(optionalProduct.get()))
			
			doNothing().when(customerRepository).delete(optionalCustomer.get());
			
			customerService.deleteCustomer(200); //return type is void 
			
			verify(customerRepository,times(1)).findById(200L);
			
			verify(customerRepository,times(1)).delete(customer);
	    	
	    }
	
	
	

}
