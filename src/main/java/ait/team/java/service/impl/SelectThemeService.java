package ait.team.java.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ait.team.java.controller.input.InputSearchTheme;
import ait.team.java.controller.input.InputSelectTheme;
import ait.team.java.controller.output.SelectThemeData;
import ait.team.java.converter.ConvertInputSelectToSearchTheme;
import ait.team.java.dto.GroupDto;
import ait.team.java.dto.SalesObjectDto;
import ait.team.java.dto.ThemeDto;
import ait.team.java.dto.output.DataSearchTheme;
import ait.team.java.entity.GroupEntity;
import ait.team.java.entity.SalesObjectEntity;
import ait.team.java.entity.ThemeEntity;
import ait.team.java.entity.UserScreenItemEntity;
import ait.team.java.repository.GroupRepository;
import ait.team.java.repository.SalesObjectRepository;
import ait.team.java.repository.ThemeRepository;
import ait.team.java.repository.UserScreenItemRepository;
import ait.team.java.service.ISelectThemeService;

@Service
public class SelectThemeService implements ISelectThemeService {

	@Autowired
	UserScreenItemRepository userScreenItemRepository;
	@Autowired
	GroupRepository groupRepository;
	@Autowired
	SalesObjectRepository salesObjectRepository;
	@Autowired
	ThemeRepository themeRepository;
	@Autowired
	ConvertInputSelectToSearchTheme convertInputSelectToSearch;
	@Autowired
	ModelMapper modelMapper;

	private final String SCREEN_URL = "SelectTheme";
	private final String THEME_NO = "ThemeNo";
	private final String THEME_NAME = "ThemeName";
	private final String ACCOUNTING_GROUP_CODE = "AccountingGroupCode";
	private final String SALES_OBJECT_CODE = "SalesObjectCode";
	private final String SOLD_FLG = "SoldFlg";
	private final String FALSE = "false";
	private final int MAX_RECORD_THEME = 1000;
	private final String WAR_005 = "売上済のテーマです。テーマを追加しますか？";

	@Override
	public InputSelectTheme getInfoScreenItem(String userNo) {
		List<UserScreenItemEntity> userScreenItems = userScreenItemRepository.findByUserNoAndScreenUrl(userNo,
				SCREEN_URL);
		InputSelectTheme inputSelectTheme = new InputSelectTheme();

		for (UserScreenItemEntity userScreenItemEntity : userScreenItems) {
			if (userScreenItemEntity.getScreenItem().equals(THEME_NO)) {
				inputSelectTheme.setThemeNo(userScreenItemEntity.getScreenInput());
			}
			if (userScreenItemEntity.getScreenItem().equals(THEME_NAME)) {
				inputSelectTheme.setThemeName1(userScreenItemEntity.getScreenInput());
			}
			if (userScreenItemEntity.getScreenItem().equals(ACCOUNTING_GROUP_CODE)) {
				inputSelectTheme.setAccountingGroupCode(userScreenItemEntity.getScreenInput());
			}
			if (userScreenItemEntity.getScreenItem().equals(SALES_OBJECT_CODE)) {
				inputSelectTheme.setSalesObjectCode(userScreenItemEntity.getScreenInput());
			}
			if (userScreenItemEntity.getScreenItem().equals(SOLD_FLG)) {
				inputSelectTheme.setSoldFlg(userScreenItemEntity.getScreenInput());
			}
		}

		if (inputSelectTheme.getSoldFlg() == null) {
			inputSelectTheme.setSoldFlg(FALSE);
		}

		return inputSelectTheme;
	}

	@Override
	public List<GroupDto> getGroup() {

		List<GroupEntity> groupEntityList = groupRepository.findAll();
		List<GroupDto> groupSet = groupEntityList.stream()
				.filter(distinctByKey(group -> group.getAccountingGroupCode()))
				.map(group -> modelMapper.map(group, GroupDto.class)).collect(Collectors.toList());
		return groupSet;
	}

	@Override
	public List<SalesObjectDto> getSalesObject() {

		List<SalesObjectEntity> salesObjectEntityList = salesObjectRepository.findAll();
		List<SalesObjectDto> salesObjectDtoList = salesObjectEntityList.stream()
				.map(salesObjectEntity -> modelMapper.map(salesObjectEntity, SalesObjectDto.class))
				.collect(Collectors.toList());
		return salesObjectDtoList;
	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {

		Map<Object, Boolean> seen = new ConcurrentHashMap<>();
		return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

	@Override
	public SelectThemeData getSelectThemeData(String userNo) {
		InputSelectTheme inputSelectTheme = getInfoScreenItem(userNo);
		List<GroupDto> groupList = getGroup();
		List<SalesObjectDto> salesObjectList = getSalesObject();

		SelectThemeData selectThemeData = new SelectThemeData();

		selectThemeData.setInputSelectTheme(inputSelectTheme);
		selectThemeData.setGroupList(groupList);
		selectThemeData.setSalesObjectList(salesObjectList);

		return selectThemeData;
	}

	@Override
	public DataSearchTheme searchTheme(InputSelectTheme input, String userNo) {
		updateUserScreen(userNo, input);
		DataSearchTheme dataSearchTheme = new DataSearchTheme();
		InputSearchTheme inputSearch = convertInputSelectToSearch.toInputSearch(input);
		int themeRecord = themeRepository.countTheme(inputSearch);
		if(themeRecord < MAX_RECORD_THEME) {
			List<ThemeEntity> themeEntityList = themeRepository.findTheme(inputSearch);
			List<ThemeDto> themeList = themeEntityList.stream().map(theme -> modelMapper.map(theme, ThemeDto.class))
					.collect(Collectors.toList());
			dataSearchTheme.setThemeList(themeList);
			dataSearchTheme.setCheckError(1);;
		}else {
			dataSearchTheme.setCheckError(0);
			dataSearchTheme.setMes(WAR_005);
		}
		
		return dataSearchTheme;
	}

	@SuppressWarnings("null")
	@Override
	public void updateUserScreenItem(String userNo, InputSelectTheme input, String ScreenItem) {
		UserScreenItemEntity userScreeenItemEntity = userScreenItemRepository
				.findOneByUserNoAndScreenUrlAndScreenItem(userNo, SCREEN_URL, ScreenItem);

		if (userScreeenItemEntity == null) {
			userScreeenItemEntity = new UserScreenItemEntity();
			String newKey = userNo + System.nanoTime();
			userScreeenItemEntity.setSurrogateKey(newKey);
			userScreeenItemEntity.setUserNo(userNo);
			userScreeenItemEntity.setScreenUrl(SCREEN_URL);
			userScreeenItemEntity.setScreenItem(ScreenItem);
		}
		if (ScreenItem.equals(THEME_NO)) {
			userScreeenItemEntity.setScreenInput(input.getThemeNo());
		}
		if (ScreenItem.equals(THEME_NAME)) {
			userScreeenItemEntity.setScreenInput(input.getThemeName1());
		}
		if (ScreenItem.equals(ACCOUNTING_GROUP_CODE)) {
			userScreeenItemEntity.setScreenInput(input.getAccountingGroupCode());
		}
		if (ScreenItem.equals(SALES_OBJECT_CODE)) {
			userScreeenItemEntity.setScreenInput(input.getSalesObjectCode());
		}
		if (ScreenItem.equals(SOLD_FLG)) {
			userScreeenItemEntity.setScreenInput(input.getSoldFlg());
		}

		userScreenItemRepository.save(userScreeenItemEntity);

	}
	
	@Override
	public void updateUserScreen(String userNo, InputSelectTheme input) {
		
		updateUserScreenItem(userNo, input, THEME_NO);
		updateUserScreenItem(userNo, input, THEME_NAME);
		updateUserScreenItem(userNo, input, ACCOUNTING_GROUP_CODE);
		updateUserScreenItem(userNo, input, SALES_OBJECT_CODE);
		updateUserScreenItem(userNo, input, SOLD_FLG);
	}

}
