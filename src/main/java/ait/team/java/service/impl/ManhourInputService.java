package ait.team.java.service.impl;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ait.team.java.api.output.ContingDataOutput;
import ait.team.java.common.CommonUtils;
import ait.team.java.controller.output.ManhourInputGeneral;
import ait.team.java.dto.GroupDto;
import ait.team.java.dto.ManhourDto;
import ait.team.java.dto.SalesObjectDto;
import ait.team.java.dto.ThemeDto;
import ait.team.java.dto.UserScreenItemDto;
import ait.team.java.dto.WorkContentsDto;
import ait.team.java.dto.output.ManHourInputDto;
import ait.team.java.entity.GroupEntity;
import ait.team.java.entity.ManhourEntity;
import ait.team.java.entity.SalesObjectEntity;
import ait.team.java.entity.ThemeEntity;
import ait.team.java.entity.UserScreenItemEntity;
import ait.team.java.entity.WorkContentsEntity;
import ait.team.java.entity.input.ManHourInputEntity;
import ait.team.java.repository.GroupRepository;
import ait.team.java.repository.ManhourInputRepository;
import ait.team.java.repository.ManhourRepository;
import ait.team.java.repository.SaleObjectRepository;
import ait.team.java.repository.ThemeRepository;
import ait.team.java.repository.UserScreenItemRepository;
import ait.team.java.repository.WorkContentRepository;
import ait.team.java.service.IManhourInputService;

@Service
public class ManhourInputService implements IManhourInputService {

	public final String DATE_FORMAT = "yyyy/MM/dd hh:mm:ss";
	private final String EMPTY = "";

	@Autowired
	ManhourRepository manhourRepository;
	@Autowired
	WorkContentRepository workContentRepository;
	@Autowired
	UserScreenItemRepository userScreenItemRepository;
	@Autowired
	GroupRepository groupRepository;
	@Autowired
	ThemeRepository themeRepository;
	@Autowired
	SaleObjectRepository saleObjectRepository;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	ManhourInputRepository manhourInputRepository;

	/*
	 * get list data man hour input with processing date and user no (non-Javadoc)
	 * 
	 * @param processingDate
	 * 
	 * @param userNo
	 */
	@Override
	public List<ManHourInputDto> getManhourInput(String processingDate, String userNo) {

		int processingYear = Integer.parseInt(processingDate.substring(0, 4));
		int processingMonth = Integer.parseInt(processingDate.substring(5, 7));
		List<ManHourInputEntity> hourInput = manhourInputRepository.findAllManhourInput(processingYear, processingMonth,
				userNo);
		List<ManHourInputDto> list = hourInput.stream().map(group -> modelMapper.map(group, ManHourInputDto.class))
				.collect(Collectors.toList());
		return list;
	}

	/*
	 * get list data man hour input week
	 * 
	 * @param processingDate
	 * 
	 * @param userNo
	 */
	@Override
	public List<ManHourInputDto> getManhourWeek(String processingDate, String userNo) {

		Calendar calendar = Calendar.getInstance();
		// list hour working in day of month
		double[] result = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0 };

		List<ManHourInputDto> manhourInputList = new ArrayList<>();
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
			Date processingDateFormat = formatter.parse(processingDate);
			calendar.setTime(processingDateFormat);
			List<Date> weekDateList = CommonUtils.getDateOfWeek(processingDateFormat);

			int processingYear = calendar.get(Calendar.YEAR);
			int processingMonth = calendar.get(Calendar.MONTH) + 1;
			int processingDay = calendar.get(Calendar.DAY_OF_MONTH);
			List<ManHourInputEntity> hourInput = manhourInputRepository.findAllManhourInput(processingYear,
					processingMonth, userNo);
			manhourInputList = hourInput.stream().map(group -> modelMapper.map(group, ManHourInputDto.class))
					.collect(Collectors.toList());
			for (ManHourInputDto manhour : manhourInputList) {

				result[0] = manhour.getDay1();
				result[1] = manhour.getDay2();
				result[2] = manhour.getDay3();
				result[3] = manhour.getDay4();
				result[4] = manhour.getDay5();
				result[5] = manhour.getDay6();
				result[6] = manhour.getDay7();
				result[7] = manhour.getDay8();
				result[8] = manhour.getDay9();
				result[9] = manhour.getDay10();
				result[10] = manhour.getDay11();
				result[11] = manhour.getDay12();
				result[12] = manhour.getDay13();
				result[13] = manhour.getDay14();
				result[14] = manhour.getDay15();
				result[15] = manhour.getDay16();
				result[16] = manhour.getDay17();
				result[17] = manhour.getDay18();
				result[18] = manhour.getDay19();
				result[19] = manhour.getDay20();
				result[20] = manhour.getDay21();
				result[21] = manhour.getDay22();
				result[22] = manhour.getDay23();
				result[23] = manhour.getDay24();
				result[24] = manhour.getDay25();
				result[25] = manhour.getDay26();
				result[26] = manhour.getDay27();
				result[27] = manhour.getDay28();
				result[28] = manhour.getDay29();
				result[29] = manhour.getDay30();
				result[30] = manhour.getDay31();

				for (Date weekDate : weekDateList) {
					Calendar cal = Calendar.getInstance();
					cal.setTime(weekDate);
					String weekDateStr = weekDate.toString().substring(0, 3); // weekDate String
					int dayInMonth = cal.get(Calendar.DAY_OF_MONTH); // weekDate
					if ("Sun".equals(weekDateStr)) {
						manhour.setSun(result[dayInMonth - 1]);
					} else if ("Mon".equals(weekDateStr)) {
						manhour.setMon(result[dayInMonth - 1]);
					} else if ("Tue".equals(weekDateStr)) {
						manhour.setTue(result[dayInMonth - 1]);
					} else if ("Wed".equals(weekDateStr)) {
						manhour.setWed(result[dayInMonth - 1]);
					} else if ("Thu".equals(weekDateStr)) {
						manhour.setThu(result[dayInMonth - 1]);
					} else if ("Fri".equals(weekDateStr)) {
						manhour.setFri(result[dayInMonth - 1]);
					} else if ("Sat".equals(weekDateStr)) {
						manhour.setSat(result[dayInMonth - 1]);
					}
				}
				manhour.setProcessDay(result[processingDay - 1]);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return manhourInputList;
	}

	// get list work content data
	@Override
	public List<WorkContentsDto> workContentList() {

		List<WorkContentsEntity> workContent = workContentRepository.findAll();
		List<WorkContentsDto> listWorkContent = workContent.stream()
				.map(group -> modelMapper.map(group, WorkContentsDto.class)).collect(Collectors.toList());
		return listWorkContent;
	}

	// get list group code
	@Override
	public List<GroupDto> groupCodeList() {

		List<GroupEntity> groupCode = groupRepository.findAll();
		List<GroupDto> listGroupCode = groupCode.stream().map(group -> modelMapper.map(group, GroupDto.class))
				.collect(Collectors.toList());
		return listGroupCode;
	}

	// get list sale object
	@Override
	public List<SalesObjectDto> saleObjectList() {

		List<SalesObjectEntity> saleObject = saleObjectRepository.findAll();
		List<SalesObjectDto> listSaleObject = saleObject.stream()
				.map(group -> modelMapper.map(group, SalesObjectDto.class)).collect(Collectors.toList());
		return listSaleObject;
	}

	// get theme list
	@Override
	public List<ThemeDto> themeList() {

		List<ThemeEntity> theme = themeRepository.findAll();
		List<ThemeDto> listTheme = theme.stream().map(group -> modelMapper.map(group, ThemeDto.class))
				.collect(Collectors.toList());
		return listTheme;
	}

	// get screen item to display button [日/週/月]
	@Override
	public String getScreenItem(String userNo, String screenUrl) {

		List<UserScreenItemEntity> userScreenEntityList = userScreenItemRepository.findByUserNoAndScreenUrl(userNo,
				screenUrl);

		UserScreenItemEntity userScreenItemEntity = userScreenEntityList.get(0);
		UserScreenItemDto userScreenItemDto = modelMapper.map(userScreenItemEntity, UserScreenItemDto.class);
		return userScreenItemDto.getScreenItem();
	}

	// check exits screen url
	@Override
	public boolean isExistScreen(String userNo, String screenUrl) {

		List<UserScreenItemEntity> userScreenEntityList = userScreenItemRepository.findByUserNoAndScreenUrl(userNo,
				screenUrl);

		UserScreenItemEntity userScreenItemEntity = userScreenEntityList.get(0);
		if (userScreenItemEntity == null) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * Save Screen Item when click change mode button
	 */
	@Override
	public void saveScreen(String processingDate, String userNo, String screenUrl, String screenItem) {
		UserScreenItemDto userScreenItemDto = new UserScreenItemDto();

		// get YYYYMMDDHHMMSS of date process for surrogate_key
		String dateKey = processingDate.replaceAll("\\D+", EMPTY);
		String newKey = userNo + dateKey + "000";

		List<UserScreenItemEntity> userScreenEntityList = userScreenItemRepository.findByUserNoAndScreenUrl(userNo,
				screenUrl);

		UserScreenItemEntity userScreenItemEntity = userScreenEntityList.get(0);

		String key = (userScreenItemEntity == null) ? newKey : userScreenItemEntity.getSurrogateKey();
		userScreenItemEntity = modelMapper.map(userScreenItemDto, UserScreenItemEntity.class);

		userScreenItemEntity.setSurrogateKey(key);
		userScreenItemEntity.setUserNo(userNo);
		userScreenItemEntity.setScreenUrl(screenUrl);
		userScreenItemEntity.setScreenItem(screenItem);

		userScreenItemRepository.save(userScreenItemEntity);
	}

	/*
	 * check list manhoursDay when save if exist return true and save else return
	 * false
	 */
	@Transactional
	@Override
	public boolean saveManhourInputDay(List<List<ManhourDto>> manhours, String getDay) {

		try {
			for (ManhourDto deletedManhour : manhours.get(0)) {

				ManhourEntity manhourEntity = modelMapper.map(deletedManhour, ManhourEntity.class);
				manhourRepository.delete(manhourEntity);
			}
			for (ManhourDto savedManhour : manhours.get(1)) {

				ManhourEntity manhourEntity = manhourRepository.findOneManhourUpdate(savedManhour.getYear(),
						savedManhour.getMonth(), savedManhour.getUserNo(), savedManhour.getThemeNo(),
						savedManhour.getWorkContentsClass(), savedManhour.getWorkContentsCode(),
						savedManhour.getWorkContentsDetail());
				if (manhourEntity == null) {
					manhourEntity = modelMapper.map(savedManhour, ManhourEntity.class);
				} else {
					Method getColumnDay = ManhourDto.class.getMethod("get" + getDay);
					double result = (Double) getColumnDay.invoke(savedManhour);

					Method setComlumnDay = ManhourEntity.class.getMethod("set" + getDay, double.class);
					setComlumnDay.invoke(manhourEntity, result);
				}

				manhourRepository.save(manhourEntity);
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * check list manhoursWeek when save if exist return true and save else return
	 * false
	 */
	@Transactional
	@Override
	public boolean saveManhourInputWeek(List<List<ManHourInputDto>> manhours) {

		try {
			for (ManHourInputDto deletedManhour : manhours.get(0)) {
				ManhourEntity manhourEntity = modelMapper.map(deletedManhour, ManhourEntity.class);
				manhourRepository.delete(manhourEntity);
			}
			for (ManHourInputDto savedManhour : manhours.get(1)) {

				ManhourEntity manhourEntity = manhourRepository.findOneManhourUpdate(savedManhour.getYear(),
						savedManhour.getMonth(), savedManhour.getUserNo(), savedManhour.getThemeNo(),
						savedManhour.getWorkContentsClass(), savedManhour.getWorkContentsCode(),
						savedManhour.getWorkContentsDetail());
				if (manhourEntity == null) {
					manhourEntity = modelMapper.map(savedManhour, ManhourEntity.class);
				} else {
					Calendar calEnd = Calendar.getInstance();
					Calendar calStart = Calendar.getInstance();
					SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
					Date startDate = formatter.parse(savedManhour.getStartDate());
					Date endDate = formatter.parse(savedManhour.getEndDate());
					calStart.setTime(startDate);
					calEnd.setTime(endDate);
					int sDay = calStart.get(Calendar.DAY_OF_MONTH);
					int eDay = calEnd.get(Calendar.DAY_OF_MONTH);
					for(int i = sDay; i<=eDay; i++) {
						Method getColumnDay = ManhourDto.class.getMethod("getDay" + i);
						double result = (Double) getColumnDay.invoke(savedManhour);

						Method setComlumnDay = ManhourEntity.class.getMethod("setDay" + i, double.class);
						setComlumnDay.invoke(manhourEntity, result);
					} 
				} 
				manhourRepository.save(manhourEntity);
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * write file CSV with data is manhour of person search.
	 */
	@Override
	public ContingDataOutput writeDataToCSV(String processingDate, String userNo) throws ParseException {

		ContingDataOutput contingDataOutput = new ContingDataOutput(); 
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
			Date processingDateFormat = formatter.parse(processingDate);
			cal.setTime(processingDateFormat);
			int processingYear = cal.get(Calendar.YEAR);
			int processingMonth = cal.get(Calendar.MONTH);
			List<ManHourInputEntity> manhours = manhourInputRepository.findAllManhourInput(processingYear,
					processingMonth, userNo);
			// get YYYYMMDDHHMMSS to file name csv
			String childFileName = processingDate.replaceAll("\\D+", EMPTY);
			List<String[]> contentCSV = new ArrayList<>();

			String[] headerCSV = { "年", "月", "ユーザーNO", "所属コード", "サイトコード", "テーマNO", "作業内容区分", "作業内容コード", "作業内容詳細", "ピン止めフラグ",
					"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
					"20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "確定日付" };

			contentCSV.add(headerCSV);
			for (ManHourInputEntity manhour : manhours) {
				String[] line = new String[42];

				line[0] = String.valueOf(manhour.getYear());
				line[1] = String.valueOf(manhour.getMonth());
				line[2] = manhour.getUserNo();
				line[3] = manhour.getGroupCode();
				line[4] = manhour.getSiteCode();
				line[5] = manhour.getThemeNo();
				line[6] = manhour.getWorkContentsClass();
				line[7] = manhour.getWorkContentsCode();
				line[8] = manhour.getWorkContentsDetail();
				line[9] = String.valueOf(manhour.isPinFlg());
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
				line[41] = manhour.getFixDate();

				contentCSV.add(line);
			} 
		contingDataOutput.setFileName(childFileName);
		contingDataOutput.setCsvData(contentCSV);

		
		return contingDataOutput;
	}

	// general function
	@Override
	public ManhourInputGeneral generalFunction(String processingDate, String userNo, String screenUrl) {

		List<ManHourInputDto> showManhourInput = getManhourWeek(processingDate, userNo);
		String modeValue = getScreenItem(userNo, screenUrl);

		boolean isExistScreen = isExistScreen(userNo, screenUrl);
		if (!isExistScreen) {
			saveScreen(processingDate, userNo, screenUrl, "Day");
		}

		ManhourInputGeneral manhourInputGeneral = new ManhourInputGeneral();
		manhourInputGeneral.setModeValue(modeValue);
		manhourInputGeneral.setShowManhourInput(showManhourInput);
		return manhourInputGeneral;
	}

	// general function
	@Override
	public ManhourInputGeneral parrentFunction(String processingDate, String userNo, String screenUrl,
			String modeValue) {

		saveScreen(processingDate, userNo, screenUrl, modeValue);
		ManhourInputGeneral manhourInputGeneral = generalFunction(processingDate, userNo, screenUrl);
		return manhourInputGeneral;
	}

}
