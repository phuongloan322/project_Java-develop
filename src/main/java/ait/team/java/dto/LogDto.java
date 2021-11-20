package ait.team.java.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class LogDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ú
     */
    private Timestamp dateTime;

    /**
     * [U[NO
     */
    private String userNo;

    /**
     * j[ID
     */
    private String screenId;

    /**
     * ANV^Cv
     */
    private String actionType;

    /**
     * Ê
     */
    private String result;

    /**
     * îñ1
     */
    private String info1;

    /**
     * îñ2
     */
    private String info2;

	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getScreenId() {
		return screenId;
	}

	public void setScreenId(String screenId) {
		this.screenId = screenId;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getInfo1() {
		return info1;
	}

	public void setInfo1(String info1) {
		this.info1 = info1;
	}

	public String getInfo2() {
		return info2;
	}

	public void setInfo2(String info2) {
		this.info2 = info2;
	}

}