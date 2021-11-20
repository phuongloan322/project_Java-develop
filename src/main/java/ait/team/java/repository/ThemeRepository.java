package ait.team.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ait.team.java.controller.input.InputSearchTheme;
import ait.team.java.entity.ThemeEntity;

public interface ThemeRepository extends JpaRepository<ThemeEntity, String> {
	@Query("SELECT theme FROM ThemeEntity theme WHERE 1=1"
			+ "	AND (:#{#input.themeNo} IS NULL OR theme.themeNo = :#{#input.themeNo})"
			+ " AND (:#{#input.themeName1} IS NULL OR theme.themeName1 = :#{#input.themeName1})"
			+ " AND (:#{#input.accountingGroupCode} IS NULL OR theme.accountingGroupCode = :#{#input.accountingGroupCode})"
			+ " AND (:#{#input.salesObjectCode} IS NULL OR theme.salesObjectCode = :#{#input.salesObjectCode})"
			+ " AND (:#{#input.soldFlg} IS NULL OR theme.soldFlg = :#{#input.soldFlg})")
	List<ThemeEntity> findTheme(@Param("input") InputSearchTheme input);
	
	@Query("SELECT count(theme) FROM ThemeEntity theme WHERE 1=1"
			+ "	AND (:#{#input.themeNo} IS NULL OR theme.themeNo = :#{#input.themeNo})"
			+ " AND (:#{#input.themeName1} IS NULL OR theme.themeName1 = :#{#input.themeName1})"
			+ " AND (:#{#input.accountingGroupCode} IS NULL OR theme.accountingGroupCode = :#{#input.accountingGroupCode})"
			+ " AND (:#{#input.salesObjectCode} IS NULL OR theme.salesObjectCode = :#{#input.salesObjectCode})"
			+ " AND (:#{#input.soldFlg} IS NULL OR theme.soldFlg = :#{#input.soldFlg})")
	int countTheme(@Param("input") InputSearchTheme input);
	
	ThemeEntity findByThemeNo(String themeNo);

}
