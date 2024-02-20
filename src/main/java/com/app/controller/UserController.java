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

import com.app.dto.LoginDTO;
import com.app.dto.UserDTO;
import com.app.service.UserService;


@RestController
@RequestMapping("/Users")
@CrossOrigin(origins="http://localhost:3000")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("{id}")
	public ResponseEntity<?> getUser(@PathVariable Long id)
	{
		return ResponseEntity.ok(userService.getUser(id));
	}
	
	@PostMapping("/login")
	public ResponseEntity<?>login(@RequestBody @Valid LoginDTO dto){
		return ResponseEntity.status(401).body(userService.Login(dto));
		
	}
	
	@PostMapping
	public ResponseEntity<?> addUser(@RequestBody @Valid UserDTO dto)
	{
		return ResponseEntity.ok(userService.addNewUser(dto));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody @Valid UserDTO dto)
	{
		return ResponseEntity.ok(userService.updateUser(id,dto));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id)
	{
		return ResponseEntity.ok(userService.deleteById(id));
	}

	
	@GetMapping
	public ResponseEntity<?> getAllUser(@RequestParam(defaultValue = "0",required = false)int pagenumber,
			@RequestParam (defaultValue = "3",required = false)int pageSize)
	{
		List<UserDTO>list=userService.getAllUsers(pagenumber, pageSize);
		if(list.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.ok(list);
	}
}
