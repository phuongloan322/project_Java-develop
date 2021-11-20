package ait.team.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ait.team.java.entity.WorkContentsEntity;
import ait.team.java.entity.idclass.WorkContentId;

public interface WorkContentRepository  extends JpaRepository<WorkContentsEntity, WorkContentId> {

	/*
	 *@author: diennv 
	 */
	@Query(value = "SELECT * " +
				   "FROM m_work_contents "+
				   "ORDER BY work_contents_code", nativeQuery = true)
	List<WorkContentsEntity> findAllWorkContent();

	
	List<WorkContentsEntity> getByWorkContentsClass(String workContentClass);

	WorkContentsEntity findByWorkContentsClassAndWorkContentsCode(String workContentClass, String workContent);
	
	@Query(value = "SELECT wct.* "
			+ "FROM m_work_contents AS wct INNER JOIN m_theme AS t ON wct.work_contents_class = t.work_contents_class WHERE t.theme_no = ?1", nativeQuery = true)
	List<WorkContentsEntity> findAllWorkContentByTheme(String themeCode);
	List<WorkContentsEntity> findByWorkContentsClass(String workContentsClass); 
}
