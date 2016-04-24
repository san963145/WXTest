var x;
function init()
{
	if(window.XMLHttpRequest)
    {
	    x=new XMLHttpRequest();
	}
	else
    {
		x=new ActiveXObject("Microsoft.XMLHTTP");
    }
	x.open("GET","FeedBack",true);
	x.onreadystatechange=result;
	x.send();
	
}
function refresh()
{

	x.open("GET","FeedBack",true);
	x.onreadystatechange=result;
	x.send();	
}
function result()
{
	if(x.readyState==4 && x.status==200)
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
}