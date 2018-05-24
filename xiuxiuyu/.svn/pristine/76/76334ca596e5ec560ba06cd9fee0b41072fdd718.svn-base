<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>内容管理首页</title>
</head>
<body>
        <div class="easyui-layout" data-options="fit:true">   
            <div data-options="region:'west',collapsed:false,title:'内容栏目'" style="width:220px">
                 <ul id="channel_tree" class="easyui-tree" data-options="url:'/channel/findChannelList.do',onClick:findContentList"></ul> 
                 <script type="text/javascript">
                   function findContentList(node){
                	   $('#content_list_panel').panel({href:'/content/toList.do?channelId='+node.id});
                   }
                 </script>
            </div>   
            <div id="content_list_panel" class="easyui-panel" data-options="region:'center',href:'/content/toList.do',">
              
            </div>   
        </div> 
</body>
</html>