<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 

<title>Movimientos On-line</title>
	<!--<link runat="server" href="../css/estilos.css" rel="stylesheet" type="text/css" />-->
		
	<style>
	body {
	background-color: #3D4751;
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	}
		
	
	.Mensaje {
	font-size: 16px;
	color: #699CDB;
	font-family:Verdana, Arial, Helvetica, sans-serif;
	
}


#peg:hover
	{	cursor: pointer;	}

     .parrafo1 {font-size: 18px; color: #699CDB; font-family: Verdana, Arial, Helvetica, sans-serif; font-weight: bold; }

    .Estilo1 {font-size: 18px; color: #699CDB; font-family: Verdana, Arial, Helvetica, sans-serif; font-weight: bold; }
    </style>
	<script language="JavaScript">
<!--


function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}

function MM_goToURL() { //v3.0
  var i, args=MM_goToURL.arguments; document.MM_returnValue = false;
  for (i=0; i<(args.length-1); i+=2) eval(args[i]+".location='"+args[i+1]+"'");
}
//-->
</script> 

	
</head>

<body onload="MM_preloadImages('imagenes/bot-alta-o.png','imagenes/bot-modificar-o.png','imagenes/bot-cec-o.png')">

<center>


<div style="background-image:url('imagenes/encabezado.jpg');width:939px;height:100px;margin: 0 auto"></div>

<div style="width:930px;height:50px;padding-top:100px;">
		<p class="parrafo1" align="center"> Bienvenido al sistema Movimientos On-line de Mecanizadas </p>
	</div>



<!-- tabla de informacion -->
<table width="750" style="margin-top:70px;" height="50" bgcolor="#D3D8DC" bordercolor="#5F6B76" border="1" align="center" cellpadding="0" cellspacing="0"> 
	
	
	
	<tr  height="30">
	
	<td width="250" height="30"   >
							<img  src="imagenes/bot-alta.png" width="130" height="30" hspace="85" border="0"  align="middle" id="botAlta" onclick="MM_goToURL('parent','CondicionesUso.php');return document.MM_returnValue" onmouseover="MM_swapImage('botAlta','','imagenes/bot-alta-o.png',1)" onmouseout="MM_swapImgRestore()" /> 
	<!-- </a>  -->	</td> 
	
		  
	<td width="250"> 
	
		<img src="imagenes/bot-modificar.png"  width="130" height="30" hspace="85" border="0"  id="botModificar" onclick="MM_goToURL('parent','/Mecanizada/modificarDatos');return document.MM_returnValue" onmouseover="MM_swapImage('botModificar','','imagenes/bot-modificar-o.png',1)" onmouseout="MM_swapImgRestore()"  /> 
	<!-- </a>  -->	</td>
		
	<td width="250"> 	<img src="imagenes/bot-cec.png"  width="130" height="30" hspace="85" border="0" id="botCec" onclick="MM_goToURL('parent','/Mecanizada/confirmarUsuarios');return document.MM_returnValue" onmouseover="MM_swapImage('botCec','','imagenes/bot-cec-o.png',1)" onmouseout="MM_swapImgRestore()"  /></td>
  </tr>
</table>



<div id="peg" class="peg" style="background:url('imagenes/piePeg.jpg') no-repeat;width:939px;height:50px;margin-top:200px;" onclick="window.open('http://www.pegsoluciones.com');"></div>
	
</center>
</body>
</html>