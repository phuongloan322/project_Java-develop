package ait.team.java.entity;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import ait.team.java.entity.idclass.WorkContentId;

@Entity
@Table(name = "m_work_contents", schema = "public")
@IdClass(WorkContentId.class)
public class WorkContentsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ìÆàeæª
     */
    @Id
    @Column(name = "work_contents_class", nullable = false, length = 2)
    private String workContentsClass;

    /**
     * ìÆàeR[h
     */
    @Id
    @Column(name = "work_contents_code", nullable = false, length = 2)
    private String workContentsCode;

    /**
     * ìÆàe¼
     */
    @Column(name = "work_contents_code_name", length = 50)
    private String workContentsCodeName;

    /**
     * à¾
     */
    @Column(name = "memo", length = 60)
    private String memo;

    /**
     * ¬vPÊR[h
     */
    @Column(name = "subtotal_code", nullable = false, length = 2)
    private String subtotalCode;

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