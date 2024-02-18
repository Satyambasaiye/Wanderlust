package com.app.service;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.dao.CustomerDao;
import com.app.dto.AddCustomerDTO;
import com.app.entities.Customer;

public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private ModelMapper mapper;
	@Override
	public AddCustomerDTO addNewCustomer(AddCustomerDTO cust) 
	{
		Customer customer=mapper.map(cust, Customer.class);
		return null;
	}
	
	 
	
	

}
