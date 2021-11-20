package ait.team.java.entity.idclass;

import java.io.Serializable;

public class WorkContentId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String workContentsClass;
	private String workContentsCode;
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
	
}
