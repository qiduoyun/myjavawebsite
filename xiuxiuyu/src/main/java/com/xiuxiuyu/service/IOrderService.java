package com.xiuxiuyu.service;

import com.xiuxiuyu.beans.PayOrder;
import com.xiuxiuyu.util.PageInfo;

public interface IOrderService {
	/**
	 * 更新订单信息
	 * @param order
	 */
public void updateOrder(PayOrder order);
/**
 * 根据订单号查询订单信息
 * @param orderId
 * @return
 */
public PayOrder getPayOrderByOrderId(String orderId);
/**
 * 分页查询订单信息
 * @param pageInfo
 * @param order
 * @return
 */
public PageInfo<PayOrder> getPayOrderPageInfo(PageInfo<PayOrder> pageInfo,PayOrder order,String startTime,String endTime,String username);
}
