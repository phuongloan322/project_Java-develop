package ait.team.java.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class InternalExternalSalesDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * àOÌæªR[h
     */
    private String internalExternalSalesCode;

    /**
     * àOÌæª¼
     */
    private String internalExternalSalesName;

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

	public String getInternalExternalSalesCode() {
		return internalExternalSalesCode;
	}

	public void setInternalExternalSalesCode(String internalExternalSalesCode) {
		this.internalExternalSalesCode = internalExternalSalesCode;
	}

	public String getInternalExternalSalesName() {
		return internalExternalSalesName;
	}

	public void setInternalExternalSalesName(String internalExternalSalesName) {
		this.internalExternalSalesName = internalExternalSalesName;
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