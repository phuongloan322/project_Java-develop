package ait.team.java.converter;

import org.springframework.stereotype.Component;

import ait.team.java.controller.input.InputSearchTheme;
import ait.team.java.controller.input.InputSelectTheme;

@Component
public class ConvertInputSelectToSearchTheme {
	public InputSearchTheme toInputSearch(InputSelectTheme inputSelect) {
		InputSearchTheme inputSearch = new InputSearchTheme();
		if (inputSelect.getThemeNo() == "") {
			inputSearch.setThemeNo(null);
		}else {
			inputSearch.setThemeNo(inputSelect.getThemeNo());
		}
		
		if (inputSelect.getThemeName1() == "") {
			inputSearch.setThemeName1(null);
		}else {
			inputSearch.setThemeName1(inputSelect.getThemeName1());
		}
		
		if (inputSelect.getAccountingGroupCode() == "") {
			inputSearch.setAccountingGroupCode(null);
		}else {
			inputSearch.setAccountingGroupCode(inputSelect.getAccountingGroupCode());
		}
		
		if (inputSelect.getSalesObjectCode() == "") {
			inputSearch.setSalesObjectCode(null);
		}else {
			inputSearch.setSalesObjectCode(inputSelect.getSalesObjectCode());
		}
		
		if (inputSelect.getSoldFlg().equals("") || inputSelect.getSoldFlg().equals("全て")) {
			inputSearch.setSoldFlg(null);
		}else {
			if(inputSelect.getSoldFlg().equals("false")) {
				inputSearch.setSoldFlg(false);
			}
			if(inputSelect.getSoldFlg().equals("true")) {
				inputSearch.setSoldFlg(true);
			}
		}
		return inputSearch;
	}
}
