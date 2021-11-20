package ait.team.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ait.team.java.entity.SalesObjectEntity;

public interface SaleObjectRepository extends JpaRepository<SalesObjectEntity, String> {
}
