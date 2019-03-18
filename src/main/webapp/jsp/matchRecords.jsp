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
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.min.js"></script>
</head>

<script type="text/javascript">
    $(document).ready(function () {
        $(document).on('click','.records',function () {
            var $matchClassId = $('#matchClassId').val();
            var $parentsPhone = $(this).parents('tr').children('td:eq(2)');
            var password = prompt("请输入密码");
			if(password!=null){
				if(password.trim().length==0||password==null){
					alert("请输入密码！");
				}else if(password.trim().length<6){
					alert("密码不能小于6位！");
				}else {
					$.ajax({
						type:"post",
						url:"${pageContext.request.contextPath }/records.do",
						data:{"matchClassId":$matchClassId,"parentsPhone":$parentsPhone.html(),"password":password},
						success:function (result) {
							alert(result);
						}
					})
				}
			}
		})

    })
</script>
<body>
<jsp:include page="/jsp/teaManAccess.jsp"></jsp:include>
<div class="place">
	<span>学生签到</span>
</div>

<div class="formbody">
	<div id="usual1" class="usual">
		<div class="itab">
			<ul>
				<li><a href="#tab1" class="selected">${param.matchClassName}</a></li>
			</ul>
		</div>

		<div id="tab1" class="tabson">
			<form action="${pageContext.request.contextPath}/matchRecords.do" method="post" name="form4" id="form4">
				<ul class="seachform">
					<input type="hidden" name="matchClassId" id="matchClassId" value="${param.matchClassId}">
					<input type="hidden" name="matchClassName" value="${param.matchClassName}">
					<li><label>学生姓名</label><input type="text" name="studentName" size="10" value="${param.studentName}" class="scinput" /></li>
					<li><label>&nbsp;</label><input type="submit" onclick="return expo()" class="scbtn" value="查询"/>&nbsp;<input type="button" name="button" value="返回" class="btn btn-primary" onclick="javascript:location.href='${pageContext.request.contextPath }/queryMatchClassInfo.do'"/></li>
				</ul>
			</form>
			<table class="tablelist">
				<thead>
				<tr>
					<th>学生姓名</th>
					<th>家长姓名</th>
					<th>家长手机号</th>
					<th>学生状态</th>
					<th>操作</th>
				</tr>
				</thead>
				<c:if test="${fn:length(list)>0}">
                <c:forEach items="${list}" var="matchRecords">
				<tbody>
				<tr>
					<td>${matchRecords.studentName}</td>
					<td>${matchRecords.parentsName}</td>
					<td>${matchRecords.parentsPhone}</td>
					<td>
						<c:choose>
                            <c:when test="${matchRecords.status==0}">冻结</c:when>
                            <c:when test="${matchRecords.status==1}">正常</c:when>
                        </c:choose>
					</td>
					<td><a href="#" class="records">签到</a></td>
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
		<form action="${pageContext.request.contextPath }/matchRecords.do" method="post">
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
				<li class="paginItem pli"><input type="hidden" name="matchClassName" value="${param.matchClassName}"></li>
				<li class="paginItem pli"><input type="hidden" name="studentName" value="${param.studentName}"></li>
				<li class="paginItem pli"><input type="hidden" name="matchClassId" value="${param.matchClassId}"></li>
				<li class="paginItem pli"><input type="submit" class="pinput" value="GO"/></li>
			</ul>
		</form>
		<form action="quertyteacher.do" method="post">
			<ul class="paginList">
				<c:if test="${currentPageNo>1}">
					<li class="paginItem"><a href="${pageContext.request.contextPath }/matchRecords.do?currentPageNo=1&pageSize=${param.pageSize}&matchClassName=${param.matchClassName}&studentName=${param.studentName}&matchClassId=${param.matchClassId}">首页</a></li>
					<li class="paginItem"><a href="${pageContext.request.contextPath }/matchRecords.do?currentPageNo=${currentPageNo-1}&pageSize=${param.pageSize}&matchClassName=${param.matchClassName}&studentName=${param.studentName}&matchClassId=${param.matchClassId}" class="pia">上一页</a></li>
                </c:if>
                <c:if test="${currentPageNo<=1}">
				<li class="paginItem"><a href="javascript:;">首页</a></li>
				<li class="paginItem"><a href="javascript:;" class="pia">上一页</a></li>
				</c:if>
                <c:if test="${currentPageNo<totalPageCount}">
				<li class="paginItem"><a href="${pageContext.request.contextPath }/matchRecords.do?currentPageNo=${currentPageNo+1}&pageSize=${param.pageSize}&matchClassName=${param.matchClassName}&studentName=${param.studentName}&matchClassId=${param.matchClassId}" class="pia">下一页</a></li>
				<li class="paginItem"><a href="${pageContext.request.contextPath }/matchRecords.do?currentPageNo=${totalPageCount}&pageSize=${param.pageSize}&matchClassName=${param.matchClassName}&studentName=${param.studentName}&matchClassId=${param.matchClassId}">尾页</a></li>
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
</html>