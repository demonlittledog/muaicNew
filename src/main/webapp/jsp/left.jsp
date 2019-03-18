<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
	<script language="JavaScript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
</head>
<script type="text/javascript">
    $(function(){
        //导航切换
        $(".menuson li").click(function(){
            $(".menuson li.active").removeClass("active")
            $(this).addClass("active");
        });

        $('.title').click(function(){
            var $ul = $(this).next('ul');
            $('dd').find('ul').slideUp();
            if($ul.is(':visible')){
                $(this).next('ul').slideUp();
            }else{
                $(this).next('ul').slideDown();
            }
        });
    })
</script>
<body style="background:#f0f9fd;">
<jsp:include page="/jsp/teaManAccess.jsp"></jsp:include>



<div class="lefttop"><span></span>操作区</div>
<dl class="leftmenu">
	<dd>
		<div class="title">
			<span><img src="../images/leftico01.png" /></span>管理个人信息
		</div>
		<ul class="menuson">
			<li><cite></cite><a href="changePwd.jsp" target="rightFrame">修改密码</a><i></i></li>
			<li><cite></cite><a href="personalInfo.jsp" target="rightFrame">个人信息</a><i></i></li>

		</ul>
	</dd>
	<c:if test="${teaManInfo.roleId==1}">
	<dd>
		<div class="title">
			<span><img src="../images/leftico02.png" /></span>老师功能
		</div>
		<ul class="menuson">
			<li><cite></cite><a href="${pageContext.request.contextPath}/queryMyClass.do" target="rightFrame">签到</a><i></i></li>
			<li><cite></cite><a href="${pageContext.request.contextPath}/queryTeachClass.do" target="rightFrame">查看所教班级</a><i></i></li>
		</ul>
	</dd>
	</c:if>
	<c:if test="${teaManInfo.roleId==2}">
	<dd>
		<div class="title">
			<span><img src="../images/leftico04.png" /></span>学生管理
		</div>
		<ul class="menuson">
			<li><cite></cite><a href="${pageContext.request.contextPath }/queryStudentInfo.do" target="rightFrame">学生信息</a><i></i></li>
			<li><cite></cite><a href="studentFill.jsp" target="rightFrame">学生补签</a><i></i></li>
			<li><cite></cite><a href="${pageContext.request.contextPath}/queryCommonRecords.do" target="rightFrame">学生签到查询</a><i></i></li>
		</ul>
	</dd>
	<dd><div class="title"><span><img src="../images/leftico04.png" /></span>老师管理</div>
		<ul class="menuson">
			<li><cite></cite><a href="${pageContext.request.contextPath }/queryTeacherInfo.do" target="rightFrame">老师信息</a><i></i></li>
			<li><cite></cite><a href="${pageContext.request.contextPath}/queryTeaCommonRecords.do" target="rightFrame">老师签到查询</a><i></i></li>
		</ul>
	</dd>
	<dd><div class="title"><span><img src="../images/leftico03.png" /></span>班级管理</div>
		<ul class="menuson">
			<li><cite></cite><a href="${pageContext.request.contextPath}/queryCommonClassInfo.do" target="rightFrame">普通班级管理</a><i></i></li>
			<li><cite></cite><a href="${pageContext.request.contextPath}/queryMatchClassInfo.do" target="rightFrame">集训班级管理</a><i></i></li>
		</ul>
	</dd>
	<dd><div class="title"><span><img src="../images/leftico02.png" /></span>学费查询</div>
		<ul class="menuson">
			<li><cite></cite><a href="${pageContext.request.contextPath }/queryCommonTuition.do" target="rightFrame">普通学费查询</a><i></i></li>
			<li><cite></cite><a href="${pageContext.request.contextPath}/queryMatchTuition.do" target="rightFrame">集训学费查询</a><i></i></li>
		</ul>
	</dd>
	</c:if>
</dl>
</body>
</html>