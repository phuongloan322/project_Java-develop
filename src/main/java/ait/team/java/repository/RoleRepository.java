package ait.team.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ait.team.java.entity.Role;
import ait.team.java.entity.idclass.RoleId;

public interface RoleRepository extends JpaRepository<Role, RoleId> {
	Role findByRoleCodeAndScreenUrl(String roleCode, String screenUrl);
}
