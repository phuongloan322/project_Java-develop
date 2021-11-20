package ait.team.java.controller.output.manhourReport;

import java.util.List;

public class ExportManhourReport {
	private String groupCode = "";
	private String groupName = "";
	private String userNo = "";
	private String userName = "";
	private String themeNo = "";
	private String themeName1 = "";
	private String workContentCode = "";
	private String workContentCodeName = "";
	private String workContentDetail = "";
	private List<Integer> monthHour;
	private List<Integer> dayHour;
	private String totalMonth = null;
	
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getThemeNo() {
		return themeNo;
	}
	public void setThemeNo(String themeNo) {
		this.themeNo = themeNo;
	}
	public String getThemeName1() {
		return themeName1;
	}
	public void setThemeName1(String themeName1) {
		this.themeName1 = themeName1;
	}
	public String getWorkContentCode() {
		return workContentCode;
	}
	public void setWorkContentCode(String workContentCode) {
		this.workContentCode = workContentCode;
	}
	public String getWorkContentCodeName() {
		return workContentCodeName;
	}
	public void setWorkContentCodeName(String workContentCodeName) {
		this.workContentCodeName = workContentCodeName;
	}
	public String getWorkContentDetail() {
		return workContentDetail;
	}
	public void setWorkContentDetail(String workContentDetail) {
		this.workContentDetail = workContentDetail;
	}
	public List<Integer> getMonthHour() {
		return monthHour;
	}
	public void setMonthHour(List<Integer> monthHour) {
		this.monthHour = monthHour;
	}
	public List<Integer> getDayHour() {
		return dayHour;
	}
	public void setDayHour(List<Integer> dayHour) {
		this.dayHour = dayHour;
	}
	public String getTotalMonth() {
		return totalMonth;
	}
	public void setTotalMonth(String totalMonth) {
		this.totalMonth = totalMonth;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
}
