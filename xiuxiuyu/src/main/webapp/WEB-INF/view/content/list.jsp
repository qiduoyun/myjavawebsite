<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>内容列表</title>
</head>
<body>
				<table class="easyui-datagrid" style="width:820px;overflow: hidden;"   
				        data-options="url:'/content/list.do?id=${channel.id}',fitColumns:true,singleSelect:true,
				        pagination:true,
				        	toolbar: [{
										iconCls: 'icon-add',
										handler: function(){toAdd()}
									},'-',{
										iconCls: 'icon-help',
										handler: function(){alert('帮助按钮')}
									}]">   
				    <thead>   
				        <tr>
				            <th width="auto" data-options="field:'channelName'">所属栏目</th>      
				            <th width="120" data-options="field:'title'">内容标题</th>   
				            <th width="auto" data-options="field:'type',formatter:contentType">内容类型</th> 
				            <th width="auto" data-options="field:'id',formatter:myOperator">操作</th>   
				        </tr>   
				    </thead>   
				</table>
				<script type="text/javascript">
				     //添加内容
				     function toAdd(){
				    	 $('#content_list_panel').panel({href:'/content/toAdd.do'});
				     }
				     //修改内容
				     function toEdit(id){
				    	 $('#content_list_panel').panel({href:'/content/toEdit.do?id='+id});
				     }
				     //删除内容
				     function deleteContent(id){
				    	 $('#content_list_panel').panel({href:'/content/delete.do?channelId=${channel.id}&id='+id});
				     }
				     //内容类型
				     function contentType(value,node){
				    	 switch(value){
				    	 case 1:
				    		 return '普通';
				    	 case 2:
				    		 return '图文';
				    	 case 3:
				    		 return '视频';
				    	 default:
				    		 return '?';
				    	 }
				     }
				     //操作
				     function myOperator(value,node){
				    	 var s='<a href="javascript:void(0)" onClick="toEdit('+value+')">修改</a>&nbsp;&nbsp;<a href="javascript:void(0)" onClick="deleteContent('+value+')">删除</a>';
				    	 return s;
				     }
				</script>  
</body>
</html>