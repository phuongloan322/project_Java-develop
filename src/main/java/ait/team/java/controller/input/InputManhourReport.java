package ait.team.java.controller.input;

import java.util.List;

public class InputManhourReport {
	private String saveName;
	private String startDate;
	private String endDate;
	private List<String> groupList;
	private List<String> userList;
	private List<String> themeList;
	private List<String> workContentList;
	private List<String> workContentDetailList;
	private List<String> hideList;
	private List<String> showList;
	private String total;
	private String comma;
	private String apostrophe;
	
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
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
	public List<String> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<String> groupList) {
		this.groupList = groupList;
	}
	public List<String> getUserList() {
		return userList;
	}
	public void setUserList(List<String> userList) {
		this.userList = userList;
	}
	public List<String> getThemeList() {
		return themeList;
	}
	public void setThemeList(List<String> themeList) {
		this.themeList = themeList;
	}
	public List<String> getWorkContentList() {
		return workContentList;
	}
	public void setWorkContentList(List<String> workContentList) {
		this.workContentList = workContentList;
	}
	public List<String> getWorkContentDetailList() {
		return workContentDetailList;
	}
	public void setWorkContentDetailList(List<String> workContentDetailList) {
		this.workContentDetailList = workContentDetailList;
	}
	public List<String> getHideList() {
		return hideList;
	}
	public void setHideList(List<String> hideList) {
		this.hideList = hideList;
	}
	public List<String> getShowList() {
		return showList;
	}
	public void setShowList(List<String> showList) {
		this.showList = showList;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getComma() {
		return comma;
	}
	public void setComma(String comma) {
		this.comma = comma;
	}
	public String getApostrophe() {
		return apostrophe;
	}
	public void setApostrophe(String apostrophe) {
		this.apostrophe = apostrophe;
	}
	
}
