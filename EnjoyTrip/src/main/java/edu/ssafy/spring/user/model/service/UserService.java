package edu.ssafy.spring.user.model.service;

import edu.ssafy.spring.user.dto.UserDto;

public interface UserService {
	
	boolean loginUser(UserDto userDto);
	int registUser(UserDto userDto);
	boolean findUser(UserDto userDto);
	UserDto getUser(String userId);
	String getSalt(String userId);
	void updateUser(UserDto userDto);
	int deleteUser(String userId);
	
}
