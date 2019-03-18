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
</head>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">


</script>




<body>
<jsp:include page="/jsp/teaManAccess.jsp"></jsp:include>
<div class="place">
	<span>查看所教班级</span>
</div>

<div class="formbody">
	<div id="usual1" class="usual">
		<div class="itab">
			<ul>
				<li><a href="#tab1" class="selected">班级信息</a></li>
			</ul>
		</div>

		<div id="tab1" class="tabson">
			<form action="${pageContext.request.contextPath}/queryTeachClass.do" method="post" name="form4" id="form4">
				<ul class="seachform">
					<li><label>班级名称</label><input type="text" name="commonClassName" size="6" value="${param.commonClassName}" class="scinput" /></li>
					<li><label>&nbsp;</label><input type="submit" class="scbtn" value="查询"/></li>
				</ul>
			</form>
			<table class="tablelist">
				<thead>
				<tr>
					<th>普通班级id</th>
					<th>普通班级名称</th>
					<th>普通班级类型</th>
					<th>课时费</th>
					<th>总课程数</th>
					<th>开班日期</th>
					<th>周</th>
					<th>上课时间</th>
					<th>上课时长(分钟)</th>
					<th>学生人数</th>
					<th>操作</th>
				</tr>
				</thead>
				<c:if test="${fn:length(list)>0}">
				<c:forEach items="${list}" var="commonClassInfo">
				<tbody>
				<tr>
					<td>${commonClassInfo.commonClassId}</td>
					<td>${commonClassInfo.commonClassName}</td>
					<td>${commonClassInfo.classTypeName}</td>
					<td>${commonClassInfo.commonCost}</td>
					<td>${commonClassInfo.commonSum}</td>
					<td>
						<fmt:formatDate value="${commonClassInfo.commonClassDate}" pattern="yyyy-MM-dd"/>
					</td>
					<td>${commonClassInfo.commonWeek}</td>
					<td>
						<fmt:formatDate value="${commonClassInfo.commonTime}" pattern="HH:mm:ss"/>
					</td>
					<td>${commonClassInfo.commonSpan}</td>
					<td>${commonClassInfo.studentNum}</td>
					<td>
						<a href="${pageContext.request.contextPath }/queryStudentTodayRecords.do?commonClassId=${commonClassInfo.commonClassId}&commonClassName=${commonClassInfo.commonClassName}">查看学生打卡</a>
					</td>
				</tr>
				</c:forEach>
				</c:if>
				<c:if test="${fn:length(list)==0}">
					<tr align="center"><td colspan="13">没有数据</td></tr>
				</c:if>
				</tbody>
			</table>
		</div>
	</div>

	<div class="pagin">
		<div class="message">共<i class="blue">${totalPageCount}</i>页，当前显示第&nbsp;<i class="blue">${currentPageNo}&nbsp;</i>页</div>
		<form action="${pageContext.request.contextPath }/queryTeachClass.do" method="post">
			<ul class="paginList paginList2">
				<li class="paginItem pli">每页显示</li>
				<li class="paginItem pli">
					<select name="pageSize" id="pageSize">
						<c:forEach begin="5" end="10" step="1" var="i">
							<option <c:if test="${param.pageSize==i}">selected</c:if> value="${i}">${i}</option>
						</c:forEach>
					</select>
				</li>
				<li class="paginItem pli">跳转到第</li>
				<li class="paginItem pli"><input class="pageNo" type="number" size="2" max="${totalPageCount}" min="1" name="currentPageNo" value="${currentPageNo}"/>页</li>
				<li class="paginItem pli"><input type="hidden" name="commonClassName" value="${param.commonClassName}"></li>
				<li class="paginItem pli"><input type="submit" class="pinput" value="GO"/></li>
			</ul>
		</form>
		<form action="quertyteacher.do" method="post">
			<ul class="paginList">
				<c:if test="${currentPageNo>1}">
					<li class="paginItem"><a href="${pageContext.request.contextPath }/queryTeachClass.do?currentPageNo=1&pageSize=${param.pageSize}&commonClassName=${param.commonClassName}">首页</a></li>
					<li class="paginItem"><a href="${pageContext.request.contextPath }/queryTeachClass.do?currentPageNo=${currentPageNo-1}&pageSize=${param.pageSize}&commonClassName=${param.commonClassName}" class="pia">上一页</a></li>
				</c:if>
				<c:if test="${currentPageNo<=1}">
					<li class="paginItem"><a href="javascript:;">首页</a></li>
					<li class="paginItem"><a href="javascript:;" class="pia">上一页</a></li>
				</c:if>
				<c:if test="${currentPageNo<totalPageCount}">
					<li class="paginItem"><a href="${pageContext.request.contextPath }/queryTeachClass.do?currentPageNo=${currentPageNo+1}&pageSize=${param.pageSize}&commonClassName=${param.commonClassName}" class="pia">下一页</a></li>
					<li class="paginItem"><a href="${pageContext.request.contextPath }/queryTeachClass.do?currentPageNo=${totalPageCount}&pageSize=${param.pageSize}&commonClassName=${param.commonClassName}">尾页</a></li>
				</c:if>
				<c:if test="${currentPageNo>=totalPageCount}">
					<li class="paginItem"><a href="javascript:;" class="pia">下一页</a></li>
					<li class="paginItem"><a href="javascript:;">尾页</a></li>
				</c:if>
			</ul>
		</form>
	</div>
	<script type="text/javascript">
        $('.tablelist tbody tr:odd').addClass('odd');
	</script>
</div>
<c:if test="${not empty error}">
	<script>
        alert("${error}");
	</script>
</c:if>
</body>
</html>