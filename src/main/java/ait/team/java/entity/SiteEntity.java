package ait.team.java.entity;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_site", schema = "public")
public class SiteEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * TCgR[h
     */
    @Id
    @Column(name = "site_code", nullable = false, length = 8)
    private String siteCode;

    /**
     * TCg¼
     */
    @Column(name = "site_name", length = 60)
    private String siteName;

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

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
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