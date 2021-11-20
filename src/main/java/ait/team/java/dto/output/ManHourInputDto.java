package ait.team.java.dto.output;

import ait.team.java.dto.ManhourDto;

public class ManHourInputDto extends ManhourDto{
 
	private static final long serialVersionUID = 1L;
	
	private String startDate;
	private String endDate;
	private String themeName1;
	private String workContentsCodeName;
	private String workContentsClassName;
	private double mon;
	private double tue;
	private double wed;
	private double thu;
	private double fri;
	private double sat;
	private double sun;
	private double processDay;
	public double totalHourWeek() {
		
		return getMon() + getTue() + getWed() + getThu() + getFri() + getSat() + getSun();
	}
	
	public double getMon() {
		return mon;
	}
	public void setMon(double mon) {
		this.mon = mon;
	}
	public double getTue() {
		return tue;
	}
	public void setTue(double tue) {
		this.tue = tue;
	} 
	public double getThu() {
		return thu;
	}
	public void setThu(double thu) {
		this.thu = thu;
	}
	public double getFri() {
		return fri;
	}
	public void setFri(double fri) {
		this.fri = fri;
	}
	public double getSat() {
		return sat;
	}
	public void setSat(double sat) {
		this.sat = sat;
	}
	public double getSun() {
		return sun;
	}
	public void setSun(double sun) {
		this.sun = sun;
	}
	public double getProcessDay() {
		return processDay;
	}
	public void setProcessDay(double processDay) {
		this.processDay = processDay;
	}
	public String getThemeName1() {
		return themeName1;
	}
	public void setThemeName1(String themeName1) {
		this.themeName1 = themeName1;
	}
	public String getWorkContentsCodeName() {
		return workContentsCodeName;
	}
	public void setWorkContentsCodeName(String workContentsCodeName) {
		this.workContentsCodeName = workContentsCodeName;
	}
	public String getWorkContentsClassName() {
		return workContentsClassName;
	}
	public void setWorkContentsClassName(String workContentsClassName) {
		this.workContentsClassName = workContentsClassName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public double getWed() {
		return wed;
	}
	public void setWed(double wed) {
		this.wed = wed;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	} 	
	 
}
