<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" />

</head>
<body style="background:url(../images/topbg.gif) repeat-x;">
<jsp:include page="/jsp/teaManAccess.jsp"></jsp:include>


<div class="contop">
 <div class="logo">小白鸽音乐艺术工作室</div>
 <ul class="logo-right ">
  <li><a href="${pageContext.request.contextPath }/main.do" onclick="if(confirm('确定要退出系统吗？')==false){return false}" target="_parent">退出</a></li>
  <li><span>${sessionScope.roleName}</span></li>
  <li><span>用户类型:</span></li>
  <li><span>${teaManInfo.teaManName}</span></li>
  <li><span>用户名:</span></li>
 </ul>
</div>
</body>
</html>