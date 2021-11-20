package ait.team.java.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import ait.team.java.entity.idclass.CalendarId;

@Entity
@Table(name = "m_calendar", schema = "public")
@IdClass(CalendarId.class)
public class CalendarEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * út
     */
    @Id
    @Column(name = "date", nullable = false)
    private Timestamp date;

    /**
     * TCgR[h
     */
    @Id
    @Column(name = "site_code", nullable = false, length = 8)
    private String siteCode;

    /**
     * xútO
     */
    @Column(name = "horiday")
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
	
	public boolean getHoriday() {
		return this.horiday;
	}


	public void setHoriday(boolean horiday) {
		this.horiday = horiday;
	}

    
}