package ait.team.java.controller.output;

import java.util.List;

public class MenuOutput {
	private String userNo;
	private List<List<CalendarOutput>> contentCalendar ;
	private List<String> headerCalendar;
	private int dayNow;
	private boolean checkNone;
	private int currentMonth;
	private int currentYear;
	
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public List<List<CalendarOutput>> getContentCalendar() {
		return contentCalendar;
	}
	public void setContentCalendar(List<List<CalendarOutput>> contentCalendar) {
		this.contentCalendar = contentCalendar;
	}
	public List<String> getHeaderCalendar() {
		return headerCalendar;
	}
	public void setHeaderCalendar(List<String> headerCalendar) {
		this.headerCalendar = headerCalendar;
	}
	public int getDayNow() {
		return dayNow;
	}
	public void setDayNow(int dayNow) {
		this.dayNow = dayNow;
	}
	public boolean isCheckNone() {
		return checkNone;
	}
	public void setCheckNone(boolean checkNone) {
		this.checkNone = checkNone;
	}
	public int getCurrentMonth() {
		return currentMonth;
	}
	public void setCurrentMonth(int currentMonth) {
		this.currentMonth = currentMonth;
	}
	public int getCurrentYear() {
		return currentYear;
	}
	public void setCurrentYear(int currentYear) {
		this.currentYear = currentYear;
	}
}
