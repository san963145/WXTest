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
    <title>系统管理</title>
	 <!-- 禁缓存-->
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
    <!-- 响应屏幕宽度	-->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    
    <script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.4 -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    
    <script src="pages/js/config.js"></script>
    
    <script>
function check()
	{
		var userID=document.getElementById("userID").value.trim();
		var password=document.getElementById("password").value.trim();
		var rePassword=document.getElementById("rePassword").value.trim();
		var className=document.getElementById("class").value.trim();
		if(userID=="")
	    {
			alert("请输入用户ID!");
			return false;
	    }
		else if(password=="")
		{
			alert("请输入密码!");
			return false;
		}
		else if(password!=rePassword)
		{
			alert("密码不一致!");
			return false;
		}
		else if(className=="课程")
		{
			alert("请选择课程!");
			return false;
		}
		else
		{
			return true;
		}
	}
</script>
</head>

<body onload=getMode()> 

             <div>
               <h2 class="text-center">
                                                             系统管理
               </h2>
             </div>
			 
			


			   
				
             <div class="container">

              
			    <ul id="myTab" class="nav nav-pills col-xs-offset-1">
			 
			        <!-- 1-->
                  <li>
                     <a href="#home" data-toggle="tab">
                                                                                    模式设定
                     </a>
                   </li>
                   
                   <!-- 2-->
                 <c:if test="${not empty sessionScope.role}">
                     <c:if test="${role=='1'}">
                      <li class="dropdown active">
                         <a href="#addUser"  data-toggle="tab">
                                                                                           添加用户
                       
                         </a>
                     
                       </li>
                     </c:if>
                 </c:if>
  
				   
                  
                  
                   <!-- 3-->
                  <li class="dropdown">
                        <a href="#" id="myTabDrop2" class="dropdown-toggle" 
         data-toggle="dropdown">
                                                                                        其他 <b class="caret">
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
			 <div id="home" class="tab-pane fade">
				<div class="row" id="radio1">
				  <div class="col-md-4 col-md-offset-2"  id="curMode">
				  
                    <b>当前未进行模式设定</b>
                  </div>				  
				</div>
				<br>
				<div class="row" id="radio2">
				  <div class="col-md-4 col-md-offset-2">
  
                    <input type="radio" name="optionsRadios"  value="option1">
                    <b>签到模式</b>
                  </div>				  
				</div>
				
				<div class="row" id="radio3">
				  <div class="col-md-4 col-md-offset-2">
  
                    <input type="radio" name="optionsRadios"  value="option2">
                    <b>答题模式</b>
                  </div>				  
				</div>
				
				<div class="row">
				  <div class="col-md-4 col-md-offset-2">
  
                    <input type="radio" name="optionsRadios"  value="option3">    
					<b>问题反馈模式</b>
                  </div>				  
				</div>
              
                                                                      		 				  
				<br>
				<div class="row show a1">
				
					<div class="col-xs-4 col-md-offset-1">
						<button onclick="setMode()" class="btn btn-primary btn-block btn-flat">确定</button>
					</div>	
					<div class="col-xs-4 col-md-offset-1">
						<button onclick="clearMode()" class="btn btn-primary btn-block btn-flat">取消设定</button>
					</div>	
                     					
				</div>	
			  </div> 
			  
			  <c:if test="${not empty sessionScope.role}">
                     <c:if test="${role=='1'}">
                      
                      <div id="addUser" class="login-box-body tab-pane fade in active">
				    <h3 class="text-center"> 添加助教信息</h3>
	                <form action="AddUser" method="post" onsubmit="return check()">
        <div class="form-group has-feedback">
            <input type="text" class="form-control" id="userID" name="userID" placeholder="登录ID">
            <span class="glyphicon glyphicon-tag form-control-feedback"></span>
          </div>
          
    
          <div class="form-group has-feedback">
            <input type="password" class="form-control" id="password" name="password" placeholder="密码">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          
          <div class="form-group has-feedback">          
            <input type="password" class="form-control" id="rePassword" name="rePassword" placeholder="确认密码">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          
          
          <div class="form-group has-feedback">                       
								<select class="form-control" id="class" name="class" style="width: 100%;">								
								  <option selected="selected">课程</option>		
								    <c:forEach items="${sessionScope.classMap}" var="entry">    
                                              <option>${entry.value}</option>
                                   </c:forEach>  	                	         											 							   
								</select>
          </div>
          
          
          
		  <div class="row">            
			<div class="col-xs-4">
              <button type="submit" class="btn btn-primary btn-block btn-flat">提交</button>
            </div><!-- /.col -->
          </div>
                     
        </form>
	              
                     					
				</div>
                      
                     </c:if>
                 </c:if>
			  
				
			
				</div>	
					
									
		     </div>
	  
			
 </body>
 <c:if test="${not empty result}">
     <c:choose>
     <c:when test="${result=='1'}">
         <script charset="UTF-8">
            alert("添加成功！");
         </script>
     </c:when>
     <c:otherwise>
         <script charset="UTF-8">
                alert("添加失败！");
         </script>
     </c:otherwise>
     </c:choose>
  </c:if>
</html>
<!--  