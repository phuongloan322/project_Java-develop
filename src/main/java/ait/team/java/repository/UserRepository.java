package ait.team.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ait.team.java.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String>{

	UserEntity findByUserNo(String userName);
	List<UserEntity> findByGroupCode(String groupCode);
	UserEntity getOne(String screenInput);
}
