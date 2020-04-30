package com.springboot.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "music_score")
public class MusicScore extends BaseBean {
	private String ownerAccount;
	private int supportCount;
	private String imageAddress;
	private String imageName;
	
	public String getOwnerAccount() {
		return ownerAccount;
	}
	
	public void setOwnerAccount(String account) {
		this.ownerAccount = account;
	}
	
	public int getSupportCount() {
		return supportCount;
	}
	
	public void setSupportCount(int count) {
		this.supportCount = count;
	}
	
	public String getImageAddress() {
		return imageAddress;
	}
	
	public void setImageAddress(String address) {
		this.imageAddress = address;
	}
	
	public String getImageName() {
		return imageName;
	}
	
	public void setImageName(String name) {
		this.imageName = name;
	}
	
}
