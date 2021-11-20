package ait.team.java.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class GroupDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ®R[h
     */
    private String groupCode;

    /**
     * ®¼
     */
    private String groupName;

    /**
     * ïvåR[h
     */
    private String accountingGroupCode;

    /**
     * ïvå¼
     */
    private String accountingGroupName;

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

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getAccountingGroupCode() {
		return accountingGroupCode;
	}

	public void setAccountingGroupCode(String accountingGroupCode) {
		this.accountingGroupCode = accountingGroupCode;
	}

	public String getAccountingGroupName() {
		return accountingGroupName;
	}

	public void setAccountingGroupName(String accountingGroupName) {
		this.accountingGroupName = accountingGroupName;
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