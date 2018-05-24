<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>订单列表</title>
</head>
<body>
<span style="display: block;">

  订单号：<input id="orderId" name="orderId" value="${order.orderId}">
 用户名：<input id="username" name="username" value="${username}">
 <br>
 支付时间：<input id="startTime" name="startTime" value="${startTime}"> - <input id="endTime" name="endTime" value="${endTime}">
订单状态：<select id="state" name="state">
			<option value="">全部</option>
			<option value="0" ${order.state==0?'selected':''}>未处理</option>
			<option value="-1" ${order.state==-1?'selected':''}>作废</option>
			<option value="1" ${order.state==1?'selected':''}>成功</option>
		  </select>
		  <br>
秀秀育码：<input id="token" name="token" value="${order.token}">
  支付账号：<input id="payAccount" name="payAccount" value="${order.payAccount}">
  <input type="button" value="搜索" onclick="searchOrder(${page})">

<script type="text/javascript">
function searchOrder(page){
	var orderId=$('#orderId').val();
	var username=$('#username').val();
	var startTime=$('#startTime').val();
	var endTime=$('#endTime').val();
	var state=$('#state').val();
	var token=$('#token').val();
	var payAccount=$('#payAccount').val();
	var url = '/order/list.do?orderId='+orderId+
			'&username='+username+
			'&startTime='+startTime+
			'&endTime='+endTime+
			'&state='+state+
			'&token='+token+
			'&payAccount='+payAccount+
			'&page='+page;
	//url = encodeURI(url);
	alert(url)
	changeHref(url);
}
</script>
	  
</span>
<table cellspacing="0" cellpadding="15" style="border:1px solid #cacbcb;background-color: #f0f0f0;">
  <thead style="background-color: #cacbcb;">
	<tr style="font-size: 16px;">
	  <td align="center">id</td>
	  <td align="center">订单ID</td>
	  <td align="center">用户名</td>
	  <td align="center">支付类型</td>
	  <td align="center">支付备注</td>
	  <td align="center">订单时间</td>
	  <td align="center">订单状态</td>
	  <td align="center">秀秀育码</td>
	  <td align="center">支付账号</td>
	  <td align="center">操作</td>
	</tr>
  </thead>
  <tbody>
	<c:forEach items="${list}" var="order">
	<tr class="tb-item">
	  <td>${order.id}</td>
	  <td>${order.orderId}</td>
	  <td>${order.user.username}</td>
	  <td>
	  <c:choose>
	     <c:when test="${order.payType==1}">微信</c:when>
	     <c:otherwise>支付宝</c:otherwise>
	   </c:choose>  
	  </td>
	  <td>${order.mark}</td>
	  <td>${order.createTime}</td>
	  <td>
	   <c:choose>
	     <c:when test="${order.state==0}">未处理</c:when>
	     <c:when test="${order.state==1}">成功订单</c:when>
	     <c:otherwise>作废订单</c:otherwise>
	   </c:choose>
	  </td>
	  <td>${order.token}</td>
	  <td>${order.payAccount}</td>
	  <td>
	  <c:choose>
	     <c:when test="${order.state==0}">
	       <a onclick="changeHref('/order/handlePayOrder.do?state=-1&orderId=${order.orderId}&page=${page}')">作废订单</a>
	       &nbsp;&nbsp;
	       <a onclick="changeHref('/order/handlePayOrder.do?state=1&orderId=${order.orderId}&page=${page}')">置为成功订单</a>
	     </c:when>
	     <c:otherwise>不可操作</c:otherwise>
	   </c:choose>
	  </td>
	</tr>
	</c:forEach>
  </tbody>
</table>

</body>
</html>