<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Basic Form - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="../../themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../../themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../demo.css">
	<script type="text/javascript" src="../../jquery.min.js"></script>
	<script type="text/javascript" src="../../jquery.easyui.min.js"></script>
</head>
<body>
	<h2>修改</h2>

	<div style="margin:20px 0;"></div>
	<div class="easyui-panel" title="修改栏目" style="width:100%;max-width:400px;padding:30px 60px;">
		<form action="channel/modify.do" id="ff" method="post">
			<div style="margin-bottom:20px">
			<input type="hidden" name="id" value="${id}">
				<input class="easyui-textbox" name="name" value="${c.name}"  style="width:100%" data-options="label:'栏目名称:',required:true">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="url" value="${c.url }" style="width:100%" data-options="label:'栏目路径:',required:true">
			</div>
				<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="channelTemplate" value="${c.channelTemplate}"  style="width:100%" data-options="label:'栏目模板:',required:true">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="contentTemplate" value="${c.contentTemplate}" style="width:100%" data-options="label:'内容模板:',required:true">
			</div>
				<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="imgPath" value="${c.imgPath}" style="width:100%" data-options="label:'栏目图片路径:'">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="description" value="${c.description}"  style="width:100%" data-options="label:'栏目说明:'">
			</div>
				<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="keyword" value="${c.keyword}"  style="width:100%" data-options="label:'关键词:'">
			</div>
			
		</form>
		<div style="text-align:center;padding:5px 0">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">提交</a>
			
		</div>
	</div>
	<script>
		function submitForm(){
			$('#ff').form('submit', {success:function(data){    
		        if (data==1) changeHref('/channel/index.do?id=${id}')   
		    } }  );
		}
		function clearForm(){
			$('#ff').form('clear');
		}
	</script>
</body>
</html>