<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>学生签到页面</title>
      <link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
      <style>
          html { height: 100%; }
          body { height: 100%; background: #fff url(${pageContext.request.contextPath }/images/backgroud.png) 50% 50% no-repeat; background-size: cover; overflow: hidden}
      </style>
      <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript">
        //验证用户名是否为空
        function validateParentsPhone(){
            var parentsPhone=$("input[name='parentsPhone']").val();
            if(null==parentsPhone||""==parentsPhone){
                //$("input[name='uname']").next().html("密码不能为空");
                alert("用户名不能为空");
                return false;
            }else{
                //$("input[name='uname']").next().html("");
                return true;
            }
        }
        //验证密码是否为空
        function validatepws(){
            var studentPWD = $("input[name='studentPWD']").val();
            if(studentPWD==""){
                //$("input[name='pwd']").next().html("密码不能为空");
                alert("密码不能为空");
                return false;
            }else{
                //("input[name='pwd']").next().html("");
                return true;
            }
        }
        //鼠标事件
        $(document).ready(function(){
            //用户名验证
            $("input[name='parentsPhone']").blur(function(){
                validateParentsPhone();
            });
            //密码验证
            $("input[name='studentPWD']").blur(function(){
                validatepws();
            });
            $("form").submit(function(){
                return validateParentsPhone()&&validatepws();
            });
        });
    </script>
  </head>
  <body>
  <div id="container" class="container">
      <form action="${pageContext.request.contextPath }/studentServlet.do" method="post" name="form3" id="form3">
          <div class="login">小白鸽音乐学生签到</div>
          <div class="username-text">家长手机号:</div>
          <div class="password-text">学生密码:</div>
          <div class="username-field">
              <input type="text" name="parentsPhone" id="parentsPhone" />
          </div>
          <div class="password-field">
              <input type="password" name="studentPWD" id="studentPWD"/>
          </div>
          <input type="submit" name="submit" style="margin-left: 419px;" value="GO" class="submit"/>
      </form>
  </div>

<%
    request.setCharacterEncoding("utf-8");
    Object mess = request.getAttribute("mess");
    if (mess != null) {
  %>
<script type="text/javascript">
        alert("<%=mess.toString() %>");
    </script>
    <%
      }
    %>
  </div>
  </body>
</html>
