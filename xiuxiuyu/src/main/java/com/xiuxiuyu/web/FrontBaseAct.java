package com.xiuxiuyu.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xiuxiuyu.beans.RolePermission;
import com.xiuxiuyu.beans.User;
import com.xiuxiuyu.service.IUserService;
import com.xiuxiuyu.util.StringUtils;
import com.xiuxiuyu.vo.UserVO;

@Component
public class FrontBaseAct {
	public static final String BASEPATH="/WEB-INF/t/"; 
	@Autowired
	private IUserService userService;
    /**
     * 获取session
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
	/**
	 *转换成UserVO
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
}
