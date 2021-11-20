package ait.team.java.controller.output;

import java.util.ArrayList;
import java.util.List;

import ait.team.java.controller.input.InputSelectTheme;
import ait.team.java.dto.GroupDto;
import ait.team.java.dto.SalesObjectDto;

public class SelectThemeData {
	private InputSelectTheme inputSelectTheme = new InputSelectTheme();
	private List<GroupDto> groupList = new ArrayList<>();
	private List<SalesObjectDto> salesObjectList = new ArrayList<>();

	public InputSelectTheme getInputSelectTheme() {
		return inputSelectTheme;
	}

	public void setInputSelectTheme(InputSelectTheme inputSelectTheme) {
		this.inputSelectTheme = inputSelectTheme;
	}

	public List<GroupDto> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<GroupDto> groupList) {
		this.groupList = groupList;
	}

	public List<SalesObjectDto> getSalesObjectList() {
		return salesObjectList;
	}

	public void setSalesObjectList(List<SalesObjectDto> salesObjectList) {
		this.salesObjectList = salesObjectList;
	}
}
