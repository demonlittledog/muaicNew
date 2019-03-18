<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2018/11/22
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
<script type="text/javascript">
    function addStudent(){
        var form1 = document.getElementById("form1");
        form1.setAttribute("action", "${pageContext.request.contextPath }/addStudentToCommonClass.do");
        form1.submit();
    }

    function queryStudent(){
        var form1 = document.getElementById("form1");
        form1.setAttribute("action", "${pageContext.request.contextPath }/queryStudentFromCommonClass.do");
        form1.submit();
    }
</script>
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
            <form action="${pageContext.request.contextPath }/queryAddStudentInfo.do" method="post" name="form1" id="form1">
                <input type="hidden" name="commonClassId" value="${param.commonClassId}">
                <input type="hidden" name="commonClassName" value="${param.commonClassName}">
                <ul class="seachform">
                    <li><label>学生姓名</label><input type="text" name="studentName" size="10" value="${param.studentName}" class="scinput" /></li>
                    <li><label>&nbsp;</label><input type="submit" class="scbtn" value="查询"/>&nbsp;<input type="button" value="新增" class="scbtn" onclick="addStudent()">&nbsp;<input type="button" value="查询学生" class="scbtn" onclick="queryStudent()">&nbsp;<input type="button" name="button" value="返回" class="scbtn" onclick="javascript:location.href='${pageContext.request.contextPath }/queryCommonClassInfo.do'"/></li>
                </ul>

            <table class="tablelist">
                <thead>
                <tr>
                    <th>学生id</th>
                    <th>家长手机号</th>
                    <th>学生姓名</th>
                    <th>家长姓名</th>
                    <th>性别</th>
                    <th>出生日期</th>
                    <th>用户状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${fn:length(list)>0}">
                    <c:forEach items="${list}" var="studentInfo">
                        <tr>
                            <td>${studentInfo.studentId}</td>
                            <td>${studentInfo.parentsPhone}</td>
                            <td>${studentInfo.studentName}</td>
                            <td>${studentInfo.parentsName}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${studentInfo.studentSex==1}">男</c:when>
                                    <c:otherwise>女</c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <fmt:formatDate value="${studentInfo.studentBirthdate}" pattern="yyyy-MM-dd"/>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${studentInfo.status==1}">正常</c:when>
                                    <c:otherwise>冻结</c:otherwise>
                                </c:choose>
                            </td>
                            <td><input type="checkbox" name="chose"  value="${studentInfo.studentId}" /></td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${fn:length(list)==0}">
                    <tr align="center"><td colspan="8">没有数据</td></tr>
                </c:if>
                </tbody>
            </table>
            </form>
        </div>
    </div>

    <div class="pagin">
        <div class="message">共<i class="blue">${totalPageCount}</i>页，当前显示第&nbsp;<i class="blue">${currentPageNo}&nbsp;</i>页</div>
        <form action="${pageContext.request.contextPath }/queryAddStudentInfo.do" method="post">
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
                <li class="paginItem pli"><input type="hidden" name="studentName" value="${param.studentName}"></li>
                <li class="paginItem pli"><input type="hidden" name="commonClassId" value="${param.commonClassId}"></li>
                <li class="paginItem pli"><input type="hidden" name="commonClassName" value="${param.commonClassName}"></li>
                <li class="paginItem pli"><input type="submit" class="pinput" value="GO"/></li>
            </ul>
        </form>
        <form action="quertyteacher.do" method="post">
            <ul class="paginList">
                <c:if test="${currentPageNo>1}">
                    <li class="paginItem"><a href="${pageContext.request.contextPath }/queryAddStudentInfo.do?currentPageNo=1&pageSize=${param.pageSize}&commonClassId=${param.commonClassId}&studentName=${param.studentName}&commonClassName=${param.commonClassName}">首页</a></li>
                    <li class="paginItem"><a href="${pageContext.request.contextPath }/queryAddStudentInfo.do?currentPageNo=${currentPageNo-1}&pageSize=${param.pageSize}&commonClassId=${param.commonClassId}&studentName=${param.studentName}&commonClassName=${param.commonClassName}" class="pia">上一页</a></li>
                </c:if>
                <c:if test="${currentPageNo<=1}">
                    <li class="paginItem"><a href="javascript:;">首页</a></li>
                    <li class="paginItem"><a href="javascript:;" class="pia">上一页</a></li>
                </c:if>
                <c:if test="${currentPageNo<totalPageCount}">
                    <li class="paginItem"><a href="${pageContext.request.contextPath }/queryAddStudentInfo.do?currentPageNo=${currentPageNo+1}&pageSize=${param.pageSize}&commonClassId=${param.commonClassId}&studentName=${param.studentName}&commonClassName=${param.commonClassName}" class="pia">下一页</a></li>
                    <li class="paginItem"><a href="${pageContext.request.contextPath }/queryAddStudentInfo.do?currentPageNo=${totalPageCount}&pageSize=${param.pageSize}&commonClassId=${param.commonClassId}&studentName=${param.studentName}&commonClassName=${param.commonClassName}">尾页</a></li>
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
