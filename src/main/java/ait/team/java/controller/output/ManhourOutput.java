package ait.team.java.controller.output;

import java.util.ArrayList;
import java.util.List;

import ait.team.java.controller.input.InputSearchManhour;
import ait.team.java.dto.GroupDto;
import ait.team.java.dto.UserDto;
import ait.team.java.dto.output.ManhourUpdateDto;

public class ManhourOutput {
	private List<ManhourUpdateDto> manhour = new ArrayList<>();
	private List<UserDto> users = new ArrayList<>();
	private List<GroupDto> groups = new ArrayList<>();
	private InputSearchManhour inputSearch = new InputSearchManhour();
	private boolean[] holidays;
	private String dayNow;
	
	
	public List<UserDto> getUsers() {
		return users;
	}
	public void setUsers(List<UserDto> users) {
		this.users = users;
	}
	public List<GroupDto> getGroups() {
		return groups;
	}
	public void setGroups(List<GroupDto> groups) {
		this.groups = groups;
	}
	public InputSearchManhour getInputSearch() {
		return inputSearch;
	}
	public void setInputSearch(InputSearchManhour inputSearch) {
		this.inputSearch = inputSearch;
	}
	public List<ManhourUpdateDto> getManhour() {
		return manhour;
	}
	public void setManhour(List<ManhourUpdateDto> manhour) {
		this.manhour = manhour;
	}
	public ManhourUpdateDto manhourTotal() {
		ManhourUpdateDto manhourUpdateTotal = new ManhourUpdateDto();
		double[] days= new double[31];
		for(int i=0;i<days.length;i++) {
			days[i] = 0;
		}
		for (ManhourUpdateDto man : manhour) {
			days[0] += man.getDay1();
			days[1] += man.getDay2();
			days[2] += man.getDay3();
			days[3] += man.getDay4();
			days[4] += man.getDay5();
			days[5] += man.getDay6();
			days[6] += man.getDay7();
			days[7] += man.getDay8();
			days[8] += man.getDay9();
			days[9] += man.getDay10();
			days[10] += man.getDay11();
			days[11] += man.getDay12();
			days[12] += man.getDay13();
			days[13] += man.getDay14();
			days[14] += man.getDay15(); 
			days[15] += man.getDay16();
			days[16] += man.getDay17();
			days[17] += man.getDay18();
			days[18] += man.getDay19();
			days[19] += man.getDay20();
			days[20] += man.getDay21();
			days[21] += man.getDay22();
			days[22] += man.getDay23();
			days[23] += man.getDay24();
			days[24] += man.getDay25();
			days[25] += man.getDay26();
			days[26] += man.getDay27();
			days[27] += man.getDay28();
			days[28] += man.getDay29();
			days[29] += man.getDay30();
			days[30] += man.getDay31();
		}
		manhourUpdateTotal.setDay1(days[0]);
		manhourUpdateTotal.setDay2(days[1]);
		manhourUpdateTotal.setDay3(days[2]);
		manhourUpdateTotal.setDay4(days[3]);
		manhourUpdateTotal.setDay5(days[4]);
		manhourUpdateTotal.setDay6(days[5]);
		manhourUpdateTotal.setDay7(days[6]);
		manhourUpdateTotal.setDay8(days[7]);
		manhourUpdateTotal.setDay9(days[8]);
		manhourUpdateTotal.setDay10(days[9]);
		manhourUpdateTotal.setDay11(days[10]);
		manhourUpdateTotal.setDay12(days[11]);
		manhourUpdateTotal.setDay13(days[12]);
		manhourUpdateTotal.setDay14(days[13]);
		manhourUpdateTotal.setDay15(days[14]);
		manhourUpdateTotal.setDay16(days[15]);
		manhourUpdateTotal.setDay17(days[16]);
		manhourUpdateTotal.setDay18(days[17]);
		manhourUpdateTotal.setDay19(days[18]);
		manhourUpdateTotal.setDay20(days[19]);
		manhourUpdateTotal.setDay21(days[20]);
		manhourUpdateTotal.setDay22(days[21]);
		manhourUpdateTotal.setDay23(days[22]);
		manhourUpdateTotal.setDay24(days[23]);
		manhourUpdateTotal.setDay25(days[24]);
		manhourUpdateTotal.setDay26(days[25]);
		manhourUpdateTotal.setDay27(days[26]);
		manhourUpdateTotal.setDay28(days[27]);
		manhourUpdateTotal.setDay29(days[28]);
		manhourUpdateTotal.setDay30(days[29]);
		manhourUpdateTotal.setDay31(days[30]);
		return manhourUpdateTotal;
	 }
	public boolean[] getHolidays() {
		return holidays;
	}
	public void setHolidays(boolean[] holidays) {
		this.holidays = holidays;
	}
	public String getDayNow() {
		return dayNow;
	}
	public void setDayNow(String dayNow) {
		this.dayNow = dayNow;
	}
}
