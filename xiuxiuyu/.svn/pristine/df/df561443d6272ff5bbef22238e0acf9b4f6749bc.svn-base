<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
       
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>栏目管理首页</title>
</head>
<body>
<div><a href="javascript:void(0)" onclick="changeHref('/channel/toxz.do?id=${parentId }')">添加</a></div>
<table border="1" cellpadding="0" cellspacing="0">

<tr><td width="100">id</td>
<td width="300">名称</td>
<td width="300">URL</td>
<td width="300">父栏目</td>
<td width="100">操作</td>
</tr>
<c:forEach items="${list}" var="channel"><tr>
<td>${channel.id}</td>
<td><a href="#" onclick="changeHref('/channel/index.do?parent_id=${channel.id}')">${ channel.name}</a></td> 
<td>${channel.url}</td>
<td><c:choose>
<c:when test="${channel.parentId==0 }">顶级栏目</c:when>
<c:otherwise>${channel.parentChannel.name}</c:otherwise>

</c:choose></td>
<td><a href="javascript:void(0)" onclick="changeHref('/channel/tomodify.do?id=${channel.id}')">修改</a>
&nbsp;&nbsp;
<a href="javascript:void(0)" onclick="changeHref('/channel/delete.do?id=${channel.id}')">删除</a></td>
</tr></c:forEach>

</table>
</body>
</html>