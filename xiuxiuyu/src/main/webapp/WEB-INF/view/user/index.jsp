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

<div id="toolbar" >
用户名:<input id="username" type="text"  name=""/>
用户电话号码:<input id="telphone" type="text"  name=""/>
<input type="button" value="搜索" onclick="serchuser()"/>
</div>

<table id="userdg" class="easyui-datagrid" style="width:100%;height:500px"   
        data-options="url:'/user/list.do',fitColumns:true,singleSelect:true,pagination:true,toolbar:'#toolbar'">   
    <thead>   
        <tr>   
              
            <th data-options="field:'username'">用户名</th>   
            <th data-options="field:'day',align:'right'">会员剩余天数</th>
            <th data-options="field:'email'">邮箱</th> 
            <th data-options="field:'logintime'">登录时间</th> 
            <th data-options="field:'RegisterTime'">注册时间</th> 
            <th data-options="field:'rolename'">角色名称</th> 
            <th data-options="field:'telphone'">电话</th> 
            <th data-options="field:'state'">状态</th> 
            <th data-options="field:'updatatime'">更新时间</th> 
            <th data-options="field:'id',formatter:myformatter">操作</th>
        </tr>   
    </thead>   
</table> 
<script type="text/javascript">
function myformatter(value,row,index){
	var state=row.state;
	if(state=="-1"){
		return '<a onclick="openuser('+row.id+')">启用</a>';
	}
	else {
		return '<a onclick="closeuser('+row.id+')">禁用</a><a onclick="openvip('+row.id+')">开通会员</a>';
	}
}
function openuser(userid){
	
	$.ajax({
		method:"post",
		url:"/user/openuser.do",
		data:{"userid":userid},
		success:function(result){
			if(result=="1"){
				alert("启用成功");
				$('#userdg').datagrid('reload');
			}
			else{
				alert (result)
			}
		}
		
	}
			
	)
}
function closeuser(userid){
	
	$.ajax({
		method:"post",
		url:"/user/closeuser.do",
		data:{"userid":userid},
		success:function(result){
			if(result=="1"){
				alert("禁用成功");
				$('#userdg').datagrid('reload');
			}
			else{
				alert (result)
			}
		}
		
	}
			
	)
}
function serchuser(){
	var username=$('#username').val();
	var telphone=$('#telphone').val();
	var data={
			"username":username,"telphone":telphone
			
			}
	$('#userdg').datagrid('load',data);
	
}
function  openvip(userid){
	$('#userid').val(userid)
	$('#dd').dialog('open')
}
function confirmvip(){
	var userid=$('#userid').val();
	var days=$('#vipdays').val();
	if(userid==""||days==""){
		alert("天数不能为空");
		return "";
	}
	$.ajax(
	{   method:"post",
		url:"/user/openvip.do",
		data:{"userid":userid,"days":days},
		success:function(result){
			if (result="1"){
				alert("开通会员成功")
				$('#dd').dialog('close')
				$('#userdg').datagrid('reload');
			}
		}
		
	}		
	)
}
</script>
<input type="hidden" id="userid" value="">
<div id="dd" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"   
    data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">   

开通会员天数：<input type="text" id="vipdays">
<input type="button" value="确认开通" onclick="confirmvip()">
</div>
</body>
</html>