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

    $(document).ready(function () {
        $(document).on('click','.freeze',function () {
            var $cci = $(this).parents('tr').children('td:eq(0)');
            var $ccn = $(this).parents('tr').children('td:eq(1)');
            var $cs = $(this).parents('tr').children('td:eq(11)');
            var $operation = $(this).parents('tr').children('td:eq(12)');
            $.getJSON("${pageContext.request.contextPath}/changeCommonClassStatus.do",
                {"cci":$cci.html(),"cs":$cs.html()},function (result) {
                    if(result.cs==0){
                        $cs.html("已冻结");
                        $operation.html('<a href="#" class="freeze">解冻</a>&nbsp;' +
                            '<i style="color: #AAAAAA;font-style: normal">修改</i>&nbsp;' +
                            '<i style="color: #AAAAAA;font-style: normal">增加学生</i>&nbsp;' +
                            '<i style="color: #AAAAAA;font-style: normal">查询学生</i>');
                    }
                    if(result.cs==1){
                        $cs.html("正常");
                        $operation.html('<a href="#" class="freeze">冻结</a>&nbsp;' +
                            '<a href="${pageContext.request.contextPath}/queryCheckCommonClassInfo.do?id='+$cci.text()+'">修改</a>&nbsp;' +
                            '<a href="${pageContext.request.contextPath }/queryAddStudentInfo.do?commonClassId='+$cci.text()+'&commonClassName='+$ccn.text()+'">增加学生</a>&nbsp;' +
                            '<a href="${pageContext.request.contextPath }/queryStudentFromCommonClass.do?commonClassId='+$cci.text()+'&commonClassName='+$ccn.text()+'">查询学生</a>');
                    }
                })
        });
    })

</script>




<body>
<jsp:include page="/jsp/teaManAccess.jsp"></jsp:include>
<div class="place">
	<span>普通班级管理</span>
</div>

<div class="formbody">
	<div id="usual1" class="usual">
		<div class="itab">
			<ul>
				<li><a href="#tab1" class="selected">班级信息</a></li>
			</ul>
		</div>

		<div id="tab1" class="tabson">
			<form action="${pageContext.request.contextPath}/queryCommonClassInfo.do" method="post" name="form4" id="form4">
				<ul class="seachform">
					<li><label>班级名称</label><input type="text" name="commonClassName" size="6" value="${param.commonClassName}" class="scinput" /></li>
					<li><label>老师姓名</label><input type="text" name="teaManName" size="6" value="${param.teaManName}" class="scinput" /></li>
					<li><label>起始时间</label><input type="text" size="6" name="da1" id="da1" value="${requestScope.da1}" readonly="readonly" onClick="WdatePicker()" class="scinput" /></li>
					<li><label>截止时间</label><input type="text" size="6" name="da2" id="da2" value="${requestScope.da2}" readonly="readonly" onClick="WdatePicker()" class="scinput" /></li>
					<li><label>&nbsp;</label><input type="submit" onclick="return expo()" class="scbtn" value="查询"/>&nbsp;<input type="button" value="新增班级" class="scbtn" onclick="javascript:location.href='${pageContext.request.contextPath }/queryClassType.do'"></li>
				</ul>
			</form>
			<table class="tablelist">
				<thead>
				<tr>
					<th>普通班级id</th>
					<th>普通班级名称</th>
					<th>普通班级类型</th>
					<th>老师姓名</th>
					<th>课时费</th>
					<th>总课程数</th>
					<th>开班日期</th>
					<th>周</th>
					<th>上课时间</th>
					<th>上课时长(分钟)</th>
					<th>学生人数</th>
					<th>班级状态</th>
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
					<td>${commonClassInfo.teaManName}</td>
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
						<c:choose>
							<c:when test="${commonClassInfo.commonStatus==0}">已冻结</c:when>
							<c:when test="${commonClassInfo.commonStatus==1}">正常</c:when>
						</c:choose>
					</td>
					<td>
						<c:if test="${commonClassInfo.commonStatus==0}">
							<a href="#" class="freeze">解冻</a>
							<i style="color: #AAAAAA;font-style: normal">修改</i>
							<i style="color: #AAAAAA;font-style: normal">增加学生</i>
							<i style="color: #AAAAAA;font-style: normal">查询学生</i>
						</c:if>
						<c:if test="${commonClassInfo.commonStatus==1}">
							<a href="#" class="freeze">冻结</a>
							<a href="${pageContext.request.contextPath}/queryCheckCommonClassInfo.do?id=${commonClassInfo.commonClassId}">修改</a>
							<a href="${pageContext.request.contextPath }/queryAddStudentInfo.do?commonClassId=${commonClassInfo.commonClassId}&commonClassName=${commonClassInfo.commonClassName}">增加学生</a>
							<a href="${pageContext.request.contextPath }/queryStudentFromCommonClass.do?commonClassId=${commonClassInfo.commonClassId}&commonClassName=${commonClassInfo.commonClassName}">查询学生</a>
						</c:if>
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
		<form action="${pageContext.request.contextPath }/queryCommonClassInfo.do" method="post">
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
				<li class="paginItem pli"><input type="hidden" name="teaManName" value="${param.teaManName}"></li>
				<li class="paginItem pli"><input type="hidden" name="da1" value="${requestScope.da1}"></li>
				<li class="paginItem pli"><input type="hidden" name="da2" value="${requestScope.da2}"></li>
				<li class="paginItem pli"><input type="submit" class="pinput" value="GO"/></li>
			</ul>
		</form>
		<form action="quertyteacher.do" method="post">
			<ul class="paginList">
				<c:if test="${currentPageNo>1}">
					<li class="paginItem"><a href="${pageContext.request.contextPath }/queryCommonClassInfo.do?currentPageNo=1&pageSize=${param.pageSize}&commonClassName=${param.commonClassName}&teaManName=${param.teaManName}&da1=${requestScope.da1}&da2=${requestScope.da2}">首页</a></li>
					<li class="paginItem"><a href="${pageContext.request.contextPath }/queryCommonClassInfo.do?currentPageNo=${currentPageNo-1}&pageSize=${param.pageSize}&commonClassName=${param.commonClassName}&teaManName=${param.teaManName}&da1=${requestScope.da1}&da2=${requestScope.da2}" class="pia">上一页</a></li>
				</c:if>
				<c:if test="${currentPageNo<=1}">
					<li class="paginItem"><a href="javascript:;">首页</a></li>
					<li class="paginItem"><a href="javascript:;" class="pia">上一页</a></li>
				</c:if>
				<c:if test="${currentPageNo<totalPageCount}">
					<li class="paginItem"><a href="${pageContext.request.contextPath }/queryCommonClassInfo.do?currentPageNo=${currentPageNo+1}&pageSize=${param.pageSize}&commonClassName=${param.commonClassName}&teaManName=${param.teaManName}&da1=${requestScope.da1}&da2=${requestScope.da2}" class="pia">下一页</a></li>
					<li class="paginItem"><a href="${pageContext.request.contextPath }/queryCommonClassInfo.do?currentPageNo=${totalPageCount}&pageSize=${param.pageSize}&commonClassName=${param.commonClassName}&teaManName=${param.teaManName}&da1=${requestScope.da1}&da2=${requestScope.da2}">尾页</a></li>
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