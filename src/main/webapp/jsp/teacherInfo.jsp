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
<script type="text/javascript">
  	function addTeacher(){
  		var form5 = document.getElementById("form5");
  		form5.setAttribute("action", "jsp/addTeacherInfo.jsp");
  		form5.submit();
  	}
</script>
<body>
<jsp:include page="/jsp/teaManAccess.jsp"></jsp:include>
<div class="place">
	<span>老师信息</span>
</div>

<div class="formbody">
	<div id="usual1" class="usual">
		<div class="itab">
			<ul>
				<li><a href="#tab1" class="selected">信息查询</a></li>
			</ul>
		</div>

		<div id="tab1" class="tabson">
			<form action="${pageContext.request.contextPath }/queryTeacherInfo.do" method="post" name="form5" id="form5">
				<ul class="seachform">
					<li><label>班级名称</label><input type="text" name="commonClassName" size="10" value="${param.commonClassName}" class="scinput" /></li>
					<li><label>学生姓名</label><input type="text" name="teaManName" size="10" value="${param.teaManName}" class="scinput" /></li>
					<li><label>&nbsp;</label><input type="submit" class="scbtn" value="查询"/>&nbsp;<input type="button" value="新增老师" class="scbtn" onclick="addTeacher()"></li>
				</ul>
			</form>
			<table class="tablelist">
				<thead>
				<tr>
					<th>老师id</th>
					<th>老师手机号</th>
					<th>密码</th>
					<th>老师姓名</th>
					<th>性别</th>
					<th>出生日期</th>
					<th>班级名称</th>
					<th>用户状态</th>
					<th>操作</th>
				</tr>
				</thead>
				<c:if test="${fn:length(list)>0}">
                <c:forEach items="${list}" var="teaManInfo">
				<tbody>
				<tr>
					<td>${teaManInfo.teaManId}</td>
					<td>${teaManInfo.teaManPhone}</td>
					<td>${teaManInfo.password}</td>
					<td>${teaManInfo.teaManName}</td>
					<td>
						<c:choose>
                            <c:when test="${teaManInfo.teaManSex==1}">男</c:when>
                            <c:otherwise>女</c:otherwise>
                        </c:choose>
					</td>
					<td>
						<fmt:formatDate value="${teaManInfo.teaManBirthdate}" pattern="yyyy-MM-dd"/>
					</td>
					<td>
						<c:choose>
                            <c:when test="${teaManInfo.commonClassName==''}">空</c:when>
                            <c:otherwise>${teaManInfo.commonClassName}</c:otherwise>
                        </c:choose>
					</td>
					<td>
						<c:choose>
                            <c:when test="${teaManInfo.status==1}">正常</c:when>
                            <c:otherwise>冻结</c:otherwise>
                        </c:choose>
					</td>
					<td><a href="jsp/changeTeacherInfo.jsp?teaManId=${teaManInfo.teaManId}&teaManPhone=${teaManInfo.teaManPhone}&password=${teaManInfo.password}&teaManName=${teaManInfo.teaManName}&teaManSex=${teaManInfo.teaManSex}&teaManBirthdate=${teaManInfo.teaManBirthdate}&status=${teaManInfo.status}"  class="tablelink">修改</a></td>
				</tr>
				</c:forEach>
            </c:if>
            <c:if test="${fn:length(list)==0}">
				<tr align="center"><td colspan="10">没有数据</td></tr>
			</c:if>
				</tbody>
			</table>
		</div>
	</div>

	<div class="pagin">
		<div class="message">共<i class="blue">${totalPageCount}</i>页，当前显示第&nbsp;<i class="blue">${currentPageNo}&nbsp;</i>页</div>
		<form action="${pageContext.request.contextPath }/queryTeacherInfo.do" method="post">
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
				<li class="paginItem pli"><input class="pageNo"  type="number" max="${totalPageCount}" min="1" size="2" name="currentPageNo" value="${currentPageNo}"/>页</li>
				<li class="paginItem pli"><input type="hidden" name="commonClassName" value="${param.commonClassName}"></li>
				<li class="paginItem pli"><input type="hidden" name="teaManName" value="${param.teaManName}"></li>
				<li class="paginItem pli"><input type="submit" class="pinput" value="GO"/></li>
			</ul>
		</form>
		<form action="quertyteacher.do" method="post">
			<ul class="paginList">
				<c:if test="${currentPageNo>1}">
                    <li class="paginItem"><a href="${pageContext.request.contextPath }/queryTeacherInfo.do?currentPageNo=1&pageSize=${param.pageSize}&commonClassName=${param.commonClassName}&teaManName=${param.teaManName}">首页</a></li>
                    <li class="paginItem"><a href="${pageContext.request.contextPath }/queryTeacherInfo.do?currentPageNo=${currentPageNo-1}&pageSize=${param.pageSize}&commonClassName=${param.commonClassName}&teaManName=${param.teaManName}" class="pia">上一页</a></li>
				</c:if>
                    <c:if test="${currentPageNo<=1}">
				<li class="paginItem"><a href="javascript:;">首页</a></li>
				<li class="paginItem"><a href="javascript:;" class="pia">上一页</a></li>
				</c:if>
				<c:if test="${currentPageNo<totalPageCount}">
				<li class="paginItem"><a href="${pageContext.request.contextPath }/queryTeacherInfo.do?currentPageNo=${currentPageNo+1}&pageSize=${param.pageSize}&commonClassName=${param.commonClassName}&teaManName=${param.teaManName}" class="pia">下一页</a></li>
				<li class="paginItem"><a href="${pageContext.request.contextPath }/queryTeacherInfo.do?currentPageNo=${totalPageCount}&pageSize=${param.pageSize}&commonClassName=${param.commonClassName}&teaManName=${param.teaManName}">尾页</a></li>
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
</body>
<c:if test="${not empty error}">
	<script>
        alert("${error}");
	</script>
</c:if>
</html>