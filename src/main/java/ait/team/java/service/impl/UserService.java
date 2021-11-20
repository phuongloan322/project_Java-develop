package ait.team.java.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ait.team.java.dto.UserDto;
import ait.team.java.entity.UserEntity;
import ait.team.java.repository.UserRepository;
import ait.team.java.service.IUserService;

@Service
public class UserService implements IUserService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ModelMapper modelMaper;
	
	@Override
	public UserDto findByUserNo(String userName) {
		UserEntity entity = userRepository.findByUserNo(userName);
		if(entity == null) {
			return null;
		}
		UserDto userDto =  modelMaper.map(entity, UserDto.class);
		return userDto;
	}

}
