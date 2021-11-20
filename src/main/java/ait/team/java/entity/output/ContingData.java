package ait.team.java.entity.output;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ContingData {
	
	@Id
	private int id;
	private String concat;
	private String groupCode;
	private String groupName;
	private String userNo;
	private String userName;
	private String themeNo;
	private String themeName1;
	private String subtotalCode;
	private double total;
	private String salesObjectCode;
	private String salesDate;
	private int orderAmount;
	private BigDecimal planCostRate;
	private String companyCode;
	private String accountingGroupCode;
	private String salesMonthCode;
	private String salesMonthCodeMemo;
	private boolean processingFlg;
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
	public String getThemeNo() {
		return themeNo;
	}
	public void setThemeNo(String themeNo) {
		this.themeNo = themeNo;
	}
	public String getThemeName1() {
		return themeName1;
	}
	public void setThemeName1(String themeName1) {
		this.themeName1 = themeName1;
	}
	public String getSubtotalCode() {
		return subtotalCode;
	}
	public void setSubtotalCode(String subtotalCode) {
		this.subtotalCode = subtotalCode;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getSalesObjectCode() {
		return salesObjectCode;
	}
	public void setSalesObjectCode(String salesObjectCode) {
		this.salesObjectCode = salesObjectCode;
	}
	public String getSalesDate() {
		return salesDate;
	}
	public void setSalesDate(String salesDate) {
		this.salesDate = salesDate;
	}
	public int getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(int orderAmount) {
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
	public String getAccountingGroupCode() {
		return accountingGroupCode;
	}
	public void setAccountingGroupCode(String accountingGroupCode) {
		this.accountingGroupCode = accountingGroupCode;
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
	public boolean getProcessingFlg() {
		return processingFlg;
	}
	public void setProcessingFlg(boolean processingFlg) {
		this.processingFlg = processingFlg;
	}
	public String getConcat() {
		return concat;
	}
	public void setConcat(String concat) {
		this.concat = concat;
	}
	
}
