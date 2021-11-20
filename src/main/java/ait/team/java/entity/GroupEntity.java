package ait.team.java.entity;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_group", schema = "public")
public class GroupEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ®R[h
     */
    @Id
    @Column(name = "group_code", nullable = false, length = 10)
    private String groupCode;

    /**
     * ®¼
     */
    @Column(name = "group_name", length = 60)
    private String groupName;

    /**
     * ïvåR[h
     */
    @Column(name = "accounting_group_code", length = 2)
    private String accountingGroupCode;

    /**
     * ïvå¼
     */
    @Column(name = "accounting_group_name", length = 60)
    private String accountingGroupName;

    /**
     * ítO
     */
    @Column(name = "del_flg")
    private boolean delFlg;

    /**
     * o^ú
     */
    @Column(name = "create_date")
    private Timestamp createDate;

    /**
     * o^[UID
     */
    @Column(name = "create_user", length = 9)
    private String createUser;

    /**
     * XVú
     */
    @Column(name = "update_date")
    private Timestamp updateDate;

    /**
     * XV[UID
     */
    @Column(name = "update_user", length = 9)
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