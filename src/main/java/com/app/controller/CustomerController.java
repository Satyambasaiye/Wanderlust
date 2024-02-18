package com.app.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CustomerDTO;
import com.app.service.CustomerService;


@RestController
@RequestMapping("/Customers")
@CrossOrigin(origins="http://localhost:3000")
public class CustomerController
{
	@Autowired
	private CustomerService custService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?>getCustomer(@PathVariable Long id)
	{
		return ResponseEntity.ok(custService.getCusstomer(id));
	}
	
	@PostMapping
	public ResponseEntity<?>addCustomer(@RequestBody @Valid CustomerDTO  dto)
	{
		System.out.println("in add customer");
		return ResponseEntity.status(HttpStatus.CREATED).body(custService.addNewCustomer(dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id)
	{
		return ResponseEntity.ok(custService.deleteById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable Long id,@RequestBody @Valid CustomerDTO dto)
	{
		return ResponseEntity.ok(custService.updateCustomer(id,dto));

	}
	
	@GetMapping
	public ResponseEntity<?> getAllcustomer(@RequestParam(defaultValue = "0",required = false)int pagenumber,
			@RequestParam (defaultValue = "3",required = false)int pageSize)
	{
		List<CustomerDTO>list=custService.getAllCustomer(pagenumber,pageSize);
		if(list.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.ok(list);
	}
	
	


}
