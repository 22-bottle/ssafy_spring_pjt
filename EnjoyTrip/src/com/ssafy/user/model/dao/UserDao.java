package com.ssafy.user.model.dao;

import com.ssafy.user.dto.UserDto;

public interface UserDao {
	boolean loginUser(UserDto userDto);
	int registUser(UserDto userDto);
	boolean findUser(UserDto userDto);
	UserDto getUser(String userId);
	String getSalt(String userId);
	void updateUser(UserDto userDto);
	int deleteUser(String userId);
}
