<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>投票结果</title>
</head>
<body>

<%
    Map<String,String> map =
            (Map<String,String>)application.getAttribute("vote");
    if(map == null){
        map = new HashMap<String,String>();
    }
    Set<String> keySet = map.keySet();
    Map<String,Integer> rs = new HashMap<String,Integer>();
%>
<h1><%=map.size()%>人参与投票</h1>
<h1>投票详情</h1>
<%
    for(String key : keySet){
        String v = map.get(key);
        if(rs.get(v) == null){
            rs.put(v,1);
        }else {
            rs.put(v,rs.get(v)+ 1);
        }
%>
<h5><%=key%>：<%= v%></h5>
<%
    }
%>

<h1>投票结果</h1>
<%
    Set<String> rsSet = rs.keySet();
    for(String key: rsSet){
%>
<h5><%=key%>：<%= rs.get(key)%></h5>
<%
    }
%>
</body>
</html>
