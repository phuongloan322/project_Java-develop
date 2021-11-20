package ait.team.java.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class ThemeDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * e[}NO
     */
    private String themeNo;

    /**
     * àOÌæªR[h
     */
    private String internalExternalSalesCode;

    /**
     * óææªR[h
     */
    private String customerCode;

    /**
     * óàeæªR[h
     */
    private String orderContentsCode;

    /**
     * o^N
     */
    private String registYear;

    /**
     * e[}¼1i³®j
     */
    private String themeName1;

    /**
     * e[}¼2iª®j
     */
    private String themeName2;

    /**
     * ìÆàeæª
     */
    private String workContentsClass;

    /**
     * ótú
     */
    private Timestamp acceptDate;

    /**
     * Ëú
     */
    private Timestamp requestDate;

    /**
     * ã\èú
     */
    private Timestamp salesDate;

    /**
     * ïvåR[h
     */
    private String accountingGroupCode;

    /**
     * ãÏLøN
     */
    private String salesValidYymm;

    /**
     * ãÈÚR[h
     */
    private String salesObjectCode;

    /**
     * óàz
     */
    private Integer orderAmount;

    /**
     * væ´¿¦
     */
    private BigDecimal planCostRate;

    /**
     * ïÐR[h
     */
    private String companyCode;

    /**
     * okbqæª
     */
    private String plcrCode;

    /**
     * ãæª
     */
    private String salesMonthCode;

    /**
     * ãæª
     */
    private String salesMonthCodeMemo;

    /**
     * d|tO
     */
    private boolean processingFlg;

    /**
     * ãÏtO
     */
    private boolean soldFlg;

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

	public String getThemeNo() {
		return themeNo;
	}

	public void setThemeNo(String themeNo) {
		this.themeNo = themeNo;
	}

	public String getInternalExternalSalesCode() {
		return internalExternalSalesCode;
	}

	public void setInternalExternalSalesCode(String internalExternalSalesCode) {
		this.internalExternalSalesCode = internalExternalSalesCode;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getOrderContentsCode() {
		return orderContentsCode;
	}

	public void setOrderContentsCode(String orderContentsCode) {
		this.orderContentsCode = orderContentsCode;
	}

	public String getRegistYear() {
		return registYear;
	}

	public void setRegistYear(String registYear) {
		this.registYear = registYear;
	}

	public String getThemeName1() {
		return themeName1;
	}

	public void setThemeName1(String themeName1) {
		this.themeName1 = themeName1;
	}

	public String getThemeName2() {
		return themeName2;
	}

	public void setThemeName2(String themeName2) {
		this.themeName2 = themeName2;
	}

	public String getWorkContentsClass() {
		return workContentsClass;
	}

	public void setWorkContentsClass(String workContentsClass) {
		this.workContentsClass = workContentsClass;
	}

	public Timestamp getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(Timestamp acceptDate) {
		this.acceptDate = acceptDate;
	}

	public Timestamp getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Timestamp requestDate) {
		this.requestDate = requestDate;
	}

	public Timestamp getSalesDate() {
		return salesDate;
	}

	public void setSalesDate(Timestamp salesDate) {
		this.salesDate = salesDate;
	}

	public String getAccountingGroupCode() {
		return accountingGroupCode;
	}

	public void setAccountingGroupCode(String accountingGroupCode) {
		this.accountingGroupCode = accountingGroupCode;
	}

	public String getSalesValidYymm() {
		return salesValidYymm;
	}

	public void setSalesValidYymm(String salesValidYymm) {
		this.salesValidYymm = salesValidYymm;
	}

	public String getSalesObjectCode() {
		return salesObjectCode;
	}

	public void setSalesObjectCode(String salesObjectCode) {
		this.salesObjectCode = salesObjectCode;
	}

	public Integer getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Integer orderAmount) {
		this.orderAmount = orderAmount;
	}

	public BigDecimal getPlanCostRate() {
		return planCostRate;
	}

	public void setPlanCostRate(BigDecimal planCostRate) {
		this.planCostRate = planCostRate;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getPlcrCode() {
		return plcrCode;
	}

	public void setPlcrCode(String plcrCode) {
		this.plcrCode = plcrCode;
	}

	public String getSalesMonthCode() {
		return salesMonthCode;
	}

	public void setSalesMonthCode(String salesMonthCode) {
		this.salesMonthCode = salesMonthCode;
	}

	public String getSalesMonthCodeMemo() {
		return salesMonthCodeMemo;
	}

	public void setSalesMonthCodeMemo(String salesMonthCodeMemo) {
		this.salesMonthCodeMemo = salesMonthCodeMemo;
	}

	public boolean isProcessingFlg() {
		return processingFlg;
	}

	public void setProcessingFlg(boolean processingFlg) {
		this.processingFlg = processingFlg;
	}

	public boolean isSoldFlg() {
		return soldFlg;
	}

	public void setSoldFlg(boolean soldFlg) {
		this.soldFlg = soldFlg;
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