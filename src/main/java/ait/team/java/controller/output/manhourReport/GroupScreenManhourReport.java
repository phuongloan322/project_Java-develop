package ait.team.java.controller.output.manhourReport;

import java.util.List;

public class GroupScreenManhourReport {
	String groupName;
	String groupCode;
	List<GroupItem> groupItemsList;
	List<UserScreenManhourReport> userList;
	List<UserScreenManhourReport> userItemList;
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public List<GroupItem> getGroupItemsList() {
		return groupItemsList;
	}
	public void setGroupItemsList(List<GroupItem> groupItemsList) {
		this.groupItemsList = groupItemsList;
	}
	public List<UserScreenManhourReport> getUserList() {
		return userList;
	}
	public void setUserList(List<UserScreenManhourReport> userList) {
		this.userList = userList;
	}
	public List<UserScreenManhourReport> getUserItemList() {
		return userItemList;
	}
	public void setUserItemList(List<UserScreenManhourReport> userItemList) {
		this.userItemList = userItemList;
	}
	
	
}
