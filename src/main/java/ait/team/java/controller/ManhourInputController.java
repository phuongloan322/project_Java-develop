package ait.team.java.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ait.team.java.common.CommonUtils;
import ait.team.java.controller.output.ManhourInputGeneral;
import ait.team.java.service.impl.ManhourInputService;

@Controller
public class ManhourInputController {

	// declaring constant
	public final String DATE_FORMAT = "yyyy/MM/dd hh:mm:ss";
	public final String VIEW_INDEX = "Views/ManHourInput/index";
	public final String SCREEN_URL = "ManHourInput";
	public final String USER_NO = "userNo";

	@Autowired
	ManhourInputService manhourInputService;

	/*
	 * Get data to index ManhourInput
	 */
	@GetMapping(value = "/ManhourInput")
	public String loadFormData(Model model, HttpSession session) {

		// fake data
		String processingDate = "2021/07/03 09:30:56";
		String userNo = (String) session.getAttribute(USER_NO);

		try {
			SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
			Calendar cal = Calendar.getInstance();

			Date date = formatter.parse(processingDate);
			cal.setTime(date);
			model.addAttribute("processingDate", date);
			model.addAttribute("japaneseWeekDays", CommonUtils.formatDateString(processingDate));
			model.addAttribute("dayOfMonth", cal.get(Calendar.DAY_OF_MONTH));

			getDateInWeek(model, date);

			ManhourInputGeneral showManhourInput = manhourInputService.generalFunction(processingDate, userNo,
					SCREEN_URL);
			model.addAttribute("HourInput", showManhourInput);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return VIEW_INDEX;
	}

	/*
	 * Change mode screen at button [日/週/月] Then, load data when click
	 * 
	 * @param modeValue - get value in [日/週/月]
	 */
	@PostMapping(value = "/ManhourInput")
	public String changeModeManhour(Model model, HttpSession session,
			@RequestParam(required = false, value = "changeDate") String modeValue,
			@RequestParam(required = false, value = "processingDate") String processingDate) {

		String userNo = (String) session.getAttribute(USER_NO);

		try {
			SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
			Calendar cal = Calendar.getInstance();
			Date date = formatter.parse(processingDate);
			cal.setTime(date);
			model.addAttribute("processingDate", date);
			model.addAttribute("japaneseWeekDays", CommonUtils.formatDateString(processingDate));
			model.addAttribute("dayOfMonth", cal.get(Calendar.DAY_OF_MONTH));

			getDateInWeek(model, date);

			ManhourInputGeneral showManhourInput = manhourInputService.parrentFunction(processingDate, userNo,
					SCREEN_URL, modeValue);
			model.addAttribute("HourInput", showManhourInput);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return VIEW_INDEX;
	}

	/*
	 * Update Date Selected
	 * 
	 * @param presentDate - get the value in [日付ラベル]
	 * 
	 * @param valueDate - get the value in datepicker
	 */
	@PostMapping(value = "/ManhourInput/Day")
	public String changeDateManhour(Model model, HttpSession session,
			@RequestParam(required = false, value = "presentDate") String movingDate,
			@RequestParam(required = false, value = "valueDate") String valueDate) {

		String userNo = (String) session.getAttribute(USER_NO);

		try {
			SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
			Date nowDate = new Date();
			Calendar cal = Calendar.getInstance();
			Date processDateFormat = formatter.parse(valueDate);

			String nextDay = formatter.format(CommonUtils.addDays(processDateFormat, 1));
			String previousDay = formatter.format(CommonUtils.addDays(processDateFormat, -1));

			String tempDate = formatter.format(nowDate);

			if (movingDate.equals("previousDate")) {
				tempDate = previousDay;
			}
			if (movingDate.equals("nextDate")) {
				tempDate = nextDay;
			}
			if (movingDate.equals("selectedDate")) {
				tempDate = valueDate;
			}

			Date processDateFormat1 = formatter.parse(tempDate);
			cal.setTime(processDateFormat1);
			model.addAttribute("processingDate", processDateFormat1);
			model.addAttribute("japaneseWeekDays", CommonUtils.formatDateString(tempDate));
			model.addAttribute("dayOfMonth", cal.get(Calendar.DAY_OF_MONTH));

			getDateInWeek(model, processDateFormat1);

			ManhourInputGeneral showManhourInput = manhourInputService.generalFunction(tempDate, userNo, SCREEN_URL);
			model.addAttribute("HourInput", showManhourInput);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return VIEW_INDEX;
	}

	/*
	 * Update Date Of Week Selected
	 * 
	 * @param valueDateOfWeek - get the value in [日付ラベル]
	 * 
	 * @param valueDateOfWeek - get the value date in datepicker
	 */
	@PostMapping(value = "/ManhourInput/Week")
	public String changeDateManhourWeek(Model model, HttpSession session,
			@RequestParam(required = false, value = "presentDateOfWeek") String movingDateOfWeek,
			@RequestParam(required = false, value = "valueDateOfWeek") String valueDateOfWeek) {

		String userNo = (String) session.getAttribute(USER_NO);

		try {
			SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
			Date nowDate = new Date();
			Calendar cal = Calendar.getInstance();
			Date processDateFormat = formatter.parse(valueDateOfWeek);

			String nextDay = formatter.format(CommonUtils.addDays(processDateFormat, 1));
			String previousDay = formatter.format(CommonUtils.addDays(processDateFormat, -1));

			String tempDate = formatter.format(nowDate);

			if (movingDateOfWeek.equals("previousWeek")) {
				tempDate = previousDay;
			}
			if (movingDateOfWeek.equals("nextWeek")) {
				tempDate = nextDay;
			}
			if (movingDateOfWeek.equals("selectedDateOfWeek")) {
				tempDate = valueDateOfWeek;
			}

			Date processDateFormat1 = formatter.parse(tempDate);
			cal.setTime(processDateFormat1);
			model.addAttribute("processingDate", processDateFormat1);
			model.addAttribute("japaneseWeekDays", CommonUtils.formatDateString(tempDate));
			model.addAttribute("dayOfMonth", cal.get(Calendar.DAY_OF_MONTH));

			getDateInWeek(model, processDateFormat1);

			ManhourInputGeneral showManhourInput = manhourInputService.generalFunction(tempDate, userNo, SCREEN_URL);
			model.addAttribute("HourInput", showManhourInput);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return VIEW_INDEX;
	}

	public void getDateInWeek(Model model, Date date) {

		List<Date> dateList = CommonUtils.getDateOfWeek(date);
		for (Date elDate : dateList) {
			String dayOfWeek = new SimpleDateFormat("EEE", Locale.ENGLISH).format(elDate);
			model.addAttribute(dayOfWeek, elDate);
		}
	}

}
