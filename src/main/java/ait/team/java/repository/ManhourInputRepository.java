package ait.team.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ait.team.java.entity.idclass.ManhourId;
import ait.team.java.entity.input.ManHourInputEntity;

public interface ManhourInputRepository extends JpaRepository<ManHourInputEntity, ManhourId>{
	/*
	 *@author: diennv 
	 */
	@Query(value = "SELECT t1.*, t2.theme_name1, t3.work_contents_code_name, t4.work_contents_class_name " +
				   "FROM t_manhour t1 " + 
					   "INNER JOIN m_theme t2 ON t1.theme_no = t2.theme_no " + 
					   "INNER JOIN m_work_contents t3 ON t1.work_contents_class = t3.work_contents_class " +
					   								 "AND t1.work_contents_code = t3.work_contents_code " +
					   "INNER JOIN m_work_contents_class t4 ON t4.work_contents_class = t1.work_contents_class " +				  
				   "WHERE t1.year = :year " + 
					   "AND t1.month = :month " + 
					   "AND t1.user_no = :userNo "+
					"ORDER BY " + 
						"  t2.theme_no", nativeQuery = true)
	List<ManHourInputEntity> findAllManhourInput(@Param("year") int year, @Param("month") int month, @Param("userNo") String userNo);

}

