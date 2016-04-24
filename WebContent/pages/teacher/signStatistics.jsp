<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>    
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
    
    <script src="plugins/chartjs/Chart.min.js"></script>
    
    <script src="plugins/chartjs/Chart.js"></script>
    
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
                  <li>
                     <a href="#home" data-toggle="tab">
                                                                                    签到
                     </a>
                   </li>
				   <!-- 2-->
                  <li class="dropdown active">
                       <a href="#" id="myTabDrop1" class="dropdown-toggle" 
         data-toggle="dropdown">
                                                                                         统计<b class="caret">
                       </b>
                      </a>
                      
                      <ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1">
                         <li><a href="SignStatistics" tabindex="-1" data-toggle="tab">
                                                                                                   历史签到记录</a>
                          </li>
                            <li><a href="#statistics_second" tabindex="-1" data-toggle="tab">
                                                                                                              学生缺勤统计
                               </a>
                             </li>
                             <li><a href="SignStatistics">
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
             <div class="tab-pane fade" id="home">
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
			
			
			
			<div class="tab-pane fade in active" id="statistics_first">
			   <div class="container">
			   <div class="row">
				  <div class="col-xs-10 col-xs-offset-1">
			      
				        <div class="box box-primary">
				            <div class="box-header">
				              <h4 class="box-title text-center"><small>历史Lesson出勤率</small></h4>
				            </div>
				            <!-- /.box-header -->
				            <div class="box-body">
				            	<canvas id="lineChart"></canvas>
				            </div>
				            
			            </div>   		     
				   
				  </div>
			     </div>  <!--row -->
			     
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
	
	<%
  ArrayList<ArrayList<String>> lists=(ArrayList<ArrayList<String>>)request.getAttribute("signStatistics1_secondTable_content");
  String lessons="";
  String datas="";
  for(int i=0;i<lists.size();i++)
  {
	  ArrayList<String>list=lists.get(i);
	  lessons+=("\""+list.get(0)+"\",");
	  String s=list.get(2);
	  s=s.substring(0,s.length()-1);
	  Double d=Double.parseDouble(s)/100.0;
	  String v=String.format("%.2f", d);
	  datas+=(v+",");
  }
  
%>
 <script>

	 var linedata = {
				labels : [<%=lessons%>],
				datasets : [
					{
						fillColor : "#8470ff",
						strokeColor : "#8470ff",
						pointColor : "#8470ff",
						pointStrokeColor : "#fff",
						data : [<%=datas%>]
					}
				]
			};
			var lineChartOptions = {
					   scaleShowGridLines : true,
				       scaleShowHorizontalLines: true,
				       scaleShowVerticalLines: true,
				        bezierCurve : false,
				        pointDot : true,
				        pointDotStrokeWidth : 1,
				       datasetStroke : true,
				       datasetFill : false,
					   responsive: true,

	     	
			}
			var lineChartCanvas = document.getElementById("lineChart").getContext("2d");
	     var lineChart = new Chart(lineChartCanvas).Line(linedata,lineChartOptions); 

      
 </script>
 </body>

</html>

