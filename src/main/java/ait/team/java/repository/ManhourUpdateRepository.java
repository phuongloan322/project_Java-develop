package ait.team.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ait.team.java.entity.idclass.ManhourId;
import ait.team.java.entity.output.ManhourUpdateEntity;

public interface ManhourUpdateRepository extends JpaRepository<ManhourUpdateEntity, ManhourId>{
	@Query(value = "SELECT " + 
			"    m.*, u.user_name, t.theme_name1, w.work_contents_code_name " +
			"FROM" + 
			"    t_manhour m" + 
			"    INNER JOIN m_user u " + 
			"        ON m.user_no = u.user_no " + 
			"    INNER JOIN m_theme t " + 
			"        ON m.theme_no = t.theme_no " + 
			" 	 INNER JOIN m_work_contents w " +
			"	 	 ON m.work_contents_code = w.work_contents_code " +
			"		 AND m.work_contents_class = w.work_contents_class " +
			"WHERE" + 
			"    m.year = ?1 " + 
			"    AND m.month = ?2 " + 
			"    AND m.group_code = ?3 " + 
			"    AND m.user_no = ?4 " +
			"	 ORDER BY m.theme_no", nativeQuery = true)
	List<ManhourUpdateEntity> findAllManhour(int year, int month, String group_code, String user_no);
}
