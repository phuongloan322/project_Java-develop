package ait.team.java.entity;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_user", schema = "public")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * [U[NO
     */
    @Id
    @Column(name = "user_no", nullable = false, length = 9)
    private String userNo;

    /**
     * [U[¼
     */
    @Column(name = "user_name", length = 20)
    private String userName;

    /**
     * ®R[h
     */
    @Column(name = "group_code", length = 10)
    private String groupCode;

    /**
     * TCgR[h
     */
    @Column(name = "site_code", length = 8)
    private String siteCode;

    /**
     *  À[R[h
     */
    @Column(name = "role_code", length = 10)
    private String roleCode;

    /**
     * [AhX
     */
    @Column(name = "mail_address", length = 256)
    private String mailAddress;

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

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
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