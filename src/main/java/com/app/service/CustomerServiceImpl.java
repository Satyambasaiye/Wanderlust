package com.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.custom_exceptions.ApiException;
import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.CustomerDao;
import com.app.dto.ApiResponse;
import com.app.dto.CustomerDTO;
import com.app.dto.LoginDTO;
import com.app.entities.Customer;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDao customerRepo;

	@Autowired
	private ModelMapper mapper;
	
	@Override
	public CustomerDTO addNewCustomer(CustomerDTO cust) 
	{
		if(cust.getPassword().equals(cust.getConfirmPassword()))
		{
			Customer custEntity=mapper.map(cust, Customer.class);
			Customer savedEntity=customerRepo.save(custEntity);
			System.out.println("cust saved id"+custEntity.getId()+" "+savedEntity.getId());
			return mapper.map(savedEntity,CustomerDTO.class);
					
		}
		throw new ApiException("password doesent match");
	}
	
	@Override
	public ApiResponse deleteById(Long Id) {
		
		Customer cust = customerRepo.findById(Id).orElseThrow(()->new ResourceNotFoundException("Invalid customer id"));
		
			customerRepo.delete(cust);
			
		return new ApiResponse("customer detail of customer with id "+cust.getId()+"deleted...");
	}
	
	@Override
	public CustomerDTO updateCustomer(Long id, CustomerDTO dto) {
		if(dto.getPassword().equals(dto.getConfirmPassword()))
		{
			Customer cust=customerRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("invalid customer id"));

					mapper.map(dto, cust);
					System.out.println("after mapping " + cust);
					
					dto.setId(id);
				return dto;

		}
		throw new ApiException("password dont match");
		
	}
	
	@Override
	public Customer getCusstomer(Long id)
	{
		return customerRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("invalid customer id"));
	}
	
	@Override
	public List<CustomerDTO> getAllCustomer(int pagenumber, int pageSize) {
		Pageable pageable = PageRequest.of(pagenumber, pageSize);
		List<Customer>custList=customerRepo.findAll(pageable).getContent();
		
		return custList.stream().map(cust->mapper.map(cust, CustomerDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public ApiResponse Login(@Valid LoginDTO dto) {
		Customer cust= customerRepo.findByEmail(dto.getEmail());
		if(cust==null)
		{
			return  new ApiResponse("invalid login details");

		}
		if(cust.getPassword().equals(dto.getPassword()))
		{
			return new ApiResponse("login success");
		}
		
		
		return  new ApiResponse("invalid login details");
	}
	

}
