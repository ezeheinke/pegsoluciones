<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<head runat="server">
    <title>Usuarios por Convalidar</title>
	<link rel="stylesheet" href="js/ordenable/jq.css" type="text/css" media="print, projection, screen">
	<link rel="stylesheet" href="js/ordenable/style.css" type="text/css" media="print, projection, screen">
	<script type="text/javascript" src="js/ordenable/jquery-latest.js"></script>
	<script type="text/javascript" src="js/ordenable/jquery.tablesorter.js"></script>
	<script type="text/javascript" src="js/ordenable/docs.js"></script>

	<script>

	$(document).ready(function(){
			$("#tablaOrdenable").tablesorter();}
	);
	
	</script>
	<script src="js/jquery-1.6.js" type="text/javascript" charset="utf-8"></script>

	<script>
	
	var vector= new Array();
	var i=0;
	var j=0;
	var k=0;
	
	$(document).ready(function() {
        $('#grilla').load('grilla.php')
	});
	
	
	function ordenar(link){
	  $('#grilla').load(link)
	}
	
	
	function agregar(id){
	
	if(document.getElementById(id).checked  == true)
		{vector[i] = id;
		i = i + 1 ;}
	else
	   vector[buscar(id)] = '';
	 
	 }
	 
	 
	function buscar(id){
	
	
	 for(j=0; j <= i; j++)
	  { 
		if(vector[j] == id)
		  return j;
	  }
	  
	}
	
	
	function mostrarvector()
	{
	  var k=0;
	  for(k=0; k < i; k++)
    	{
		alert(vector[k]);
	    }
	}
	
	
	</script>
	
	<style>
	
	#grilla
	{ margin: 100 auto;
	  width: 600px;
	}
		
	.header div
    {
	 color: blue;
	 text-decoration: underline;
	 cursor: pointer;
	}
	
	
	.titulo
	{
	  width:600px;
	  font-size:14px; 
	  text-align:center;
	  height:30px; 
	  background-color:#58ACFA; 
	  border:solid 2px #ffffff;
	  titleTable, .fooTable, .contentTable { font-family:Geneva, Arial, Helvetica, sans-serif; 
	  
	}
	
	.header
	{
	 background-color: 
	}
	
	.titulo .header .contenido {font-family:Geneva, Arial, Helvetica, sans-serif;}
	
	
	</style>
	
<body>


<div id="grilla">
<?php

if(!($conexion = mysql_connect('localhost', 'gabi','gabi'))){echo'error';exit;}
if (!mysql_select_db("colegios",$conexion)){exit();}
$Query = "SELECT * FROM roles"; 
$Res = mysql_query($Query,$conexion);


echo '<table id="tablaOrdenable" class="tablesorter">';
echo '<thead>';
echo '<tr>';
echo '<th>idRol</th>';
echo '<th>Nombre</th>';
echo '<th>Check</th>';
echo '</tr></thead>';

echo '<tbody>';

while($row = mysql_fetch_array($Res))
{
	echo '<tr>';
	echo 		'<td>' . $row['idRol'] . '</td>';
	echo 		'<td>' . $row['nombre'] . '</td>';
	echo 		'<td>23</td>';
	echo '</tr>';
	
}

echo '</tbody>';
echo '</table>';

?>
</div>

<input  type="button" style="width:100px;" title="hola" onclick="mostrarvector();"/>


</body>
<html>