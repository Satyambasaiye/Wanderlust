package com.app.service;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.dao.CustomerDao;
import com.app.dto.CustomerDTO;
import com.app.dto.AddressDTO;

public interface CustomerService {	
	
	CustomerDTO addNewCustomer(CustomerDTO cust);
	

}
