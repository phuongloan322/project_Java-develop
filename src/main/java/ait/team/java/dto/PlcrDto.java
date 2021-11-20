package ait.team.java.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class PlcrDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * PLCRæªR[h
     */
    private String plcrCode;

    /**
     * PLCRæª¼
     */
    private String plcrName;

    /**
     * \¦
     */
    private BigDecimal sortNo;

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

	public String getPlcrCode() {
		return plcrCode;
	}

	public void setPlcrCode(String plcrCode) {
		this.plcrCode = plcrCode;
	}

	public String getPlcrName() {
		return plcrName;
	}

	public void setPlcrName(String plcrName) {
		this.plcrName = plcrName;
	}

	public BigDecimal getSortNo() {
		return sortNo;
	}

	public void setSortNo(BigDecimal sortNo) {
		this.sortNo = sortNo;
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