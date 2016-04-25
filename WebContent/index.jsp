<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
   String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
   session.setAttribute("basePath", basePath);   
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="${sessionScope.basePath }>">
    <meta charset="UTF-8">
    <title>课堂互动系统</title>
	<!-- 禁缓存-->
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
    <!-- Tell the browser to be responsive to screen width 响应屏幕-->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

    <!-- Bootstrap 3.3.4 -->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome Icons -->
    <link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="plugins/iCheck/square/blue.css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  
   <script charset="UTF-8">  
    var flag=0;
	function check()
	{
		if(flag==1)
		{
			return false;
		}
		var userID=document.getElementById("userID").value;
		var password=document.getElementById("password").value;
		if(userName=="")
	    {
			alert("请输入用户名!");
			return false;
	    }
		else if(password=="")
		{
			alert("请输入密码!");
			return false;
		}
		else
		{
			return true;
		}
	}
	
	function register()
	{
		flag=1;
		location="register.jsp";
	}
	
  </script>
  
  <body class="login-page">
    <div class="login-box">
      <div class="login-logo">
        <b>课堂互动系统</b>
      </div>
	  <!-- /.login-logo -->
      <div class="login-box-body">
        
        <form action="Login" method="post" onsubmit="return check()">
          <div class="form-group has-feedback">
            <input type="text" class="form-control" id="userID" name="userID" placeholder="用户名">
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input type="password" class="form-control" id="password" name="password" placeholder="密码">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          
		  <div class="row">            
			<div class="col-xs-4">
              <button type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
            </div><!-- /.col -->           
            
          </div>	           
        </form>

      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->


    <!-- jQuery 2.1.4 -->
    <script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.4 -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <!-- iCheck -->
    <script src="plugins/iCheck/icheck.min.js"></script>
    
  </body>
  <c:if test="${not empty result}">
     <c:if test="${result=='2'}">
         <script charset="UTF-8">
            alert("用户名不存在或密码错误！");
         </script>
     </c:if>
     <c:if test="${result=='busy'}">
         <script charset="UTF-8">
            alert("系统忙！");
         </script>
     </c:if>
  </c:if>

</html>
 
<!--  