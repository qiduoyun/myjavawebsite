package com.xiuxiuyu.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xiuxiuyu.beans.Permission;
import com.xiuxiuyu.beans.RolePermission;
import com.xiuxiuyu.beans.User;
import com.xiuxiuyu.service.IPermissionService;
import com.xiuxiuyu.service.IUserService;
import com.xiuxiuyu.service.UserServiceImp;
import com.xiuxiuyu.vo.UserVO;

/**
 * �û�������
 * @author ��
 *
 */
@Component
public class UserUtil {
	@Autowired
	private IPermissionService permissionService;
	/***ϵͳȨ����Ϣ***/
	private static Set<String> sysPermissions=new HashSet<String>();
	/**
	 * ��ȡϵͳȨ����Ϣ
	 * @return
	 */
	public Set<String> getSysPermissions(){
		if(!sysPermissions.isEmpty()){
			return sysPermissions;
		}
		List<Permission> list = permissionService.getSysPermissions();
		if(!list.isEmpty()){
			for(Permission permission:list){
				sysPermissions.add(permission.getUrl());
			}
		}
		return sysPermissions;
	}
	/**
	 * ����cookie��Ϣ
	 * @param response
	 * @param key
	 * @param value
	 */
      public static void setToCookie(HttpServletResponse response,String key,String value){
    	  try {
			value = URLEncoder.encode(value, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	  Cookie cookie = new Cookie(key, value);
    	  cookie.setMaxAge(72*60*60);//cookie��Ч��Ϊ����
//    	  cookie.setMaxAge(3*60);//����cookie��Ч��Ϊ3����
    	  response.addCookie(cookie);
      }
      /**
       * ��ȡcookie��Ϣ
       * @param request
       * @param key
       * @return
       */
      public static String getFromCookie(HttpServletRequest request,String key){
    	  String value="";
    	  Cookie[] cookies = request.getCookies();
    	  if(cookies==null){
    		  return value;
    	  }
    	  for(Cookie cookie:cookies){
    		  if(key.equals(cookie.getName())){
    			  value = cookie.getValue();
    			  try {
					value = URLDecoder.decode(value, "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
    		  }
    	  }
    	  return value;
      }
      /**
       * ����session
       * @param session
       * @param key
       * @param user
       */
      public static void setToSession(HttpSession session,String key,UserVO user){
    	  session.setAttribute(key, user);
      }
      
  	/**
  	 * ת��ΪUserVO
  	 * @param user
  	 * @return
  	 */
	private static UserVO toUserVO(User user) {
		UserVO userVO = new UserVO();
		userVO.setName(user.getName());
		userVO.setPassword(user.getPassword());
		userVO.setUsername(user.getUsername());
		Set<String> permissions = new HashSet<String>();
		for (RolePermission rolePermission : user.getRole().getRolePermissions()) {
			permissions.add(rolePermission.getPermission().getUrl());
		}
		userVO.setPermissions(permissions);
		return userVO;
	}     
}
