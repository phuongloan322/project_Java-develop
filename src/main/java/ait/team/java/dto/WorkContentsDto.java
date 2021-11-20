package ait.team.java.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class WorkContentsDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ìÆàeæª
     */
    private String workContentsClass;

    /**
     * ìÆàeR[h
     */
    private String workContentsCode;

    /**
     * ìÆàe¼
     */
    private String workContentsCodeName;

    /**
     * à¾
     */
    private String memo;

    /**
     * ¬vPÊR[h
     */
    private String subtotalCode;

    /**
     * ítO
     */
    private boolean delFlg;

    /**
     * o^ú
     */
    private Timestamp createDate;

    /**
     * o^[UID
     */
    private String createUser;

    /**
     * XVú
     */
    private Timestamp updateDate;

    /**
     * XV[UID
     */
    private String updateUser;

	public String getWorkContentsClass() {
		return workContentsClass;
	}

	public void setWorkContentsClass(String workContentsClass) {
		this.workContentsClass = workContentsClass;
	}

	public String getWorkContentsCode() {
		return workContentsCode;
	}

	public void setWorkContentsCode(String workContentsCode) {
		this.workContentsCode = workContentsCode;
	}

	public String getWorkContentsCodeName() {
		return workContentsCodeName;
	}

	public void setWorkContentsCodeName(String workContentsCodeName) {
		this.workContentsCodeName = workContentsCodeName;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getSubtotalCode() {
		return subtotalCode;
	}

	public void setSubtotalCode(String subtotalCode) {
		this.subtotalCode = subtotalCode;
	}

	public boolean isDelFlg() {
		return delFlg;
	}

	public void setDelFlg(boolean delFlg) {
		this.delFlg = delFlg;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
}