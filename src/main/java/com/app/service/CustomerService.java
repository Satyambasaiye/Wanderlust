package com.app.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.dao.CustomerDao;
import com.app.dto.CustomerDTO;
import com.app.dto.LoginDTO;
import com.app.entities.Customer;
import com.app.dto.AddressDTO;
import com.app.dto.ApiResponse;

public interface CustomerService {	
	
	CustomerDTO addNewCustomer(CustomerDTO cust);
	
	ApiResponse deleteById(Long Id);

	CustomerDTO updateCustomer(Long id, CustomerDTO dto);

	Customer getCusstomer(Long id);


	 List<CustomerDTO> getAllCustomer(int pagenumber, int pageSize) ;

	ApiResponse Login(@Valid LoginDTO dto);

}
