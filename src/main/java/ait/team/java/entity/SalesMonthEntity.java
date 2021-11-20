package ait.team.java.entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_sales_month", schema = "public")
public class SalesMonthEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ãæªR[h
     */
    @Id
    @Column(name = "sales_month_code", nullable = false, length = 2)
    private String salesMonthCode;

    /**
     * ãæª¼
     */
    @Column(name = "sales_month_name", length = 40)
    private String salesMonthName;

    /**
     * \¦
     */
    @Column(name = "sort_no")
    private BigDecimal sortNo;

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

	public String getSalesMonthCode() {
		return salesMonthCode;
	}

	public void setSalesMonthCode(String salesMonthCode) {
		this.salesMonthCode = salesMonthCode;
	}

	public String getSalesMonthName() {
		return salesMonthName;
	}

	public void setSalesMonthName(String salesMonthName) {
		this.salesMonthName = salesMonthName;
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