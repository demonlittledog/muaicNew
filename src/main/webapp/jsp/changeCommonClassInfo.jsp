<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2018/11/21
  Time: 22:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
    function addClassType() {
        var classTypeName=prompt("请输入新增的班级类型");
        if(classTypeName!=null){
            if(classTypeName.trim().length==0){
                alert("班级类型不能为空");
            }else if(classTypeName.trim().length!=0){
                window.location.href="${pageContext.request.contextPath }/addClassType.do?classTypeName="+classTypeName+"";
            }
        }
    }

    function validateCommonClassName() {
        var flag = false;
        var commonClassName=document.getElementById("commonClassName").value;
        $('#scommonClassName').html("");
        if(commonClassName==null || commonClassName.length==0){
            $('#scommonClassName').html("请输入普通班级名称！");
            flag = false;
        }else {
            $.ajax({
                type:"get",
                url:"${pageContext.request.contextPath }/checkChangeCommonClassName.do",
                data:{"commonClassName":commonClassName,"commonClassId":${commonClassInfo.commonClassId}},
                dataType:"text",
                contentType : "application/text;charset=utf-8",
                async: false,
                success:function (result) {
                    $('#scommonClassName').html(result);
                    if($('#scommonClassName').text().trim()=="班级姓名已存在"){
                        flag = false;
                    }else{
                        flag = true;
                    }
                }
            })
        }
        return flag;
    }

    function validateDate() {
        var commonClassDate = document.getElementById("commonClassDate").value;
        $('#scommonClassDate').html("");
        if(commonClassDate==null || commonClassDate.length==0){
            $('#scommonClassDate').html("请输入开班日期！");
            return false;
        }
        return true;
    }

    function validateTime() {
        var commonTime = document.getElementById("commonTime").value;
        $('#scommonTime').html("");
        if(commonTime==null || commonTime.length==0){
            $('#scommonTime').html("请输入上课时间！");
            return false;
        }
        return true;
    }

    function validateCommonCost() {
        var commonCost=document.getElementById("commonCost").value;
        $('#scommonCost').html("");
        var reg=/^(?!(0[0-9]{0,}$))[0-9]{1,}[.]{0,}[0-9]{0,}$/;
        if(reg.test(commonCost)){
            return true;
        }else{
            $('#scommonCost').html("请输入正确的课时费！");
            return false;
        }
    }

    function validateCommonSpan() {
        var commonSpan=document.getElementById("commonSpan").value;
        $('#scommonSpan').html("");
        var reg=/^[1-9][0-9]*$/;
        if(reg.test(commonSpan)){
            return true;
        }else{
            $('#scommonSpan').html("请输入正确的上课时长！");
            return false;
        }
    }

    function validateCommonSum() {
        var commonSum=document.getElementById("commonSum").value;
        $('#scommonSum').html("");
        var reg=/^[1-9][0-9]*$/;
        if(reg.test(commonSum)){
            return true;
        }else{
            $('#scommonSum').html("请输入正确的总课程数！");
            return false;
        }
    }

    $(document).ready(function () {
        $("#commonClassName").blur(validateCommonClassName);
        $("#commonCost").blur(validateCommonCost);
        $("#commonSum").blur(validateCommonSum);
        $("#commonClassDate").blur(validateDate);
        $("#commonTime").blur(validateTime);
        $("#commonSpan").blur(validateCommonSpan);

    })

    function check() {
        var flag = true;
        if(!validateCommonClassName()){
            flag = false;
        }
        if(!validateCommonCost()){
            flag = false;
        }
        if(!validateCommonSum()){
            flag = false;
        }
        if(!validateDate()){
            flag = false;
        }
        if(!validateTime()){
            flag = false;
        }
        if(!validateCommonSpan()){
            flag = false;
        }
        return  flag;
    }
</script>
<body>
<jsp:include page="/jsp/teaManAccess.jsp"></jsp:include>
<div class="place">
    <span>修改普通班级信息</span>
</div>
<div class="formbody">

    <div class="formtitle"><span>班级信息</span></div>
    <form action="${pageContext.request.contextPath }/changeCommonClassInfo.do" method="post" name="form7" id="form7">
        <ul class="forminfo">
            <li style="height:28px;line-height:28px"><label>普通班级id</label><span>${commonClassInfo.commonClassId}</span></li>
            <li><label>普通班级名称</label><input type="text" name="commonClassName" id="commonClassName" value="${commonClassInfo.commonClassName}" class="dfinput" /><i><span id="scommonClassName"></span></i></li>
            <li><label>班级类型</label><select name="classTypeId" id="classTypeId" class="dfinput" style="width:200px;">
                <c:forEach items="${classTypeList}" var="classType">
                    <option <c:if test="${commonClassInfo.classTypeId==classType.classTypeId}">selected</c:if> value="${classType.classTypeId}">${classType.classTypeName}</option>
                </c:forEach>
            </select><i></i></li>
            <li><label>老师姓名</label><select name="teaManName" id="teaManName" class="dfinput" style="width:200px;">
                <c:forEach items="${teaManInfoList}" var="teaManInfo">
                    <option <c:if test="${commonClassInfo.teaManId==teaManInfo.teaManId}">selected</c:if> value="${teaManInfo.teaManName}">${teaManInfo.teaManName}</option>
                </c:forEach>
            </select><i></i></li>
            <li><label>课时费</label><input type="text" name="commonCost" id="commonCost" value="${commonClassInfo.commonCost}" class="dfinput"><i><span id="scommonCost"></span></i></li>
            <li><label>总课程数</label><input type="text" name="commonSum" id="commonSum" value="${commonClassInfo.commonSum}" class="dfinput"><i><span id="scommonSum"></span></i></li>
            <li><label>开班日期</label>
                <input type="date" name="commonClassDate" id="commonClassDate" value="${commonClassInfo.commonClassDate}" class="dfinput">
                <i><span id="scommonClassDate"></span></i>
            </li>
            <li><label>星期</label><select name="commonWeek" id="commonWeek" class="dfinput" style="width:200px;">
                <c:forEach begin="1" end="7" step="1" var="i">
                    <option <c:if test="${commonClassInfo.commonWeek==i}">selected</c:if> value="${i}">
                        <c:if test="${i==1}">星期一</c:if>
                        <c:if test="${i==2}">星期二</c:if>
                        <c:if test="${i==3}">星期三</c:if>
                        <c:if test="${i==4}">星期四</c:if>
                        <c:if test="${i==5}">星期五</c:if>
                        <c:if test="${i==6}">星期六</c:if>
                        <c:if test="${i==7}">星期日</c:if>
                    </option>
                </c:forEach>
            </select></li>
            <li><label>上课时间</label><input type="time" name="commonTime" id="commonTime" value="${commonClassInfo.commonTime}" class="dfinput"><i><span id="scommonTime"></span></i></li>
            <li><label>上课时长</label><input type="text" name="commonSpan" id="commonSpan" value="${commonClassInfo.commonSpan}" class="dfinput"><i><span id="scommonSpan"></span></i></li>
            <li><label>&nbsp;</label>
                <input type="hidden" name="commonClassId" id="commonClassId" value="${commonClassInfo.commonClassId}">
                <input type="submit" name="submit" value="提交" class="btn btn-primary" onclick="return check()"/>
                <input type="button" name="button" value="返回" class="btn btn-primary" onclick="javascript:location.href='${pageContext.request.contextPath }/queryCommonClassInfo.do'"/>
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
