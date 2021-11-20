package ait.team.java.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ait.team.java.api.output.ContingDataOutput;
import ait.team.java.common.CommonUtils;
import ait.team.java.controller.input.InputSearchManhour;
import ait.team.java.controller.output.ManhourOutput;
import ait.team.java.dto.GroupDto;
import ait.team.java.dto.ManhourDto;
import ait.team.java.dto.UserDto;
import ait.team.java.dto.WorkContentsDto;
import ait.team.java.dto.output.ManhourUpdateDto;
import ait.team.java.entity.GroupEntity;
import ait.team.java.entity.ManhourEntity;
import ait.team.java.entity.Role;
import ait.team.java.entity.UserEntity;
import ait.team.java.entity.UserScreenItemEntity;
import ait.team.java.entity.WorkContentsEntity;
import ait.team.java.entity.output.ManhourUpdateEntity;
import ait.team.java.repository.GroupRepository;
import ait.team.java.repository.ManhourRepository;
import ait.team.java.repository.ManhourUpdateRepository;
import ait.team.java.repository.RoleRepository;
import ait.team.java.repository.UserRepository;
import ait.team.java.repository.UserScreenItemRepository;
import ait.team.java.repository.WorkContentRepository;
import ait.team.java.service.IManhourUpdateService;

@Service
public class ManhourUpdateService implements IManhourUpdateService {

	@Autowired
	ManhourRepository manhourRepository;
	@Autowired
	ManhourUpdateRepository manhourUpdateRepository;
	@Autowired
	GroupRepository groupRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserScreenItemRepository userScreenItemRepository;
	@Autowired
	WorkContentRepository workContentsRepository;
	@Autowired
	ModelMapper modelMaper;
	@Autowired
	CalendarService calendarService;

	private final String SCREEN_URL = "ManhourUpdate";
	private final String FUNCTION_CLASS_0 = "0";
	private final String FUNCTION_CLASS_2 = "2";
	private final String GROUP_CODE = "GROUP_CODE";
	private final String USER_NO = "USER_NO";
	private final String SITE_CODE = "SITE_CODE";
	private final String DATE_FORMAT = "dd/MM/yyyy";
	private final String DATE_FORMAT_YYYYMMDDHHMMSS = "YYYYMMDDHHMMSS";
	private final String EXTENSION_CSV = ".csv";
	private final String FILE_NAME = "DL_Manhour_";

	/*
	 * Function get current month with format YYYY/MM.
	 */
	@Override
	public String getCurrentMonth() {

		Calendar calendar = Calendar.getInstance();
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		int month = calendar.get(Calendar.MONTH) + 1;
		String currentMonth = year.concat("/").concat(StringUtils.leftPad(String.valueOf(month), 2, "0"));

		return currentMonth;
	}

	/*
	 * Function get manhours follow user with current month.
	 */
	@Override
	public List<ManhourUpdateDto> getManhour(InputSearchManhour input) {

		String yearMonthArr[] = input.getMonth().split("/");
		List<ManhourUpdateEntity> manhourEntityList = manhourUpdateRepository.findAllManhour(
				Integer.parseInt(yearMonthArr[0]), Integer.parseInt(yearMonthArr[1]), input.getGroupCode(),
				input.getUserNo());
		List<ManhourUpdateDto> manhourDtoList = manhourEntityList.stream()
				.map(manhour -> modelMaper.map(manhour, ManhourUpdateDto.class)).collect(Collectors.toList());

		return manhourDtoList;
	}

	/*
	 * Function get groups with the condition of funtionClass.
	 */
	@Override
	public List<GroupDto> getGroup(String funtionClass, String userNo) {

		List<GroupEntity> groupEntityList = null;

		if (funtionClass.equals(FUNCTION_CLASS_2)) {
			String groupCode = userRepository.findByUserNo(userNo).getGroupCode();
			groupEntityList = groupRepository.findByGroupCode(groupCode);
		} else {
			groupEntityList = groupRepository.findAll();
		}
		List<GroupDto> groupList = groupEntityList.stream().map(group -> modelMaper.map(group, GroupDto.class))
				.collect(Collectors.toList());

		return groupList;
	}

	/*
	 * Function get Users follow groupCode.
	 */
	@Override
	public List<UserDto> getUser(InputSearchManhour input) {

		List<UserEntity> userEntityList = userRepository.findByGroupCode(input.getGroupCode());
		List<UserDto> userList = userEntityList.stream().map(user -> modelMaper.map(user, UserDto.class))
				.collect(Collectors.toList());

		return userList;
	}

	/*
	 * Function get funtionClass from m_role follow RoleCode and ScreenUrl.
	 */
	@Override
	public String getFuntionClass(String roleCode) {

		Role roleEntity = roleRepository.findByRoleCodeAndScreenUrl(roleCode, SCREEN_URL);

		return roleEntity.getFunctionClass();
	}

	/*
	 * Function get history information of user from table user_screen_item. if
	 * history information equal null => get information follow that person.
	 */
	@Override
	public InputSearchManhour getInfoScreenItem(String userNo) {

		List<UserScreenItemEntity> userScreenItems = userScreenItemRepository.findByUserNoAndScreenUrl(userNo,
				SCREEN_URL);
		InputSearchManhour inputSearchManhour = new InputSearchManhour();

		for (UserScreenItemEntity userScreenItemEntity : userScreenItems) {
			if (userScreenItemEntity.getScreenItem().equals(GROUP_CODE)) {
				inputSearchManhour.setGroupCode(userScreenItemEntity.getScreenInput());
			}
			if (userScreenItemEntity.getScreenItem().equals(USER_NO)) {
				inputSearchManhour.setUserNo(userScreenItemEntity.getScreenInput());
			}
			if (userScreenItemEntity.getScreenItem().equals(SITE_CODE)) {
				inputSearchManhour.setSiteCode(userScreenItemEntity.getScreenInput());
			}
		}

		if (inputSearchManhour.getGroupCode() == null) {
			String groupCode = userRepository.findByUserNo(userNo).getGroupCode();
			inputSearchManhour.setGroupCode(groupCode);
		}
		if (inputSearchManhour.getUserNo() == null) {
			inputSearchManhour.setUserNo(userNo);
		}
		if (inputSearchManhour.getSiteCode() == null) {
			String siteCode = userRepository.findByUserNo(userNo).getSiteCode();
			inputSearchManhour.setSiteCode(siteCode);
		}

		return inputSearchManhour;
	}

	/*
	 * Function get synthetic result when search or when start.
	 */
	@Override
	public ManhourOutput getResult(String userNo, String roleCode) {

		String funtionClass = getFuntionClass(roleCode);
		if (!funtionClass.equals(FUNCTION_CLASS_0)) {

			ManhourOutput manhourOutput = new ManhourOutput();

			String currentMonth = getCurrentMonth();
			String currentDay = getCurrentDay();
			InputSearchManhour input = getInfoScreenItem(userNo);
			input.setMonth(currentMonth);
			List<GroupDto> groups = getGroup(funtionClass, userNo);
			List<UserDto> users = getUser(input);
			List<ManhourUpdateDto> manhours = getManhour(input);
			
			manhourOutput.setInputSearch(input);
			manhourOutput.setDayNow(currentDay);
			manhourOutput.setGroups(groups);
			manhourOutput.setUsers(users);
			manhourOutput.setManhour(manhours);

			try {
				String firstDayCurrentMonth = "1" + currentMonth.substring(4) + "/" + currentMonth.substring(0, 4);
				Date date = new SimpleDateFormat(DATE_FORMAT).parse(firstDayCurrentMonth);
				boolean[] holidays = calendarService.findHoliday(date, input.getSiteCode());
				manhourOutput.setHolidays(holidays);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			return manhourOutput;

		}
		return null;
	}

	/*
	 * get manhour when search
	 */
	@Override
	public ManhourOutput searchManhour(String userNo, String roleCode, InputSearchManhour input) {
		updateUserScreen(userNo, input);
		ManhourOutput manhourOutput = getResult(userNo, roleCode);
		return manhourOutput;
	}

	/*
	 * Function update with once screenItem.
	 */
	@SuppressWarnings("null")
	@Override
	public void updateUserScreenItem(String userNo, InputSearchManhour input, String ScreenItem) {

		UserEntity userEntity = userRepository.findByUserNo(input.getUserNo());
		UserScreenItemEntity userScreeenItemEntity = userScreenItemRepository
				.findOneByUserNoAndScreenUrlAndScreenItem(userNo, SCREEN_URL, ScreenItem);

		if (userScreeenItemEntity == null) {
			String newKey = input.getUserNo() + input.getMonth() + System.nanoTime();
			userScreeenItemEntity.setSurrogateKey(newKey);
			userScreeenItemEntity.setUserNo(userNo);
			userScreeenItemEntity.setScreenUrl(SCREEN_URL);
			userScreeenItemEntity.setScreenItem(ScreenItem);
		}
		if (ScreenItem.equals(GROUP_CODE)) {
			userScreeenItemEntity.setScreenInput(input.getGroupCode());
		}
		if (ScreenItem.equals(USER_NO)) {
			userScreeenItemEntity.setScreenInput(input.getUserNo());
		}
		if (ScreenItem.equals(SITE_CODE)) {
			userScreeenItemEntity.setScreenInput(userEntity.getSiteCode());
		}

		userScreenItemRepository.save(userScreeenItemEntity);
	}

	/*
	 * Function update userScreenItem.
	 */
	@Override
	public void updateUserScreen(String userNo, InputSearchManhour input) {

		updateUserScreenItem(userNo, input, GROUP_CODE);
		updateUserScreenItem(userNo, input, USER_NO);
		updateUserScreenItem(userNo, input, SITE_CODE);
	}

	/*
	 * insert/update/delete manhours when change or delete.
	 */
	@Transactional
	@Override
	public boolean saveManhourUpdate(List<List<ManhourDto>> manhours) {

		try {
			for (ManhourDto manhourDelete : manhours.get(0)) {
				ManhourEntity manhourEntity = modelMaper.map(manhourDelete, ManhourEntity.class);
				manhourRepository.delete(manhourEntity);
			}
			for (ManhourDto manhour : manhours.get(1)) {
				ManhourEntity manhourEntity = modelMaper.map(manhour, ManhourEntity.class);
				manhourRepository.save(manhourEntity);
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	/*
	 * Function get current day convert String with format dayDD.
	 */
	@Override
	public String getCurrentDay() {

		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		String currentDay = String.valueOf(day);

		return currentDay;
	}

	@Override
	public List<WorkContentsDto> getWorkContentsCode(String workContentsClass) {
		List<WorkContentsEntity> workContentsEntity = workContentsRepository.findByWorkContentsClass(workContentsClass);
		List<WorkContentsDto> workContents = workContentsEntity.stream()
				.map(work -> modelMaper.map(work, WorkContentsDto.class)).collect(Collectors.toList());
		return workContents;
	}
	
	/*
	 * Function write file CSV with data is manhour of person search.
	 */
	@Override
	public ContingDataOutput getDataExportCsv(String userNo) {

		String[] headerCSV = { "年", "月", "ユーザNo", "ユーザ名", "テーマＮｏ", "テーマ名", "内容コード", "内容名", "内容詳細コード", "合計",
				"1", "2", "3","4", "5", "6", "7", "8", "9", "10",
				"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
				"21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
		ContingDataOutput contingDataOutput = new ContingDataOutput();
		InputSearchManhour input = getInfoScreenItem(userNo);
		String currentMonth = getCurrentMonth();
		input.setMonth(currentMonth);
		List<ManhourUpdateDto> manhours = getManhour(input);
		List<String[]> contentCSV = new ArrayList<>();
		
		contentCSV.add(headerCSV);
		for (ManhourUpdateDto manhour : manhours) {
			String[] line = new String[41];
			line[0] = String.valueOf(manhour.getYear());
			line[1] = String.valueOf(manhour.getMonth());
			line[2] = manhour.getUserNo();
			line[3] = manhour.getUserName();
			line[4] = manhour.getThemeNo();
			line[5] = manhour.getThemeName1();
			line[6] = manhour.getWorkContentsCode();
			line[7] = manhour.getWorkContentsCode();
			line[8] = manhour.getWorkContentsDetail();
			line[9] = String.valueOf(manhour.getTotal());
			
			line[10] = String.valueOf(manhour.getDay1());
			line[11] = String.valueOf(manhour.getDay2());
			line[12] = String.valueOf(manhour.getDay3());
			line[13] = String.valueOf(manhour.getDay4());
			line[14] = String.valueOf(manhour.getDay5());
			line[15] = String.valueOf(manhour.getDay6());
			line[16] = String.valueOf(manhour.getDay7());
			line[17] = String.valueOf(manhour.getDay8());
			line[18] = String.valueOf(manhour.getDay9());
			line[19] = String.valueOf(manhour.getDay10());
			
			line[20] = String.valueOf(manhour.getDay11());
			line[21] = String.valueOf(manhour.getDay12());
			line[22] = String.valueOf(manhour.getDay13());
			line[23] = String.valueOf(manhour.getDay14());
			line[24] = String.valueOf(manhour.getDay15());
			line[25] = String.valueOf(manhour.getDay16());
			line[26] = String.valueOf(manhour.getDay17());
			line[27] = String.valueOf(manhour.getDay18());
			line[28] = String.valueOf(manhour.getDay19());
			line[29] = String.valueOf(manhour.getDay20());
			
			line[30] = String.valueOf(manhour.getDay21());
			line[31] = String.valueOf(manhour.getDay22());
			line[32] = String.valueOf(manhour.getDay23());
			line[33] = String.valueOf(manhour.getDay24());
			line[34] = String.valueOf(manhour.getDay25());
			line[35] = String.valueOf(manhour.getDay26());
			line[36] = String.valueOf(manhour.getDay27());
			line[37] = String.valueOf(manhour.getDay28());
			line[38] = String.valueOf(manhour.getDay29());
			line[39] = String.valueOf(manhour.getDay30());
			line[40] = String.valueOf(manhour.getDay31());
			
			contentCSV.add(line);
		}
		
		Date dateNow = new Date();
		String fileName = FILE_NAME + input.getUserNo() + "_" + CommonUtils.dateToString(dateNow, DATE_FORMAT_YYYYMMDDHHMMSS) + EXTENSION_CSV;
		contingDataOutput.setFileName(fileName);
		contingDataOutput.setCsvData(contentCSV);

		return contingDataOutput;
	}
}
