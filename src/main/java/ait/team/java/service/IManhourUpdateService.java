package ait.team.java.service;

import java.util.List;

import ait.team.java.api.output.ContingDataOutput;
import ait.team.java.controller.input.InputSearchManhour;
import ait.team.java.controller.output.ManhourOutput;
import ait.team.java.dto.GroupDto;
import ait.team.java.dto.ManhourDto;
import ait.team.java.dto.UserDto;
import ait.team.java.dto.WorkContentsDto;
import ait.team.java.dto.output.ManhourUpdateDto;

public interface IManhourUpdateService {
	
	String getFuntionClass(String roleCode);
	List<ManhourUpdateDto> getManhour( InputSearchManhour input);
	String getCurrentMonth();
	String getCurrentDay();
	List<GroupDto> getGroup(String funtionClass, String userNo);
	List<UserDto> getUser(InputSearchManhour input);
	InputSearchManhour getInfoScreenItem(String userNo);
	void updateUserScreenItem(String userNo, InputSearchManhour input, String ScreenItem);
	void updateUserScreen(String userNo, InputSearchManhour input);
	ManhourOutput getResult(String userNo,  String roleCode); 
	ContingDataOutput getDataExportCsv(String userNo);
	boolean saveManhourUpdate(List<List<ManhourDto>> manhours);
	ManhourOutput searchManhour(String userNo, String roleCode, InputSearchManhour input); 
	List<WorkContentsDto> getWorkContentsCode(String workContentsClass);
}
