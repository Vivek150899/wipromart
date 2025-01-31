package com.wipro.wipromart.service;

import java.util.List;

import com.wipro.wipromart.entity.Customer;

public interface CustomerService {
	
	Customer saveCustomer( Customer customer);
	
	Customer fetchCustomerById(long customerId);
	
	List<Customer> getAllCustomers();
	
	Customer updateCustomer (Customer customer);
	
	void deleteCustomer(long customerId);

}
