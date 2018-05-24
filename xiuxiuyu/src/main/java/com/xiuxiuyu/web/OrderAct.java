package com.xiuxiuyu.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiuxiuyu.beans.PayOrder;
import com.xiuxiuyu.service.IOrderService;
import com.xiuxiuyu.util.PageInfo;

@Controller
public class OrderAct {
	@Autowired
    private IOrderService orderService;
	@RequestMapping("/order/index.do")
	public String index(){
	     return "order/index";	
	}
	/**
	 * 订单列表
	 * @param order
	 * @param startTime 订单开始时间
	 * @param endTime 订单结束时间
	 * @param username 用户名
	 * @param page 当前页
	 * @param pageSize 页大小
	 * @param map
	 * @return
	 */
	@RequestMapping("/order/list.do")
	@ResponseBody
	public String findPayOrderList(PayOrder order,String startTime,String endTime,String username,Integer page,Integer rows){
		PageInfo<PayOrder> pageInfo = new PageInfo<PayOrder>();
		if(page==null||page<=0){
			page=1;
		}
		if(rows==null){
			rows=10;
		}
		pageInfo.setPage(page);
		pageInfo.setPageSize(rows);
		pageInfo = orderService.getPayOrderPageInfo(pageInfo, order, startTime, endTime, username);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("total", pageInfo.getRows());
		List<Map> list = new ArrayList<Map>();
		for(PayOrder payOrder:pageInfo.getResultList()){
			Map map = new HashMap();
			map.put("id", payOrder.getId());
			map.put("orderId", payOrder.getOrderId());
			map.put("username", payOrder.getUser().getUsername());
			map.put("payType", payOrder.getPayType().equals("1")?"微信":"支付宝");
			map.put("mark", payOrder.getMark());
			map.put("createTime", payOrder.getCreateTime());
			String stateInfo = "";
			if(payOrder.getState()==1){
				stateInfo="成功";
			}else if(payOrder.getState()==-1){
				stateInfo="作废";
			}else{
				stateInfo="未处理";
			}
			map.put("stateInfo", stateInfo);
			map.put("state", payOrder.getState());
			map.put("token", payOrder.getToken());
			map.put("payAccount", payOrder.getPayAccount());
			list.add(map);
		}
		jsonObject.put("rows", list);
		return jsonObject.toString();
	}
	/**
	 * 处理订单
	 * @param state  1-成功订单  -1作废订单
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/order/handlePayOrder.do")
	@ResponseBody
	public String handlePayOrder(Integer state,String orderId,Integer page,Integer pageSize){
		String result="0";
		try {
			PayOrder order = orderService.getPayOrderByOrderId(orderId);
			order.setState(state);
			orderService.updateOrder(order);
			result="1";
		} catch (Exception e) {
			result = e.getMessage();
		}
		return result;
	}
}
