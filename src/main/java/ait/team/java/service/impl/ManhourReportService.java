package ait.team.java.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ait.team.java.controller.output.manhourReport.GroupScreenManhourReport;
import ait.team.java.controller.output.manhourReport.SaveOutputRes;
import ait.team.java.common.CommonUtils;
import ait.team.java.controller.input.InputManhourReport;
import ait.team.java.controller.output.manhourReport.ExportManhourReport;
import ait.team.java.controller.output.manhourReport.GroupItem;
import ait.team.java.controller.output.manhourReport.ScreenManhourReport;
import ait.team.java.controller.output.manhourReport.ThemeFirstCreenManhourReport;
import ait.team.java.controller.output.manhourReport.UserScreenManhourReport;
import ait.team.java.controller.output.manhourReport.WorkContentFirstScreenManhourReport;
import ait.team.java.entity.GroupEntity;
import ait.team.java.entity.ManhourEntity;
import ait.team.java.entity.ThemeEntity;
import ait.team.java.entity.UserEntity;
import ait.team.java.entity.UserScreenItemEntity;
import ait.team.java.entity.WorkContentsEntity;
import ait.team.java.entity.idclass.WorkContentId;
import ait.team.java.repository.GroupRepository;
import ait.team.java.repository.ManhourRepository;
import ait.team.java.repository.ThemeRepository;
import ait.team.java.repository.UserRepository;
import ait.team.java.repository.UserScreenItemRepository;
import ait.team.java.repository.WorkContentRepository;
import ait.team.java.service.IManhourReportService;

@Service
public class ManhourReportService implements IManhourReportService {

	@Autowired
	UserScreenItemRepository userScreenItemRepository;
	@Autowired
	GroupRepository groupRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ThemeRepository themeRepository;
	@Autowired
	WorkContentRepository workContentRepository;
	@Autowired
	ManhourRepository manhourRepository;
	
	private final String ERR_012 = "期間のFromとToの日付が逆転しています";
	private final String ERR_013 = "日別合計を選択した場合、期間は１カ月(３１日)以内としてください";
	private final String ERR_014 = "表示項目には所属、ユーザ、テーマ、作業内容、作業内容詳細のいずれか１つを選択してください";
	private final String ERR_015 = "表示項目には全体合計、月別合計、日別合計のいずれか１つを選択してください";
	
	private final String SUCCESS = "保存が正常終了しました";
	private final String DATE_FORMAT = "YYYY/mm/dd";

	private final String FORMAT_YYYYMMDD = "yyyy/MM/dd";

	private final String[] SHOW_ITEM_ARR = new String[] { "所属", "ユーザ", "テーマ", "作業内容", "作業内容詳細", "全体合計", "月別合計",
			"日別合計" };

	private final String SCREEN_URL = "ManhourReport";
	private final String DATE_FORMAT_YYYYMMDDHHMMSS = "YYYYMMDDHHMMSS";
	private final String DATE_FORMAT_YYYYMMDD = "dd/MM/yyyy";

	@Override
	public ScreenManhourReport getUserScreenItem(String userNo, String saveName) {

		ScreenManhourReport lastScreenManhourReport = new ScreenManhourReport();

		UserScreenItemEntity findLastSaveName = userScreenItemRepository.findLastSaveName(userNo, "ManhourReport");

		if (findLastSaveName != null) {

			// get day now
			Date date = new Date();
			String now = CommonUtils.dateToString(date, FORMAT_YYYYMMDD);

			// check saveName empty
			if (saveName == null) {
				saveName = findLastSaveName.getScreenInput();
			}
			List<UserScreenItemEntity> findLastUserNoAndScreenUrl = userScreenItemRepository
					.findLastUserNoAndScreenUrl(userNo, "ManhourReport", saveName);
			if (findLastUserNoAndScreenUrl.size()==0) {
				List<UserScreenItemEntity> itemEntities = userScreenItemRepository.findByUserNoAndScreenUrl(userNo, SCREEN_URL);
				for (UserScreenItemEntity userScreenItemEntity : itemEntities) {
					String SVN = userScreenItemEntity.getSaveName();
					findLastUserNoAndScreenUrl = userScreenItemRepository
							.findLastUserNoAndScreenUrl(userNo, "ManhourReport", SVN);
					if(findLastUserNoAndScreenUrl.size()!=0) {
						break;
					}
				}
			}

			// get list saveName
			List<String> saveNameList = new ArrayList<>();

			// get list group screen
			List<GroupScreenManhourReport> groupList = new ArrayList<>();

			// get list theme screen
			List<ThemeFirstCreenManhourReport> themeList = new ArrayList<>();

			// get startDay
			String startDate = now;

			// get endDay;
			String endDate = now;

			// get showList
			List<String> showList = new ArrayList<>();

			// get hideList
			List<String> hideList = new ArrayList<>();

			// total
			boolean total = false;

			// delimiter
			boolean comma = false;

			// apostrophe
			boolean apostrophe = false;

			saveNameList = userScreenItemRepository.getSaveName(userNo, SCREEN_URL);

			for (UserScreenItemEntity screenItem : findLastUserNoAndScreenUrl) {

				int index_ = screenItem.getScreenItem().indexOf("_");
				String item = screenItem.getScreenItem();
				if (index_ != -1) {
					item = screenItem.getScreenItem().split("_")[0];
				}
				switch (item) {
				case "group":
					int indexGroup = Integer.parseInt(screenItem.getScreenItem().split("_")[1]);

					GroupScreenManhourReport groupFirstScreenManhourReport = new GroupScreenManhourReport();
					// find group from group_code
					GroupEntity groupEntity = groupRepository.getOne(screenItem.getScreenInput());
					String showGroup = groupEntity.getGroupCode() + "[" + groupEntity.getGroupName() + " "
							+ groupEntity.getAccountingGroupName();

					groupFirstScreenManhourReport.setGroupName(showGroup);
					groupFirstScreenManhourReport.setGroupCode(screenItem.getScreenInput());

					groupFirstScreenManhourReport.setUserList(null);

					// list user item of group
					List<UserEntity> userItemList = userRepository.findByGroupCode(screenItem.getScreenInput());
					List<UserScreenManhourReport> userFirstScreenManhourReportList = new ArrayList<>();
					for (UserEntity userEntity : userItemList) {
						UserScreenManhourReport userFirstScreenManhourReport = new UserScreenManhourReport();
						String showUser = userEntity.getUserNo() + "[" + userEntity.getUserName() + "]";
						userFirstScreenManhourReport.setUserName(showUser);
						userFirstScreenManhourReport.setUserNo(userEntity.getUserNo());
						userFirstScreenManhourReportList.add(userFirstScreenManhourReport);
					}
					groupFirstScreenManhourReport.setUserItemList(userFirstScreenManhourReportList);

					// add null sizeGroup -> indexGroup
					int sizeGroup = groupList.size();
					if (sizeGroup <= indexGroup) {
						for (int i = sizeGroup; i <= indexGroup; i++) {
							groupList.add(i, null);
						}
					}
					// add group by index group
					groupList.set(indexGroup, groupFirstScreenManhourReport);
					break;
				case "theme":
					int indexTheme = Integer.parseInt(screenItem.getScreenItem().split("_")[1]);
					// add null sizeUser -> indexGroupUser
					int sizeTheme = themeList.size();
					if (sizeTheme <= indexTheme) {
						for (int i = sizeTheme; i <= indexTheme; i++) {
							themeList.add(i, null);
						}
					}
					ThemeFirstCreenManhourReport themeFirstCreenManhourReport = new ThemeFirstCreenManhourReport();

					ThemeEntity themeEntity = themeRepository.getOne(screenItem.getScreenInput());
					String showTheme = themeEntity.getThemeNo() + "[" + themeEntity.getThemeName1() + "]";
					themeFirstCreenManhourReport.setNameFirst(showTheme);
					themeFirstCreenManhourReport.setThemeNo(screenItem.getScreenInput());

					// add list workContentItem of theme
					List<WorkContentsEntity> workContentsEntityList = workContentRepository
							.getByWorkContentsClass(themeEntity.getWorkContentsClass());
					List<WorkContentFirstScreenManhourReport> workContentItemList = new ArrayList<>();
					for (WorkContentsEntity workContentEntity : workContentsEntityList) {
						WorkContentFirstScreenManhourReport workContentFirstScreenManhourReport = new WorkContentFirstScreenManhourReport();

						String nameFirst = workContentEntity.getWorkContentsCode() + "["
								+ workContentEntity.getWorkContentsCodeName() + "]";
						String workContentCode = workContentEntity.getWorkContentsCode();
						workContentFirstScreenManhourReport.setWorkContentName(nameFirst);
						workContentFirstScreenManhourReport.setWorkContentCode(workContentCode);
						workContentItemList.add(workContentFirstScreenManhourReport);
					}

					themeFirstCreenManhourReport.setWorkContentList(workContentItemList);
					themeFirstCreenManhourReport.setWorkContent(null);
					// add theme
					themeList.set(indexTheme, themeFirstCreenManhourReport);
					break;
				case "startDate":
					startDate = screenItem.getScreenInput();
					break;
				case "endDate":
					endDate = screenItem.getScreenInput();
					break;
				case "show":
					int indexShowList = Integer.parseInt(screenItem.getScreenItem().split("_")[1]);
					int sizeShowList = showList.size();
					if (sizeShowList <= indexShowList) {
						for (int i = sizeShowList; i <= indexShowList; i++) {
							showList.add(i, null);
						}
					}
					showList.set(indexShowList, screenItem.getScreenInput());
					break;
				case "hide":
					int indexHideList = Integer.parseInt(screenItem.getScreenItem().split("_")[1]);
					int sizeHideList = hideList.size();
					if (sizeHideList <= indexHideList) {
						for (int i = sizeHideList; i <= indexHideList; i++) {
							hideList.add(i, null);
						}
					}
					hideList.set(indexHideList, screenItem.getScreenInput());
					break;
				case "total":
					total = Boolean.valueOf(screenItem.getScreenInput());
					break;
				case "comma":
					comma = Boolean.valueOf(screenItem.getScreenInput());
					break;
				case "apostrophe":
					apostrophe = Boolean.valueOf(screenItem.getScreenInput());
					break;
				default:
					break;
				}
			}

			for (UserScreenItemEntity screenItem : findLastUserNoAndScreenUrl) {
				int index_ = screenItem.getScreenItem().indexOf("_");
				String item = screenItem.getScreenItem();
				if (index_ != -1) {
					item = screenItem.getScreenItem().split("_")[0];
				}
				switch (item) {
				case "user":
					// get group index
					int indexGroupUser = Integer.parseInt(screenItem.getScreenItem().split("_")[1]);
					// get user index of group
					int indexUserOfGroup = Integer.parseInt(screenItem.getScreenItem().split("_")[2]);

					GroupScreenManhourReport groupFirstScreenManhourReport = groupList.get(indexGroupUser);
					List<UserScreenManhourReport> userFirstScreenManhourReportList = groupList.get(indexGroupUser)
							.getUserList();
					if (userFirstScreenManhourReportList == null) {
						userFirstScreenManhourReportList = new ArrayList<>();
					}

					// add null sizeUser -> indexGroupUser
					int sizeUser = userFirstScreenManhourReportList.size();
					if (sizeUser <= indexUserOfGroup) {
						for (int i = sizeUser; i <= indexUserOfGroup; i++) {
							userFirstScreenManhourReportList.add(i, null);
						}
					}

					UserScreenManhourReport userFirstScreenManhourReport = new UserScreenManhourReport();
					UserEntity userEntity = userRepository.getOne(screenItem.getScreenInput());
					String showUser = userEntity.getUserNo() + "[" + userEntity.getUserName() + "]";
					userFirstScreenManhourReport.setUserName(showUser);
					userFirstScreenManhourReport.setUserNo(screenItem.getScreenInput());

					userFirstScreenManhourReportList.set(indexUserOfGroup, userFirstScreenManhourReport);

					groupFirstScreenManhourReport.setUserList(userFirstScreenManhourReportList);

					// set list user item
					List<UserScreenManhourReport> userScreenManhourReportList = new ArrayList<>();
					for (UserScreenManhourReport userScreenManhourReport : groupFirstScreenManhourReport
							.getUserItemList()) {
						if (!userScreenManhourReport.getUserNo().equals(screenItem.getScreenInput())) {
							userScreenManhourReportList.add(userScreenManhourReport);
						}
					}
					groupFirstScreenManhourReport.setUserItemList(userScreenManhourReportList);

					groupList.set(indexGroupUser, groupFirstScreenManhourReport);

					break;
				case "workContent":
					// get group index
					int indexWorkContent = Integer.parseInt(screenItem.getScreenItem().split("_")[1]);
					ThemeFirstCreenManhourReport themeFirstCreenManhourReport = themeList.get(indexWorkContent);
					WorkContentFirstScreenManhourReport workContentFirstScreenManhourReport = themeFirstCreenManhourReport
							.getWorkContent();

					String themeCode = themeList.get(indexWorkContent).getThemeNo();
					String workContentClass = themeRepository.getOne(themeCode).getWorkContentsClass();
					// set workContentId
					WorkContentId workContentId = new WorkContentId();
					workContentId.setWorkContentsClass(workContentClass);
					workContentId.setWorkContentsCode(screenItem.getScreenInput());

					if (workContentFirstScreenManhourReport == null) {
						WorkContentsEntity workContenstByCodeEntity = workContentRepository.getOne(workContentId);

						String nameFirst = workContenstByCodeEntity.getWorkContentsCode() + "["
								+ workContenstByCodeEntity.getWorkContentsCodeName() + "]";

						workContentFirstScreenManhourReport = new WorkContentFirstScreenManhourReport();
						workContentFirstScreenManhourReport.setWorkContentName(nameFirst);
						workContentFirstScreenManhourReport.setWorkContentCode(screenItem.getScreenInput());
						themeFirstCreenManhourReport.setWorkContent(workContentFirstScreenManhourReport);
						themeList.set(indexWorkContent, themeFirstCreenManhourReport);
					}

					break;
				case "contentDetail":
					// get content detail index
					int indexWorkContentDetail = Integer.parseInt(screenItem.getScreenItem().split("_")[1]);
					ThemeFirstCreenManhourReport themeContentDetail = themeList.get(indexWorkContentDetail);
					themeContentDetail.setWorkContentDetail(screenItem.getScreenInput());
					break;
				default:
					break;
				}
			}
			// get list group item
			List<GroupItem> groupItemList = new ArrayList<>();
			// add group list
			List<GroupEntity> groupEntities = groupRepository.findAll();
			for (GroupEntity groupEntityLine : groupEntities) {
				GroupItem groupItem = new GroupItem();
				groupItem.setGroupCode(groupEntityLine.getGroupCode());
				String showGroupItem = groupEntityLine.getGroupCode() + "[" + groupEntityLine.getGroupName() + " "
						+ groupEntityLine.getAccountingGroupName();
				groupItem.setGroupName(showGroupItem);

				boolean isGroup = false;
				for (GroupScreenManhourReport group : groupList) {
					if (group.getGroupCode() == groupEntityLine.getGroupCode()) {
						isGroup = true;
						break;
					}
				}
				if (!isGroup) {
					groupItemList.add(groupItem);
				}

			}

			// check show list
			boolean isGroup1 = false;
			boolean isGroup2 = false;
			List<String> showItemList = new ArrayList<>(Arrays.asList(SHOW_ITEM_ARR));
			List<String> hideItemList = new ArrayList<>(showItemList);
			List<String> ItemGroup1List = showItemList.subList(5, 8);
			List<String> ItemGroup2List = showItemList.subList(0, 5);
			for (String show : showList) {
				if (ItemGroup1List.contains(show)) {
					isGroup2 = true;
				} else if (ItemGroup2List.contains(show)) {
					isGroup1 = true;
				}
				hideItemList.remove(show);
			}
			if (isGroup1 && isGroup2) {
				hideList = hideItemList;
			} else {
				showList = showItemList;
				hideList = new ArrayList<>();
			}

			lastScreenManhourReport.setGroupItemList(groupItemList);
			lastScreenManhourReport.setApostrophe(apostrophe);
			lastScreenManhourReport.setCheckNone(true);
			lastScreenManhourReport.setComma(comma);
			lastScreenManhourReport.setEndDate(endDate);
			lastScreenManhourReport.setGroupList(groupList.size() == 0 ? null : groupList);
			lastScreenManhourReport.setHideList(hideList);
			lastScreenManhourReport.setShowList(showList);
			lastScreenManhourReport.setStartDate(startDate);
			lastScreenManhourReport.setThemeList(themeList.size() == 0 ? null : themeList);
			lastScreenManhourReport.setTotal(total);
			lastScreenManhourReport.setSaveNameList(saveNameList);
			lastScreenManhourReport.setSaveName(saveName);
			return lastScreenManhourReport;
		} else {
			return noneSaveName(userNo);
		}
	}

	public ScreenManhourReport noneSaveName(String userNo) {
		// get list group item
		List<GroupItem> groupItemList = new ArrayList<>();
		// add group list
		List<GroupEntity> groupEntities = groupRepository.findAll();
		for (GroupEntity groupEntityLine : groupEntities) {
			GroupItem groupItem = new GroupItem();
			groupItem.setGroupCode(groupEntityLine.getGroupCode());
			String showGroupItem = groupEntityLine.getGroupCode() + "[" + groupEntityLine.getGroupName() + " "
					+ groupEntityLine.getAccountingGroupName();
			groupItem.setGroupName(showGroupItem);
			groupItemList.add(groupItem);
		}

		// get day now
		Date date = new Date();
		String now = CommonUtils.dateToString(date, FORMAT_YYYYMMDD);
		// get startDay
		String startDate = now;

		// get endDay;
		String endDate = now;

		List<String> showList = new ArrayList<>(Arrays.asList(SHOW_ITEM_ARR));
		List<String> hideList = new ArrayList<>();
		// total
		boolean total = false;

		// delimiter
		boolean comma = false;

		// apostrophe
		boolean apostrophe = false;

		ScreenManhourReport screenManhourReport = new ScreenManhourReport();
		screenManhourReport.setApostrophe(apostrophe);
		screenManhourReport.setCheckNone(false);
		screenManhourReport.setComma(comma);
		screenManhourReport.setEndDate(endDate);
		screenManhourReport.setGroupItemList(groupItemList);
		screenManhourReport.setGroupList(null);
		screenManhourReport.setHideList(hideList);
		screenManhourReport.setSaveName("");
		screenManhourReport.setSaveNameList(null);
		screenManhourReport.setShowList(showList);
		screenManhourReport.setStartDate(startDate);
		screenManhourReport.setThemeList(null);
		screenManhourReport.setTotal(total);
		return screenManhourReport;
	}

	@Transactional
	public boolean saveUserScreenItem(InputManhourReport inputManhourReport, String userNo) {

		List<String> removeItemList = new ArrayList<>();

		int index = 0;
		Date dateNow = new Date();
		String nowFormat = CommonUtils.dateToString(dateNow, DATE_FORMAT_YYYYMMDDHHMMSS);
		String indexStr;
		String surrogateKey;
		List<UserScreenItemEntity> userScreenItemEntityList = new ArrayList<>();

		UserScreenItemEntity commonUserScreenEntity = new UserScreenItemEntity();
		commonUserScreenEntity.setSaveName(inputManhourReport.getSaveName());
		commonUserScreenEntity.setScreenUrl(SCREEN_URL);
		commonUserScreenEntity.setUserNo(userNo);

		// set startDate
		removeItemList.add("startDate");
		UserScreenItemEntity startDateEntity = new UserScreenItemEntity(commonUserScreenEntity);
		startDateEntity.setScreenInput(inputManhourReport.getStartDate());
		startDateEntity.setScreenItem("startDate");
		// check empty in database
		List<UserScreenItemEntity> userScreenItemCheckEntity = userScreenItemRepository
				.findByUserNoAndScreenUrlAndScreenItemAndSaveName(userNo, SCREEN_URL, "startDate",
						inputManhourReport.getSaveName());
		if (userScreenItemCheckEntity.size() != 0) {
			startDateEntity.setSurrogateKey(userScreenItemCheckEntity.get(0).getSurrogateKey());
		} else {
			indexStr = String.format("%03d", index);
			surrogateKey = userNo + nowFormat + indexStr;
			startDateEntity.setSurrogateKey(surrogateKey);
		}

		userScreenItemEntityList.add(startDateEntity);

		// set endDate
		removeItemList.add("endDate");
		UserScreenItemEntity endDateEntity = new UserScreenItemEntity(commonUserScreenEntity);
		endDateEntity.setScreenItem("endDate");
		endDateEntity.setScreenInput(inputManhourReport.getEndDate());
		userScreenItemCheckEntity = userScreenItemRepository.findByUserNoAndScreenUrlAndScreenItemAndSaveName(userNo,
				SCREEN_URL, "endDate", inputManhourReport.getSaveName());
		if (userScreenItemCheckEntity.size() != 0) {
			endDateEntity.setSurrogateKey(userScreenItemCheckEntity.get(0).getSurrogateKey());
		} else {
			indexStr = String.format("%03d", ++index);
			surrogateKey = userNo + nowFormat + indexStr;
			endDateEntity.setSurrogateKey(surrogateKey);
		}
		userScreenItemEntityList.add(endDateEntity);

		// set total
		removeItemList.add("total");
		UserScreenItemEntity totalEntity = new UserScreenItemEntity(commonUserScreenEntity);
		totalEntity.setScreenItem("total");
		totalEntity.setScreenInput(inputManhourReport.getTotal());
		userScreenItemCheckEntity = userScreenItemRepository.findByUserNoAndScreenUrlAndScreenItemAndSaveName(userNo,
				SCREEN_URL, "total", inputManhourReport.getSaveName());
		if (userScreenItemCheckEntity.size() != 0) {
			totalEntity.setSurrogateKey(userScreenItemCheckEntity.get(0).getSurrogateKey());
		} else {
			indexStr = String.format("%03d", ++index);
			surrogateKey = userNo + nowFormat + indexStr;
			totalEntity.setSurrogateKey(surrogateKey);
		}
		userScreenItemEntityList.add(totalEntity);

		// set comma
		removeItemList.add("comma");
		UserScreenItemEntity commaEntity = new UserScreenItemEntity(commonUserScreenEntity);
		commaEntity.setScreenItem("comma");
		commaEntity.setScreenInput(inputManhourReport.getComma());
		userScreenItemCheckEntity = userScreenItemRepository.findByUserNoAndScreenUrlAndScreenItemAndSaveName(userNo,
				SCREEN_URL, "comma", inputManhourReport.getSaveName());
		if (userScreenItemCheckEntity.size() != 0) {
			commaEntity.setSurrogateKey(userScreenItemCheckEntity.get(0).getSurrogateKey());
		} else {
			indexStr = String.format("%03d", ++index);
			surrogateKey = userNo + nowFormat + indexStr;
			commaEntity.setSurrogateKey(surrogateKey);
		}
		userScreenItemEntityList.add(commaEntity);

		// set comma
		removeItemList.add("apostrophe");
		UserScreenItemEntity apostropheEntity = new UserScreenItemEntity(commonUserScreenEntity);
		apostropheEntity.setScreenItem("apostrophe");
		apostropheEntity.setScreenInput(inputManhourReport.getApostrophe());
		userScreenItemCheckEntity = userScreenItemRepository.findByUserNoAndScreenUrlAndScreenItemAndSaveName(userNo,
				SCREEN_URL, "apostrophe", inputManhourReport.getSaveName());
		if (userScreenItemCheckEntity.size() != 0) {
			apostropheEntity.setSurrogateKey(userScreenItemCheckEntity.get(0).getSurrogateKey());
		} else {
			indexStr = String.format("%03d", ++index);
			surrogateKey = userNo + nowFormat + indexStr;
			apostropheEntity.setSurrogateKey(surrogateKey);
		}
		userScreenItemEntityList.add(apostropheEntity);

		// set list group
		int indexGroup = 0;
		for (String group : inputManhourReport.getGroupList()) {
			if (groupRepository.findByGroupCode(group).size() > 0) {
				removeItemList.add("group_" + indexGroup);
				UserScreenItemEntity groupEntity = new UserScreenItemEntity(commonUserScreenEntity);
				groupEntity.setScreenItem("group_" + indexGroup);
				groupEntity.setScreenInput(group);
				userScreenItemCheckEntity = userScreenItemRepository.findByUserNoAndScreenUrlAndScreenItemAndSaveName(
						userNo, SCREEN_URL, "group_" + indexGroup, inputManhourReport.getSaveName());
				if (userScreenItemCheckEntity.size() != 0) {
					groupEntity.setSurrogateKey(userScreenItemCheckEntity.get(0).getSurrogateKey());
				} else {
					indexStr = String.format("%03d", ++index);
					surrogateKey = userNo + nowFormat + indexStr;
					groupEntity.setSurrogateKey(surrogateKey);
				}
				userScreenItemEntityList.add(groupEntity);
				indexGroup++;
			}
		}

		// set list user
		List<Integer> indexUserList = new ArrayList<>();
		for (String user : inputManhourReport.getUserList()) {
			if (userRepository.findByUserNo(user) != null) {
				UserScreenItemEntity userEntity = new UserScreenItemEntity(commonUserScreenEntity);
				String groupCode = userRepository.getOne(user).getGroupCode();
				indexGroup = inputManhourReport.getGroupList().indexOf(groupCode);
				if (indexUserList.size() < indexGroup + 1) {
					for (int i = indexUserList.size(); i <= indexGroup; i++) {
						indexUserList.add(0);
					}
				}
				removeItemList.add("user_" + indexGroup + "_" + indexUserList.get(indexGroup));
				userEntity.setScreenItem("user_" + indexGroup + "_" + indexUserList.get(indexGroup));
				userEntity.setScreenInput(user);
				userScreenItemCheckEntity = userScreenItemRepository.findByUserNoAndScreenUrlAndScreenItemAndSaveName(
						userNo, SCREEN_URL, "user_" + indexGroup + "_" + indexUserList.get(indexGroup),
						inputManhourReport.getSaveName());
				if (userScreenItemCheckEntity.size() != 0) {
					userEntity.setSurrogateKey(userScreenItemCheckEntity.get(0).getSurrogateKey());
				} else {
					indexStr = String.format("%03d", ++index);
					surrogateKey = userNo + nowFormat + indexStr;
					userEntity.setSurrogateKey(surrogateKey);
				}
				userScreenItemEntityList.add(userEntity);
				indexUserList.set(indexGroup, indexUserList.get(indexGroup) + 1);
			}

		}

		// set list theme
		int indexTheme = 0;
		for (String theme : inputManhourReport.getThemeList()) {
			if (themeRepository.findByThemeNo(theme) != null) {
				removeItemList.add("theme_" + indexTheme);
				UserScreenItemEntity themeEntity = new UserScreenItemEntity(commonUserScreenEntity);
				themeEntity.setScreenItem("theme_" + indexTheme);
				themeEntity.setScreenInput(theme);
				userScreenItemCheckEntity = userScreenItemRepository.findByUserNoAndScreenUrlAndScreenItemAndSaveName(
						userNo, SCREEN_URL, "theme_" + indexTheme, inputManhourReport.getSaveName());
				if (userScreenItemCheckEntity.size() != 0) {
					themeEntity.setSurrogateKey(userScreenItemCheckEntity.get(0).getSurrogateKey());
				} else {
					indexStr = String.format("%03d", ++index);
					surrogateKey = userNo + nowFormat + indexStr;
					themeEntity.setSurrogateKey(surrogateKey);
				}
				userScreenItemEntityList.add(themeEntity);
				indexTheme++;
			}
		}

		// set list workContent
		int indexWorkcontent = 0;
		for (String workContent : inputManhourReport.getWorkContentList()) {
			String themeCode = inputManhourReport.getThemeList().get(indexWorkcontent);
			ThemeEntity themeEntity = themeRepository.findByThemeNo(themeCode);
			if (themeEntity != null) {
				UserScreenItemEntity workContentEntity = new UserScreenItemEntity(commonUserScreenEntity);
				String workContentClass = themeEntity.getWorkContentsClass();
				// set workContentId
				WorkContentId workContentId = new WorkContentId();
				workContentId.setWorkContentsClass(workContentClass);
				workContentId.setWorkContentsCode(workContent);
				if (workContentRepository.findByWorkContentsClassAndWorkContentsCode(workContentClass,
						workContent) != null) {
					removeItemList.add("workContent_" + indexWorkcontent);
					workContentEntity.setScreenItem("workContent_" + indexWorkcontent);
					workContentEntity.setScreenInput(workContent);
					userScreenItemCheckEntity = userScreenItemRepository
							.findByUserNoAndScreenUrlAndScreenItemAndSaveName(userNo, SCREEN_URL,
									"workContent_" + indexWorkcontent, inputManhourReport.getSaveName());
					if (userScreenItemCheckEntity.size() != 0) {
						workContentEntity.setSurrogateKey(userScreenItemCheckEntity.get(0).getSurrogateKey());
					} else {
						indexStr = String.format("%03d", ++index);
						surrogateKey = userNo + nowFormat + indexStr;
						workContentEntity.setSurrogateKey(surrogateKey);
					}
					userScreenItemEntityList.add(workContentEntity);
				}
			}

		}

		// set list workContentDetail
		int indexWorkcontentDetail = 0;
		for (String wctDetail : inputManhourReport.getWorkContentDetailList()) {
			if (indexWorkcontentDetail < indexTheme && wctDetail.matches("-?\\d+(\\.\\d+)?") && wctDetail.equals("")) {
				if (Integer.parseInt(wctDetail) >= 0 && Integer.parseInt(wctDetail) <= 99) {
					removeItemList.add("contentDetail_" + indexWorkcontentDetail);
					UserScreenItemEntity wctDetailEntity = new UserScreenItemEntity(commonUserScreenEntity);
					wctDetailEntity.setScreenItem("contentDetail_" + indexWorkcontentDetail);
					wctDetailEntity.setScreenInput(wctDetail);
					userScreenItemCheckEntity = userScreenItemRepository
							.findByUserNoAndScreenUrlAndScreenItemAndSaveName(userNo, SCREEN_URL,
									"contentDetail_" + indexWorkcontentDetail, inputManhourReport.getSaveName());
					if (userScreenItemCheckEntity.size() != 0) {
						wctDetailEntity.setSurrogateKey(userScreenItemCheckEntity.get(0).getSurrogateKey());
					} else {
						indexStr = String.format("%03d", ++index);
						surrogateKey = userNo + nowFormat + indexStr;
						wctDetailEntity.setSurrogateKey(surrogateKey);
					}
					userScreenItemEntityList.add(wctDetailEntity);
					indexWorkcontentDetail++;
				}
			}
		}

		// set list hide
		int indexHide = 0;
		for (String hide : inputManhourReport.getHideList()) {
			removeItemList.add("hide_" + indexHide);
			UserScreenItemEntity hideEntity = new UserScreenItemEntity(commonUserScreenEntity);
			hideEntity.setScreenItem("hide_" + indexHide);
			hideEntity.setScreenInput(hide);
			userScreenItemCheckEntity = userScreenItemRepository.findByUserNoAndScreenUrlAndScreenItemAndSaveName(
					userNo, SCREEN_URL, "hide_" + indexHide, inputManhourReport.getSaveName());
			if (userScreenItemCheckEntity.size() != 0) {
				hideEntity.setSurrogateKey(userScreenItemCheckEntity.get(0).getSurrogateKey());
			} else {
				indexStr = String.format("%03d", ++index);
				surrogateKey = userNo + nowFormat + indexStr;
				hideEntity.setSurrogateKey(surrogateKey);
			}
			userScreenItemEntityList.add(hideEntity);
			indexHide++;
		}

		// set list show
		int indexShow = 0;
		for (String hide : inputManhourReport.getShowList()) {
			removeItemList.add("show_" + indexShow);
			UserScreenItemEntity showEntity = new UserScreenItemEntity(commonUserScreenEntity);
			showEntity.setScreenItem("show_" + indexShow);
			showEntity.setScreenInput(hide);
			userScreenItemCheckEntity = userScreenItemRepository.findByUserNoAndScreenUrlAndScreenItemAndSaveName(
					userNo, SCREEN_URL, "show_" + indexShow, inputManhourReport.getSaveName());
			if (userScreenItemCheckEntity.size() != 0) {
				showEntity.setSurrogateKey(userScreenItemCheckEntity.get(0).getSurrogateKey());
			} else {
				indexStr = String.format("%03d", ++index);
				surrogateKey = userNo + nowFormat + indexStr;
				showEntity.setSurrogateKey(surrogateKey);
			}
			userScreenItemEntityList.add(showEntity);
			indexShow++;
		}
		List<UserScreenItemEntity> userScreenItemEntitySaveName = userScreenItemRepository
				.findByUserNoAndScreenUrlAndScreenItem(userNo, SCREEN_URL, "saveName");
		try {
			if (userScreenItemEntitySaveName.size() > 0) {
				userScreenItemEntitySaveName.get(0).setScreenInput(inputManhourReport.getSaveName());
				userScreenItemRepository.save(userScreenItemEntitySaveName.get(0));
			} else {
				UserScreenItemEntity userScreenItemEntity = new UserScreenItemEntity(commonUserScreenEntity);
				userScreenItemEntity.setSaveName("");
				userScreenItemEntity.setScreenInput(inputManhourReport.getSaveName());
				userScreenItemEntity.setScreenItem("saveName");
				userScreenItemEntity.setScreenUrl(SCREEN_URL);
				indexStr = String.format("%03d", ++index);
				surrogateKey = userNo + nowFormat + indexStr;
				userScreenItemEntity.setSurrogateKey(surrogateKey);
				userScreenItemEntity.setUserNo(userNo);
				userScreenItemRepository.save(userScreenItemEntity);
			}

			for (UserScreenItemEntity userScreenItemEntity : userScreenItemEntityList) {
				userScreenItemRepository.save(userScreenItemEntity);
			}

			userScreenItemRepository.removeItem(userNo, SCREEN_URL, inputManhourReport.getSaveName(), removeItemList);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<UserScreenManhourReport> getUserByGroup(String groupCode) {
		// list user item of group
		List<UserEntity> userItemList = userRepository.findByGroupCode(groupCode);
		List<UserScreenManhourReport> userFirstScreenManhourReportList = new ArrayList<>();
		for (UserEntity userEntity : userItemList) {
			UserScreenManhourReport userFirstScreenManhourReport = new UserScreenManhourReport();
			String showUser = userEntity.getUserNo() + "[" + userEntity.getUserName() + "]";
			userFirstScreenManhourReport.setUserName(showUser);
			userFirstScreenManhourReport.setUserNo(userEntity.getUserNo());
			userFirstScreenManhourReportList.add(userFirstScreenManhourReport);
		}
		return userFirstScreenManhourReportList;
	}

	@Override
	public List<String[]> exportManhourReport(InputManhourReport inputManhourReport, String userNo)
			throws ParseException {

		List<ManhourEntity> manhourEntityList = new ArrayList<>();

		String startDate = inputManhourReport.getStartDate();
		String[] startArr = startDate.split("/");
		String endDate = inputManhourReport.getEndDate();
		String[] endArr = endDate.split("/");
		List<String> workContentDetailList = inputManhourReport.getWorkContentDetailList();
		List<String> workContentList = inputManhourReport.getWorkContentList();
		List<String> groupList = inputManhourReport.getGroupList();
		List<String> userList = inputManhourReport.getUserList();
		List<String> themList = inputManhourReport.getThemeList();

		int indexTheme = 0;
		for(int year = Integer.parseInt(startArr[0]); year <= Integer.parseInt(endArr[0]); year++) {
			int monthStart = 0;
			int monthEnd = 0;
			if(Integer.parseInt(startArr[0]) == Integer.parseInt(endArr[0])) {
				monthStart = Integer.parseInt(startArr[1]);
				monthEnd = Integer.parseInt(endArr[1]);
			}else if(year == Integer.parseInt(startArr[0])) {
				monthStart = Integer.parseInt(startArr[1]);
				monthEnd = 12;
			}else if(year == Integer.parseInt(endArr[0])){
				monthStart = 1;
				monthEnd = Integer.parseInt(endArr[1]);
			}else {
				monthStart = 1;
				monthEnd = 12;
			}
			for(int month = monthStart; month <= monthEnd; month++) {
				for (String themeCode : themList) {
					List<String> workContentByThemeList = new ArrayList<>();
					if (!themeCode.equals("") && workContentList.get(indexTheme).equals("")) {
						List<WorkContentsEntity> workContentEntityList = workContentRepository
								.findAllWorkContentByTheme(themeCode);
						for (WorkContentsEntity workContentsEntity : workContentEntityList) {
							workContentByThemeList.add(workContentsEntity.getWorkContentsCode());
						}
					} else {
						if(workContentList.size() > indexTheme) {
							workContentByThemeList.add(workContentList.get(indexTheme));
						}
					}
					String workContentDetail = "";
					if(workContentDetailList.size() > indexTheme) {
						workContentDetail = workContentDetailList.get(indexTheme);
					}
					
					for (String groupCode : groupList) {
						List<String> users = new ArrayList<>();
						boolean checkUserByGroup = false;
						List<UserEntity> userEntityList = userRepository.findByGroupCode(groupCode);
						List<String> userCodeList = new ArrayList<>();
						if (userEntityList != null) {
							for (UserEntity userEntity : userEntityList) {
								userCodeList.add(userEntity.getUserNo());
								if (userList.contains(userEntity.getUserNo())) {
									users.add(userEntity.getUserNo());
									checkUserByGroup = true;
								}
							}
							if (!checkUserByGroup) {
								users = userCodeList;
							}
						} else {
							users.add("");
						}
					
						List<ManhourEntity> entitiyInMonthList = manhourRepository.searchReport(year, month,
										themeCode, workContentByThemeList, workContentDetail, groupCode, users);
						if(entitiyInMonthList.size()!=0) {
							manhourEntityList.addAll(entitiyInMonthList);
						}
						
					}
					indexTheme++;
				}
			}
		}
		
		
		List<String[]> exportManhourReportListArr = convertEntityToListArray(startArr, endArr, manhourEntityList, inputManhourReport);
		return exportManhourReportListArr;
	}
	
	public List<String[]> convertEntityToListArray(String[] startArr, String[] endArr,List<ManhourEntity> manhourEntityList, InputManhourReport inputManhourReport) throws ParseException{
		
		// convert to exportManhourReport
		boolean checkGroup = false;
		boolean checkUser = false;
		boolean checkWorkContent = false;
		boolean checkDetail = false;
		boolean checkTheme = false;
		boolean checkMonth = false;
		boolean checkDay = false;
		boolean checkTotalMonth = false;
		
		for (String show : inputManhourReport.getShowList()) {
			switch (show) {
				case "全体合計":
					checkTotalMonth = true;
					break;
				case "月別合計":
					checkMonth = true;
					break;
				case "日別合計":
					checkDay = true;
					break;
				case "所属":
					checkGroup = true;
					break;
				case "ユーザ":
					checkUser = true;
					break;
				case "テーマ":
					checkTheme = true;
					break;
				case "作業内容":
					checkWorkContent = true;
					break;
				case "作業内容詳細":
					checkDetail = true;
					break;
					
				default:
					break;
			}
		}
		
		boolean[] show = {checkGroup, checkUser, checkTheme, checkWorkContent, checkDetail, checkTotalMonth, checkMonth, checkDay};
		
		int startYear = Integer.parseInt(startArr[0]);
		int endYear = Integer.parseInt(endArr[0]);
		int startMonth = Integer.parseInt(startArr[1]);
		int endMonth = Integer.parseInt(endArr[1]);
		int startDay = Integer.parseInt(startArr[2]);
		int endDay = Integer.parseInt(endArr[2]);
		
		int[] date = {startYear, endYear, startMonth, endMonth, startDay, endDay};
		
		List<ExportManhourReport> exportManhourReportList = new ArrayList<>();
		List<String> checkManhourList = new ArrayList<>();
		ExportManhourReport exportManhourReportTotal = new ExportManhourReport();
		exportManhourReportTotal.setGroupCode("合計");
		if(manhourEntityList != null) {
			List<Integer> checkEmpty = new ArrayList<>();
			List<Integer> dayHour = new ArrayList<>();
			List<Integer> monthHour = new ArrayList<>();
			List<Integer> totalDayHour = new ArrayList<>();
			List<Integer> totalMonthHour = new ArrayList<>();
			int startCheck = startMonth;
			int startCheckYear = startYear;
			manhourEntityList.add(null);
			for (ManhourEntity manhour : manhourEntityList) {
				int yearManhour = 0;
				int monthManhour = 0;
				if(manhour == null) {
					yearManhour = endYear;
					monthManhour = endMonth+1;
				}else {
					yearManhour = manhour.getYear();
					monthManhour = manhour.getMonth();
				}
				if(startCheck != monthManhour) {
					int checkFirstMonthLoop = 0;
					for (int yearIndex = startCheckYear; yearIndex <= yearManhour; yearIndex++) {
						int monthStart = 0;
						int monthEnd = 0;
						if(startCheckYear == yearManhour) {
							monthStart = startCheck;
							monthEnd = monthManhour-1;
						}else if(yearIndex == startCheckYear) {
							monthStart = startCheck;
							monthEnd = 12;
						}else if(yearIndex == yearManhour){
							monthStart = 1;
							monthEnd = monthManhour-1;
						}else {
							monthStart = 1;
							monthEnd = 12;
						}
						for (int monthIndex = monthStart; monthIndex <= monthEnd; monthIndex++) {
							dayHour = new ArrayList<>();
							monthHour = new ArrayList<>();
							String firstDayMonth = yearIndex + "/" + monthIndex + "/1";
							Date dateFirstNextMonth = new SimpleDateFormat(DATE_FORMAT_YYYYMMDD).parse(firstDayMonth);
							Calendar calendarFirstMonth = Calendar.getInstance();
							calendarFirstMonth.setTime(dateFirstNextMonth);
							int startLoop = 0;
							int endLoop = 0;
							boolean checkMonthYear = (startMonth == monthIndex && monthIndex == endMonth
									&& startYear == yearIndex && yearIndex == endYear);
							if (checkMonthYear) {
								startLoop = startDay;
								endLoop = endDay;
							} else if (monthIndex == startMonth && startYear == yearIndex) {
								startLoop = startDay;
								endLoop = calendarFirstMonth.getMaximum(Calendar.DATE);
							} else if (monthIndex == endMonth && yearIndex == endYear) {
								startLoop = 1;
								endLoop = endDay;
							} else {
								startLoop = 1;
								endLoop = calendarFirstMonth.getMaximum(Calendar.DATE);
							}
							//set list day
							if(checkDay) {
								for (int k = startLoop; k <= endLoop; k++) {
									dayHour.add(0);
								}
							}
							monthHour.add(0);
							totalDayHour.addAll(dayHour);
							totalMonthHour.add(0);
							
							int index = 0;
							for (ExportManhourReport exportManhourReport : exportManhourReportList) {
								if(checkEmpty.indexOf(index) == -1 || checkFirstMonthLoop != 0) {
									List<Integer> dayNone = exportManhourReport.getDayHour();
									if(dayNone != null) {
										dayNone.addAll(dayHour);
										exportManhourReport.setDayHour(dayNone);
									}
									List<Integer> monthNone = exportManhourReport.getMonthHour();
									if(monthNone != null) {
										monthNone.add(0);
										exportManhourReport.setMonthHour(monthNone);
									}else {
										List<Integer> monthHourFirst = new ArrayList<>();
										monthHourFirst.add(0);
										exportManhourReport.setMonthHour(monthHourFirst);
									}
								}
								index++;
							}
							
							if(checkEmpty.size() == 0 || checkFirstMonthLoop > 0) {
								List<Integer> dayNone = exportManhourReportTotal.getDayHour();
								if(dayNone != null) {
									dayNone.addAll(dayHour);
									exportManhourReportTotal.setDayHour(dayNone);
								}
								
								List<Integer> monthNone = exportManhourReportTotal.getMonthHour();
								if(monthNone != null) {
									monthNone.add(0);
									exportManhourReportTotal.setMonthHour(monthNone);
								}else {
									List<Integer> monthHourFirst = new ArrayList<>();
									monthHourFirst.add(0);
									exportManhourReportTotal.setMonthHour(monthHourFirst);
								}
							}
							
							checkFirstMonthLoop++;
						}
					}
					checkEmpty = new ArrayList<>();
					startCheck = monthManhour;
					startCheckYear = yearManhour;
					
				}
				
				if(manhour != null) {
					List<String> subCheckManHourList = new ArrayList<>();
					subCheckManHourList.add(manhour.getGroupCode());
					subCheckManHourList.add(manhour.getThemeNo());
					subCheckManHourList.add(manhour.getUserNo());
					subCheckManHourList.add(manhour.getWorkContentsClass());
					subCheckManHourList.add(manhour.getWorkContentsCode());
					subCheckManHourList.add(manhour.getWorkContentsDetail());
					String key = String.join("_", subCheckManHourList);
					int indexCheckManHour = checkManhourList.indexOf(key);
					
					if (indexCheckManHour != -1) {
						checkEmpty.add(indexCheckManHour);
						ExportManhourReport exportManhourReport = exportManhourReportList.get(indexCheckManHour);
						List<ExportManhourReport> exportManhourReportLine = convertEntitytoExport(null, null, exportManhourReportTotal,
								exportManhourReport, manhour, date, show, inputManhourReport);
						exportManhourReport = exportManhourReportLine.get(0);
						exportManhourReportTotal = exportManhourReportLine.get(1);
						exportManhourReportList.set(indexCheckManHour, exportManhourReport);
					} else {
						checkEmpty.add(checkManhourList.size());
						List<ExportManhourReport> exportManhourReportLine = convertEntitytoExport(totalDayHour, totalMonthHour, exportManhourReportTotal,
								null, manhour, date, show, inputManhourReport);
						ExportManhourReport exportManhourReport = exportManhourReportLine.get(0);
						exportManhourReportTotal = exportManhourReportLine.get(1);
						checkManhourList.add(key);
						exportManhourReportList.add(exportManhourReport);
					}
				}
				
			}
			
			if(inputManhourReport.getTotal().equals("true")) {
				exportManhourReportList.add(exportManhourReportTotal);
			}
		}
		List<String[]> dataToListArr = convertExportManhourReportToListArr(date, show, exportManhourReportList, inputManhourReport);
		return dataToListArr;
	}
	
	public String[] getHeader(int[] date,boolean[] show, InputManhourReport inputManhourReport) throws ParseException{
		int startYear = date[0];
		int endYear = date[1];
		int startMonth = date[2];
		int endMonth = date[3];
		int startDay = date[4];
		int endDay = date[5];
		
		boolean checkGroup = show[0];
		boolean checkUser = show[1];
		boolean checkWorkContent = show[3];
		boolean checkDetail = show[4];
		boolean checkTheme = show[2];
		boolean checkMonth = show[6];
		boolean checkDay = show[7];
		boolean checkTotalMonth = show[5];
		
		List<String> itemList = new ArrayList<>();
		List<String> monthList = new ArrayList<>();
		List<String> dayList = new ArrayList<>();
		
		if(checkGroup) {
			itemList.add("所属コード");
			itemList.add("所属コード");
		}

		if(checkUser) {
			itemList.add("ユーザNo");
			itemList.add("ユーザ名");
		}

		if(checkTheme) {
			itemList.add("テーマNo");
			itemList.add("テーマ名");
		}

		if(checkWorkContent) {
			itemList.add("作業内容コード");
			itemList.add("作業内容名");
		}
		
		if(checkDetail) {
			itemList.add("作業内容詳細");
		}
		
		if(checkTotalMonth) {
			itemList.add("工数合計");
		}
		
		for (int year = startYear; year <= endYear; year++) {
			int monthStart = 0;
			int monthEnd = 0;
			if(startYear == endYear) {
				monthStart = startMonth;
				monthEnd = endMonth;
			}else if(year == startYear) {
				monthStart = startMonth;
				monthEnd = 12;
			}else if(year == endYear){
				monthStart = 1;
				monthEnd = endMonth;
			}else {
				monthStart = 1;
				monthEnd = 12;
			}
			for (int month = monthStart; month <= monthEnd; month++) {
				if(checkMonth) {
					String headerMonth = month+"/"+year;
					monthList.add(headerMonth);
				}
				if(checkDay) {
					String firstDayMonth = year + "/" + month + "/1";
					Date dateFirstNextMonth = new SimpleDateFormat(DATE_FORMAT_YYYYMMDD).parse(firstDayMonth);
					Calendar calendarFirstMonth = Calendar.getInstance();
					calendarFirstMonth.setTime(dateFirstNextMonth);
					
					int start = 0;
					int end = 0;
					if(startMonth == month && endMonth == month && startYear == endYear) {
						start = startDay;
						end = endDay;
					}else if(startMonth == month && startYear == year) {
						start = startDay;
						end = calendarFirstMonth.getMaximum(Calendar.DATE);
					}else if(endMonth == month && endYear == year) {
						start = 1;
						end = endDay;
					}else {
						start = 1;
						end = calendarFirstMonth.getMaximum(Calendar.DATE);
					}
					for (int day = start; day <= end; day++) {
						String headerDay = day+"/"+month+"/"+year;
						dayList.add(headerDay);
					}
				}
			}
		}
		
		itemList.addAll(monthList);
		itemList.addAll(dayList);
		String[] headerArr = new String[itemList.size()];
		itemList.toArray(headerArr);
		return headerArr;
	}

	public List<ExportManhourReport> convertEntitytoExport(List<Integer> totalDayHour, List<Integer> totalMonthHour, ExportManhourReport exportManhourReportTotal,
			ExportManhourReport exportManhourReport, ManhourEntity manhour, int[] date, boolean[] show, InputManhourReport inputManhourReport) throws ParseException {
		
		int startYear = date[0];
		int endYear = date[1];
		int startMonth = date[2];
		int endMonth = date[3];
		int startDay = date[4];
		int endDay = date[5];

		boolean checkGroup = show[0];
		boolean checkUser = show[1];
		boolean checkWorkContent = show[3];
		boolean checkDetail = show[4];
		boolean checkTheme = show[2];
		boolean checkMonth = show[6];
		boolean checkDay = show[7];
		boolean checkTotalMonth = show[5];
		
		List<ExportManhourReport> exportManhourReportList = new ArrayList<>();
		
		if (exportManhourReport == null) {
			exportManhourReport = new ExportManhourReport();
			List<Integer> monthList = new ArrayList<>(); 
			monthList.addAll(totalMonthHour);
			if(checkMonth) {
				exportManhourReport.setMonthHour(monthList);
			}else {
				exportManhourReport.setMonthHour(null);
				exportManhourReportTotal.setMonthHour(null);
			}
			
			List<Integer> dayList = new ArrayList<>(); 
			dayList.addAll(totalDayHour);
			if(checkDay) {
				exportManhourReport.setDayHour(dayList);
			}else {
				exportManhourReport.setDayHour(null);
				exportManhourReportTotal.setDayHour(null);
			}
			
			if(checkGroup) {
				exportManhourReport.setGroupCode(manhour.getGroupCode());
				String groupName = groupRepository.getOne(manhour.getGroupCode()).getGroupName();
				exportManhourReport.setGroupName(groupName);
			}else {
				exportManhourReport.setGroupCode(null);
				exportManhourReport.setGroupName(null);
				exportManhourReportTotal.setGroupCode(null);
				exportManhourReportTotal.setGroupName(null);
			}

			if(checkUser) {
				String userName = userRepository.getOne(manhour.getUserNo()).getUserName();
				exportManhourReport.setUserName(userName);
				exportManhourReport.setUserNo(manhour.getUserNo());
			}else {
				exportManhourReport.setUserName(null);
				exportManhourReport.setUserNo(null);
				exportManhourReportTotal.setUserName(null);
				exportManhourReportTotal.setUserNo(null);
			}

			ThemeEntity themeEntity = themeRepository.findByThemeNo(manhour.getThemeNo());
			if(checkTheme) {
				String themeName1 = "";
				if(themeEntity != null) {
					themeName1 = themeEntity.getThemeName1();
				}
				exportManhourReport.setThemeName1(themeName1);
				exportManhourReport.setThemeNo(manhour.getThemeNo());
			}else {
				exportManhourReport.setThemeName1(null);
				exportManhourReport.setThemeNo(null);
				exportManhourReportTotal.setThemeName1(null);
				exportManhourReportTotal.setThemeNo(null);
			}

			if(checkWorkContent) {
				exportManhourReport.setWorkContentCode(manhour.getWorkContentsCode());
				String workContentCodeName = "";
				if(themeEntity != null) {
					WorkContentsEntity workContentsEntity = workContentRepository.findByWorkContentsClassAndWorkContentsCode(
							themeEntity.getWorkContentsClass(), manhour.getWorkContentsCode());
					if (workContentsEntity != null) {
						workContentCodeName = workContentsEntity.getWorkContentsCodeName();
					}
				}
				
				exportManhourReport.setWorkContentCodeName(workContentCodeName);
			}else {
				exportManhourReport.setWorkContentCode(null);
				exportManhourReport.setWorkContentCodeName(null);
				exportManhourReportTotal.setWorkContentCode(null);
				exportManhourReportTotal.setWorkContentCodeName(null);
			}
			
			if(checkDetail) {
				exportManhourReport.setWorkContentDetail(manhour.getWorkContentsDetail());
			}else {
				exportManhourReport.setWorkContentDetail(null);
				exportManhourReportTotal.setWorkContentDetail(null);
			}
			
		}

		
		String firstDayMonth = manhour.getYear() + "/" + manhour.getMonth() + "/1";
		Date dateFirstNextMonth = new SimpleDateFormat(DATE_FORMAT_YYYYMMDD).parse(firstDayMonth);
		Calendar calendarFirstMonth = Calendar.getInstance();
		calendarFirstMonth.setTime(dateFirstNextMonth);
		
		int startLoop = 0;
		int endLoop = 0;
		boolean checkMonthYear = (startMonth == manhour.getMonth() && manhour.getMonth() == endMonth
				&& startYear == manhour.getYear() && manhour.getYear() == endYear);
		if (checkMonthYear) {
			startLoop = startDay;
			endLoop = endDay;
		} else if (manhour.getMonth() == startMonth && startYear == manhour.getYear()) {
			startLoop = startDay;
			endLoop = calendarFirstMonth.getMaximum(Calendar.DATE);
		} else if (manhour.getMonth() == endMonth && manhour.getYear() == endYear) {
			startLoop = 1;
			endLoop = endDay;
		} else {
			startLoop = 1;
			endLoop = calendarFirstMonth.getMaximum(Calendar.DATE);
		}
		
		int[] workHour = getWorkHour(manhour);
		
		//set list day
		if(checkDay) {
			List<Integer> dayHour = exportManhourReport.getDayHour();
			List<Integer>  totalDay = exportManhourReportTotal.getDayHour();
			if (totalDay == null) {
				totalDay = new ArrayList<>();
			}
			
			for (int i = startLoop; i <= endLoop; i++) {
				dayHour.add(workHour[i - 1]);
				if(totalDay.size() < dayHour.size()) {
					totalDay.add(workHour[i - 1]);
				}else {
					totalDay.set(dayHour.size()-1,totalDay.get(dayHour.size()-1) + workHour[i - 1]);
				}
			}
			
			//update day manhourReport
			exportManhourReport.setDayHour(dayHour);
			exportManhourReportTotal.setDayHour(totalDay);
		}
		
		if(checkMonth || checkTotalMonth) {
			int monthHourTotal = 0;
			for (int i = 0; i < calendarFirstMonth.getMaximum(Calendar.DATE); i++) {
				monthHourTotal += workHour[i];
			}
			
			if(checkMonth) {
				List<Integer>  monthHour = exportManhourReport.getMonthHour();
				List<Integer>  totalMonth = exportManhourReportTotal.getMonthHour();
				if (totalMonth == null) {
					totalMonth = new ArrayList<>();
				}
				
				monthHour.add(monthHourTotal);
				if(totalMonth.size() < monthHour.size()) {
					totalMonth.add(monthHourTotal);
				}else {
					totalMonth.set(monthHour.size()-1, totalMonth.get(monthHour.size()-1) + monthHourTotal);
				}
				//update month manhourReport
				exportManhourReport.setMonthHour(monthHour);
				exportManhourReportTotal.setMonthHour(totalMonth);
			}
			
			if(checkTotalMonth) {
				// total manhourReport
				int totalAlltotalMonth = Integer.parseInt(exportManhourReportTotal.getTotalMonth() == null ? "0" : exportManhourReportTotal.getTotalMonth());
				int totalMonthLine = Integer.parseInt(exportManhourReport.getTotalMonth() == null ? "0" : exportManhourReport.getTotalMonth()); 
				
				totalMonthLine += monthHourTotal;
				totalAlltotalMonth += monthHourTotal;
				//update totalMonth manhourReport
				exportManhourReportTotal.setTotalMonth(String.valueOf(totalAlltotalMonth));
				exportManhourReport.setTotalMonth(String.valueOf(totalMonthLine));
			}
		}
		
		exportManhourReportList.add(exportManhourReport);
		exportManhourReportList.add(exportManhourReportTotal);
		return exportManhourReportList;
	}

	public int[] getWorkHour(ManhourEntity manhour) {
		int[] result = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		result[0] += manhour.getDay1();
		result[1] += manhour.getDay2();
		result[2] += manhour.getDay3();
		result[3] += manhour.getDay4();
		result[4] += manhour.getDay5();
		result[5] += manhour.getDay6();
		result[6] += manhour.getDay7();
		result[7] += manhour.getDay8();
		result[8] += manhour.getDay9();
		result[9] += manhour.getDay10();
		result[10] += manhour.getDay11();
		result[11] += manhour.getDay12();
		result[12] += manhour.getDay13();
		result[13] += manhour.getDay14();
		result[14] += manhour.getDay15();
		result[15] += manhour.getDay16();
		result[16] += manhour.getDay17();
		result[17] += manhour.getDay18();
		result[18] += manhour.getDay19();
		result[19] += manhour.getDay20();
		result[20] += manhour.getDay21();
		result[21] += manhour.getDay22();
		result[22] += manhour.getDay23();
		result[23] += manhour.getDay24();
		result[24] += manhour.getDay25();
		result[25] += manhour.getDay26();
		result[26] += manhour.getDay27();
		result[27] += manhour.getDay28();
		result[28] += manhour.getDay29();
		result[29] += manhour.getDay30();
		result[30] += manhour.getDay31();
		return result;
	}

	@Override
	public SaveOutputRes validate(InputManhourReport inputManhourReport) throws ParseException {
		// convert to exportManhourReport
		boolean validate = true;
		String error = "";
		if(inputManhourReport.getSaveName().length()>40) {
			validate = false;
			if(error.equals("")) {
				error += ERR_015;
			}else {
				error += " | " + ERR_015;
			}
		}
		CommonUtils.isValidFormat(DATE_FORMAT,inputManhourReport.getStartDate());
		CommonUtils.isValidFormat(DATE_FORMAT,inputManhourReport.getEndDate());
		if(CommonUtils.isValidFormat(DATE_FORMAT,inputManhourReport.getStartDate()) || CommonUtils.isValidFormat(DATE_FORMAT,inputManhourReport.getEndDate())) {
			validate = false;
			if(error.equals("")) {
				error += ERR_015;
			}else {
				error += " | " + ERR_015;
			}
		}else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd"); 
			Date startDate = sdf.parse(inputManhourReport.getStartDate()); 
			Date endDate = sdf.parse(inputManhourReport.getEndDate());
			long checkDay = (endDate.getTime() - startDate.getTime())/86400000;
			if(checkDay<0) {
				validate = false;
				if(error.equals("")) {
					error += ERR_012;
				}else {
					error += " | " + ERR_012;
				}
			}else if(inputManhourReport.getShowList().contains("日別合計") && checkDay>31) {
				validate = false;
				if(error.equals("")) {
					error += ERR_013;
				}else {
					error += " | " + ERR_013;
				}
			}
		}
		boolean isGroup1 = false;
		boolean isGroup2 = false;
		List<String> showItemList = new ArrayList<>(Arrays.asList(SHOW_ITEM_ARR));
		List<String> ItemGroup1List = showItemList.subList(5, 8);
		List<String> ItemGroup2List = showItemList.subList(0, 5);
		for (String show : inputManhourReport.getShowList()) {
			if(ItemGroup1List.contains(show)) {
				isGroup2 = true;
			}else if(ItemGroup2List.contains(show)){
				isGroup1 = true;
			}
		}
		if(!isGroup1) {
			validate = false;
			if(error.equals("")) {
				error += ERR_014;
			}else {
				error += " | " + ERR_014;
			}
		}
		if(!isGroup2) {
			validate = false;
			if(error.equals("")) {
				error += ERR_015;
			}else {
				error += " | " + ERR_015;
			}
		}
		
		SaveOutputRes saveOutputRes = new SaveOutputRes();
		saveOutputRes.setResult(validate);
		if(validate) {
			saveOutputRes.setMessage(SUCCESS);
		}else {
			saveOutputRes.setMessage(error);
		}
		return saveOutputRes;
	}

	public List<String[]> convertExportManhourReportToListArr(int[] date, boolean[] show, List<ExportManhourReport> exportManhourReportList, InputManhourReport inputManhourReport) {
		List<String[]> data = new ArrayList<>();
		try {
			String[] header = getHeader(date, show, inputManhourReport);
			data.add(header);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for (ExportManhourReport exportManhourReport : exportManhourReportList) {
			List<String> line = new ArrayList<>();
			
			if(exportManhourReport.getGroupCode() != null) {
				line.add(exportManhourReport.getGroupCode());
			}
			if (exportManhourReport.getGroupName() != null) {
				line.add(exportManhourReport.getGroupName());
			}
			if (exportManhourReport.getUserNo() != null) {
				line.add(exportManhourReport.getUserNo());
			}
			if (exportManhourReport.getUserName() != null) {
				line.add(exportManhourReport.getUserName());
			}
			if (exportManhourReport.getThemeNo() != null) {
				line.add(exportManhourReport.getThemeNo());
			}
			if (exportManhourReport.getThemeName1() != null) {
				line.add(exportManhourReport.getThemeName1());
			}
			
			if (exportManhourReport.getWorkContentCode() != null) {
				line.add(exportManhourReport.getWorkContentCode());
			}
			if (exportManhourReport.getWorkContentCodeName() != null) {
				line.add(exportManhourReport.getWorkContentCodeName());
			}
			if (exportManhourReport.getWorkContentDetail() != null) {
				line.add(exportManhourReport.getWorkContentDetail());
			}
			if (exportManhourReport.getTotalMonth() != null) {
				line.add(exportManhourReport.getTotalMonth());
			}
			if (exportManhourReport.getMonthHour() != null) {
				for (int month : exportManhourReport.getMonthHour()) {
					line.add(String.valueOf(month));
				}			
			}
			if(exportManhourReport.getDayHour() != null) {
				for (int day : exportManhourReport.getDayHour()) {
					line.add(String.valueOf(day));
				}
			}
			String[] ListToArray = new String[line.size()];
			line.toArray(ListToArray);
			data.add(ListToArray);
		}
		return data;
	}

	@Override
	public boolean delete(String saveName, String userNo) {
		boolean result = userScreenItemRepository.deleteBySaveName(saveName, userNo);
		return result;
	}
	
}
