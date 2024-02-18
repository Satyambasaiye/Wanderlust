package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.dao.UserDao;

public class UserServiceImpl implements UserService
{
	@Autowired 
	private UserDao userDao;
	
//	@Autowired

}
