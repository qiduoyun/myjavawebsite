<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
       
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户管理首页</title>
</head>
<body>
<div>
<h3>秀秀育用户管理</h3>
<form action="searchuser.do" method="post">
<span>
<label>
用户属性归类:
<select>
<option value="10" selected="selected">被禁用的</option>
<option value="25">普通用户</option>
<option value="50">会员</option>
<option value="100">100</option>
</select>
</label>
<font>&nbsp;&nbsp;输入查询的用户名:</font><input type="text" name="username" />
<font>&nbsp;&nbsp;输入查询的用ID:</font><input type="text" name="userid"/>
<font>&nbsp;&nbsp;输入注册时间:</font><input type="text" name="time"/>
<input type="submit" value="查询">
</span>
<table border="1" bordercolor="blue" cellspacing="5" cellpadding="20">

<thead>

<tr  >

<th width="50" rowspan="1" colspan="1" >用户ID</th>
<th rowspan="1" colspan="1" >用户名</th>
<th rowspan="1" colspan="1" >用户类型</th>
<th rowspan="1" colspan="1" >联系方式</th>
<th rowspan="1" colspan="1" >邮箱</th>
<th rowspan="1" colspan="1" >注册时间</th>
<th rowspan="1" colspan="1" >登录时间</th>
<th rowspan="1" colspan="1" >会员剩余天数</th>
<th   rowspan="1" colspan="1" >状态</th>
<th rowspan="1" colspan="1" >操作</th>
</tr>
<c:forEach items="${list}" var="user">
<tr  >
<th width="50" rowspan="1" colspan="1" >${user.id}</th>
<th rowspan="1" colspan="1" >${user.username}</th>
<th rowspan="1" colspan="1" >${user.roleId}</th>
<th rowspan="1" colspan="1" >${user.telphone}</th>
<th rowspan="1" colspan="1" >${user.email}</th>
<th rowspan="1" colspan="1" >${user.registerTime}</th>
<th rowspan="1" colspan="1" >${user.loginTime}</th>
<th rowspan="1" colspan="1" >${user.days}</th>
<th   rowspan="1" colspan="1" >${user.state}</th>

<th rowspan="1" colspan="1" ><a href="#">开通会员&nbsp;&nbsp;</a><a href="#">禁用&nbsp;&nbsp;</a><a href="#">启用</a></th>
</tr>
</c:forEach>

</thead>

</table>

</form>

</div>
</body>
</html>