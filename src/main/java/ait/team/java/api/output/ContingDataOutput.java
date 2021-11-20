package ait.team.java.api.output;

import java.util.List;

public class ContingDataOutput {
	private String fileName;
	private List<String[]> csvData;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public List<String[]> getCsvData() {
		return csvData;
	}
	public void setCsvData(List<String[]> csvData) {
		this.csvData = csvData;
	}
}
