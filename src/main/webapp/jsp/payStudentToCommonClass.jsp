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
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $(document).on('click','.givemoney',function () {
            var $cci = $('#commonClassId').val();
            var $sid = $(this).parents('tr').children('td:eq(0)');
            var $commonSurplus = $(this).parents('tr').children('td:eq(6)');
            var $commonAllCost = $(this).parents('tr').children('td:eq(7)');
            $.ajax({
                type:"get",
                url:"${pageContext.request.contextPath }/getMoney.do",
                data:"commonClassId="+$cci,
                dataType:"text",
                contentType : "application/text;charset=utf-8",
                success:function (result) {
                    var money = prompt("请输入缴费金额",result);
                    if(money!=null){
                        var reg = /^-?\d*\.?\d*$/;
                        if(money.trim().length==0){
                            alert("请输入金额！");
                        }else if(!reg.test(money)){
                            alert("请输入正确金额！");
                        }else if(money=="."||money==".0"||money=="0."){
                            alert("请输入正确金额！");
                        } else {
                            $.ajax({
                                type:"get",
                                url:"${pageContext.request.contextPath }/payMoney.do",
                                data:{"commonClassId":$cci,"studentId":$sid.html(),"money":money},
                                dataType:"json",
                                contentType : "application/json;charset=utf-8",
                                success:function (result) {
                                    $commonSurplus.html(result.commonSurplus);
                                    $commonAllCost.html(result.commonAllCost);
                                }
                            })
                        }
                    }
                }
            })
        })
        
        
        $(document).on('click',".deleteStudent",function () {
            var r = confirm('确认删除学生吗？');
            if(!r){
                return false;
            }
            var $cci = $('#commonClassId').val();
            var $sid = $(this).parents('tr').children('td:eq(0)');
            var $tr =  $(this).parent().parent();
            $.ajax({
                type:"get",
                url:"${pageContext.request.contextPath }/deleteStudentToClass.do",
                data:{"commonClassId":$cci,"studentId":$sid.html()},
                dataType:"text",
                contentType : "application/json;charset=utf-8",
                success:function (count) {
                    if(count=="1"){
                        $tr.remove();
                        alert("删除成功！");
                    }else {
                        alert("删除失败！");
                    }

                }
            })
        })


    })
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
            <form action="${pageContext.request.contextPath }/queryStudentFromCommonClass.do" method="post" name="form1" id="form1">
                <input type="hidden" name="commonClassId" id="commonClassId" value="${param.commonClassId}">
                <input type="hidden" name="commonClassName" value="${param.commonClassName}">
                <ul class="seachform">
                    <li><label>学生姓名</label><input type="text" name="studentName" size="10" value="${param.studentName}" class="scinput" /></li>
                    <li><label>&nbsp;</label><input type="submit" class="scbtn" value="查询"/>&nbsp;<input type="button" name="button" value="返回" class="scbtn" onclick="javascript:location.href='${pageContext.request.contextPath }/queryCommonClassInfo.do'"/></li>
                </ul>
            </form>
            <table class="tablelist">
                <thead>
                <tr>
                    <th>学生id</th>
                    <th>家长手机号</th>
                    <th>学生姓名</th>
                    <th>家长姓名</th>
                    <th>性别</th>
                    <th>出生日期</th>
                    <th>剩余学费</th>
                    <th>总学费</th>
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
                            <td>${studentInfo.commonSurplus}</td>
                            <td>${studentInfo.commonAllCost}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${studentInfo.status==1}">正常</c:when>
                                    <c:otherwise>冻结</c:otherwise>
                                </c:choose>
                            </td>
                            <td><a href="#" class="givemoney">缴费</a>
                                <a href="#" class="deleteStudent">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${fn:length(list)==0}">
                    <tr align="center"><td colspan="10">没有数据</td></tr>
                </c:if>
                </tbody>
            </table>

        </div>
    </div>

    <div class="pagin">
        <div class="message">共<i class="blue">${totalPageCount}</i>页，当前显示第&nbsp;<i class="blue">${currentPageNo}&nbsp;</i>页</div>
        <form action="${pageContext.request.contextPath }/queryStudentFromCommonClass.do" method="post">
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
                    <li class="paginItem"><a href="${pageContext.request.contextPath }/queryStudentFromCommonClass.do?currentPageNo=1&pageSize=${param.pageSize}&commonClassId=${param.commonClassId}&studentName=${param.studentName}&commonClassName=${param.commonClassName}">首页</a></li>
                    <li class="paginItem"><a href="${pageContext.request.contextPath }/queryStudentFromCommonClass.do?currentPageNo=${currentPageNo-1}&pageSize=${param.pageSize}&commonClassId=${param.commonClassId}&studentName=${param.studentName}&commonClassName=${param.commonClassName}" class="pia">上一页</a></li>
                </c:if>
                <c:if test="${currentPageNo<=1}">
                    <li class="paginItem"><a href="javascript:;">首页</a></li>
                    <li class="paginItem"><a href="javascript:;" class="pia">上一页</a></li>
                </c:if>
                <c:if test="${currentPageNo<totalPageCount}">
                    <li class="paginItem"><a href="${pageContext.request.contextPath }/queryStudentFromCommonClass.do?currentPageNo=${currentPageNo+1}&pageSize=${param.pageSize}&commonClassId=${param.commonClassId}&studentName=${param.studentName}&commonClassName=${param.commonClassName}" class="pia">下一页</a></li>
                    <li class="paginItem"><a href="${pageContext.request.contextPath }/queryStudentFromCommonClass.do?currentPageNo=${totalPageCount}&pageSize=${param.pageSize}&commonClassId=${param.commonClassId}&studentName=${param.studentName}&commonClassName=${param.commonClassName}">尾页</a></li>
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
