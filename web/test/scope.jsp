<%--
  Created by IntelliJ IDEA.
  User: aa
  Date: 2018/12/5
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>作用域对象</title>
</head>
<body>

<%
    ServletContext context = request.getServletContext();

%>
<h1>context:<%=context.getAttribute("context")%></h1>
<h1>application:<%=application.getAttribute("context")%></h1>
<h1>session:<%=session.getAttribute("session")%></h1>
<h1>request:<%=request.getAttribute("requst")%></h1>
</body>
</html>
