<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 
		http://localhost:8080/L04_Web/test.jsp(手机不以这样访问)
		http://192.168.20.165:8080/L04_Web/test.jsp
	 -->
	 请求方式: <%=request.getMethod()%><br>
	 姓名:<%=request.getParameter("name") %>,年方:<%=request.getParameter("age") %>
</body>
</html>