package ait.team.java.service;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ait.team.java.controller.output.CalendarOutput;
import ait.team.java.controller.output.MenuOutput;

public interface ICalendarService {
	public int[] findCalendarMonth(Date date, String userName);
	public boolean[] findHoliday(Date date, String siteCode);
	public String getCurrentMonth();
	public List<List<CalendarOutput>> getContentCalendarOfMonth(String userNo, String siteCode) throws ParseException;
	public List<String> getHeaderCalendarOfMonth() throws ParseException;
	public Calendar getCalendarOfMonth() throws ParseException;
	public int getDayNow() throws ParseException;
	public boolean checkNone(List<List<CalendarOutput>> contentCalendar) throws ParseException;
	public MenuOutput getMenu(String userNo, String siteCode) throws ParseException;
}
