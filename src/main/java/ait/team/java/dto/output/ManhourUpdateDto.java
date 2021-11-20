package ait.team.java.dto.output;

import ait.team.java.dto.ManhourDto;
public class ManhourUpdateDto extends ManhourDto{

	private static final long serialVersionUID = 1L;

	private String userName;
	private String themeName1;
	private String workContentsCodeName;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getThemeName1() {
		return themeName1;
	}
	public void setThemeName1(String themeName1) {
		this.themeName1 = themeName1;
	}
	public double sum() {
		double sum = getDay1() + getDay2() + getDay3() + getDay4() + getDay5() + getDay6() + getDay7() + getDay8() + getDay9() + getDay10()
					+ getDay11() + getDay12() + getDay13() + getDay14() + getDay15() + getDay16() + getDay17() + getDay18() + getDay19() + getDay20()
					+getDay21() + getDay22() + getDay23() + getDay24() + getDay25() + getDay26() + getDay27() + getDay28() + getDay29() + getDay30() +getDay31();
		return sum;
	}
	
	public ManhourUpdateDto manhourMissing() {
		ManhourUpdateDto manhourMissing = new ManhourUpdateDto();
		double timeWork = 8;
		double timeMin = 0;
		double day1 = getDay1();
		if(day1>timeMin && day1<timeWork) {
			manhourMissing.setDay1(timeWork-day1);
		}
		double day2 = getDay2();
		if(day2>timeMin && day2<timeWork) {
			manhourMissing.setDay2(timeWork-day2);
		}
		double day3 = getDay3();
		if(day3>timeMin && day3<timeWork) {
			manhourMissing.setDay3(timeWork-day3);
		}
		double day4 = getDay4();
		if(day4>timeMin && day4<timeWork) {
			manhourMissing.setDay4(timeWork-day4);
		}
		double day5 = getDay5();
		if(day5>timeMin && day5<timeWork) {
			manhourMissing.setDay5(timeWork-day5);
		}
		double day6 = getDay6();
		if(day6>timeMin && day6<timeWork) {
			manhourMissing.setDay6(timeWork-day6);
		}
		double day7 = getDay7();
		if(day7>timeMin && day7<timeWork) {
			manhourMissing.setDay7(timeWork-day7);
		}
		double day8 = getDay8();
		if(day8>timeMin && day8<timeWork) {
			manhourMissing.setDay8(timeWork-day8);
		}
		double day9 = getDay9();
		if(day9>timeMin && day9<timeWork) {
			manhourMissing.setDay9(timeWork-day9);
		}
		double day10 = getDay10();
		if(day10>timeMin && day10<timeWork) {
			manhourMissing.setDay10(timeWork-day10);
		}
		double day11 = getDay11();
		if(day11>timeMin && day11<timeWork) {
			manhourMissing.setDay11(timeWork-day11);
		}
		double day12 = getDay12();
		if(day12>timeMin && day12<timeWork) {
			manhourMissing.setDay12(timeWork-day12);
		}
		double day13 = getDay13();
		if(day13>timeMin && day13<timeWork) {
			manhourMissing.setDay13(timeWork-day13);
		}
		double day14 = getDay14();
		if(day14>timeMin && day14<timeWork) {
			manhourMissing.setDay14(timeWork-day14);
		}
		double day15 = getDay15();
		if(day15>timeMin && day15<timeWork) {
			manhourMissing.setDay15(timeWork-day15);
		}
		double day16 = getDay16();
		if(day16>timeMin && day16<timeWork) {
			manhourMissing.setDay16(timeWork-day16);
		}
		double day17 = getDay17();
		if(day17>timeMin && day17<timeWork) {
			manhourMissing.setDay17(timeWork-day17);
		}
		double day18 = getDay18();
		if(day18>timeMin && day18<timeWork) {
			manhourMissing.setDay18(timeWork-day18);
		}
		double day19 = getDay19();
		if(day19>timeMin && day19<timeWork) {
			manhourMissing.setDay19(timeWork-day19);
		}
		double day20 = getDay20();
		if(day20>timeMin && day20<timeWork) {
			manhourMissing.setDay20(timeWork-day20);
		}
		double day21 = getDay21();
		if(day21>timeMin && day21<timeWork) {
			manhourMissing.setDay21(timeWork-day21);
		}
		double day22 = getDay22();
		if(day22>timeMin && day22<timeWork) {
			manhourMissing.setDay22(timeWork-day22);
		}
		double day23 = getDay23();
		if(day23>timeMin && day23<timeWork) {
			manhourMissing.setDay23(timeWork-day23);
		}
		double day24 = getDay24();
		if(day24>timeMin && day24<timeWork) {
			manhourMissing.setDay24(timeWork-day24);
		}
		double day25 = getDay25();
		if(day25>timeMin && day25<timeWork) {
			manhourMissing.setDay25(timeWork-day25);
		}
		double day26 = getDay26();
		if(day26>timeMin && day26<timeWork) {
			manhourMissing.setDay26(timeWork-day26);
		}
		double day27 = getDay27();
		if(day27>timeMin && day27<timeWork) {
			manhourMissing.setDay27(timeWork-day27);
		}
		double day28 = getDay28();
		if(day28>timeMin && day28<timeWork) {
			manhourMissing.setDay28(timeWork-day28);
		}
		double day29 = getDay29();
		if(day29>timeMin && day29<timeWork) {
			manhourMissing.setDay29(timeWork-day29);
		}
		double day30 = getDay30();
		if(day30>timeMin && day30<timeWork) {
			manhourMissing.setDay30(timeWork-day30);
		}
		double day31 = getDay31();
		if(day31>timeMin && day31<timeWork) {
			manhourMissing.setDay31(timeWork-day31);
		}
		return manhourMissing;
	}
	public String getWorkContentsCodeName() {
		return workContentsCodeName;
	}
	public void setWorkContentsCodeName(String workContentsCodeName) {
		this.workContentsCodeName = workContentsCodeName;
	}
}