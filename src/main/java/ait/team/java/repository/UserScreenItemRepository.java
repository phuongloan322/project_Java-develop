package ait.team.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ait.team.java.entity.UserScreenItemEntity;

public interface UserScreenItemRepository extends JpaRepository<UserScreenItemEntity, String> {

	UserScreenItemEntity findOneByUserNoAndScreenUrlAndScreenItem(String userNo, String screenUrl, String screenItem);

	List<UserScreenItemEntity> findByUserNoAndScreenUrl(String userNo, String screenUrl); 
	
	@Query(value = "SELECT * " + 
			"FROM m_user_screen_item " + 
			"WHERE " + 
				"user_no = ?1 " + 
				"and screen_url = ?2 " +
				"and screen_item = 'saveName'", nativeQuery = true)
	UserScreenItemEntity findLastSaveName(String userNo, String screenUrl);
	
	@Query(value = "SELECT * " + 
			"FROM m_user_screen_item " + 
			"WHERE " + 
				"user_no = ?1 " + 
				"and screen_url = ?2 " +
				"and save_name = ?3", nativeQuery = true)
	List<UserScreenItemEntity> findLastUserNoAndScreenUrl(String userNo, String screenUrl, String saveName);
	
	@Query(value = "SELECT * " + 
			"FROM m_user_screen_item " + 
			"WHERE " + 
				"user_no = ?1 " + 
				"and screen_url = ?2 " +
				"and screen_item = ?3", nativeQuery = true)

	List<UserScreenItemEntity> findByUserNoAndScreenUrlAndScreenItem(String userNo, String screenUrl, String string);

	List<UserScreenItemEntity> findByUserNoAndScreenUrlAndScreenItemAndSaveName(String userNo, String screenUrl,
			String string, String saveName);	@Modifying()
	@Query(value = "DELETE FROM m_user_screen_item" + 
			"  WHERE user_no = ?1 AND screen_url = ?2 AND save_name = ?3 AND screen_item NOT IN ?4", nativeQuery = true)
	void removeItem(String userNo, String screenUrl, String saveName,List<String> removeItem);

	@Query(value = "SELECT save_name" + 
			" FROM" + 
			" public.m_user_screen_item" + 
			" WHERE" + 
			"    user_no = ?1" + 
			"    AND screen_url = ?2 AND save_name != ''" + 
			"    GROUP BY save_name", nativeQuery = true)
	List<String> getSaveName(String userNo, String screenUrl);

	@Query(value = "DELETE FROM m_user_screen_item" + 
			"  WHERE user_no = ?2 AND screen_url = 'ManhourReport' AND save_name = ?1", nativeQuery = true)
	boolean deleteBySaveName(String saveName, String userNo);
	
}
