package ait.team.java.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class CalendarDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * út
     */
    private Timestamp date;

    /**
     * TCgR[h
     */
    private String siteCode;

    /**
     * xútO
     */
    private boolean horiday;

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

	public boolean isHoriday() {
		return horiday;
	}

	public void setHoriday(boolean horiday) {
		this.horiday = horiday;
	}


}