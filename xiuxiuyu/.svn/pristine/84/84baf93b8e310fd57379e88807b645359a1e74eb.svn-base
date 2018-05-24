package com.xiuxiuyu.web;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.xiuxiuyu.beans.Channel;
import com.xiuxiuyu.beans.Content;
import com.xiuxiuyu.beans.RolePermission;
import com.xiuxiuyu.beans.User;
import com.xiuxiuyu.service.IChannelService;
import com.xiuxiuyu.service.IContentService;
import com.xiuxiuyu.service.IUserService;
import com.xiuxiuyu.util.StringUtils;
import com.xiuxiuyu.util.UserUtil;
import com.xiuxiuyu.vo.UserVO;

@Controller
public class FrontIndexAct {
	/**
	 * ģ��Ŀ¼
	 */
	private static String BASEPATH="/WEB-INF/t/"; 
	@Autowired
	private IContentService contentService;
	@Autowired
	private IChannelService channelService;
	@Autowired
	private UserUtil userUtil;
	@Autowired
	private IUserService userService;
	@Autowired
	private Producer captchaProducer;
	/**
	 * ��ҳ
	 * @param map
	 * @return
	 */
	@RequestMapping("/index.jhtml")
    public String index(ModelMap map,HttpServletRequest request){
		map.put("user", getFromSession(request,request.getSession(), "user","username"));
    	return "/WEB-INF/t/index/index.html";
    }
	/**
	 * ��Ŀ��ҳ
	 * @param url
	 * @return
	 */
	@RequestMapping(value="/{url}/index{page}.jhtml",method=RequestMethod.GET)
	public String channelIndex(@PathVariable String url,@PathVariable String page,ModelMap modelMap,HttpServletRequest request){
		Channel channel = channelService.getChannelInfoByUrl(url);
		modelMap.put("channel", channel);
		modelMap.put("page", page);
		modelMap.put("user", getFromSession(request,request.getSession(), "user","username"));
		return BASEPATH+channel.getChannelTemplate();
	}
	/**
	 * ����ҳ
	 * @param url
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/{url}/{id}.jhtml")
	public String content(@PathVariable String url,@PathVariable Integer id,ModelMap modelMap,HttpServletRequest request){
		int state = validatePermission(url, request);
		if(state!=1){
			modelMap.put("state", state);
			return BASEPATH+"no_permission.html";
		}
		Content content = contentService.getContentByUrlAndId(url, id);
		modelMap.put("content", content);
		modelMap.put("user", getFromSession(request,request.getSession(), "user","username"));
		return BASEPATH+content.getContentTemplate();
	}

	/**
	 * ȥע��ҳ��
	 * @return
	 */
	@RequestMapping("/toRegister.jhtml")
	public String toregister(HttpServletRequest request,ModelMap modelMap){
		modelMap.put("user", getFromSession(request,request.getSession(), "user","username"));
		return BASEPATH+"register.html";
	}
	/**e
	 * �û�ע��
	 * @return
	 */
	@RequestMapping("/register.jhtml")
	public String register(User user, ModelMap map){
		if (user.getState()==null)
			user.setState(0);
		String password=user.getPassword();
		String password1=StringUtils .getMD5String(password, "");
		user.setPassword(password1);
		user.setDays(0);
		user.setRoleId(1);
		user.setRegisterTime(new Date());
		if(userService.getUserByUsername(user.getUsername())==null){
		userService.SaveUser(user);
		
		return "redirect:/toLogin.jhtml";}
		
		else {
			map.put("message", "ע��ʧ�ܣ��û������ظ���������ע��");
			return BASEPATH+"register.html";
		
		}
	}	
	/**
	 * ȥ��¼
	 * @return
	 */
	@RequestMapping("/toLogin.jhtml")
	public String tologin(HttpServletRequest request,ModelMap modelMap){
		modelMap.put("user", getFromSession(request,request.getSession(), "user","username"));
		return BASEPATH+"login.html";
	}
	/**
	 * �û���¼
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/login.jhtml")
	public String login(String username,String password,HttpServletRequest request,HttpServletResponse response,ModelMap map) {
		
		User user=userService.getUserByUsername(username);
		
		String password1=StringUtils.getMD5String(password, "");
		
		if((user!=null)&&(user.getPassword().equals(password1))){
			user.setLoginTime(new Date());
			UserVO vo = toUserVO(user);
			userUtil.setToCookie(response, "username", username);
			userUtil.setToCookie(response, "password", StringUtils.getMD5String(username, vo.getPassword()));
			userUtil.setToSession(request.getSession(), "user", vo);
			
			return "redirect:/index.jhtml";
		}
		
		else{ map.put("message", "用户名或密码错误");
			return BASEPATH+"login.html";}
		
	}
	/**
	 * �˳��˺�
	 */
	@RequestMapping("/loginout.jhtml")
	public String loginout(HttpServletRequest req,HttpServletResponse res,String path){
		
		req.getSession().removeAttribute("user");
		userUtil.setToCookie(res, "username", "");
		userUtil.setToCookie(res, "password", "");
		return "redirect:"+path;
		
	}
	/*
	 * 
	 */
	@RequestMapping("/touser.jhtml")
	public String touser(Integer userid,ModelMap map ){
		
		User user= userService.getUserById(userid);
		
		map.put("user", user);
	
		return BASEPATH+"user.html";
		
		
	}
	@RequestMapping("/user.jhtml")
	public String updatauserinfo(String email,String telphone,Integer userid){
		User user=userService.getUserById(userid);		
		user.setEmail(email);
		user.setTelphone(telphone);
		
		user.setUpdateTime(new Date());
		userService.SaveUser(user);
		
		return "redirect:touser.jhtml?userid="+user.getId();
		
	}
	
	/*
	 * 
	 */
	@RequestMapping("/touserpassword.jhtml")
	public String touserpassword(HttpServletRequest request,ModelMap map){
		map.put("user", getFromSession(request, request.getSession(), "user", "username"));
		
		return BASEPATH+"userpassword.html";
		
	}
	/*
	 * 
	 */
	@RequestMapping("/userpassword.jhtml")
	@ResponseBody
	public String userpassword(HttpServletRequest request,HttpServletResponse response,String oldpassword,String newpassword){
		String var=null;
		try{
      UserVO userVo= getFromSession(request, request.getSession(), "user", "username");
      User user=userService.getUserById(userVo.getId());
      if(StringUtils.getMD5String(oldpassword, "").equals(user.getPassword())){
    	  user.setPassword(StringUtils.getMD5String(newpassword, ""));
    	  user.setUpdateTime(new Date());
    	  userService.SaveUser(user);
    	  var="1";
      }
      else{
    	 
		var="-1";
      }
		}catch(Exception e){var="0";}
      return var;
	}
	
	/**
	 * �����û���¼
	 * @return
	 */
	@RequestMapping("/loginAjax.jhtml")
	@ResponseBody
	public String loginAjax(String username,String password,HttpServletRequest request,HttpServletResponse response){
		int state = 0;
		User user=userService.getUserByUsername(username);
		
		String password1=StringUtils.getMD5String(password, "");
		
		if((user!=null)&&(user.getPassword().equals(password1))){
			
			UserVO vo = toUserVO(user);
			userUtil.setToCookie(response, "username", username);
			userUtil.setToCookie(response, "password", StringUtils.getMD5String(username, vo.getPassword()));
			userUtil.setToSession(request.getSession(), "user", vo);
			state=1;
		}
		JSONObject jsonObject =new JSONObject();
		jsonObject.put("state", state);
		return jsonObject.toString();
	}
	/**
	 * ת��ΪUserVO
	 * @param user
	 * @return
	 */
	private UserVO toUserVO(User user){
		UserVO userVO = new UserVO();
		userVO.setId(user.getId());
		userVO.setName(user.getName());
		userVO.setPassword(user.getPassword());
		userVO.setUsername(user.getUsername());
		userVO.setDays(user.getDays());
		Set<String> permissions = new HashSet<String>();
		for (RolePermission rolePermission : user.getRole().getRolePermissions()) {
			permissions.add(rolePermission.getPermission().getUrl());
		}
		userVO.setPermissions(permissions);
		return userVO;
	}
	/**
	 * 验证权限
	 * @param url
	 * @param request
	 * @return 1-通过 -2未登陆 -1未开通会员 0会员剩余天数为0
	 */
	private Integer validatePermission(String url,HttpServletRequest request){
		if(userUtil.getSysPermissions().contains(url)){
			String username = userUtil.getFromCookie(request, "username");
			String password = userUtil.getFromCookie(request, "password");
			if(StringUtils.isEmpty(username)){
				return -2;
			}else{
				UserVO userVO = getFromSession(request,request.getSession(), "user","username");
				if(userVO==null){
					User user = userService.getUserByUsername(username);
					if(user!=null){
                       userVO = toUserVO(user);
                       //未开通会员
                       if(!userVO.getPermissions().contains(url)){
                    	   return -1;
                       }else{
                    	   if(user.getDays()<=0){
                    		   return 0;
                    	   }
                       }
                       userUtil.setToSession(request.getSession(), "user", userVO);
					}else{
						return -2;
					}
				}else{
					//未开通会员
                    if(!userVO.getPermissions().contains(url)){
                 	   return -1;
                    }else{
                 	   if(userVO.getDays()<=0){
                		   return 0;
                	   }
                   }
				}
			}
		}
		return 1;
	}
	/**
	 * ��ȡ��֤��
	 * @param request
	 * @param response
	 */
	@RequestMapping("/kaptchaImg.jhtml")
	public void kaptchaImg(HttpServletRequest request,HttpServletResponse response){
        response.setDateHeader("Expires", 0); 
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");    
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");    
        response.setHeader("Pragma", "no-cache");  
        response.setContentType("image/jpeg"); 
		String text = captchaProducer.createText(); 
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text); 
		BufferedImage image = captchaProducer.createImage(text);
		ServletOutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			ImageIO.write(image, "jpg", outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��֤�û����Ƿ����
	 * @param username
	 * @return 0-������ 1-����
	 */
	@RequestMapping("/validateUsername.jhtml")
	@ResponseBody
	public String validateUsername(String username){
		int state = 0;
		User user  = userService.getUserByUsername(username);
		if(user==null){
			state =1;
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("state", state);
		return jsonObject.toString();
	}
	
    /**
     * ��ȡsession
     * @param session
     * @param key
     * @return
     */
    public UserVO getFromSession(HttpServletRequest request,HttpSession session,String key,String cookieKey){
  	  Object obj = session.getAttribute(key);
  	  if(obj!=null){
  		  return (UserVO) obj;
  	  }
  	  String value="";
  	  Cookie[] cookies = request.getCookies();
  	  if(cookies==null){
  		  return (UserVO) obj;
  	  }
  	  for(Cookie cookie:cookies){
  		  if(cookieKey.equals(cookie.getName())){
  			  value = cookie.getValue();
  			  try {
					value = URLDecoder.decode(value, "utf-8");
					if(!StringUtils.isEmpty(value)){
						User user = userService.getUserByUsername(value);
						if(user!=null){
							obj = toUserVO(user);
							session.setAttribute(key, obj);
						}
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
  		  }
  	  }
  	  return obj==null?null:(UserVO)obj;
    }
    @RequestMapping("/search{page}.jhtml")
    public String search(String searcher,@PathVariable String page,ModelMap map,HttpServletRequest request){
    	map.put("searcher", searcher);
    	map.put("user", getFromSession(request, request.getSession(), "user", "username"));
    	map.put("page", StringUtils.isEmpty(page)?1:Integer.valueOf(page));
    	return BASEPATH + "search/search_result.html";
    }
    @RequestMapping("/testPath.jhtml")
    @ResponseBody
    public String testPath(){
    	return this.getClass().getClassLoader().getResource("").getPath();
    }
    /**
     * 统计内容浏览量
     * @param id
     * @return
     */
    @RequestMapping("/viewsCount.jhtml")
    public String viewsCount(Integer id){
    	if(id!=null&&id.intValue()!=0){
    		Content content = contentService.getContentById(id);
    		content.setViews(content.getViews()==null?0:content.getViews()+1);
    		contentService.updateContent(content);
    	}
    	return "";
    }
}
