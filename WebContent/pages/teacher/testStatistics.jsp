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

    <script src="pages/js/test.js"></script>
   
<script>
function check()
	{
	var title1=document.getElementById("title1").value.trim();
	var title2=document.getElementById("title2").value.trim();
	var title3=document.getElementById("title3").value.trim();
	var answer1=document.getElementById("answer1").value.trim();
	var answer3=document.getElementById("answer3").value.trim();
	var s=document.getElementsByName("checkbox");
	var flag=0;
	for( var i = 0; i < s.length; i++ )
    {
         if ( s[i].checked ){
              flag=1;
              break;
           }
    }
	if(title1!=""||title2!=""||title3!="")
    {
	    if(answer1!="答案"||answer3!=""||flag==1)
		{
		  return true; 
		}
		else
		{
		  alert("请输入答案!");
		  return false;
		}			
    }
	else
	{
	   alert("请输入标题!");
		return false;
	}
	}
</script>    
</head>

<body>

             <div>
               <h3 class="text-center">
                                                              课堂答题子系统
               </h3>
             </div>
			 
             <ul id="myTab" class="nav nav-pills col-xs-offset-1">
			 
			 
			      <!-- 1-->
                  <li>
                     <a href="#home" data-toggle="tab">
                                                                            应答
                     </a>
                   </li>
				   
			       <!-- 2-->
                  <li class="dropdown">
                       <a href="#" id="myTabDrop1" class="dropdown-toggle" 
         data-toggle="dropdown">
                                                                                         题目录入<b class="caret">
                       </b>
                      </a>
                      
                      <ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1">
                         <li><a href="#singleChoose" tabindex="-1" data-toggle="tab">
                                                                                          单选题</a>
                          </li>
						  <li><a href="#multiChoose" tabindex="-1" data-toggle="tab">
                                                                                          多选题</a>
                          </li>
						  <li><a href="#shortAnswer" tabindex="-1" data-toggle="tab">
                                                                                          简答题</a>
                          </li>
                            
                        </ul>
                  </li>
				   
				   
				   
				  <!--3-->
                  <li class="dropdown active">
                       <a href="#" id="myTabDrop3" class="dropdown-toggle" 
         data-toggle="dropdown">
                                                                                        统计<b class="caret">
                       </b>
                      </a>
                      
                      <ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop3">
                         <li><a href="#statistics" tabindex="-1" data-toggle="tab">
                                                                                          
                                                                                      学生答案汇总</a>
                          </li>
						  <li><a href="TestStatistics">
                                                                                          获取最新数据</a>
                          </li>
                            
                        </ul>
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
             <div class="tab-pane fade" id="home">
			     <div class="container">
				 <div class="row">
			       <div class="form-group col-xs-8">
					      <select class="form-control" id="question" name="question" style="width: 100%;">								
								  <option selected="selected">选择题目</option>	
								  
								  	<c:forEach items="${questionList}" var="value">
                                           <option>${value}</option>  
                                    </c:forEach>						  
								   
							       								 							   
						</select>
				   </div>
				   <div class="col-xs-4">
					      <button  onclick="start()" type="submit" class="btn btn-primary btn-block btn-flat">开始</button>
				   </div>
				 </div>
				 <div class="row">
			       <div class="form-group col-xs-12">
					      <label >学生答题结果</label>				       
				          <textarea  id="textarea" class="form-control"  rows="14"></textarea>
				   </div>
				 </div>   
                 <div class="row">
				       <div class="col-xs-4">
					      <button onclick="stop()" type="submit" class="btn btn-danger btn-block btn-flat">结束答题</button>
				      </div >  
			             <h4 id="watch" class="col-xs-4"></h4>					     
				 </div>				 
			    </div>
			</div>
			
			<!-- singleChoose -->
			<div class="tab-pane fade" id="singleChoose">
			     <form action="AddQuestion" method="post" onsubmit="return check()" class="col-xs-10 col-xs-offset-1">
        <div class="form-group has-feedback">
            <input type="text" class="form-control" id="title1" name="title" placeholder="题目标题(必填)">
          </div>
          
    
          <div class="form-group has-feedback">
  		       
				          <textarea  id="content" name="content" class="form-control"  rows="6" placeholder="题目内容"></textarea>
          </div>
		  
		  <div class="form-group has-feedback">
		    <select class="form-control" id="answer1" name="answer" style="width: 100%;">								
								  <option selected="selected">答案</option>								  
								   <option>A</option>
								   <option>B</option>
								   <option>C</option>
								   <option>D</option>
							       <option>E</option>	
                                   <option>F</option>								   
						</select>
          

          </div>                     
		  <div class="row">            
			<div class="col-xs-4">
              <button type="submit" class="btn btn-primary btn-block btn-flat">提交</button>
            </div>
          </div>
                     
        </form>
			</div>
			
			<!-- multiChoose -->
			<div class="tab-pane fade" id="multiChoose">
			
			    <form action="AddQuestionMulti" method="post" onsubmit="return check()" class="col-xs-10 col-xs-offset-1">
        <div class="form-group has-feedback">
            <input type="text" class="form-control" id="title2" name="title" placeholder="题目标题(必填)">
          </div>
          
    
          <div class="form-group has-feedback">
  		       
				          <textarea  id="content" name="content" class="form-control"  rows="6" placeholder="题目内容"></textarea>
          </div>
		  
		  <div class="form-group has-feedback">
		    <label >答案</label>	
            <br>
                    <input type="checkbox" name="checkbox"  id="inlineCheckbox1" value="A" class="inline col-xs-offset-2"> A  


                     <input type="checkbox" name="checkbox" id="inlineCheckbox2" value="B" class="inline col-xs-offset-2"> B 


                     <input type="checkbox" name="checkbox" id="inlineCheckbox3" value="C" class="inline col-xs-offset-2"> C 

			        <input type="checkbox" name="checkbox" id="inlineCheckbox4" value="D" class="inline"> D 
			<br>
                   <input type="checkbox" name="checkbox" id="inlineCheckbox5" value="E" class="inline col-xs-offset-2"> E 
				    <input type="checkbox" name="checkbox" id="inlineCheckbox6" value="F" class="inline col-xs-offset-2"> F 

			</div>
			<div class="row">            
			<div class="col-xs-4">
              <button type="submit" class="btn btn-primary btn-block btn-flat">提交</button>
            </div>
          </div>
			 </form>
			</div>
			<!-- shortAnswer -->
			<div class="tab-pane fade" id="shortAnswer">
                <form action="AddQuestion" method="post" onsubmit="return check()" class="col-xs-10 col-xs-offset-1">
        <div class="form-group has-feedback">
            <input type="text" class="form-control" id="title3" name="title" placeholder="题目标题(必填)">
          </div>
          
    
          <div class="form-group has-feedback">
  		       
				          <textarea  id="content" name="content" class="form-control"  rows="6" placeholder="题目内容"></textarea>
          </div>
		  
		  <div class="form-group has-feedback">
            <input type="text" class="form-control" id="answer3" name="answer" placeholder="答案(必填)">
          </div>
		    
			<div class="row">            
			<div class="col-xs-4">
              <button type="submit" class="btn btn-primary btn-block btn-flat">提交</button>
            </div>
          </div>
			 </form>
			</div>
			
			<!-- statistics -->
			<div class="tab-pane fade in active" id="statistics">
			  <c:if test="${not empty qid}">
     
                   <div class="container">
			    <div class="row">
				  <div class="col-xs-10 col-xs-offset-1">
				    
				     ${table1}
				   
				  </div>
			     </div>  <!--row -->
                 <div class="row">
				  <div class="col-xs-10 col-xs-offset-1">
				   
				    ${table2}
				   
				  </div>
			     </div>  <!--row -->
                
    </div>   <!--container -->
               </c:if>
               <c:if test="${empty qid}">
     
                  <h4 class="text-center">
                                                              当前未发布题目！
               </h4>
               </c:if>
               
                
			</div>

	</div>		
 </body>
 <c:if test="${not empty result}">
     <c:choose>
     <c:when test="${result=='error'}">
         <script charset="UTF-8">
            alert("添加失败！");
         </script>
     </c:when>
     <c:otherwise>
         <script charset="UTF-8">
                alert("添加成功！");
         </script>
     </c:otherwise>
     </c:choose>
  </c:if>
</html>
