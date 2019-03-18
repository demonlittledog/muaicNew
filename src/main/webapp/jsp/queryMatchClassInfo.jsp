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
  	function addMatchClass(){
  		var form5 = document.getElementById("form5");
  		form5.setAttribute("action", "${pageContext.request.contextPath }/queryMatchTea.do");
  		form5.submit();
  	}

    function queryMatchRecords(){
        var form5 = document.getElementById("form5");
        form5.setAttribute("action", "${pageContext.request.contextPath }/queryHistoryMatchRecords.do");
        form5.submit();
    }




    $(document).ready(function () {
        $(document).on('click','.freeze',function () {
            var $mci = $(this).parents('tr').children('td:eq(0)');
            var $mcn = $(this).parents('tr').children('td:eq(1)');
            var $ms = $(this).parents('tr').children('td:eq(6)');
            var $operation = $(this).parents('tr').children('td:eq(7)');
            $.getJSON("${pageContext.request.contextPath}/changeMatchClassStatus.do",
                {"mci":$mci.html(),"ms":$ms.html()},function (result) {
                    if(result.ms==0){
                        $ms.html("已冻结");
                        $operation.html('<a href="#" class="freeze">解冻</a>&nbsp;' +
                            '<i style="color: #AAAAAA;font-style: normal">修改</i>&nbsp;' +
                            '<i style="color: #AAAAAA;font-style: normal">签到</i>&nbsp;' +
                            '<i style="color: #AAAAAA;font-style: normal">查看签到</i>');
                    }
                    if(result.ms==1){
                        $ms.html("正常");
                        $operation.html('<a href="#" class="freeze">冻结</a>&nbsp;' +
                            '<a href="${pageContext.request.contextPath}/queryCheckMatchClassInfo.do?id='+$mci.text()+'">修改</a>&nbsp;' +
                            '<a href="${pageContext.request.contextPath }/matchRecords.do?matchClassId='+$mci.text()+'&matchClassName='+$mcn.text()+'">签到</a>&nbsp;' +
                            '<a href="${pageContext.request.contextPath }/queryNowMatchRecords.do?matchClassId='+$mci.text()+'&matchClassName='+$mcn.text()+'">查看签到</a>');
                    }
                })
        });
    })
</script>
<body>
<jsp:include page="/jsp/teaManAccess.jsp"></jsp:include>
<div class="place">
	<span>集训班级信息</span>
</div>

<div class="formbody">
	<div id="usual1" class="usual">
		<div class="itab">
			<ul>
				<li><a href="#tab1" class="selected">班级查询</a></li>
			</ul>
		</div>

		<div id="tab1" class="tabson">
			<form action="${pageContext.request.contextPath }/queryMatchClassInfo.do" method="post" name="form5" id="form5">
				<ul class="seachform">
					<li><label>班级名称</label><input type="text" name="matchClassName" size="10" value="${param.matchClassName}" class="scinput" /></li>
					<li><label>老师姓名</label><input type="text" name="teaManName" size="10" value="${param.teaManName}" class="scinput" /></li>
					<li><label>&nbsp;</label><input type="submit" class="scbtn" value="查询"/>&nbsp;<input type="button" value="新增班级" class="scbtn" onclick="addMatchClass()">&nbsp;<input type="button" value="历史签到" class="scbtn" onclick="queryMatchRecords()"></li>
				</ul>
			</form>
			<table class="tablelist">
				<thead>
				<tr>
					<th>集训班级id</th>
					<th>集训班级名称</th>
					<th>老师姓名</th>
					<th>课时费</th>
					<th>开班日期</th>
					<th>上课时长(分钟)</th>
					<th>班级状态</th>
					<th>操作</th>
				</tr>
				</thead>
				<c:if test="${fn:length(list)>0}">
                <c:forEach items="${list}" var="matchClassInfo">
				<tbody>
				<tr>
					<td>${matchClassInfo.matchClassId}</td>
					<td>${matchClassInfo.matchClassName}</td>
					<td>${matchClassInfo.teaManName}</td>
					<td>${matchClassInfo.matchCost}</td>
					<td>
						<fmt:formatDate value="${matchClassInfo.matchClassDate}" pattern="yyyy-MM-dd"/>
					</td>
					<td>${matchClassInfo.matchSpan}</td>
					<td>
						<c:choose>
							<c:when test="${matchClassInfo.matchStatus==0}">已冻结</c:when>
							<c:when test="${matchClassInfo.matchStatus==1}">正常</c:when>
						</c:choose>
					</td>
					<td>
						<c:if test="${matchClassInfo.matchStatus==0}">
							<a href="#" class="freeze">解冻</a>
							<i style="color: #AAAAAA;font-style: normal">修改</i>
							<i style="color: #AAAAAA;font-style: normal">签到</i>
							<i style="color: #AAAAAA;font-style: normal">查看签到</i>
						</c:if>
						<c:if test="${matchClassInfo.matchStatus==1}">
							<a href="#" class="freeze">冻结</a>
							<a href="${pageContext.request.contextPath}/queryCheckMatchClassInfo.do?id=${matchClassInfo.matchClassId}">修改</a>
							<a href="${pageContext.request.contextPath }/matchRecords.do?matchClassId=${matchClassInfo.matchClassId}&matchClassName=${matchClassInfo.matchClassName}">签到</a>
							<a href="${pageContext.request.contextPath }/queryNowMatchRecords.do?matchClassId=${matchClassInfo.matchClassId}&matchClassName=${matchClassInfo.matchClassName}">查看签到</a>
						</c:if>
					</td>
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
		<form action="${pageContext.request.contextPath }/queryMatchClassInfo.do" method="post">
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
				<li class="paginItem pli"><input type="hidden" name="matchClassName" value="${param.matchClassName}"></li>
				<li class="paginItem pli"><input type="hidden" name="teaManName" value="${param.teaManName}"></li>
				<li class="paginItem pli"><input type="submit" class="pinput" value="GO"/></li>
			</ul>
		</form>
		<form action="quertyteacher.do" method="post">
			<ul class="paginList">
				<c:if test="${currentPageNo>1}">
                    <li class="paginItem"><a href="${pageContext.request.contextPath }/queryMatchClassInfo.do?currentPageNo=1&pageSize=${param.pageSize}&matchClassName=${param.matchClassName}&teaManName=${param.teaManName}">首页</a></li>
                    <li class="paginItem"><a href="${pageContext.request.contextPath }/queryMatchClassInfo.do?currentPageNo=${currentPageNo-1}&pageSize=${param.pageSize}&matchClassName=${param.matchClassName}&teaManName=${param.teaManName}" class="pia">上一页</a></li>
				</c:if>
                    <c:if test="${currentPageNo<=1}">
				<li class="paginItem"><a href="javascript:;">首页</a></li>
				<li class="paginItem"><a href="javascript:;" class="pia">上一页</a></li>
				</c:if>
				<c:if test="${currentPageNo<totalPageCount}">
				<li class="paginItem"><a href="${pageContext.request.contextPath }/queryMatchClassInfo.do?currentPageNo=${currentPageNo+1}&pageSize=${param.pageSize}&matchClassName=${param.matchClassName}&teaManName=${param.teaManName}" class="pia">下一页</a></li>
				<li class="paginItem"><a href="${pageContext.request.contextPath }/queryMatchClassInfo.do?currentPageNo=${totalPageCount}&pageSize=${param.pageSize}&matchClassName=${param.matchClassName}&teaManName=${param.teaManName}">尾页</a></li>
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