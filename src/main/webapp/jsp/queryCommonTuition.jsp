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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
    //判断时间
    function expo(){
        //得到起始时间
        var startDate=document.getElementById("da1").value;
        //得到终止时间
        var endDate=document.getElementById("da2").value;

        //清空格式
        var startNum = parseInt(startDate.replace(/-/g,''));
        var endNum = parseInt(endDate.replace(/-/g,''));
        if(startNum>endNum){
            alert("结束日期不能早于起始日期！");
            return false;
        }
    }
</script>
<body>
<jsp:include page="/jsp/teaManAccess.jsp"></jsp:include>
<div class="place">
	<span>普通学费查询</span>
</div>

<div class="formbody">
	<div id="usual1" class="usual">
		<div class="itab">
			<ul>
				<li><a href="#tab1" class="selected">学费查询</a></li>
			</ul>
		</div>

		<div id="tab1" class="tabson">
			<form action="${pageContext.request.contextPath}/queryCommonTuition.do" method="post" name="form4" id="form4">
				<ul class="seachform">
					<li><label>班级名称</label><input type="text" name="commonClassName" size="10" value="${param.commonClassName}" class="scinput" /></li>
					<li><label>学生姓名</label><input type="text" name="studentName" size="10" value="${param.studentName}" class="scinput" /></li>
					<li><label>起始时间</label><input type="text" size="10" name="da1" id="da1" value="${requestScope.da1}" readonly="readonly" onClick="WdatePicker()" class="scinput" /></li>
					<li><label>截止时间</label><input type="text" size="10" name="da2" id="da2" value="${requestScope.da2}" readonly="readonly" onClick="WdatePicker()" class="scinput" /></li>
					<li><label>&nbsp;</label><input type="submit" onclick="return expo()" class="scbtn" value="查询"/></li>
				</ul>
			</form>
			<table class="tablelist">
				<thead>
				<tr>
					<th>学生姓名</th>
					<th>家长手机号</th>
					<th>班级类型</th>
					<th>班级名称</th>
					<th>老师姓名</th>
					<th>课时费</th>
					<th>上课时长</th>
					<th>剩余学费</th>
					<th>总学费</th>
				</tr>
				</thead>
				<c:if test="${fn:length(list)>0}">
				<c:forEach items="${list}" var="commonTuition">
				<tbody>
				<tr>
					<td>${commonTuition.studentName}</td>
					<td>${commonTuition.parentsPhone}</td>
					<td>${commonTuition.classTypeName}</td>
					<td>${commonTuition.commonClassName}</td>
					<td>${commonTuition.teaManName}</td>
					<td>${commonTuition.commonCost}</td>
					<td>${commonTuition.commonSpan}</td>
					<td>${commonTuition.commonSurplus}</td>
					<td>${commonTuition.commonAllCost}</td>
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
		<form action="${pageContext.request.contextPath }/queryCommonTuition.do" method="post">
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
				<li class="paginItem pli"><input type="hidden" name="studentName" value="${param.studentName}"></li>
				<li class="paginItem pli"><input type="hidden" name="da1" value="${requestScope.da1}"></li>
				<li class="paginItem pli"><input type="hidden" name="da2" value="${requestScope.da2}"></li>
				<li class="paginItem pli"><input type="submit" class="pinput" value="GO"/></li>
			</ul>
		</form>
		<form action="quertyteacher.do" method="post">
			<ul class="paginList">
				<c:if test="${currentPageNo>1}">
					<li class="paginItem"><a href="${pageContext.request.contextPath }/queryCommonTuition.do?currentPageNo=1&pageSize=${param.pageSize}&commonClassName=${param.commonClassName}&studentName=${param.studentName}&da1=${requestScope.da1}&da2=${requestScope.da2}">首页</a></li>
					<li class="paginItem"><a href="${pageContext.request.contextPath }/queryCommonTuition.do?currentPageNo=${currentPageNo-1}&pageSize=${param.pageSize}&commonClassName=${param.commonClassName}&studentName=${param.studentName}&da1=${requestScope.da1}&da2=${requestScope.da2}" class="pia">上一页</a></li>
				</c:if>
				<c:if test="${currentPageNo<=1}">
					<li class="paginItem"><a href="javascript:;">首页</a></li>
					<li class="paginItem"><a href="javascript:;" class="pia">上一页</a></li>
				</c:if>
				<c:if test="${currentPageNo<totalPageCount}">
					<li class="paginItem"><a href="${pageContext.request.contextPath }/queryCommonTuition.do?currentPageNo=${currentPageNo+1}&pageSize=${param.pageSize}&commonClassName=${param.commonClassName}&studentName=${param.studentName}&da1=${requestScope.da1}&da2=${requestScope.da2}" class="pia">下一页</a></li>
					<li class="paginItem"><a href="${pageContext.request.contextPath }/queryCommonTuition.do?currentPageNo=${totalPageCount}&pageSize=${param.pageSize}&commonClassName=${param.commonClassName}&studentName=${param.studentName}&da1=${requestScope.da1}&da2=${requestScope.da2}">尾页</a></li>
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