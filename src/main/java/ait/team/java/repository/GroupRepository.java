package ait.team.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ait.team.java.entity.GroupEntity;

public interface GroupRepository extends JpaRepository<GroupEntity, String>{
	List<GroupEntity> findByGroupCode(String groupCode);

	GroupEntity getOne(String screenInput);
}
