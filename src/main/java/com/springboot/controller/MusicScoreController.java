package com.springboot.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.springboot.bean.MusicScore;
import com.springboot.service.MusicScoreService;

@Controller
public class MusicScoreController {
	@Autowired
	private MusicScoreService scoreService;
	
	@RequestMapping(value="/practice/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(@RequestBody MusicScore score) {
		if(score == null) {
			return "上传失败,上传者信息为空";
		}
		
		if(scoreService.checkExistsBy(score.getOwnerAccount(), score.getImageName())) {
			return "该曲谱已经上传过了";
		}
		String result = "上传成功";
		try {
			scoreService.save(score);
		} catch(Exception e) {
			result = "上传失败" + e.getMessage();
		} finally {}
		return result;
	}
	
	@RequestMapping(value = "/practice/upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(@RequestParam(required = false, value = "file") MultipartFile file) {
		String result = "";
		if(!file.isEmpty()) {
			try {
				result = saveInLocal(file);
			} catch(Exception e) {
			}
		} else {
			
		}
		
		return result;
	}
	
	@RequestMapping(value="/practice/get", method = RequestMethod.GET)
	@ResponseBody
	public String getByID(@RequestParam long id) {
		MusicScore score = null;
		int max = scoreService.getMaxID();
		if(id == 0 || id > max) {
			return "";
		}
		while(score == null) {
			try {
				score = scoreService.getByID(id);
//				score.getID();
				System.out.println(score.getID());
			} catch(Exception e) {
				return "";
			}
			id += 1;
			if(id > max) {
				id = 1;
			}
		}
		
		System.out.println(JSON.toJSONString(score));
		return JSON.toJSONString(score);
	}
	
	@RequestMapping(value="/practice/updateSupport", method = RequestMethod.GET)
	@ResponseBody
	public boolean updateSupportBy(@RequestParam(value = "iD", required = false) int iD, @RequestParam(value = "isAdd", required = false) boolean isAdd) {
			return scoreService.updateSupportCount(iD, isAdd);
	}

	@SuppressWarnings("unused")
	private String saveInLocal(MultipartFile data) {
		String result = "";
		String projectPath  = System.getProperty("user.dir");
		String dirPath = projectPath + "/src/main/resources/static";
		File file = new File(dirPath);
		if(!file.exists()) {
			file.mkdir();
		}
		String name = getRandomString() + data.getOriginalFilename();
		System.out.println(name);
		String scorePath = dirPath + "/" + name;
		file = new File(scorePath);
		try {
			file.createNewFile();
			data.transferTo(file);
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
