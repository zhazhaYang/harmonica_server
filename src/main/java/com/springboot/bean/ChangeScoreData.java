package com.springboot.bean;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class ChangeScoreData {

	private String ownerAccount;
	private int supportCount;
	private MultipartFile imageData;
	
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
	
	public MultipartFile getImageData() {
		return imageData;
	}
	
	public void setImageData(MultipartFile data) {
		this.imageData = data;
	}
	
	public MusicScore getMusicScore() {
		String path = saveInLocal();
		if(path != null) {
			MusicScore score = new MusicScore();
			score.setOwnerAccount(ownerAccount);
			score.setSupportCount(supportCount);
//			score.setImageAdress(path);
			return score;
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("unused")
	private String saveInLocal() {
		String result = "";
		String projectPath  = System.getProperty("user.dir");
		String dirPath = projectPath + "/" + "musicScores";
		File file = new File(dirPath);
		if(!file.exists()) {
			file.mkdir();
		}
		String name = getRandomString();
		String scorePath = dirPath + "/" + name;
		file = new File(scorePath);
		try {
			file.createNewFile();
			imageData.transferTo(file);
			result = file.getAbsolutePath();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		return result;
	} 
	
    @SuppressWarnings("unused")
	private String getRandomString(){
        StringBuilder sb = new StringBuilder();
        sb.append(LocalDateTime.now().getYear());
        int month = LocalDateTime.now().getMonth().getValue();
        sb.append(month>=10?month:'0'+month);
        sb.append(LocalDateTime.now().getDayOfMonth());
        sb.append(Math.abs(UUID.randomUUID().getLeastSignificantBits()%1_00_00_000_000L));
        return sb.toString();
    }
	
}
