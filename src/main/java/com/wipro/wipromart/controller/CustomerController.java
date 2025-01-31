package com.wipro.wipromart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.wipromart.entity.Customer;
import com.wipro.wipromart.service.CustomerService;

import jakarta.validation.Valid;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	
	@PostMapping("/save")
	public  ResponseEntity<Customer> saveCustomer(@Valid @RequestBody Customer customer) {
		
		customerService.saveCustomer(customer);
		
		return new ResponseEntity<>(customer,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/get/{customerId}")
	public  ResponseEntity<Customer> fetchCustomerById(@PathVariable long customerId) {
		
		Customer customer =customerService.fetchCustomerById(customerId);
		
		return new ResponseEntity<>(customer,HttpStatus.OK);
		
	}
	
	@GetMapping("/get/all")
	public  List<Customer> fetchAllCustomers() {
		
		List<Customer> customers =customerService.getAllCustomers();
		
		return customers;
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer){	
		customer=customerService.updateCustomer(customer);	
	return new ResponseEntity<>(customer, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{customerId}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable long customerId){	
		customerService.deleteCustomer(customerId);	
	return new ResponseEntity<>(HttpStatus.OK);
	}
	
	

}
