<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<head runat="server">
    <title>Usuarios por Convalidar</title>
	<script src="js/jquery-1.6.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" href="js/ordenable/jq.css" type="text/css" media="print, projection, screen">
	<link rel="stylesheet" href="js/ordenable/style.css" type="text/css" media="print, projection, screen">
	<script type="text/javascript" src="js/ordenable/jquery-latest.js"></script>
	<script type="text/javascript" src="js/ordenable/jquery.tablesorter.js"></script>
	<script type="text/javascript" src="js/ordenable/docs.js"></script>

	
	<style type="text/css">
	
	* {margin: 0 auto;}
	
	
	#grilla
	{ margin: 30 auto;
	  width: 600px;
	}

	
	</style>
	
	
	<script>

	var i;
	var j;
	var k;
	var vector;
	
	$(document).ready(function(){
			$("#tablaOrdenable").tablesorter();
		    vector= new Array();
			i=0;
			j=0;
			k=0;
		}
			
	);
	
		
	
	
		
	function agregar(id){
	
	if(document.getElementById(id).checked  == true)
		{vector[i] = id;
		i = i + 1 ;}
	else
	   vector[buscar(id)] = '';
	 
	 }
	 
	 
	function buscar(id){
	 
	 var pos = -1;
	
	 for(j=0; j <= i; j++)
	  { 
		if(vector[j] == id)
		  pos = j;
	  }
	  return pos;
	}
	
	
	function mostrarvector()
	{
	  var k=0;
	  for(k=0; k < i; k++)
    	{
		alert(vector[k]);
	    }
	}
	
	
	function procesar(valor)
	{
	
	  k=0;
	  	  
	  if(valor == 1)
	    if(confirm('Esta seguro que desea validar todos los seleccionados?'))
		    {
			document.getElementById('cargando').style.visibility = 'visible';
			for(k=0; k < i; k++)
			   if(vector[k] != '')
					$("#resul").load('procesarValidaciones.php?op=validar&id=' + vector[k]);
			}
	   
	   
	   
	  if(valor == 2)
	  	if(confirm('Esta seguro que desea descartar todos los seleccionados?'))
			{
			document.getElementById('cargando').style.visibility = 'visible';
			for(k=0; k < i; k++)
				if(vector[k] != '')
					$("#resul").load('procesarValidaciones.php?op=descartar&id=' + vector[k]);
			}
	  	  	  

	  setTimeout("location.reload()", 500);

	}
	
	
	function seleccionarTodos() {
	var i = 0;
	var pos = -1;
	var name= '';
		
	if(document.getElementById('selAll').className == 'desmarcados')
		{
		document.getElementById('selAll').className = 'marcados';
		document.getElementById('selAll').title = 'Deseleccionar Todos';
		for (i=0;i<document.getElementById('tablaOrdenable').rows.length;i++)
			{
			 name = 'check' + i;
			 (document.getElementsByName(name))[0].checked = true;
			 agregar((document.getElementsByName(name))[0].id);
			}	
		}
	
	else
		{
		document.getElementById('selAll').className = 'desmarcados';
		document.getElementById('selAll').title = 'Seleccionar Todos';
		for (i=0;i<document.getElementById('tablaOrdenable').rows.length;i++)
			{
			 name = 'check' + i;
			 (document.getElementsByName(name))[0].checked = false;
			 pos = buscar((document.getElementsByName(name))[0].id);
			 if (pos > -1)
			    vector[pos] = '';
			}	
		}
	}
	

	
	</script>
	
	
	
	
</head>	
<body>



<center>
<div id="grilla">
<?php

include ("funcionesComunes.php");
$conexion = conectarDB();
$Query = "SELECT * FROM altas WHERE estado = 2"; 
$Res = mysql_query($Query,$conexion);


echo '<table id="tablaOrdenable" class="tablesorter">';
echo '<thead>';
echo '<tr>';
echo '<th>Colegio</th>';
echo '<th>Dipregep</th>';
echo '<th>Solicitante</th>';
echo '<th>Usuario</th>';
echo '<th>Usuario CUIL</th>';
echo '<td style="background-color:#E6EEEE;border: 1px solid #FFF;font-size: 8pt;padding: 4px;font-weight: bold;">+Info</td>';
echo '<td style="background-color:#E6EEEE;border: 1px solid #FFF;padding: 4px;"><input type="checkbox" id="selAll" class="desmarcados" title="Seleccionar Todos" onclick="seleccionarTodos();"/></td>';
echo '</tr></thead>';

echo '<tbody>';

$count = 0;
$clase = '';
$band = 1;



while($row = mysql_fetch_array($Res))
{
   if ($count % 2)
   {
	$clase = 'class="impar"';
   }
   else
   {
    $clase = 'class="par"';
   }
   
	echo '<tr id="tr' .  $row['idReg']   .'" ' . $clase . ' >';
	echo 		'<td>' . $row['col_nombre'] . '</td>';
	echo 		'<td>' . $row['col_nro'] . '</td>';
	echo 		'<td>' . $row['sol_apellido'] . ',' . $row['sol_nombre'] . '</td>';
	echo 		'<td>' . $row['al_apellido'] .  ',' . $row['al_nombre'] . '</td>';
	echo 		'<td>' . $row['al_cuil'] . '</td>';
	echo        '<td id="info">' . "<input type='image' onclick='window.open(\"info.php?id=" . $row['idReg'] .  "\",\"cubaPete\",\"toolbar=no,status=no,menubar=no,resizable=no,width=350,height=600\");' src='imagenes/more2.png' style='width:20px;height:20px;'/><a></td>";
	echo 		'<td><input type="checkbox"' . 'id="' . $row['idReg'] . '" ' .  'name="check' . $count  .  '" onclick="agregar(' . $row['idReg'] .  ');"/></td>';
	echo '</tr>';
	
	$count = $count +1;
	
	$band = 0;
}

if($band == 1)
	{
	 echo '<tr>';
	 echo '<td>No Hay Registros</td>';
	 echo '<td></td>';
	 echo '<td></td>';
	 echo '<td></td>';
	 echo '<td></td>';
	 echo '<td></td>';
	 echo '<td></td>';
	 echo '</tr>';
	}


echo '</tbody>';
echo '</table>';

?>
</div>


	<input type="button" style="width:150px;background-color: #E6EEEE;" value="Validar Marcados" onclick="procesar(1);"/>
	&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" style="width:150px;background-color: #E6EEEE;" value="Descartar Marcados" onclick="procesar(2);"/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<img  id="cargando" src='imagenes/cargando.gif' style="width:35px;height:35px;visibility:hidden;float:top;" />	

</center>



<div id="resul"></div>


</body>
<html>