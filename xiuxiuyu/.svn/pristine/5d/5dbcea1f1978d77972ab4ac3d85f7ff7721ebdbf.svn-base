<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>订单管理首页</title>
</head>
<body>

<div id="dg-searcher">
  订单号：<input id="orderId" name="orderId"">
 用户名：<input id="username" name="username"">
 秀秀育码：<input id="token" name="token"">
 订单状态：<select id="state" name="state">
			<option value="">全部</option>
			<option value="0">未处理</option>
			<option value="-1">作废</option>
			<option value="1">成功</option>
		  </select>
 <br>
 支付时间：<input id="startTime" class="easyui-datebox" data-options="formatter:myformatter" style="width:190px;" name="startTime""> 
     - <input id="endTime" class="easyui-datebox" data-options="formatter:myformatter" style="width:190px;" name="endTime"">
  支付账号：<input id="payAccount" name="payAccount"">
  <input type="button" value="搜索" onclick="searchOrder()">
</div>

				<table id="order-dg" class="easyui-datagrid" style="width:820px;overflow: hidden;"   
				        data-options="url:'/order/list.do',fitColumns:true,singleSelect:true,
				        pagination:true,
				        	toolbar:'#dg-searcher'">   
				    <thead>   
				        <tr>
				            <th  data-options="field:'id'">ID</th>      
				            <th  data-options="field:'orderId'">订单号</th>   
				            <th  data-options="field:'username'">用户名</th> 
				            <th  data-options="field:'payType'">交易类型</th> 
				            <th  data-options="field:'mark'">备注</th> 
				            <th  data-options="field:'createTime'">创建时间</th> 
				            <th  data-options="field:'stateInfo'">状态</th> 
				            <th  data-options="field:'token'">秀秀育码</th> 
				            <th  data-options="field:'payAccount'">付款账号/昵称</th> 
				            <th  data-options="field:'state',formatter:myoperate">操作</th>  
				        </tr>   
				    </thead>   
				</table>

<script type="text/javascript">
//格式化时间
  function myformatter(date){
		var y = date.getFullYear();
		var m = date.getMonth()+1;
		var d = date.getDate();
		return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
  }
  //搜索
  function searchOrder(){
	    var data = getParams();
        $('#order-dg').datagrid('load',data);
  }
  //获取参数信息
  function getParams(){
		var orderId=$('#orderId').val();
		var username=$('#username').val();
		var startTime=$('#startTime').datebox('getValue');
		var endTime=$('#endTime').datebox('getValue');
		var state=$('#state').val();
		var token=$('#token').val();
		var payAccount=$('#payAccount').val();
		var data = {'orderId':orderId,'username':username,'startTime':startTime,'endTime':endTime,'state':state,'token':token,'payAccount':payAccount};
		return data;
  }
  //格式化操作
  function myoperate(value,row,index){
	  if(value=='-1'||value=='1'){
		  return '不可操作';
	  }else{
	  var result = '<a onclick="handlePayOrder(-1,\''+row.orderId+'\')">作废订单</a>'+
	       '&nbsp;&nbsp;'+
	       '<a onclick="handlePayOrder(1,\''+row.orderId+'\')">置为成功订单</a>';
		 return result;
	  }
  }
  //处理订单（作废、成功）
  function handlePayOrder(state,orderId){
	  var data={'state':state,'orderId':orderId};
	  $.ajax({
		  method:'POST',
		  url:'/order/handlePayOrder.do',
		  data:data,
		  success:function(result){
			  if(result=='1'){
				   var data = getParams();
			       $('#order-dg').datagrid('reload',data);
			  }else{
				  alert(result);
			  }
		  }
	  });
  }
</script>
</body>
</html>