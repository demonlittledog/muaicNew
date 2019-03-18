<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2018/11/6
  Time: 0:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
    function validateTeacherName() {
        var teaManName=document.getElementById("teaManName").value;
        $('#steaManName').html("");
        if(teaManName==null || teaManName.length==0){
            $('#steaManName').html("请输入学生姓名！");
            return false;
        }
        return true;
    }
    function validatePhone() {
        var flag = false;
        var teaManPhone=document.getElementById("teaManPhone").value;
        $('#steaManPhone').html("");
        var reg=/^1[3|4|5|7|8][0-9]{9}$/;
        if(reg.test(teaManPhone)){
            $.ajax({
                type:"get",
                url:"${pageContext.request.contextPath }/checkChangeTeaManPhone.do",
                data:{"teaManPhone":teaManPhone,"teaManId":${sessionScope.teaManInfo.teaManId}},
                dataType:"text",
                contentType : "application/text;charset=utf-8",
                async: false,
                success:function (result) {
                    $('#steaManPhone').html(result);
                    if($('#steaManPhone').text().trim()=="手机号已存在"){
                        flag = false;
                    }else{
                        flag = true;
                    }
                }
            })
        }else{
            $('#steaManPhone').html("请输入正确的手机号！");
            flag = false;
        }
        return flag;
    }
    function validateDate() {
        var teaManBirthdate = document.getElementById("teaManBirthdate").value;
        $('#steaManBirthdate').html("");
        if(teaManBirthdate==null || teaManBirthdate.length==0){
            $('#steaManBirthdate').html("请输入出生日期！");
            return false;
        }
        return true;
    }

    $(document).ready(function () {
        $("#teaManPhone").blur(validatePhone);
        $("#teaManBirthdate").blur(validateTeacherName);
        $("#teaManBirthdate").blur(validateDate);
    })

    function check() {
        var flag = true;
        if(!validatePhone()){
            flag = false;
        }
        if(!validateTeacherName()){
            flag = false;
        }
        if(!validateDate()){
            flag = false;
        }
        return  flag;
    }
</script>
<body>
<jsp:include page="/jsp/teaManAccess.jsp"></jsp:include>
<div class="place">
    <span>修改老师信息</span>
</div>

<div class="formbody">
    <div class="formtitle"><span>基本信息</span></div>
    <form action="${pageContext.request.contextPath }/changePersonalInfo.do" method="post" name="form6" id="form6">
        <ul class="forminfo">
            <li style="height:28px;line-height:28px"><label>老师id</label><span>${sessionScope.teaManInfo.teaManId}</span><i><input type="hidden" name="teaManId" value="${sessionScope.teaManInfo.teaManId}"></i></li>
            <li><label>老师手机号</label><input type="text" name="teaManPhone" id="teaManPhone" value="${sessionScope.teaManInfo.teaManPhone}" class="dfinput" /><i><span id="steaManPhone"></span></i></li>
            <li><label>老师姓名</label><input type="text" name="teaManName" id="teaManName" value="${sessionScope.teaManInfo.teaManName}" class="dfinput" /><i><span id="steaManName"></span></i></li>
            <li><label>老师性别</label>
                <input type="radio" name="teaManSex" value="1" <c:if test="${sessionScope.teaManInfo.teaManSex==1}">checked</c:if>  class="sex"/>男
                <input type="radio" name="teaManSex" value="2" <c:if test="${sessionScope.teaManInfo.teaManSex==2}">checked</c:if>  style="margin-left:30px;" class="sex"/>女
            </li>
            <li><label>老师出生日期</label>
                <input type="date" name="teaManBirthdate" id="teaManBirthdate" value="<fmt:formatDate value="${sessionScope.teaManInfo.teaManBirthdate}" pattern="yyyy-MM-dd"/>" class="dfinput" />
                <i><span id="steaManBirthdate"></span></i>
            </li>
            <li><label>&nbsp;</label>
                <input type="submit" name="submit" value="提交" onclick="return check()" style="width:137px;height:35px; background:url(${pageContext.request.contextPath }/images/btnbg.png) no-repeat; font-size:14px;font-weight:bold;color:#fff; cursor:pointer;" class="btn btn-primary"/>
                <input type="button" name="button" value="返回"  class="btn btn-primary" onclick="javascript:location.href='${pageContext.request.contextPath }/jsp/content.jsp'" />
            </li>
        </ul>
    </form>
</div>
</body>
<c:if test="${not empty error}">
    <script>
        alert("${error}");
    </script>
</c:if>
</html>
