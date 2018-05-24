package com.xiuxiuyu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台首页
 * @author 桂都
 *
 */
@Controller
public class AdminIndexAct {
   @RequestMapping("/index.do")
   public String index(){
	   return "index";
   }
}
