<%--
  Created by IntelliJ IDEA.
  User: 62530
  Date: 2018/11/5
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>小白鸽工作室集训新增</title>
    <link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript">
      function validateCommonClassName() {
        var flag = false;
        var matchClassName=document.getElementById("matchClassName").value;
        $('#s').html("");
        if(matchClassName==null || matchClassName.length==0){
            $('#s').html("请输入集训班级名称！");
            flag = false;
        }else {
            $.ajax({
                type:"get",
                url:"${pageContext.request.contextPath }/checkMatchClassName.do",
                data:{"matchClassName":matchClassName},
                dataType:"text",
                contentType : "application/text;charset=utf-8",
                async: false,
                success:function (result) {
                    $('#s').html(result);
                    if($('#s').text().trim()=="班级姓名已存在"){
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
        var matchClassDate = document.getElementById("matchClassDate").value;
        $('#s1').html("");
        if(matchClassDate==null || matchClassDate.length==0){
            $('#s1').html("请输入开班日期！");
            return false;
        }
        return true;
    }

    function validateCommonCost() {
        var matchCost=document.getElementById("matchCost").value;
        $('#s4').html("");
        var reg=/^(?!(0[0-9]{0,}$))[0-9]{1,}[.]{0,}[0-9]{0,}$/;
        if(reg.test(matchCost)){
            return true;
        }else{
            $('#s4').html("请输入正确的课时费！");
            return false;
        }
    }

    function validateCommonSpan() {
        var matchSpan=document.getElementById("matchSpan").value;
        $('#s2').html("");
        var reg=/^[1-9][0-9]*$/;
        if(reg.test(matchSpan)){
            return true;
        }else{
            $('#s2').html("请输入正确的上课时长！");
            return false;
        }
    }
    
    
    
     $(document).ready(function() {
        $("#matchClassName").blur(validateCommonClassName);
        $("#matchCost").blur(validateCommonCost);
        $("#matchClassDate").blur(validateDate);
        $("#matchSpan").blur(validateCommonSpan);
     
     	
     	

     });
      function check() {
        var flag = true;
        if(!validateCommonClassName()){
            flag = false;
        }
        if(!validateCommonCost()){
            flag = false;
        }
        
        if(!validateDate()){
            flag = false;
        }
       
        if(!validateCommonSpan()){
            flag = false;
        }
        return  flag;
    }
    </script>
    
</head>
    
<body>
<jsp:include page="/jsp/teaManAccess.jsp"></jsp:include>
<div class="place">
    <span>增加集训班级信息</span>
</div>
<div class="formbody">
    <div class="formtitle"><span>集训班级信息</span></div>
    <form action="${pageContext.request.contextPath }/addMatchClassInfo.do" method="post">
        <ul class="forminfo">
            <li><label>集训班级名称</label><input type="text" name="matchClassName" id="matchClassName" class="dfinput"/><i><span
                    id="s"></span></i></li>
            <li><label>集训代课老师</label><select name="teaManName" id="teaManName" class="dfinput" style="width:200px;">
                <c:forEach items="${teaManInfoList}" var="teaManInfo">
                    <option value="${teaManInfo.teaManName}">${teaManInfo.teaManName}</option>
                </c:forEach>
            </select><i></i></li>
            <li><label>集训课时费</label><input type="text" name="matchCost" id="matchCost" class="dfinput"><i><span
                    id="s4"></span></i></li>
            <li><label>集训开班日期</label><input type="date" name="matchClassDate" id="matchClassDate" class="dfinput"><i><span
                    id="s1"></span></i></li>
            <li><label>集训上课时长</label><input type="text" name="matchSpan" id="matchSpan" class="dfinput"><i><span
                    id="s2"></span></i></li>
            <li><label>集训班级状态</label>
                <input type="radio" name="matchStatus" checked class="sex" id="rd1" value="1"/>正常
                <input type="radio" name="matchStatus" class="sex" id="rd2" value="0"/>冻结
                <i><span id="s5"></span></i></li>
            <li><label>&nbsp;</label>
                <input id="save" type="submit" name="submit" value="提交" class="btn btn-primary"
                       onclick="return check()"/>
                <input type="button" name="button" value="返回" class="btn btn-primary"
                       onclick="javascript:location.href='${pageContext.request.contextPath }/queryMatchClassInfo.do'"/>
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
