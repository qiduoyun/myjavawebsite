package com.xiuxiuyu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiuxiuyu.beans.PayOrder;
import com.xiuxiuyu.dao.IOrderDao;
import com.xiuxiuyu.util.PageInfo;
import com.xiuxiuyu.util.StringUtils;

@Service
@Transactional
public class OrderServiceImp implements IOrderService {
	@Autowired
    private IOrderDao orderDao;
	public void updateOrder(PayOrder order) {
		orderDao.updateOrder(order);
	}
	public PayOrder getPayOrderByOrderId(String orderId) {
		return orderDao.getPayOrderByOrderId(orderId);
	}
	public PageInfo<PayOrder> getPayOrderPageInfo(PageInfo<PayOrder> pageInfo, PayOrder order,String startTime,String endTime,String username) {
		StringBuilder sql = new StringBuilder();
		List param = new ArrayList();
		sql.append("from PayOrder where 1=1 ");
		if(order.getState()!=null){
			sql.append(" and state=?");
			param.add(order.getState());
		}
		if(!StringUtils.isEmpty(order.getOrderId())){
			sql.append(" and orderId=?");
			param.add(order.getOrderId());
		}
		if(!StringUtils.isEmpty(order.getPayAccount())){
			sql.append(" and payAccount=?");
			param.add(order.getPayAccount());
		}
		if(!StringUtils.isEmpty(order.getToken())){
			sql.append(" and token=?");
			param.add(order.getToken());
		}
		if(!StringUtils.isEmpty(startTime)){
			sql.append(" and createTime>=?");
			param.add(startTime);
		}
		if(!StringUtils.isEmpty(endTime)){
			sql.append(" and createTime<=?");
			param.add(endTime);
		}
		if(!StringUtils.isEmpty(username)){
			sql.append(" and user.username=?");
			param.add(username);
		}
		Integer total = orderDao.getTotalCount("select count(*) "+sql.toString(), param);
		sql.append(" order by id desc");
		List<PayOrder> list = orderDao.findPayOrderList(sql.toString(), param, pageInfo.getPage(), pageInfo.getPageSize());
		pageInfo.setResultList(list);
		pageInfo.setRows(total);
		return pageInfo;
	}

}
