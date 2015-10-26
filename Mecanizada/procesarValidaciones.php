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
	
	
	include ("funcionesComunes.php");
	$conexion = conectarDB();
	
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