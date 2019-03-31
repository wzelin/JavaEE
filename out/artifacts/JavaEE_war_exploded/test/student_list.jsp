<%@ page import="java.util.List" %>
<%@ page import="com.xxx.javaee.bean.Student" %><%--
  Created by IntelliJ IDEA.
  User: aa
  Date: 2018/12/4
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>显示学生信息</title>
</head>
<body>
<table border="1px">
    <tr>
        <td>学生ID</td>
        <td>学生姓名</td>
        <td>学生年龄</td>
        <td>学生性别</td>
        <td>学生学历</td>
        <td>学生爱好</td>
        <td>自我简介</td>
        <td>删除操作</td>
    </tr>
<%
    //这里面是JAVA代码
    List<Student> list = (List<Student>)request.getAttribute("list");
    for(Student s:list){

%>
    <tr>
        <td><%= s.getId()%></td>
        <td><%= s.getName()%></td>
        <td><%= s.getAge()%></td>
        <td><%= s.getSex()%></td>
        <td><%= s.getEdu()%></td>
        <td><%= s.getFavs()%></td>
        <td><%= s.getDesc()%></td>
        <td><a href="javascript:void(0)" onclick="fn(<%= s.getId()%>)">删除</a></td>
    </tr>
  <%
    }
    %>
</table>
<%--这里是HTML代码--%>
</body>
<script>
    function fn(id) {
        var br = confirm("确认删除？");
        if(br == true){
            location.href = "/test6?id="+id;
        }
    }

</script>
</html>
