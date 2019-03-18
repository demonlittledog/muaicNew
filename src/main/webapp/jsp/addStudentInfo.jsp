<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2018/11/6
  Time: 0:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
    <link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
    function validatePassword() {
        var password = document.getElementById("password").value;
        $('#spassword').html("");
        if(password.trim()==null||password.trim().length==0){
            $('#spassword').html("密码不能为空！");
            return false;
        }else if(password.trim().length<6){
            $('#spassword').html("密码长度至少6位！");
            return false;
        }
        return true;

    }
    function validateParentsName() {
        var parentsName=document.getElementById("parentsName").value;
        $('#sparentsName').html("");
        if(parentsName==null || parentsName.length==0){
            $('#sparentsName').html("请输入家长姓名！");
            return false;
        }
        return true;
    }
    function validateStudentName() {
        var studentName=document.getElementById("studentName").value;
        $('#sstudentName').html("");
        if(studentName==null || studentName.length==0){
            $('#sstudentName').html("请输入学生姓名！");
            return false;
        }
        return true;
    }
    function validatePhone() {
        var flag = false;
        var parentsPhone=document.getElementById("parentsPhone").value;
        $('#sparentsPhone').html("");
        var reg=/^1[3|4|5|7|8][0-9]{9}$/;
        if(reg.test(parentsPhone)){
            $.ajax({
                type:"get",
                url:"${pageContext.request.contextPath }/checkParentsPhone.do",
                data:{"parentsPhone":parentsPhone},
                dataType:"text",
                contentType : "application/text;charset=utf-8",
                async: false,
                success:function (result) {
                    $('#sparentsPhone').html(result);
                    if($('#sparentsPhone').text().trim()=="手机号已存在"){
                        flag = false;
                    }else{
                        flag = true;
                    }
                }
            })
        } else{
            $('#sparentsPhone').html("请输入正确的手机号！");
            flag = false;
        }
        return flag;
    }
    function validateDate() {
        var studentBirthdate = document.getElementById("studentBirthdate").value;
        $('#sstudentBirthdate').html("");
        if(studentBirthdate==null || studentBirthdate.length==0){
            $('#sstudentBirthdate').html("请输入出生日期！");
            return false;
        }
        return true;
    }

    $(document).ready(function () {
        $("#parentsPhone").blur(validatePhone);
        $("#password").blur(validatePassword);
        $("#studentName").blur(validateStudentName);
        $("#parentsName").blur(validateParentsName);
        $("#studentBirthdate").blur(validateDate);
    })
    function check() {
        var flag = true;
        if(!validatePhone()){
            flag = false;
        }
        if(!validatePassword()){
            flag = false;
        }
        if(!validateStudentName()){
            flag = false;
        }
        if(!validateParentsName()){
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
    <span>增加学生信息</span>
</div>

<div class="formbody">

    <div class="formtitle"><span>基本信息</span></div>
    <form action="${pageContext.request.contextPath }/addStudentInfo.do" method="post" name="form3" id="form3" onsubmit="return check()">
        <ul class="forminfo">
            <li><label>家长手机号</label><input name="parentsPhone" id="parentsPhone" type="text" class="dfinput" /><i><span id="sparentsPhone"></span></i></li>
            <li><label>密码登录用</label><input name="password" id="password" type="text" class="dfinput" /><i><span id="spassword"></span></i></li>
            <li><label>学生姓名</label><input name="studentName" id="studentName" type="text" class="dfinput" /><i><span id="sstudentName"></span></i></li>
            <li><label>家长姓名</label><input  name="parentsName" id="parentsName" type="text" class="dfinput" /><i><span id="sparentsName"></span></i></li>
            <li><label>学生性别</label>
                <input type="radio" name="studentSex" value="1" checked  class="sex"/>男
                <input type="radio" name="studentSex" value="2"  style="margin-left:30px;" class="sex"/>女
            </li>
            <li><label>学生出生日期</label>
                <input type="date" name="studentBirthdate" id="studentBirthdate" class="dfinput" />
                <i><span id="sstudentBirthdate"></span></i>
            </li>
            <li><label>用户状态</label>
                <input type="radio" name="status" value="1" class="sex"  checked />正常
                <input type="radio" name="status" value="0"  style="margin-left:20px;" class="sex" />冻结
            </li>
            <li><label>&nbsp;</label>
                <input type="submit" name="submit" value="提交" style="width:137px;height:35px; background:url(${pageContext.request.contextPath }/images/btnbg.png) no-repeat; font-size:14px;font-weight:bold;color:#fff; cursor:pointer;" class="btn btn-primary"/>
                <input type="button" name="button" value="返回" class="btn btn-primary" onclick="javascript:location.href='${pageContext.request.contextPath }/queryStudentInfo.do'"/>
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
