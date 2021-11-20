package ait.team.java.service;

import java.text.ParseException;
import java.util.List;

import ait.team.java.api.output.ContingDataOutput;
import ait.team.java.controller.output.ManhourInputGeneral;
import ait.team.java.dto.GroupDto;
import ait.team.java.dto.ManhourDto;
import ait.team.java.dto.SalesObjectDto;
import ait.team.java.dto.ThemeDto;
import ait.team.java.dto.WorkContentsDto;
import ait.team.java.dto.output.ManHourInputDto;

public interface IManhourInputService {

	List<WorkContentsDto> workContentList();

	List<GroupDto> groupCodeList();

	List<ThemeDto> themeList();

	List<ManHourInputDto> getManhourInput(String dateProcess, String userNo);

	public List<ManHourInputDto> getManhourWeek(String processingDate, String userNo); 

	boolean isExistScreen(String userNo, String screenUrl); 

	String getScreenItem(String userNo, String screenUrl);

	List<SalesObjectDto> saleObjectList();

	ContingDataOutput writeDataToCSV(String processingDate, String userNo) throws ParseException;

	void saveScreen(String processingDate, String userNo, String screenUrl, String screenItem);

	ManhourInputGeneral generalFunction(String processingDate, String userNo, String screenUrl);

	ManhourInputGeneral parrentFunction(String processingDate, String userNo, String screenUrl, String modeValue);

	boolean saveManhourInputDay(List<List<ManhourDto>> manhours, String getDay);  

	boolean saveManhourInputWeek(List<List<ManHourInputDto>> manhours);

}
