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
    
</head>
<script>
function check()
{
	var randNum=document.getElementById("randNum").value;	
	if(randNum=="")
    {
		alert("请输入随机数!");
		return false;
    }	
	else
	{
		return true;
	}
}
function submit()
{
	if(check())
	{
		  if(window.XMLHttpRequest)
	      {
		    x=new XMLHttpRequest();
		  }
		  else
	      {
			x=new ActiveXObject("Microsoft.XMLHTTP");
	      }
		  var randNum=document.getElementById("randNum").value;
		  x.open("GET","SetRandNum?randNum="+randNum,true);
		  x.onreadystatechange=update;
		  x.send();
	}		  
}
function update()
{
	if(x.readyState==4 && x.status==200)
	{
		//添加
		if(x.responseText=="1")      
		{
			//document.getElementById("textarea").innerHTML+=x.responseText;
			alert("设置成功！");
		}
		else if(x.responseText=="2")
		{
			alert("请勿多次设置随机数！");
		}									
	}
}
function getStudentSignList()
{

		if(window.XMLHttpRequest)
	    {
		    x=new XMLHttpRequest();
		}
		else
	    {
			x=new ActiveXObject("Microsoft.XMLHTTP");
	    }
		x.open("GET","GetStudentSignList",true);
		x.onreadystatechange=refresh;
		x.send();

}
function refresh()
{
	var list=new Array();
	list=x.responseText.split("#");
	document.getElementById("textarea").innerHTML="";
	for(i=0;i<list.length;i++)
	{
		document.getElementById("textarea").innerHTML+=(list[i]);
		document.getElementById("textarea").innerHTML+='\r\n';
	}
}
function reset()
{
	if(confirm("确认进行系统重置？"))
	{
		if(window.XMLHttpRequest)
	    {
		    x=new XMLHttpRequest();
		}
		else
	    {
			x=new ActiveXObject("Microsoft.XMLHTTP");
	    }
		x.open("GET","ResetSignSystem",true);
		x.onreadystatechange=resetResult;
		x.send();
	}	
}
function resetResult()
{
	if(x.responseText=="1")
	{
		alert("系统数据已清空!");
	}
}
</script>
<body onload="getStudentSignList()">
             <div>
               <h2 class="text-center">
                                                              课堂签到子系统
               </h2>
             </div>
             <div>
             <div class="box-body">
                 
						<div class="row">
							<div class="col-md-6">
							    <div class="form-group">
								  <label for="studentID" class="col-sm-2 control-label">随机数</label>
								  <div class="col-sm-10">
									<input type="text" class="form-control" id="randNum" name="randNum" placeholder="设置随机数">									
								  </div>
								</div><!-- /.form-group -->
							</div><!-- /.col -->
							
							<div class="col-md-2 col-md-offset-2">
							  <button onclick="submit()" class="btn btn-primary btn-block btn-flat">提交</button>
							</div>
						</div><!-- /.row -->
					
					<div class="form-group">
				       <label >学生签到记录</label>				       
				       <textarea  id="textarea" class="form-control"  rows="12"></textarea>
				       
			        </div><!-- /.form-group -->
			        <br>
			        <div class="col-md-2 col-md-offset-2">
							  <button onclick="getStudentSignList()" class="btn btn-primary btn-block btn-flat">刷新</button>
					</div>
					<div class="col-md-2 col-md-offset-2">
							  <button onclick="reset()" class="btn btn-primary btn-block btn-flat">系统重置</button>
					</div>
				</div>
			</div>
			
 </body>
</html>
<!--