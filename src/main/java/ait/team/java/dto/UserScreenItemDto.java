package ait.team.java.dto;

import java.io.Serializable;

public class UserScreenItemDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * TQ[gL[
     */
    private String surrogateKey;

    /**
     * [U[NO
     */
    private String userNo;

    /**
     * æÊURL
     */
    private String screenUrl;

    /**
     * æÊÚ
     */
    private String screenItem;

    /**
     * æÊüÍl
     */
    private String screenInput;

    /**
     * Û¶¼
     */
    private String saveName;

	public String getSurrogateKey() {
		return surrogateKey;
	}

	public void setSurrogateKey(String surrogateKey) {
		this.surrogateKey = surrogateKey;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getScreenUrl() {
		return screenUrl;
	}

	public void setScreenUrl(String screenUrl) {
		this.screenUrl = screenUrl;
	}

	public String getScreenItem() {
		return screenItem;
	}

	public void setScreenItem(String screenItem) {
		this.screenItem = screenItem;
	}

	public String getScreenInput() {
		return screenInput;
	}

	public void setScreenInput(String screenInput) {
		this.screenInput = screenInput;
	}

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

}