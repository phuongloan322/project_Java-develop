package ait.team.java.entity.idclass;

import java.io.Serializable;

public class RoleId implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String roleCode;
	private String screenUrl;
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getScreenUrl() {
		return screenUrl;
	}
	public void setScreenUrl(String screenUrl) {
		this.screenUrl = screenUrl;
	}
}
