<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改内容</title>
   
</head>

<body>


   <div style="width:820px;margin-left:20px;margin-top:20px;">
		<form action="/content/edit.do" id="ff" method="post">
		    
		    <div style="margin-bottom:20px">
				<select id="channel-id" class="easyui-combotree" name="channelId" label="栏目" style="width:300px" data-options="url:'/channel/findChannelList.do?selectId=1',required:true,onLoadSuccess:setSelectValue"></select>
			    <script type="text/javascript">
			       function setSelectValue(node,data){
			    	   var value=${content.channelId};
			    	   $('#channel-id').combotree('setValue',value);
			       }
			    </script>
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="title" value="${content.title}" style="width:500px" data-options="label:'内容标题',required:true,onChange:getKeyword">
			</div>
			<script type="text/javascript">
			    //拆分标题关键词
			    function getKeyword(title){
			    	$.ajax({
			    		method:'post',
			    		url:'/content/getKeyword.do',
			    		data:{'title':title},
			    		type:'json',
			    		error:function(e){e.message},
			    		success:function(data){
			    			var jsonData = eval('('+data+')');
			    			$('#keyword').textbox('setValue',jsonData.tags);
			    		}
			    	});
			    }
			</script>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="author" value="${content.author}" style="width:200px" data-options="label:'作者'">
			</div>
			<div style="margin-bottom:20px">
				<input id="keyword" class="easyui-textbox" name="keyword" value="${content.keyword}" style="width:500px" data-options="label:'关键词'">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="description" value="${content.description}" style="width:800px;height:60px" data-options="label:'内容描述',multiline:true"">
			</div>
			<div style="margin-bottom:20px">
				<select class="easyui-combobox" name="type" label="内容类型" style="width:200px">
				  <option value="1" ${content.type==1?'selected="selected"':''}>普通</option>
				  <option value="2" ${content.type==2?'selected="selected"':''}>图文</option>
				  <option value="3" ${content.type==3?'selected="selected"':''}>视频</option>
				</select>
			</div>	
			<div style="margin-bottom:20px">
			<input id="imgPath" class="easyui-textbox" label="类型图" name="imgPath" value="${content.imgPath }" style="width:300px"/>
			   <a style="display:block;width:67px;margin-top:20px;margin-left:85px;padding-left:1px;cursor: pointer;border:2px solid #95B8E7;border-radius:5px;">
			     <span id="spanButtonPlaceHolder"></span>
			   </a>  
			     <span id="fsUploadProgress"></span>
			     <span id="btnCancel"></span>
			</div>	
			
			<div style="margin-bottom:20px">
				<select class="easyui-combobox" name="videoType" label="视频类型" style="width:200px">
				  <option value="">请选择</option>
				  <option value="1" ${content.videoType==1?'selected="selected"':''}>mp4</option>
				  <option value="2" ${content.videoType==2?'selected="selected"':''}>flv</option>
				  <option value="3" ${content.videoType==3?'selected="selected"':''}>rmvb</option>
				</select>
			</div>
			<div style="margin-bottom:20px">
		    <input id="videoPath" class="easyui-textbox" label="视频" name="filePath" value="${content.filePath}" style="width:300px"/>
			<a style="display:block;width:57px;margin-top:20px;margin-left:85px;padding-left:1px;cursor: pointer;border:2px solid #95B8E7;border-radius:5px;">
			    <span id="spanButtonPlaceHolder2"></span>
			</a>
			    <span id="fsUploadProgress2"></span>
			    <span id="btnCancel2"></span>
			</div>					
			<div style="margin-bottom:20px">
				内容
			</div>
			<div style="margin-bottom:20px;padding-left:85px;">
				 <textarea id="editor_id" name="txt" style="width:710px;height:300px;">
				    ${content.txt}
				</textarea>
				<script>
                         $(document).ready(function(){
                        	 var editor=KindEditor.create('#editor_id',{
                        		 afterBlur: function(){this.sync();}
                        	 });
                         });
				
				</script>				
			</div>
			<input type="hidden" name="id" value="${content.id}"/>
		</form>
		<div style="text-align:center;padding:5px 0">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">清空</a>
		</div>
	</div>	
	<script>
		function submitForm(){
			//$('#ff').submit();
			
			$('#ff').form('submit',{
				success:function(resultStr){
					var data=eval('('+resultStr+')');
					if(data.state==1){
						var node=$('#channel_tree').tree('getSelected');
						$('#content_list_panel').panel({href:'/content/toList.do?channelId='+node.id});
					}else{
						alert(data.error)
					}
				}
			});
			
		}
		function clearForm(){
			$('#ff').form('clear');
		}
	</script>
	<script type="text/javascript">
	var swfu;
	var swfu2;
	
	$(document).ready(function() {
		var uploadPicsUrl = "/upload/uploadImg.do";
		var uploadAttachsUrl = "/upload/uploadFile.do";
		//alert(uploadPicsUrl)
		swfu=new SWFUpload({
			upload_url : uploadPicsUrl,
			flash_url : "/swfupload/swfupload.swf",
			file_queue_limit : 0,
			custom_settings : {
				progressTarget : "fsUploadProgress",
				cancelButtonId : "btnCancel"
			},
			debug: false,
			button_placeholder_id : "spanButtonPlaceHolder",
			button_text: "类型图上传",
			button_width: 65,
			button_height: 20,
			button_text_top_padding: 2,
			button_text_left_padding: 0,
			button_text_style: '.btnText{color:#666666;}',
			
			file_queued_handler : fileQueued,
			file_queue_error_handler : fileQueueError,
			file_dialog_complete_handler : fileDialogComplete,
			upload_progress_handler : uploadProgress,
			upload_error_handler : uploadError,
			upload_success_handler : uploadPicsSuccess,
			upload_complete_handler : uploadComplete,
			queue_complete_handler : queueComplete
		});
		swfu2=new SWFUpload({
			upload_url : uploadAttachsUrl,
			flash_url : "/swfupload/swfupload.swf",
			file_queue_limit : 0,
			custom_settings : {
				progressTarget : "fsUploadProgress2",
				cancelButtonId : "btnCancel2"
			},
			debug: false,
			button_placeholder_id : "spanButtonPlaceHolder2",
			button_text: "视频上传",
			button_width: 55,
			button_height: 20,
			button_text_top_padding: 2,
			button_text_left_padding: 0,
			button_text_style: '.btnText{color:#666666;}',
			
			file_queued_handler : fileQueued,
			file_queue_error_handler : fileQueueError,
			file_dialog_complete_handler : fileDialogComplete,
			upload_progress_handler : uploadProgress,
			upload_error_handler : uploadError,
			upload_success_handler : uploadFileSuccess,
			upload_complete_handler : uploadComplete,
			queue_complete_handler : queueComplete
		});		
	});
	
	function uploadPicsSuccess(file,dataStr) {
		var data=eval('('+dataStr+')');
		if(data.state==1){
			$('#imgPath').textbox('setValue',data.path);
		}else{
			alert(data.error);
		}
	}	
	function uploadFileSuccess(file,dataStr) {
		var data=eval('('+dataStr+')')
		if(data.state==1){
			$('#videoPath').textbox('setValue',data.path);
		}else{
			alert(data.error);
		}
	}
	
	</script>
</body>
</html>