<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
		#iframeTop{
            width: 100%;
            height: 150px;
        }
        #iframeLeft{
            width: 9%;
            height: 400px;
            float: left;
        }
        #iframeContent{
            border: 2px solid blue;
            width: 90%;
            height: 1000px;
        }
</style>
</head>


<frameset rows="88,*" cols="*" frameborder="no" border="0" framespacing="0">
    <frame src="top.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
    <frameset cols="187,*" frameborder="no" border="0" framespacing="0">
        <frame src="left.jsp" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame" />
        <frame src="content.jsp" name="rightFrame" id="rightFrame" title="rightFrame" />
    </frameset>
</frameset>
<body>

<jsp:include page="/jsp/teaManAccess.jsp"></jsp:include>

<%--<div>
    <iframe id="iframeTop" name="iframeTop" frameborder="0" src="top.jsp"></iframe>
    <iframe id="iframeLeft" name="iframeLeft" frameborder="0" src="left.jsp"></iframe>
    <iframe id="iframeContent" name="iframeContent" frameborder="0" src="content.jsp"></iframe>
</div>--%>
</body>
</html>