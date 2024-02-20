package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ApiException;
import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.UserDao;
import com.app.dto.ApiResponse;
import com.app.dto.LoginDTO;
import com.app.dto.UserDTO;
import com.app.entities.Customer;
import com.app.entities.User;

@Service
@Transactional
public class UserServiceImpl implements UserService
{
	@Autowired 
	private UserDao userRepo;
	
	@Autowired ModelMapper mapper;

	@Override
	public UserDTO addNewUser(UserDTO userDto) {
		if(userDto.getPassword().equals(userDto.getConfirmPassword()))
		{
			User user=mapper.map(userDto, User.class);
			User savedUser = userRepo.save(user);
			return mapper.map(savedUser, UserDTO.class);
					
		}
		throw new ApiException("password doesent matfch");
	}

	@Override
	public ApiResponse deleteById(Long Id) {
		User user = userRepo.findById(Id).orElseThrow(()->new ResourceNotFoundException(null));
		userRepo.delete(user);
		return new ApiResponse("user deleted with id "+Id);
	}

	@Override
	public UserDTO updateUser(Long id, UserDTO dto) 
	{
		if(dto.getPassword().equals(dto.getConfirmPassword()))
		{
		User user = userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException(null));
		mapper.map(dto, user);
		dto.setId(id);
		return dto;
		}
		throw new ApiException("password dont match");
	}

	@Override
	public User getUser(Long id) {
		return userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("invalid customer id"));
	}

	@Override
	public List<UserDTO> getAllUsers(int pagenumber, int pageSize) 
	{
		Pageable pageable = PageRequest.of(pagenumber, pageSize);
		List<User>UserList=userRepo.findAll(pageable).getContent();
		return UserList.stream().map(user->mapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public ApiResponse Login(@Valid LoginDTO dto) {
		User user= userRepo.findByEmail(dto.getEmail());
		if(user==null)
		{
			return  new ApiResponse("invalid login details");

		}
		if(user.getPassword().equals(dto.getPassword()))
		{
			return new ApiResponse("login success");
		}
			
		return  new ApiResponse("invalid login details");
	}
	
}
