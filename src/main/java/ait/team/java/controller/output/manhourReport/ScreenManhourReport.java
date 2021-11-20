package ait.team.java.controller.output.manhourReport;

import java.util.List;

public class ScreenManhourReport {
	boolean checkNone = false;
	String saveName = null;
	List<String> saveNameList;
	String startDate;
	String endDate;
	List<GroupScreenManhourReport> groupList;
	List<ThemeFirstCreenManhourReport> themeList;
	List<GroupItem> groupItemList;
	List<String> showList ;
	List<String> hideList;
	boolean total;
	boolean comma;
	boolean apostrophe;
	
	
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	public List<GroupItem> getGroupItemList() {
		return groupItemList;
	}
	public void setGroupItemList(List<GroupItem> groupItemList) {
		this.groupItemList = groupItemList;
	}
	public boolean isCheckNone() {
		return checkNone;
	}
	public void setCheckNone(boolean checkNone) {
		this.checkNone = checkNone;
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public List<GroupScreenManhourReport> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<GroupScreenManhourReport> groupList) {
		this.groupList = groupList;
	}
	
	public List<ThemeFirstCreenManhourReport> getThemeList() {
		return themeList;
	}
	public void setThemeList(List<ThemeFirstCreenManhourReport> themeList) {
		this.themeList = themeList;
	}
	public List<String> getShowList() {
		return showList;
	}
	public void setShowList(List<String> showList) {
		this.showList = showList;
	}
	public List<String> getHideList() {
		return hideList;
	}
	public void setHideList(List<String> hideList) {
		this.hideList = hideList;
	}
	public boolean isTotal() {
		return total;
	}
	public void setTotal(boolean total) {
		this.total = total;
	}
	public List<String> getSaveNameList() {
		return saveNameList;
	}
	public void setSaveNameList(List<String> saveNameList) {
		this.saveNameList = saveNameList;
	}
	public boolean isComma() {
		return comma;
	}
	public void setComma(boolean comma) {
		this.comma = comma;
	}
	public boolean isApostrophe() {
		return apostrophe;
	}
	public void setApostrophe(boolean apostrophe) {
		this.apostrophe = apostrophe;
	}
	
	
}
