package com.xiuxiuyu.dao;

import java.util.List;

import com.xiuxiuyu.beans.PayOrder;

public interface IOrderDao {
	/**
	 * 更新订单信息
	 * @param order
	 */
public void updateOrder(PayOrder order);
/**
 * 获取订单列表
 * @param sql
 * @param param
 * @param page
 * @param pageSize
 * @return
 */
public List<PayOrder> findPayOrderList(String sql,List param,Integer page,Integer pageSize);
/**
 * 根据订单号查询订单信息
 * @param orderId
 * @return
 */
public PayOrder getPayOrderByOrderId(String orderId);
/**
 * 查询订单总数
 * @param sql
 * @param param
 * @return
 */
public Integer getTotalCount(String sql,List param);
}
