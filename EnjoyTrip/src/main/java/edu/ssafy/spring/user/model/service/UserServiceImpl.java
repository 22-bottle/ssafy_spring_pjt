package edu.ssafy.spring.user.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ssafy.spring.user.dto.UserDto;
import edu.ssafy.spring.user.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{
	
	private UserMapper userMapper;
	
	@Autowired
	public UserServiceImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public boolean loginUser(UserDto userDto) {
		return userMapper.loginUser(userDto);
	}

	public int registUser(UserDto userDto) {
		return userMapper.registUser(userDto);
	}

	@Override
	public boolean findUser(UserDto userDto) {
		return userMapper.findUser(userDto);
	}

	@Override
	public UserDto getUser(String userId) {
		return userMapper.getUser(userId);
	}

	@Override
	public void updateUser(UserDto userDto) {
		userMapper.updateUser(userDto);
	}

	@Override
	public int deleteUser(String userId) {
		return userMapper.deleteUser(userId);		
	}

	@Override
	public String getSalt(String userId) {
		return userMapper.getSalt(userId);
	}
}
