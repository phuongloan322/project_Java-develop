package ait.team.java.dto;

import java.io.Serializable;

public class ProcessingMonthDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * x
     */
    private String processingMonth;

	public String getProcessingMonth() {
		return processingMonth;
	}

	public void setProcessingMonth(String processingMonth) {
		this.processingMonth = processingMonth;
	}


}