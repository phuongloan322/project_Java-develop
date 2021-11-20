package ait.team.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ait.team.java.entity.output.ContingData;

public interface ContingDataOutputRepositoty extends JpaRepository<ContingData,Integer>{
	@Query(value="SELECT ROW_NUMBER() OVER () AS id," + 
			"		CONCAT(tm.YEAR, lpad(CAST(tm.MONTH AS VARCHAR), 2, '0')),																																\r\n" + 
			"		tm.GROUP_CODE," + 
			"		mg.GROUP_NAME," + 
			"		tm.USER_NO," + 
			"		mu.USER_NAME," + 
			"		tm.THEME_NO," + 
			"		mt.THEME_NAME1," + 
			"		mwc.SUBTOTAL_CODE," + 
			"       sum(tm.TOTAL) as TOTAL," + 
			"       mt.SALES_OBJECT_CODE," + 
			"	    mt.SALES_DATE," + 
			"    	mt.ORDER_AMOUNT," + 
			"    	mt.PLAN_COST_RATE," + 
			"    	mt.COMPANY_CODE," + 
			"    	mt.ACCOUNTING_GROUP_CODE," + 
			"    	mt.SALES_MONTH_CODE," + 
			"    	mt.SALES_MONTH_CODE_MEMO," + 
			"    	mt.PROCESSING_FLG" + 
			"    FROM T_MANHOUR tm" + 
			"        INNER JOIN M_USER mu ON mu.USER_NO = tm.USER_NO" + 
			"        INNER JOIN M_GROUP mg ON mg.GROUP_CODE = tm.GROUP_CODE" + 
			"        INNER JOIN M_THEME mt ON mt.THEME_NO = tm.THEME_NO" + 
			"        INNER JOIN M_WORK_CONTENTS mwc ON mwc.WORK_CONTENTS_CLASS = tm.WORK_CONTENTS_CLASS" + 
			"            AND mwc.WORK_CONTENTS_CODE = tm.WORK_CONTENTS_CODE" +
			"    WHERE tm.YEAR = ?1 AND tm.MONTH = ?2" + 
			"    GROUP BY" + 
			"        tm.YEAR," + 
			"        tm.MONTH," + 
			"        tm.GROUP_CODE," + 
			"        mg.GROUP_NAME," + 
			"        tm.USER_NO," + 
			"        mu.USER_NAME," + 
			"        tm.THEME_NO," + 
			"        mt.THEME_NAME1," + 
			"        mwc.SUBTOTAL_CODE," + 
			"        mt.sales_object_code," + 
			"        mt.SALES_DATE," + 
			"        mt.ORDER_AMOUNT," + 
			"        mt.PLAN_COST_RATE," + 
			"        mt.COMPANY_CODE," + 
			"        mt.ACCOUNTING_GROUP_CODE," + 
			"        mt.SALES_MONTH_CODE," + 
			"        mt.SALES_MONTH_CODE_MEMO," + 
			"        mt.PROCESSING_FLG," + 
			"        mt.THEME_NO  ", nativeQuery = true)
	List<ContingData> findContingDataOuput(short year, short month);
}
