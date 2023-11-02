package com.ssafy.user.model.service;

import com.ssafy.user.dto.UserDto;
import com.ssafy.user.model.dao.UserDao;
import com.ssafy.user.model.dao.UserDaoImpl;

public class UserServiceImpl implements UserService{
	
	UserDao dao;
	
	public UserServiceImpl() {
		dao = new UserDaoImpl();
	}

	public boolean loginUser(UserDto userDto) {
		return dao.loginUser(userDto);
	}

	public int registUser(UserDto userDto) {
		return dao.registUser(userDto);
	}

	@Override
	public boolean findUser(UserDto userDto) {
		return dao.findUser(userDto);
	}

	@Override
	public UserDto getUser(String userId) {
		return dao.getUser(userId);
	}

	@Override
	public void updateUser(UserDto userDto) {
		dao.updateUser(userDto);
		
	}

	@Override
	public int deleteUser(String userId) {
		return dao.deleteUser(userId);		
	}

	@Override
	public String getSalt(String userId) {
		return dao.getSalt(userId);
	}
}
