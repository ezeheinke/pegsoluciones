<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Carga Usuario</title>
	
	<style type="text/css" media="screen">
	</style>
	
 
	
</head>
<body style="margin:auto;">

	<?php
	
	$operacion = '';
	$idReg = '';
	$operacion = $_GET['op'];
	$idReg = $_GET['id'];
	
	
	if(!($conexion = mysql_connect('localhost', 'pegsoluc_altaCEC','gwwvmv9xgb1b'))){echo'error';exit;}
	if (!mysql_select_db("pegsoluc_Mecanizada",$conexion)){exit();}
	
	date_default_timezone_set('America/Argentina/Buenos_Aires');
	$fecha = date("YmdHis");
	
	
	if($operacion == 'validar')
	   {
	   $query = "UPDATE altas SET estado = 3, fechaConvalidacion = " .  $fecha  . " WHERE idReg = " . $idReg; 
	   $result = mysql_query($query, $conexion);
	   echo 'ok';
	   }
	
	if($operacion == 'descartar')
	   {
	   $query = "UPDATE altas SET estado = 99 WHERE idReg = " . $idReg; 
	   $result = mysql_query($query, $conexion);
	   echo 'ok';
	   }
	
	?>
</body>

</html>