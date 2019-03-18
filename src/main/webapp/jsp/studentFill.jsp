<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2018/11/8
  Time: 0:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript">
    function validateCommonClassName() {
        var commonClassName = document.getElementById("commonClassName").value;
        if(commonClassName==null || commonClassName.length==0){
            alert("请输入普通班级姓名！");
            return false;
        }
        return true;
    }
    function validatePhone() {
        var parentsPhone=document.getElementById("parentsPhone").value;
        var reg=/^1[3|4|5|7|8][0-9]{9}$/;
        if(reg.test(parentsPhone)){
            return true;
        }else{
            alert("请输入正确的手机号！");
            return false;
        }
    }

    function validateFill() {
        if(confirm('确认要补签吗？')==false){
            return false;
        }else {
            return true;
        }
    }

    function check() {
        return validatePhone() && validateCommonClassName() && validateFill();
    }
</script>
<body>
<jsp:include page="/jsp/teaManAccess.jsp"></jsp:include>
<div class="place">
    <span>学生补签</span>
</div>

<div class="formbody">

    <div class="formtitle"><span>基本信息</span></div>
    <form action="${pageContext.request.contextPath }/StudentFill.do" method="post" name="form4" id="form4">
        <ul class="forminfo">
            <li><label>家长手机号</label><input type="text" name="parentsPhone" id="parentsPhone" class="dfinput" /><i></i></li>
            <li><label>普通班级名称</label><input type="text" name="commonClassName" id="commonClassName" class="dfinput" /><i></i></li>
            <li><label>&nbsp;</label>
                <input type="submit" name="submit" value="提交" class="btn btn-primary" onclick="return check()"/>
                <input type="button" name="button" value="返回" class="btn btn-primary" onclick="location.href='content.jsp'"/>
            </li>
        </ul>
    </form>
</div>
</body>
</html>
