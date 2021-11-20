package ait.team.java.service;

import java.util.List;

import ait.team.java.controller.input.InputSelectTheme;
import ait.team.java.controller.output.SelectThemeData;
import ait.team.java.dto.GroupDto;
import ait.team.java.dto.SalesObjectDto;
import ait.team.java.dto.output.DataSearchTheme;

public interface ISelectThemeService {
	InputSelectTheme getInfoScreenItem(String userNo);
	List<GroupDto> getGroup();
	List<SalesObjectDto> getSalesObject();
	SelectThemeData getSelectThemeData(String userNo);
	DataSearchTheme searchTheme(InputSelectTheme input, String userNo);
	void updateUserScreenItem(String userNo, InputSelectTheme input, String ScreenItem);
	void updateUserScreen(String userNo, InputSelectTheme input);
}
