<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 
		http://192.168.10.165:8080/Web_Server/index.jsp?name=Tom&age=12
		1. 请求方式: GET/POST
		2. Hello Tom, 你的年龄是:12
	 -->
	 <%
	 	System.out.println("处理请求中....");
	 	Thread.sleep(2000);
	 %>
	请求方式: <%=request.getMethod()%><br>
	Hello <%=request.getParameter("name") %>, 你的年龄是:<%=request.getParameter("age") %>
</body>
</html>