<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
  <head>
 
    <base href="${basePath}">
    <c:if test="${empty sessionScope.curUser}">
     <script>
      location="../../index.jsp"
    </script>
    </c:if>

    <meta charset="UTF-8">
    <title>课堂签到子系统</title>
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
    
    <script src="pages/js/sign.js"></script>
    
   
    
</head>

<body onload="init()">

             <div>
               <h3 class="text-center">
                                                              课堂签到子系统
               </h3>
             </div>
			 
             <ul id="myTab" class="nav nav-pills col-xs-offset-1">
			 
			        <!-- 1-->
                  <li class="active">
                     <a href="#home" data-toggle="tab">
                                                                                    签到
                     </a>
                   </li>
				   <!-- 2-->
                  <li class="dropdown">
                       <a href="#" id="myTabDrop1" class="dropdown-toggle" 
         data-toggle="dropdown">
                                                                                         统计<b class="caret">
                       </b>
                      </a>
                      
                      <ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1">
                         <li><a href="#statistics_first" tabindex="-1" data-toggle="tab">
                                                                                                   历史签到记录</a>
                          </li>
                            <li><a href="#statistics_second" tabindex="-1" data-toggle="tab">
                                                                                                              学生缺勤统计
                               </a>
                             </li>
                             <li><a href="SignPage">
                                                                                                             获取最新数据
                               </a>
                             </li>
                             
                        </ul>
                  </li>
                  
                   <!-- 3-->
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
						  <div class="col-xs-4 ">
						    <label class="control-label">随机数</label>
						  </div>
						</div><!-- /.row -->
						
						<div class="row">
						  <div class="col-xs-7">
						    <input class="form-control" type="text" id="randNum" name="randNum" placeholder="设置随机数">
						  </div>
																					
						  <div class="col-xs-4 ">
							  <button onclick="submit()" class="btn btn-primary btn-block btn-flat">提交</button>
						  </div>
						</div><!-- /.row -->
						
						<br>
					<div class="row">
					  <div class="form-group col-xs-7 ">
					      <label >学生签到记录</label>				       
				          <textarea  id="textarea" class="form-control"  rows="14"></textarea>
					  </div><!-- /.form-group -->
					   
					  <div class="form-group col-xs-5 ">
					    <label >未签到学生列表</label>				       
				        <textarea  id="textarea2" class="form-control"  rows="14"></textarea>			       				       
			         </div><!-- /.form-group -->
			        </div><!-- /.row -->
			        
			        <br>
			        <div class="row">
			          <div class="col-xs-3 ">
					     <button onclick="getStudentSignListResult()" class="btn btn-primary btn-block btn-flat">刷新</button>
					  </div>
			          <div class="col-xs-4 ">
					     <button onclick="stopSign()" class="btn btn-danger btn-block btn-flat">终止签到</button>
					  </div>
			          <div class="col-xs-5 ">
			             <h4 id="watch"></h4>					     
					  </div>
					</div><!-- /.row -->
					
					<br>

					
				</div>
			</div>
			
			
			
			<div class="tab-pane fade" id="statistics_first">
			   <div class="container">
			    <div class="row">
				  <div class="col-xs-10 col-xs-offset-1">
				  
				    ${signStatistics1_firstTable}
				   
				  </div>
			     </div>  <!--row -->
                 <div class="row">
				  <div class="col-xs-10 col-xs-offset-1">
				   
				   ${signStatistics1_secondTable}
				   
				  </div>
			     </div>  <!--row -->
                
    </div>   <!--container -->
			</div>
			
			<div class="tab-pane fade" id="statistics_second">
                <div class="container">
			    <div class="row">
				  <div class="col-xs-10 col-xs-offset-1">
				    
				    ${signStatistics2_firstTable}
				    
				  </div>
			     </div>  <!--row -->
                 <div class="row">
				  <div class="col-xs-10 col-xs-offset-1">
				   
				    ${signStatistics2_secondTable}
				   
				  </div>
			     </div>  <!--row -->
                
    </div>   <!--container -->
			</div>

	</div>		
 </body>
</html>

