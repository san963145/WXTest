<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
   String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
   String userName=(String)request.getAttribute("userName");
%>
<html>
<head>
<meta charset="UTF-8">
<title>登录成功</title>
</head>
<body class="login-page">
<div style="text-align: center">
     <h1>
        <b> 登录成功:<%=userName %></b>
      </h1>

</div>
</body>
</html>
<!--</html>