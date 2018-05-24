package com.xiuxiuyu.web;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.xiuxiuyu.beans.UploadFile;
import com.xiuxiuyu.service.IUploadService;

/**
 * 上传控制类
 * 
 * @author 桂都
 *
 */
@Controller
public class UploadAct {
	@Autowired
	private IUploadService uploadService;

	/**
	 * 上传图片
	 * 
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping("/upload/uploadImg.do")
	@ResponseBody
	public String uploadImg(HttpServletRequest request, @RequestParam(value = "Filedata", required = false) CommonsMultipartFile file) {
		JSONObject jsonObject = new JSONObject();
		int state = 0;// 1成功，其他失败
		String error = "";// 失败信息
		if (!file.isEmpty()) {
			String realName = file.getOriginalFilename();
			String fileSuffix = realName.substring(realName.lastIndexOf('.'), realName.length());
			if (fileSuffix .equals(".jpg")  || fileSuffix .equals(".jpeg") || fileSuffix .equals(".png")
					|| fileSuffix .equals(".bmp") || fileSuffix .equals(".gif")) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
				String path = request.getRealPath("/");
				String relativePath = "/u/" + sdf.format(new Date());
				path+=relativePath;
				File dirFile = new File(path);
				if(!dirFile.exists()){
					dirFile.mkdirs();
				}
				File destFile = new File(path, System.currentTimeMillis() + fileSuffix);
				try {
					file.transferTo(destFile);
					UploadFile uploadFile = new UploadFile();
					uploadFile.setName(destFile.getName());
					uploadFile.setPath(relativePath+"/"+destFile.getName());
					uploadFile.setRealName(realName);
					uploadFile.setUploadTime(new Date());
					uploadService.save(uploadFile);
					state = 1;
					jsonObject.put("path", uploadFile.getPath());
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		jsonObject.put("state", state);
		jsonObject.put("error", error);
		return jsonObject.toString();
	}

	/**
	 * 上传文件
	 * 
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping("/upload/uploadFile.do")
	@ResponseBody
	public String uploadFile(HttpServletRequest request, @RequestParam(value = "Filedata", required = false) CommonsMultipartFile file) {
		JSONObject jsonObject = new JSONObject();
		int state = 0;// 1成功，其他失败
		String error = "";// 失败信息
		if (!file.isEmpty()) {
			String realName = file.getOriginalFilename();
			String fileSuffix = realName.substring(realName.lastIndexOf('.'), realName.length());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			String path = request.getRealPath("/");
			String relativePath = "/u/" + sdf.format(new Date());
			path += relativePath;
			File dirFile = new File(path);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			File destFile = new File(path, System.currentTimeMillis() + fileSuffix);
			try {
				file.transferTo(destFile);
				UploadFile uploadFile = new UploadFile();
				uploadFile.setName(destFile.getName());
				uploadFile.setPath(relativePath + "/" + destFile.getName());
				uploadFile.setRealName(realName);
				uploadFile.setUploadTime(new Date());
				uploadService.save(uploadFile);
				state = 1;
				jsonObject.put("path", uploadFile.getPath());
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		jsonObject.put("state", state);
		jsonObject.put("error", error);
		return jsonObject.toString();
	}  
}
