package ait.team.java.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ait.team.java.entity.CalendarEntity;
import ait.team.java.entity.idclass.CalendarId;

public interface CalendarReponsitory extends JpaRepository<CalendarEntity, CalendarId>{
	
	List<CalendarEntity> findBySiteCodeAndDateBetween(String siteCode, Timestamp firstTime, Timestamp lastTime);
	
}
