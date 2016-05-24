var x;
var y;
var interval;
var testFlag=0;
function start()      //开始
{
	var title=document.getElementById("question").value;
	var index=title.indexOf('.');
	var number=title.substr(0,index);
	if(title=="选择题目")
	{
		alert("请选择题目,若无选项请先录入题目!");
		return;
	}

	if(window.XMLHttpRequest)
    {
	    x=new XMLHttpRequest();
	    y=new XMLHttpRequest();
	}
	else
    {
		x=new ActiveXObject("Microsoft.XMLHTTP");
		y=new ActiveXObject("Microsoft.XMLHTTP");
    }
	x.open("GET","TestControl?parameter=start&question="+number,true);
	x.onreadystatechange=startTestResult;
	x.send();
}
function startTestResult()
{
	if(x.readyState==4 && x.status==200)
	{
		//添加
		if(x.responseText=="success")      
		{
			interval=setInterval("getAnswer()",2000);
			testFlag=1;
			alert("已开始接收答案！");
		}
		else
		{
			alert("请勿重复启动！");
		}

	}
}
function getAnswer()    //刷新
{
	y.open("GET","TestControl?parameter=getAnswer",true);
	y.onreadystatechange=getAnswerResult;
	y.send();
}
function getAnswerResult()
{
	if(y.readyState==4 && y.status==200)
	{
		var list=new Array();
		var data=new Array();
		var array=new Array();
		array=y.responseText.split("-");
		data=array[0].split("#");
		list=array[1].split("#");
		if(data.length>0)
		{
			var s="在线人数:"+data[0]+",答题人数:"+data[1];
			document.getElementById("watch").innerHTML=s;
		}
		document.getElementById("textarea").innerHTML="";
		for(i=0;i<list.length;i++)
		{
			document.getElementById("textarea").innerHTML+=(list[i]);
			document.getElementById("textarea").innerHTML+='\r\n';
		}		
	}
}
function stop()    //刷新
{
	if(testFlag==0)
	{
		alert("答题未开始");
		return;
	}	
	if(confirm("确定终止签到？"))
	{
		clearInterval(interval);
		y.open("GET","TestControl?parameter=stop",true);
		y.onreadystatechange=stopTestResult;
		y.send();
	}
	
}
function stopTestResult()
{
	if(y.readyState==4 && y.status==200)
	{
		testFlag=0;
		alert("已关闭答题！");	
	}
}
