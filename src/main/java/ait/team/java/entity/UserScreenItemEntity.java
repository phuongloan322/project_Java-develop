package ait.team.java.entity;
import java.io.Serializable;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_user_screen_item", schema = "public")
public class UserScreenItemEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * TQ[gL[
     */
    @Id
    @Column(name = "surrogate_key", nullable = false, length = 26)
    private String surrogateKey;

    /**
     * [U[NO
     */
    @Column(name = "user_no", nullable = false, length = 9)
    private String userNo;

    /**
     * æÊURL
     */
    @Column(name = "screen_url", nullable = false, length = 256)
    private String screenUrl;

    /**
     * æÊÚ
     */
    @Column(name = "screen_item", nullable = false, length = 256)
    private String screenItem;

    /**
     * æÊüÍl
     */
    @Column(name = "screen_input", length = 256)
    private String screenInput;

    /**
     * Û¶¼
     */
    @Column(name = "save_name", length = 40)
    private String saveName;

    public UserScreenItemEntity() {
    	
    }
    
    public UserScreenItemEntity(UserScreenItemEntity userScreenItemEntity) {
    	this.saveName = userScreenItemEntity.getSaveName();
    	this.screenInput = userScreenItemEntity.getScreenInput();
    	this.screenItem = userScreenItemEntity.getScreenItem();
    	this.screenUrl = userScreenItemEntity.getScreenUrl();
    	this.surrogateKey = userScreenItemEntity.getSurrogateKey();
    	this.userNo = userScreenItemEntity.getUserNo();
    }
    
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