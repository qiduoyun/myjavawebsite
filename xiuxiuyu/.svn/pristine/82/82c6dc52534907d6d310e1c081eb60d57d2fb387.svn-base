package com.xiuxiuyu.web;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiuxiuyu.beans.User;
import com.xiuxiuyu.service.IUploadService;
import com.xiuxiuyu.service.IUserService;
import com.xiuxiuyu.util.PageInfo;

@Controller
public class UserAct {
	@Autowired
	private IUserService userService;
  @RequestMapping("/user/index.do")
	public String touser(){
	 
		
		return "user/index";
		
		
	}
  @RequestMapping("/user/list.do")
  @ResponseBody
	public String user(User user, ModelMap map,Integer page,Integer rows){
		
		 PageInfo<User > pageInfo=new PageInfo<User>();
		  if(page==null){
			  page=1;
		  }
		  pageInfo.setPage(page);
		  if(rows==null){
			  rows=10;
		  }
		  pageInfo.setPageSize(rows);
		  
		  pageInfo=userService.findUserList(pageInfo, user);
		JSONObject jsonObject1 =new JSONObject();
		jsonObject1.put("total", pageInfo.getRows());
		
		JSONArray jsonArray=new JSONArray();
		List<User> list =pageInfo.getResultList();
		for(int index=0;index<list.size();index++){
			User u=list.get(index);
			JSONObject jsonObject =new JSONObject();
			jsonObject.put("id", u.getId());
			jsonObject.put("username", u.getUsername());
			jsonObject.put("day", u.getDays());
			jsonObject.put("email", u.getEmail());
			jsonObject.put("logintime",u.getLoginTime());
			jsonObject.put("RegisterTime",u.getRegisterTime());
			jsonObject.put("rolename",u.getRole().getName());
			jsonObject.put("state",u.getState());
			jsonObject.put("telphone",u.getTelphone());
			jsonObject.put("updatatime",u.getUpdateTime());
			
			jsonArray.put(jsonObject);
		}
		
		jsonObject1.put("rows", jsonArray);
		
		 return jsonObject1.toString();
	}
  @RequestMapping("/user/openuser.do")
  @ResponseBody
  public String openuser(Integer userid){
	  String result=null;try{
	User user=userService.getUserById(userid);
	  if(user!=null&&(user.getState()==-1)){
		  user.setState(0);
		  userService.SaveUser(user);
		  result="1";
	  }}catch(Exception e){
		  result=e.getMessage();
	  }
	  
	  
	  return result;
	  
	  
  }
  
  @RequestMapping("/user/closeuser.do")
  @ResponseBody
  public String closeuser(Integer userid){
	  String result=null;try{
	User user=userService.getUserById(userid);
	  if(user!=null&&(user.getState()==0)){
		  user.setState(-1);
		  userService.SaveUser(user);
		  result="1";
	  }}catch(Exception e){
		  result=e.getMessage();
	  }
	  
	  
	  return result;
	  
	  
  }
  @RequestMapping("/user/openvip.do")
  @ResponseBody
  public String openvip(Integer userid,Integer days){
	User user=  userService.getUserById(userid);
	String message=null;
	if(user!=null&&user.getState()==0){
		user.setDays(user.getDays()+days);
		user.setRoleId(2);
		userService.SaveUser(user);
		message="1";
	}  
	return message;
  }
}
