package ait.team.java.controller.output;

import java.util.ArrayList;
import java.util.List;

import ait.team.java.dto.output.ManHourInputDto;

public class ManhourInputGeneral {

	private List<ManHourInputDto> showManhourInput = new ArrayList<>();
	private String modeValue;

	public List<ManHourInputDto> getShowManhourInput() {
		return showManhourInput;
	}

	public void setShowManhourInput(List<ManHourInputDto> showManhourInput) {
		this.showManhourInput = showManhourInput;
	}

	public String getModeValue() {
		return modeValue;
	}

	public void setModeValue(String valueMode) {
		this.modeValue = valueMode;
	}
}
