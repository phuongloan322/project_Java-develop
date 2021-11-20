package ait.team.java.dto.output;

import java.util.List;

import ait.team.java.dto.ThemeDto;

public class DataSearchTheme {
	private List<ThemeDto> themeList;
	private int checkError;
	private String mes;

	public List<ThemeDto> getThemeList() {
		return themeList;
	}

	public void setThemeList(List<ThemeDto> themeList) {
		this.themeList = themeList;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public int getCheckError() {
		return checkError;
	}

	public void setCheckError(int checkError) {
		this.checkError = checkError;
	}

}
