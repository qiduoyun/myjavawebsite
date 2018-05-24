package com.xiuxiuyu.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiuxiuyu.beans.PayOrder;
import com.xiuxiuyu.service.IOrderService;

@Controller
public class FrontOrderAct extends FrontBaseAct{
	@Autowired
	private IOrderService orderService;
	/**
	 * VIP会员开通、续费
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/toOrder.jhtml")
    public String toOrder(HttpServletRequest request,ModelMap map){
    	//map.put("user", getFromSession(request, request.getSession(), "user", "username"));
    	return BASEPATH+"order/to_order.html";
    }
	@RequestMapping("/order.jhtml")
	public String order(PayOrder order,ModelMap map,HttpServletRequest request){
		order.setCreateTime(new Date());
		order.setOrderId(String.valueOf(System.currentTimeMillis()));
		order.setState(0);
		order.setUserId(getFromSession(request, request.getSession(), "user", "username").getId());
		if(order.getPayType()!=null&&order.getPayType().equals("2")){
	    	order.setMark("支付宝支付");
	    }else{
	    	order.setMark("微信支付");
	    }
		orderService.updateOrder(order);
		return "redirect:/payOrder.jhtml?payType="+order.getPayType();
	}
	/**
	 * 支付页面
	 * @param payType 2-支付宝 1-微信
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping("/payOrder.jhtml")
	public String payOrder(String payType,HttpServletRequest request,ModelMap map){
		map.put("user", getFromSession(request, request.getSession(), "user", "username"));
		if(payType!=null&&payType.endsWith("2")){
			return BASEPATH+"order/zhifubao_order.html";
		}
		return BASEPATH+"order/weixin_order.html";
	}
}
