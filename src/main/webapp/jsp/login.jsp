<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
	<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
	<style>
		html { height: 100%; }
		body { height: 100%; background: #fff url(${pageContext.request.contextPath }/images/backgroud.png) 50% 50% no-repeat; background-size: cover; overflow: hidden}
	</style>
<script type="text/javascript">
	function validateForm(){
		var teaManPhone = document.getElementById("teaManPhone").value;
		var password = document.getElementById("password").value;
		if(teaManPhone.trim()==null||teaManPhone.trim().length==0){
			alert("用户名不能为空！");
			return false;
		}else if(password.trim()==null||password.trim()==0){
			alert("密码不能为空！");
			return false;
		}else if(password.trim().length<6){
			alert("密码长度至少6位！");
			return false;
		}else{
			return true;
		}
	}
</script>
</head>
<body>
<div id="container" class="container">
	<form action="${pageContext.request.contextPath }/login.do" method="post" name="form1" id="form1" onsubmit="return validateForm()">
		<div class="login">小白鸽音乐艺术工作室</div>
		<div class="username-text">账号:</div>
		<div class="password-text">密码:</div>
		<div class="username-field">
			<input type="text" name="teaManPhone" id="teaManPhone" />
		</div>
		<div class="password-field">
			<input type="password" name="password" id="password"/>
		</div>
		<input type="checkbox" name="remember-me" id="remember-me" /><label for="remember-me">记住密码</label>
		<div class="forgot-usr-pwd"><a href="#">忘记密码</a>?</div>
		<input type="submit" name="submit" value="GO" class="submit"/>
	</form>
</div>
</body>
<c:if test="${not empty error}">
  	<script>
  	alert("${error}");
  	</script>
   </c:if>
</html>