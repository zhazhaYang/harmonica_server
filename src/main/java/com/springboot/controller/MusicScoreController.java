package com.springboot.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springboot.bean.ChangeScoreData;
import com.springboot.bean.MusicScore;
import com.springboot.bean.User;
import com.springboot.service.MusicScoreService;
import com.springboot.service.UserService;

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
		long max = scoreService.getMaxID();
		if(id == 0 || id > max) {
			return "";
		}
		while(score == null) {
			try {
				score = scoreService.getByID(id);
			} catch(Exception e) {
				return "";
			}
			id += 1;
			if(id > max) {
				id = 1;
			}
		}
		
		return JSON.toJSONString(score);
	}

	@SuppressWarnings("unused")
	private String saveInLocal(MultipartFile data) {
		String result = "";
		String projectPath  = System.getProperty("user.dir");
		String dirPath = projectPath + "/src/main/resources/static/"+ "musicScores";
		File file = new File(dirPath);
		if(!file.exists()) {
			file.mkdir();
		}
		String name = getRandomString();
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
