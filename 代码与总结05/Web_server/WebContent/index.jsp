<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<%
		try {
			Thread.sleep(2000);
		}catch(Exception e) {
			
		}
		String username = request.getParameter("username");
		String age = request.getParameter("age");
		System.out.println(request.getMethod()+"请求 "+username);
		out.print("我叫" + username + ", 年方" + age);
	%>
</body>
</html>