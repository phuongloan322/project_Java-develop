package ait.team.java.entity;
import java.io.Serializable;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import ait.team.java.entity.idclass.ManhourId;

@Entity
@Table(name = "t_manhour", schema = "public")
@IdClass(ManhourId.class)
public class ManhourEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * N
     */
    @Id
    @Column(name = "year", nullable = false)
    private short year;

    /**
     * 
     */
    @Id
    @Column(name = "month", nullable = false)
    private short month;

    /**
     * [U[NO
     */
    @Id
    @Column(name = "user_no", nullable = false, length = 9)
    private String userNo;

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
     * e[}NO
     */
    @Id
    @Column(name = "theme_no", nullable = false, length = 12)
    private String themeNo;

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
     * ìÆàeÚ×
     */
    @Id
    @Column(name = "work_contents_detail", nullable = false, length = 2)
    private String workContentsDetail;

    /**
     * s~ßtO
     */
    @Column(name = "pin_flg")
    private boolean pinFlg;

    /**
     * v
     */
    @Column(name = "total")
    private double total;

    /**
     * 1ú
     */
    @Column(name = "day1")
    private double day1;

    /**
     * 2ú
     */
    @Column(name = "day2")
    private double day2;

    /**
     * 3ú
     */
    @Column(name = "day3")
    private double day3;

    /**
     * 4ú
     */
    @Column(name = "day4")
    private double day4;

    /**
     * 5ú
     */
    @Column(name = "day5")
    private double day5;

    /**
     * 6ú
     */
    @Column(name = "day6")
    private double day6;

    /**
     * 7ú
     */
    @Column(name = "day7")
    private double day7;

    /**
     * 8ú
     */
    @Column(name = "day8")
    private double day8;

    /**
     * 9ú
     */
    @Column(name = "day9")
    private double day9;

    /**
     * 10ú
     */
    @Column(name = "day10")
    private double day10;

    /**
     * 11ú
     */
    @Column(name = "day11")
    private double day11;

    /**
     * 12ú
     */
    @Column(name = "day12")
    private double day12;

    /**
     * 13ú
     */
    @Column(name = "day13")
    private double day13;

    /**
     * 14ú
     */
    @Column(name = "day14")
    private double day14;

    /**
     * 15ú
     */
    @Column(name = "day15")
    private double day15;

    /**
     * 16ú
     */
    @Column(name = "day16")
    private double day16;

    /**
     * 17ú
     */
    @Column(name = "day17")
    private double day17;

    /**
     * 18ú
     */
    @Column(name = "day18")
    private double day18;

    /**
     * 19ú
     */
    @Column(name = "day19")
    private double day19;

    /**
     * 20ú
     */
    @Column(name = "day20")
    private double day20;

    /**
     * 21ú
     */
    @Column(name = "day21")
    private double day21;

    /**
     * 22ú
     */
    @Column(name = "day22")
    private double day22;

    /**
     * 23ú
     */
    @Column(name = "day23")
    private double day23;

    /**
     * 24ú
     */
    @Column(name = "day24")
    private double day24;

    /**
     * 25ú
     */
    @Column(name = "day25")
    private double day25;

    /**
     * 26ú
     */
    @Column(name = "day26")
    private double day26;

    /**
     * 27ú
     */
    @Column(name = "day27")
    private double day27;

    /**
     * 28ú
     */
    @Column(name = "day28")
    private double day28;

    /**
     * 29ú
     */
    @Column(name = "day29")
    private double day29;

    /**
     * 30ú
     */
    @Column(name = "day30")
    private double day30;

    /**
     * 31ú
     */
    @Column(name = "day31")
    private double day31;

    /**
     * mèút
     */
    @Column(name = "fix_date", length = 8)
    private String fixDate;

	public short getYear() {
		return year;
	}

	public void setYear(short year) {
		this.year = year;
	}

	public short getMonth() {
		return month;
	}

	public void setMonth(short month) {
		this.month = month;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
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

	public String getThemeNo() {
		return themeNo;
	}

	public void setThemeNo(String themeNo) {
		this.themeNo = themeNo;
	}

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

	public String getWorkContentsDetail() {
		return workContentsDetail;
	}

	public void setWorkContentsDetail(String workContentsDetail) {
		this.workContentsDetail = workContentsDetail;
	}

	public boolean isPinFlg() {
		return pinFlg;
	}

	public void setPinFlg(boolean pinFlg) {
		this.pinFlg = pinFlg;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getDay1() {
		return day1;
	}

	public void setDay1(double day1) {
		this.day1 = day1;
	}

	public double getDay2() {
		return day2;
	}

	public void setDay2(double day2) {
		this.day2 = day2;
	}

	public double getDay3() {
		return day3;
	}

	public void setDay3(double day3) {
		this.day3 = day3;
	}

	public double getDay4() {
		return day4;
	}

	public void setDay4(double day4) {
		this.day4 = day4;
	}

	public double getDay5() {
		return day5;
	}

	public void setDay5(double day5) {
		this.day5 = day5;
	}

	public double getDay6() {
		return day6;
	}

	public void setDay6(double day6) {
		this.day6 = day6;
	}

	public double getDay7() {
		return day7;
	}

	public void setDay7(double day7) {
		this.day7 = day7;
	}

	public double getDay8() {
		return day8;
	}

	public void setDay8(double day8) {
		this.day8 = day8;
	}

	public double getDay9() {
		return day9;
	}

	public void setDay9(double day9) {
		this.day9 = day9;
	}

	public double getDay10() {
		return day10;
	}

	public void setDay10(double day10) {
		this.day10 = day10;
	}

	public double getDay11() {
		return day11;
	}

	public void setDay11(double day11) {
		this.day11 = day11;
	}

	public double getDay12() {
		return day12;
	}

	public void setDay12(double day12) {
		this.day12 = day12;
	}

	public double getDay13() {
		return day13;
	}

	public void setDay13(double day13) {
		this.day13 = day13;
	}

	public double getDay14() {
		return day14;
	}

	public void setDay14(double day14) {
		this.day14 = day14;
	}

	public double getDay15() {
		return day15;
	}

	public void setDay15(double day15) {
		this.day15 = day15;
	}

	public double getDay16() {
		return day16;
	}

	public void setDay16(double day16) {
		this.day16 = day16;
	}

	public double getDay17() {
		return day17;
	}

	public void setDay17(double day17) {
		this.day17 = day17;
	}

	public double getDay18() {
		return day18;
	}

	public void setDay18(double day18) {
		this.day18 = day18;
	}

	public double getDay19() {
		return day19;
	}

	public void setDay19(double day19) {
		this.day19 = day19;
	}

	public double getDay20() {
		return day20;
	}

	public void setDay20(double day20) {
		this.day20 = day20;
	}

	public double getDay21() {
		return day21;
	}

	public void setDay21(double day21) {
		this.day21 = day21;
	}

	public double getDay22() {
		return day22;
	}

	public void setDay22(double day22) {
		this.day22 = day22;
	}

	public double getDay23() {
		return day23;
	}

	public void setDay23(double day23) {
		this.day23 = day23;
	}

	public double getDay24() {
		return day24;
	}

	public void setDay24(double day24) {
		this.day24 = day24;
	}

	public double getDay25() {
		return day25;
	}

	public void setDay25(double day25) {
		this.day25 = day25;
	}

	public double getDay26() {
		return day26;
	}

	public void setDay26(double day26) {
		this.day26 = day26;
	}

	public double getDay27() {
		return day27;
	}

	public void setDay27(double day27) {
		this.day27 = day27;
	}

	public double getDay28() {
		return day28;
	}

	public void setDay28(double day28) {
		this.day28 = day28;
	}

	public double getDay29() {
		return day29;
	}

	public void setDay29(double day29) {
		this.day29 = day29;
	}

	public double getDay30() {
		return day30;
	}

	public void setDay30(double day30) {
		this.day30 = day30;
	}

	public double getDay31() {
		return day31;
	}

	public void setDay31(double day31) {
		this.day31 = day31;
	}

	public String getFixDate() {
		return fixDate;
	}

	public void setFixDate(String fixDate) {
		this.fixDate = fixDate;
	}


}