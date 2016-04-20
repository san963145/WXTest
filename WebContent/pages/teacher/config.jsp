<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 
%>

<html>
  <head>
    <base href="<%=basePath%>">
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
</head>
<script>

function x0()
{
	$(".a2").removeClass("show");
	$(".a2").addClass("hidden");
	$(".a1").addClass("show");
	$(".a1").removeClass("hidden");
	$("#li01").addClass("active");
	$("#li02").removeClass("active");
	$("#li03").removeClass("active");
	
}
function y()
{
	$(".a1").removeClass("show");
	$(".a1").addClass("hidden");
	$(".a2").addClass("show");
	$(".a2").removeClass("hidden");
	$("#li02").addClass("active");
	$("#li01").removeClass("active");
	$("#li03").removeClass("active");
}
function z()
{
	$("#li03").addClass("active");
	$("#li01").removeClass("active");
	$("#li02").removeClass("active");
	$(".a1").removeClass("show");
	$(".a1").addClass("hidden");
	$(".a2").addClass("hidden");
	$(".a2").removeClass("show");
}
function setMode()
{
	var s=$("input[type=radio]:checked").val()
	if(s)
	{
		if(window.XMLHttpRequest)
	    {
		    x=new XMLHttpRequest();
		}
		else
	    {
			x=new ActiveXObject("Microsoft.XMLHTTP");
	    }
		x.open("GET","SetMode?mode="+s,true);
		x.onreadystatechange=update;
		x.send();
	}	
	else
	{
		alert("请先选择模式!");
	}
}
function update()
{
	if(x.responseText=="option1")
	{
		document.getElementById("curMode").innerHTML="<b>系统当前为签到模式</b>";
		alert("系统已设定为签到模式!");
	}
	else if(x.responseText=="option2")
	{
		document.getElementById("curMode").innerHTML="<b>系统当前为选择题模式</b>";
		alert("系统已设定为选择题模式!");
	}
	else if(x.responseText=="option3")
	{
		document.getElementById("curMode").innerHTML="<b>系统当前为简答题模式</b>";
		alert("系统已设定为简答题模式!");
	}
}
function clearMode()
{
	if(window.XMLHttpRequest)
    {
	    x=new XMLHttpRequest();
	}
	else
    {
		x=new ActiveXObject("Microsoft.XMLHTTP");
    }
	x.open("GET","SetMode?mode=clear",true);
	x.onreadystatechange=update2;
	x.send();
}
function update2()
{
	if(x.responseText=="4")
	{
		document.getElementById("curMode").innerHTML="<b>当前未进行模式设定</b>";
		alert("已取消模式设定!");
	}
}
function getMode()
{
	if(window.XMLHttpRequest)
    {
	    x=new XMLHttpRequest();
	}
	else
    {
		x=new ActiveXObject("Microsoft.XMLHTTP");
    }
	x.open("GET","SetMode?mode=empty",true);
	x.onreadystatechange=update3;
	x.send();
}
function update3()
{
	if(x.responseText=="1")
	{
		document.getElementById("curMode").innerHTML="<b>系统当前为签到模式</b>";
	}
	else if(x.responseText=="2")
	{
		document.getElementById("curMode").innerHTML="<b>系统当前为选择题模式</b>";
	}
	else if(x.responseText=="3")
	{
		document.getElementById("curMode").innerHTML="<b>系统当前为简答题模式</b>";
	}
	else
	{
		document.getElementById("curMode").innerHTML="<b>当前未进行模式设定</b>";
	}
}
</script>
<body onload=getMode()> 
             <div>
               <h2 class="text-center">
                                                             系统管理
               </h2>
             </div>
			 
			
          <div class="box-body">

			   
				
             <div class="container">

			    <div class="row">
				   <div class="col-md-4 col-md-offset-2">
			        <ul class="nav nav-pills">
                       <li id="li01" onclick="x0()" class="active"><a><b>模式设定</b></a></li>
                       <li id="li02" onclick="y()"><a><b>数据管理</b></a></li>
                       <li id="li03" onclick="z()"><a><b> 其他</b></a></li>
                     </ul>
                   </div>
			    </div>
				<br>
				
				<div class="row show a1" id="radio1">
				  <div class="col-md-4 col-md-offset-2"  id="curMode">
				  
                    <b>当前未进行模式设定</b>
                  </div>				  
				</div>
				<br>
				<div class="row show a1" id="radio2">
				  <div class="col-md-4 col-md-offset-2">
  
                    <input type="radio" name="optionsRadios"  value="option1">
                    <b>签到模式</b>
                  </div>				  
				</div>
				
				<div class="row show a1" id="radio3">
				  <div class="col-md-4 col-md-offset-2">
  
                    <input type="radio" name="optionsRadios"  value="option2">
                    <b>选择题模式</b>
                  </div>				  
				</div>
				
				<div class="row show a1">
				  <div class="col-md-4 col-md-offset-2">
  
                    <input type="radio" name="optionsRadios"  value="option3">    
					<b>简答题模式</b>
                  </div>				  
				</div>
                 
                                                                      		 				  
				<br>
				<div class="row show a1">
				
					<div class="col-xs-4 col-md-offset-1">
						<button onclick="setMode()" class="btn btn-danger btn-block btn-flat">确定</button>
					</div>	
					<div class="col-xs-4 col-md-offset-1">
						<button onclick="clearMode()" class="btn btn-danger btn-block btn-flat">取消设定</button>
					</div>	
                     					
				</div>	
				<br>
				<div class="row hidden a2">
	
                     					
				</div>
									
		     </div>
	   </div>
			
 </body>
</html>
<!--