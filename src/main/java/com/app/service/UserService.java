package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.UserDTO;
import com.app.entities.User;

public interface UserService {
	
	UserDTO addNewUser(UserDTO user);
	
	ApiResponse deleteById(Long Id);

	UserDTO updateUser(Long id, UserDTO dto);

	User getUser(Long id);

	List<UserDTO> getAllUsers(int pagenumber, int pageSize) ;


}
