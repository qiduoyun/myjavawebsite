<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
<script src="/js/jquery-3.1.0-min.js"></script>
<script src="/layer/layer.js"></script>
</head>
<style>
.link-a{
text-decoration: none;
color:#333;
}
.link-a:HOVER {
	cursor: pointer;
	color:#996600;
}
.link-bt{
background-color: #528ab3;
}
.link-bt:hover{
 background-color: #028ab3;
 cursor: pointer;
}
</style>
<body>
<div id="login-error" style="width:300px;margin: 0 auto;margin-top:32px;color: #ff0000;font-size: 12px;">
</div>
<div style="width:300px;height:30px;margin: 0 auto;margin-top:10px;border:1px solid #cacbcb;">
  <span style="display: inline-block;float:left;">
    <img height="28" alt="" src="/images/password.gif">
  </span>
  <span style="display: inline-block;float:left;">
    <input id="username" style="border:none;width:260px;padding-left:5px;height:26px;font-size: 16px;" type="text">
  </span>
</div>
<div style="width:300px;height:30px;margin: 15px auto;border:1px solid #cacbcb;">
  <span style="display: inline-block;float:left;">
    <img height="28" src="/images/user.gif"/>
  </span>
  <span style="display: inline-block;float:left;">
     <input id="password" style="border:none;width:260px;padding-left:5px;height:26px;font-size: 16px;" type="password">
  </span>
</div>
<div style="width:300px;margin: 0 auto;">
  <span style="display: inline-block;float: left;font-size: 14px;">
     <input type="checkbox" style="margin-left: 0;">记住密码</span>
  <span style="display: inline-block;float: right;">
    <a class="link-a" href="javascript:void(layer.msg('请联系客服'))" style="font-size: 14px;">忘记密码</a>
    <a style="display: inline-block;width: 20px;"></a>
    <a class="link-a" target="_blank" href="/toRegister.jhtml" style="font-size: 14px;">注册账号</a>
  </span>
  <div style="clear: both;"></div>
</div>
<a class="link-bt" style="display: block;width:300px;margin: 10px auto;padding-top:5px;padding-bottom:5px;text-align: center;color:#fff;font-size: 18px;" onclick="loginIn()">
登录</a>

<script type="text/javascript">
   function loginIn(){
	   var username = $('#username').val();
	   if(username==''){
		   layer.alert('用户名不能为空');
		   return;
	   }
	   var password = $('#password').val();
	   if(password==''){
		   layer.alert('密码不能为空');
		   return;
	   }
	   var data = {'username':username,'password':password};
	   $.ajax({
		   method:'POST',
		   url:'/loginAjax.jhtml',
		   data:data,
		   type:'json',
		   error:function(e){layer.msg(e.message);},
	       success:function(resultData){
	    	   //alert(resultData);
	    	   var data = eval('('+resultData+')');
	    	   if(data.state==1){
	    		   window.parent.location = window.parent.location;
	    	   }else{
	    		   $('#login-error').html('用户名或密码错误，请重新输入');
	    	   }
	       }
	   });
   }
</script>
</body>
</html>