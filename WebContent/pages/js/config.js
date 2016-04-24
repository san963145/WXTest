
var x;

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
		alert("系统已设定为答题模式!");
	}
	else if(x.responseText=="option3")
	{
		document.getElementById("curMode").innerHTML="<b>系统当前为简答题模式</b>";
		alert("系统已设定为问题反馈模式!");
	}
}
function clearMode()
{
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
function clearData()
{
	if(confirm("确定进行系统重置？"))
	{
		x.open("GET","ClearDate",true);
		x.onreadystatechange=clearDataResult;
		x.send();
	}
}
function clearDataResult()
{
	if(x.responseText=="1")
	{
		alert("系统数据已清空!");
	}
}