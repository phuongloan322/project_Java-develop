package ait.team.java.service;

import ait.team.java.dto.UserDto;

public interface IUserService {
	public UserDto findByUserNo(String userName);
}
