
package ait.team.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ait.team.java.entity.ManhourEntity;
import ait.team.java.entity.idclass.ManhourId;

public interface ManhourRepository extends JpaRepository<ManhourEntity,ManhourId>{

	
	@Query(value = "SELECT *" + 
			" FROM"+ 
			"    t_manhour" + 
			" WHERE" + 
			"    year = ?1"+ 
			"    AND month = ?2" + 
			"    AND user_no = ?3" + 
			"    AND theme_no = ?4" + 
			"    AND work_contents_class = ?5" + 
			"    AND work_contents_code = ?6" + 
			"    AND work_contents_detail = ?7 ", nativeQuery = true)
	ManhourEntity findOneManhourUpdate(int year, int month, String userNo, String themeNo, String workContentsClass, String workContentsCode,String workContentsDetail);
	
	
	@Query(value="SELECT" + 
			" *" + 
			" FROM" + 
			" t_manhour" + 
			" WHERE" + 
			" year = ?2" + 
			" AND month = ?3" + 
			" AND user_no = ?1 ", nativeQuery = true)
	List<ManhourEntity> findByUserNoAndYearAndMonth(String userName, short year, short month); 


	@Query(value="SELECT" + 
			"    *    " + 
			"FROM" + 
			"    public.t_manhour	" + 
			"WHERE" + 
			"    year = ?1" + 
			"    AND month = ?2" + 
			"    AND (theme_no = ?3" + 
			"    AND work_contents_code IN ?4" + 
			"    AND (work_contents_detail = ?5 OR '' = ?7) OR ?3 = '')" + 
			"    AND group_code = ?6" + 
			"    AND user_no IN ?7 ORDER BY (year, month)", nativeQuery = true)
	List<ManhourEntity> searchReport(int year, int month, String themeCode, List<String> workContentCode,
			String workContentDetail, String groupCode, List<String> userList);
	
}
