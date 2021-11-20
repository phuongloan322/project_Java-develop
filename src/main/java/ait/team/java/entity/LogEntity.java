package ait.team.java.entity;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_log", schema = "public")
public class LogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ú
     */
    @Id
    @Column(name = "date_time", nullable = false)
    private Timestamp dateTime;

    /**
     * [U[NO
     */
    @Id
    @Column(name = "user_no", nullable = false, length = 9)
    private String userNo;

    /**
     * j[ID
     */
    @Id
    @Column(name = "screen_id", nullable = false, length = 20)
    private String screenId;

    /**
     * ANV^Cv
     */
    @Id
    @Column(name = "action_type", nullable = false, length = 8)
    private String actionType;

    /**
     * Ê
     */
    @Column(name = "result", nullable = false, length = 7)
    private String result;

    /**
     * îñ1
     */
    @Column(name = "info1", length = 256)
    private String info1;

    /**
     * îñ2
     */
    @Column(name = "info2", length = 256)
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