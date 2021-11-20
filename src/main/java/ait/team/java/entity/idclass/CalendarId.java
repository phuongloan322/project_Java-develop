package ait.team.java.entity.idclass;

import java.io.Serializable;
import java.sql.Timestamp;

public class CalendarId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 406382639782937627L;
	private Timestamp date;
	private String siteCode;
	
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
}
