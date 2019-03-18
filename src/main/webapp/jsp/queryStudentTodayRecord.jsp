<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
</head>


<body>
<jsp:include page="/jsp/teaManAccess.jsp"></jsp:include>
<div class="place">
	<span>学生信息</span>
</div>

<div class="formbody">
	<div id="usual1" class="usual">
		<div class="itab">
			<ul>
				<li><a href="#tab1" class="selected">${param.commonClassName}</a></li>
			</ul>
		</div>

		<div id="tab1" class="tabson">
			<form action="${pageContext.request.contextPath }/queryStudentTodayRecords.do" method="post" name="form1" id="form1">
				<ul class="seachform">
					<input type="hidden" name="commonClassId" value="${param.commonClassId}">
					<input type="hidden" name="commonClassName" value="${param.commonClassName}">
					<li><label>&nbsp;</label><input type="submit" class="scbtn" value="刷新"/>&nbsp;<input type="button" name="button" value="返回" class="btn btn-primary" onclick="javascript:location.href='${pageContext.request.contextPath }/queryTeachClass.do'"/></li>
				</ul>
			</form>

			<table class="tablelist">
				<thead>
				<tr align="center">
					<td colspan="6">已签到</td>
				</tr>
				<tr>
					<th>学生id</th>
					<th>家长手机号</th>
					<th>学生姓名</th>
					<th>家长姓名</th>
					<th>性别</th>
					<th>签到时间</th>
				</tr>
				</thead>
				<tbody>
				<c:if test="${fn:length(yList)>0}">
                    <c:forEach items="${yList}" var="ystudentInfo">
					<tr>
						<td>${ystudentInfo.studentId}</td>
						<td>${ystudentInfo.parentsPhone}</td>
						<td>${ystudentInfo.studentName}</td>
						<td>${ystudentInfo.parentsName}</td>
						<td>
							<c:choose>
								<c:when test="${ystudentInfo.studentSex==1}">男</c:when>
								<c:otherwise>女</c:otherwise>
							</c:choose>
						</td>
						<td>
							<fmt:formatDate value="${ystudentInfo.commonCardTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
					</tr>
					</c:forEach>
            </c:if>
            <c:if test="${fn:length(yList)==0}">
				<tr align="center"><td colspan="6">没有数据</td></tr>
			</c:if>
				</tbody>
			</table>

			<table class="tablelist">
				<thead>
				<tr align="center">
					<td colspan="6">未签到</td>
				</tr>
				<tr>
					<th>学生id</th>
					<th>家长手机号</th>
					<th>学生姓名</th>
					<th>家长姓名</th>
					<th>性别</th>
					<th>签到时间</th>
				</tr>
				</thead>
				<tbody>
				<c:if test="${fn:length(nList)>0}">
					<c:forEach items="${nList}" var="nstudentInfo">
						<tr>
							<td>${nstudentInfo.studentId}</td>
							<td>${nstudentInfo.parentsPhone}</td>
							<td>${nstudentInfo.studentName}</td>
							<td>${nstudentInfo.parentsName}</td>
							<td>
								<c:choose>
									<c:when test="${nstudentInfo.studentSex==1}">男</c:when>
									<c:otherwise>女</c:otherwise>
								</c:choose>
							</td>
							<td>
								未签到
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${fn:length(nList)==0}">
					<tr align="center"><td colspan="6">没有数据</td></tr>
				</c:if>
				</tbody>
			</table>
		</div>
	</div>


	<script type="text/javascript">
        $('.tablelist tbody tr:odd').addClass('odd');
	</script>
</div>
</body>
<c:if test="${not empty error}">
	<script>
        alert("${error}");
	</script>
</c:if>
</html>