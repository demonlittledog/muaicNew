<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2018/11/9
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
</head>
<style type="text/css">
    div{margin: 0 auto;}
    h2{text-align: center;}
    table {margin: 0 auto;}
    tr td{text-align: center;}
</style>
<script type="text/javascript">
    function validateForm(){
        var ypwd = document.getElementById("ypwd").value;
        var xpwd = document.getElementById("xpwd").value;
        var qpwd = document.getElementById("qpwd").value;
        if(ypwd.trim()==null||ypwd.trim().length==0){
            alert("旧密码不能为空！");
            return false;
        }else if(ypwd.trim().length<6){
            alert("旧密码不能小于6！");
            return false;
        }else if(xpwd.trim()==null||xpwd.trim().length==0||qpwd.trim()==null||qpwd.trim().length==0){
            alert("新密码不能为空！");
            return false;
        }else if(xpwd.trim().length<6){
            alert("新密码不能小于6！");
            return false;
        }
        else if(xpwd.trim()!=qpwd.trim()){
            alert("两次新密码不相同！");
            return false;
        }else{
            return true;
        }
    }

</script>
<body>
<jsp:include page="/jsp/teaManAccess.jsp"></jsp:include>
<div class="place">
    <span>修改密码</span>
</div>
<div class="formbody">

    <div class="formtitle"><span>基本信息</span></div>
    <form action="${pageContext.request.contextPath }/changePwd.do" name="form7" id="form7" method="post" onsubmit="return validateForm()">
        <ul class="forminfo">
            <li><label>原始密码</label><input name="ypwd" id="ypwd" type="text" class="dfinput" /><i></i></li>
            <li><label>新&nbsp;密&nbsp;码</label><input type="password" name="xpwd" id="xpwd" class="dfinput2" /><i></i></li>
            <li><label>确认密码</label><input type="password" name="qpwd" id="qpwd" class="dfinput" /><i></i></li>
            <li><label>&nbsp;</label>
                <input type="submit" name="submit" value="修改" style="width:137px;height:35px; background:url(${pageContext.request.contextPath }/images/btnbg.png) no-repeat; font-size:14px;font-weight:bold;color:#fff; cursor:pointer;" class="btn btn-primary"/>
                <input type="button" name="button" value="返回" class="btn btn-primary" onclick="location.href='content.jsp'"/>
            </li>
        </ul>
    </form>
</div>
</body>
</html>
