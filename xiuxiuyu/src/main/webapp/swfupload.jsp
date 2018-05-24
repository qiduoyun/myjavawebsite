<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新增内容</title>
   	<script src="/swfupload/swfupload.js" type="text/javascript"></script>
	<script src="/swfupload/swfupload.queue.js" type="text/javascript"></script>
	<script src="/swfupload/fileprogress.js" type="text/javascript"></script>
	<script src="/swfupload/handlers.js" type="text/javascript"></script>
	<link href="/swfupload/process.css" rel="stylesheet" type="text/css"/>	
</head>

<body>


   <div style="width:820px;margin-left:20px;margin-top:20px;">
		<form action="/content/add.do" id="ff" method="post">
		    
		    
			<div style="margin-bottom:20px">
			类型图：<input id="imgPath" name="imgPath"/><br/>
			     <span id="spanButtonPlaceHolder"></span>
			     <span id="fsUploadProgress"></span>
			     <span id="btnCancel"></span>
			</div>	
			
			<input type="submit" value="提交"/>
		</form>

	</div>	
	
	<script type="text/javascript">
	var swfu;
	window.onload=function() {
		var uploadPicsUrl = "/upload/uploadImg.do";
		var uploadAttachsUrl = "/upload/uploadFile.do";
		alert(uploadPicsUrl)
		swfu=new SWFUpload({
			upload_url : uploadPicsUrl,
			flash_url : "/swfupload/swfupload.swf",
//			file_size_limit : "20 MB",
//			file_types : "*.jpg;*.gif;*.png;*.bmp",
//			file_types_description : "<@s.m 'content.picture'/>",
			file_queue_limit : 0,
			custom_settings : {
				progressTarget : "fsUploadProgress",
				cancelButtonId : "btnCancel"
			},
			debug: false,
			
//			button_image_url : "${base}/res/common/img/theme/menu_search.jpg",
			button_placeholder_id : "spanButtonPlaceHolder",
			button_text: "button_text",
			button_width: 52,
			button_height: 19,
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
	};
	function uploadPicsSuccess(file,data) {
		alert(data)
		var jsonData=eval("("+data+")");//转换为json对象 
		if(jsonData.error!=null){
			alert(data.state)
		}else{
			alert(data.path)
		}
	}	
	</script>

</body>
</html>