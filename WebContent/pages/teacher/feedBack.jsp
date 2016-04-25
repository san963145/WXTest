<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
  <head>
 
    <base href="${sessionScope.basePath}">
    <c:if test="${empty sessionScope.curUser}">
     <script>
      location="../../index.jsp"
    </script>
    </c:if>

    <meta charset="UTF-8">
    <title>问题反馈子系统</title>
	 <!-- 禁缓存-->
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
    <!-- 响应屏幕宽度	-->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

    <!-- Bootstrap 3.3.4 框架-->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    
    <script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.4 -->
    <script src="bootstrap/js/bootstrap.min.js"></script>

    <script src="pages/js/feedBack.js"></script>
   
    
</head>

<body onload="init()">

             <div>
               <h3 class="text-center">
                                                              问题反馈子系统
               </h3>
             </div>
			 
             <ul id="myTab" class="nav nav-pills col-xs-offset-1">
			 
			 
			      <!-- 1-->
                  <li class="active">
                     <a href="#home" data-toggle="tab">

					 首页
                     </a>
                   </li>
				   
			       
                   <!-- 4-->
                  <li class="dropdown">
                        <a href="#" id="myTabDrop2" class="dropdown-toggle" 
         data-toggle="dropdown">
                                                                                        系统 <b class="caret">
                       </b>
                       </a>
                      <ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop2">
                         <li><a href="pages/teacher/main.jsp">
                              返回上一级页面</a>
                          </li>
                            <li><a href="Logout" tabindex="-1">
                               退出系统
                               </a>
                             </li>
                        </ul>
                   </li>
                   
                 </ul>
			 <hr style="height:1px;border:none;border-top:1px solid #cccccc;" />
       
		
		<div id="myTabContent" class="tab-content">
             <div class="tab-pane fade in active" id="home">
			     <div class="container">
				
				 <div class="row">
			       <div class="form-group col-xs-12">
					      <label >学生问题反馈</label>				       
				          <textarea  id="textarea" class="form-control"  rows="14"></textarea>
				   </div>
				 </div>   
                 <div class="row">
				       
				       <div class="col-xs-4">
					      <button onclick="refresh()" type="submit" class="btn btn-primary btn-block btn-flat">刷新</button>
				      </div >  				     
				 </div>				 
			    </div>
			</div>
			
			
		

	</div>		
 </body>
</html>
<!--  