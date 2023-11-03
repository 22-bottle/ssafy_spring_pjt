package edu.ssafy.spring.user.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ssafy.spring.user.dto.UserDto;
import edu.ssafy.spring.user.model.mapper.UserMapper;
import edu.ssafy.spring.util.Encrypt;

@Service
public class UserServiceImpl implements UserService{
	
	private UserMapper userMapper;
	
	private Encrypt encrypt;
	
	@Autowired
	public UserServiceImpl(UserMapper userMapper, Encrypt encrypt) {
		this.userMapper = userMapper;
		this.encrypt = encrypt;
	}

	public boolean loginUser(UserDto userDto) {
		// 암호화로 확인
		String userPw = userDto.getUserPassword();
		userDto = userMapper.loginUser(userDto);
		if(userDto.getUserPassword().equals(encrypt.getEncrypt(userPw, userDto.getSalt()))) {
			return true;
		}
		return false;
	}

	public int registUser(UserDto userDto) {
		// 암호화
		String salt = encrypt.getSalt();
		userDto.setUserPassword(encrypt.getEncrypt(userDto.getUserPassword(), salt));
		userDto.setSalt(salt);
		
		return userMapper.registUser(userDto);
	}

	@Override
	public boolean findUser(UserDto userDto) {
		return userMapper.findUser(userDto);
	}

	@Override
	public UserDto getUser(String userId) {
		UserDto dto = userMapper.getUser(userId);
		return dto;
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
