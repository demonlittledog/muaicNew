<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2018/12/2
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.min.js"></script>
</head>
<body>
<jsp:include page="/jsp/teaManAccess.jsp"></jsp:include>
<div class="place">
    <span>老师签到</span>
</div>
<div class="formbody">

    <div class="formtitle"><span>班级信息</span></div>
    <form action="${pageContext.request.contextPath }/teaCommonRecords.do" method="post" name="form7" id="form7">
        <ul class="forminfo">
            <li><label>班级名称</label><select name="commonClassId" id="commonClassId" class="dfinput" style="width:280px;">
                <c:forEach items="${classList}" var="classInfo">
                    <option value="${classInfo.commonClassId}">${classInfo.commonClassName}</option>
                </c:forEach>
            </select><i></i></li>
            <li><label>&nbsp;</label>
                <input type="hidden" name="teaManId" value="${teaManId}">
                <input type="submit" name="submit" value="签到" style="width:137px;height:35px; background:url(${pageContext.request.contextPath }/images/btnbg.png) no-repeat; font-size:14px;font-weight:bold;color:#fff; cursor:pointer;" class="btn btn-primary"/>
                <input type="button" name="button" value="返回" class="btn btn-primary" onclick="location.href='jsp/content.jsp'"/>
            </li>
        </ul>
    </form>
</div>
<c:if test="${not empty error}">
    <script>
        alert("${error}");
    </script>
</c:if>
</body>
</html>
