var x,y,z;
var interval;
var timer;
var watch;
var signTimeLimit;
function init()
{
	if(window.XMLHttpRequest)
    {
	    x=new XMLHttpRequest();
	    y=new XMLHttpRequest();
	    z=new XMLHttpRequest();
	}
	else
    {
		x=new ActiveXObject("Microsoft.XMLHTTP");
		y=new ActiveXObject("Microsoft.XMLHTTP");
		z=new ActiveXObject("Microsoft.XMLHTTP");
    }
	
}
function check()
{
	var randNum=document.getElementById("randNum").value;	
	var reg="^[0-9]*$";
	var r=randNum.match(reg);
	if(randNum=="")
    {
		alert("请输入随机数!");
		return false;
    }	
	else if(r==null)
	{
		alert("随机数格式错误!");
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
		var randNum=document.getElementById("randNum").value.trim();
		  x.open("GET","SetRandNum?randNum="+randNum,true);
		  x.onreadystatechange=update;
		  x.send(); 		  
	}		  
}
function update()
{
	if(x.readyState==4 && x.status==200)
	{		
		if(x.responseText=="error")
		{
			alert("请勿多次设置随机数！");
		}
		else if(x.responseText=="signed")
		{
			if(confirm("确定重新签到？"))
			{
				  var randNum=document.getElementById("randNum").value.trim();
				  x.open("GET","SetRandNum?randNum="+randNum+"&reSign=1",true);
				  x.onreadystatechange=update;
				  x.send(); 
			}
		}
		else    
		{
			//document.getElementById("textarea").innerHTML+=x.responseText;
			signTimeLimit=parseInt(x.responseText)*60;
			var timeLimit=signTimeLimit;
			watch=setInterval("cal()",1000);
			getStudentSignList();
			interval=setInterval("getStudentSignList()",2000);
			timer=setTimeout(getAbsenceList, timeLimit*1000);
			document.getElementById("textarea").innerHTML="";
			ocument.getElementById("textarea2").innerHTML="";
			alert("设置成功！");
		}

	}
}

function getStudentSignList()
{		
		y.open("GET","GetStudentSignList?sign=1",true);
		y.onreadystatechange=getStudentSignListResult;
		y.send();
}
function getStudentSignListResult()
{
	var list=new Array();
	list=y.responseText.split("#");
	for(i=0;i<list.length;i++)
	{
		if(document.getElementById("textarea").innerHTML.indexOf(list[i])<0)
		{
			document.getElementById("textarea").innerHTML+=(list[i]);
			document.getElementById("textarea").innerHTML+='\r\n';
		}		
	}
}
function getAbsenceList()
{
	z.open("GET","GetStudentSignList?sign=2",true);
	z.onreadystatechange=getAbsenceListResult;
	z.send();
}
function getAbsenceListResult()
{
	var list=new Array();
	list=z.responseText.split("*");
	if(list[0]!="-")
	{
		var array=new Array();
		array=list[0].split("#");
		document.getElementById("textarea").innerHTML="";
		for(i=0;i<array.length;i++)
		{
			document.getElementById("textarea").innerHTML+=(array[i]);
			document.getElementById("textarea").innerHTML+='\r\n';
		}
	}
	if(list[1]!="-")
	{
		var array=new Array();
		array=list[1].split("#");
		document.getElementById("textarea2").innerHTML="";
		for(i=0;i<array.length;i++)
		{
			document.getElementById("textarea2").innerHTML+=(array[i]);
			document.getElementById("textarea2").innerHTML+='\r\n';
		}
	}
	
}
function stopSign()
{
	if(confirm("确定终止签到？"))
	{
		x.open("GET","StopSign",true);
		x.onreadystatechange=stopSignResult;
		x.send();
	}	
}
function stopSignResult()
{
	if(x.responseText=="1")
	{
		document.getElementById("watch").innerHTML=("签到已终止!");
		clearInterval(interval);
		clearInterval(watch);
		clearTimeout(timer);		
		getAbsenceList();		
		
	}
}
function cal()
{
	signTimeLimit--;
	if(signTimeLimit==0)
	{
		document.getElementById("watch").innerHTML=("签到已结束!");
		clearInterval(watch);
	}
	else
	{
		document.getElementById("watch").innerHTML=("签到剩余时间:"+signTimeLimit+"秒");
	}	
}