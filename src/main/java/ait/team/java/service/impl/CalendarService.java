package ait.team.java.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ait.team.java.common.CommonUtils;
import ait.team.java.controller.output.CalendarOutput;
import ait.team.java.controller.output.MenuOutput;
import ait.team.java.entity.CalendarEntity;
import ait.team.java.entity.ManhourEntity;
import ait.team.java.entity.ProcessingMonthEntity;
import ait.team.java.repository.CalendarReponsitory;
import ait.team.java.repository.ManhourRepository;
import ait.team.java.repository.ProcessingMonthRepository;
import ait.team.java.service.ICalendarService;
import ait.team.java.service.IManhourUpdateService;

@Service
public class CalendarService implements ICalendarService {

	@Autowired
	ManhourRepository manhourRepository;

	@Autowired
	CalendarReponsitory calendarReponsitory;

	@Autowired
	ProcessingMonthRepository processingMonthRepository;

	@Autowired
	IManhourUpdateService manhourUpdateService;
	
	private final String DATE_FORMAT_YYYYMMDD = "dd/MM/yyyy";
	
	private final String FIRST_DAY = "1";

	@Override
	public int[] findCalendarMonth(Date date, String userName) {

		int[] result = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    short year = (short)calendar.get(Calendar.YEAR);
	    short month = (short)(calendar.get(Calendar.MONTH)+1);
	    List<ManhourEntity> manhourEntity = manhourRepository.findByUserNoAndYearAndMonth(userName,year,month);
	    
		for (ManhourEntity manhour : manhourEntity) {
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
		}

		return result;
	}

	@Override
	public boolean[] findHoliday(Date date, String siteCode) {
		boolean[] result = new boolean[31];
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);

		String firstDay = FIRST_DAY + "/" + (month + 1) + "/" + year;
		String firstDayNextMonth = FIRST_DAY + "/" + (month + 2) + "/" + year;
		try {
			Date firstdate = new SimpleDateFormat(DATE_FORMAT_YYYYMMDD).parse(firstDay);
			Date firstdateNextMonth = new SimpleDateFormat(DATE_FORMAT_YYYYMMDD).parse(firstDayNextMonth);
			Timestamp firstTime = new Timestamp(firstdate.getTime());
			Timestamp lastTime = new Timestamp(firstdateNextMonth.getTime() - 86400000);

			// get holiday for month
			List<CalendarEntity> calendarEntity = calendarReponsitory.findBySiteCodeAndDateBetween(siteCode, firstTime,
					lastTime);
			for (CalendarEntity entity : calendarEntity) {
				Date dateDay = new Date(entity.getDate().getTime());
				Calendar calendarDay = Calendar.getInstance();
				calendarDay.setTime(dateDay);
				result[calendarDay.get(Calendar.DAY_OF_MONTH) - 1] = entity.getHoriday();
			}
			return result;

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getCurrentMonth() {
		String result = "";
		List<ProcessingMonthEntity> currentMonth = processingMonthRepository.findAll();
		for (ProcessingMonthEntity current : currentMonth) {
			result = current.getProcessingMonth();
			break;
		}
		return result;
	}

	// get data calendar for menu page
	@Override
	public List<List<CalendarOutput>> getContentCalendarOfMonth(String userNo, String siteCode) throws ParseException {
		CommonUtils commonUtils = new CommonUtils();

		String currentMonth = getCurrentMonth();

		String firstDayCurrentMonth = FIRST_DAY + "/"+ currentMonth.substring(4) + "/" + currentMonth.substring(0, 4);
		Date date = new SimpleDateFormat(DATE_FORMAT_YYYYMMDD).parse(firstDayCurrentMonth);

		// get the working hours of the day by month
		int[] hourWorking = findCalendarMonth(date, userNo);

		// take the holidays of the month
		boolean[] holiday = findHoliday(date, siteCode);

		// get the calendar for the month
		String[][] calendar = commonUtils.toDayOfWeekInMonth(date);

		// create a work schedule for the month
		List<List<CalendarOutput>> resultCalendar = new ArrayList<>();
		int headLine = 0;
		for (String[] line : calendar) {
			if (headLine == 0 || (headLine != 1 && line[0] == null)) {
				headLine++;
				continue;
			}
			List<CalendarOutput> resultCalendarLine = new ArrayList<>();
			for (String record : line) {
				CalendarOutput calendarOutput = new CalendarOutput();
				if (record != null) {
					calendarOutput.setDay(Integer.parseInt(record));
					calendarOutput.setHour(hourWorking[Integer.parseInt(record) - 1]);
					calendarOutput.setHoliday(holiday[Integer.parseInt(record) - 1]);
				}
				resultCalendarLine.add(calendarOutput);
			}
			resultCalendar.add(resultCalendarLine);
			headLine++;
		}

		return resultCalendar;
	}

	@Override
	public List<String> getHeaderCalendarOfMonth() throws ParseException {
		// get the calendar for the month
		CommonUtils commonUtils = new CommonUtils();
		String currentMonth = getCurrentMonth();
		String firstDayNextMonth = FIRST_DAY + "/" + currentMonth.substring(4) + "/" + currentMonth.substring(0, 4);
		Date date = new SimpleDateFormat(DATE_FORMAT_YYYYMMDD).parse(firstDayNextMonth);

		// get header calendar
		String[][] calendar = commonUtils.toDayOfWeekInMonth(date);
		List<String> headerCalendar = new ArrayList<>();
		for (String line : calendar[0]) {
			headerCalendar.add(line);
		}
		return headerCalendar;
	}

	@Override
	public Calendar getCalendarOfMonth() throws ParseException {
		// get the calendar for the month
		String currentMonth = getCurrentMonth();
		String firstDayOfMonthString = FIRST_DAY + "/" + currentMonth.substring(4) + "/" + currentMonth.substring(0, 4);
		Date firstDayOfMonthDate = new SimpleDateFormat(DATE_FORMAT_YYYYMMDD).parse(firstDayOfMonthString);

		// get month,year current type Calendar
		Calendar calendarMonth = Calendar.getInstance();
		calendarMonth.setTime(firstDayOfMonthDate);
		return calendarMonth;
	}

	@Override
	public int getDayNow() throws ParseException {
		Date dateNow = new Date();
		Calendar calendarNow = Calendar.getInstance();
		calendarNow.setTime(dateNow);
		return calendarNow.get(Calendar.DAY_OF_MONTH);
	}

	//check business hours <8
	@Override
	public boolean checkNone(List<List<CalendarOutput>> contentCalendar) throws ParseException {
		boolean check = false;
		for (List<CalendarOutput> line : contentCalendar) {
			for (CalendarOutput record : line) {
				if (record.getHoliday() != true && record.getDay() != -1 && record.getDay() < getDayNow()
						&& record.getHour() < 8) {
					check = true;
					break;
				}
			}
		}
		return check;
	}

	// get data menu page
	@Override
	public MenuOutput getMenu(String userNo, String siteCode) throws ParseException {

		MenuOutput menuOutput = new MenuOutput();

		// get content calendar
		List<List<CalendarOutput>> contentCalendarOfMonth = getContentCalendarOfMonth(userNo, siteCode);
		menuOutput.setContentCalendar(contentCalendarOfMonth);

		// check business hours alert
		boolean isNone = checkNone(contentCalendarOfMonth);
		menuOutput.setCheckNone(isNone);
		menuOutput.setUserNo(userNo);

		// get current month
		int currentMonth = getCalendarOfMonth().get(Calendar.MONTH) + 1;
		menuOutput.setCurrentMonth(currentMonth);

		// get current year
		int currentYear = getCalendarOfMonth().get(Calendar.YEAR);
		menuOutput.setCurrentYear(currentYear);

		// get current day
		int dayNow = getDayNow();
		menuOutput.setDayNow(dayNow);

		// get header calendar
		List<String> headerCalendar = getHeaderCalendarOfMonth();
		menuOutput.setHeaderCalendar(headerCalendar);
		return menuOutput;
	}

}
