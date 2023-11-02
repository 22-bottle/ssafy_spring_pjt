package edu.ssafy.spring.user.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import edu.ssafy.spring.user.dto.UserDto;

@Mapper
public interface UserMapper {
	
	boolean loginUser(UserDto userDto);
	int registUser(UserDto userDto);
	boolean findUser(UserDto userDto);
	UserDto getUser(String userId);
	String getSalt(String userId);
	void updateUser(UserDto userDto);
	int deleteUser(String userId);
	
}
