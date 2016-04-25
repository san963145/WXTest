
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
  <head>
    <base href="${sessionScope.basePath}">
    <c:if test="${empty sessionScope.curUser}">
     <script>
      location="../../index.jsp"
    </script>
    </c:if>
    
    <meta charset="UTF-8">
    <title>课堂互动系统</title>
	 <!-- 禁缓存-->
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
    <!-- 响应屏幕宽度	-->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<!-- 添加的css-->
    <link rel="stylesheet" href="dist/css/my.css">
    <!-- Bootstrap 3.3.4 框架-->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <!-- FontAwesome 4.3.0 图标-->
    <link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
    <!-- Theme style 网站构造-->
    <link rel="stylesheet" href="dist/css/AdminLTE.min.css"> 
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">

    <!-- bootstrap wysihtml5 - text editor 文本格式编辑框-->
    <link rel="stylesheet" href="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->

<style>
    .box-set{width:90%;height:150%}
   
   </style><!-- 截止 -->

  </head>
  
  <body>
    
        <div>
               <h2 class="text-center">
                                                              选择课程
               </h2>
        </div>
        <br><br>
        <div class="container">
          <c:forEach items="${classMap}" var="entry">  
              <div class="row">
                 <div class="col-xs-8 col-xs-offset-2">
			        <a href="Login?classID=${entry.key}" class="btn btn-primary btn-block btn-flat">
			          <c:out value= "${entry.value}" ></c:out>
			        </a>
                 </div>           
              </div>	
              <br><br>
          </c:forEach>                        																	
		</div>

      <footer class="clear main-footer">      
		<p class="text-center">
        <strong>课堂互动系统  Version1.0</strong>
		</p>
      </footer>

    <!-- jQuery 2.1.4 -->
    <script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.4 -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <!-- FastClick 

    <script src="plugins/fastclick/fastclick.min.js"></script>
    <!-- AdminLTE App -->
    <script src="dist/js/app.min.js"></script>


   
  </body>
</html>

