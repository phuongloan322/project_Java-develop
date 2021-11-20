package ait.team.java.controller.output.manhourReport;

import java.util.List;

public class ThemeFirstCreenManhourReport {
	String nameFirst;
	String themeNo;
	WorkContentFirstScreenManhourReport workContent;
	String workContentDetail;
	List<WorkContentFirstScreenManhourReport> workContentList;
	
	public List<WorkContentFirstScreenManhourReport> getWorkContentList() {
		return workContentList;
	}
	public void setWorkContentList(List<WorkContentFirstScreenManhourReport> workContentList) {
		this.workContentList = workContentList;
	}
	public WorkContentFirstScreenManhourReport getWorkContent() {
		return workContent;
	}
	public void setWorkContent(WorkContentFirstScreenManhourReport workContent) {
		this.workContent = workContent;
	}
	public String getWorkContentDetail() {
		return workContentDetail;
	}
	public void setWorkContentDetail(String workContentDetail) {
		this.workContentDetail = workContentDetail;
	}
	public String getNameFirst() {
		return nameFirst;
	}
	public void setNameFirst(String nameFirst) {
		this.nameFirst = nameFirst;
	}
	public String getThemeNo() {
		return themeNo;
	}
	public void setThemeNo(String themeNo) {
		this.themeNo = themeNo;
	}
	
	
}
