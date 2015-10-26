<?php
	
	require_once "funcionesMail.php";

	if(!($conexion = mysql_connect("localhost", "pegsoluc_altaCEC", "gwwvmv9xgb1b"))){
          exit();
	}
    
	if (!mysql_select_db("pegsoluc_Mecanizada",$conexion)){ 
      exit(); 
 	}
	
	$codigo1 = $_GET["codigo"];
	$mail = $_GET["mail"];
	$cuil = $_GET["cuil"];
	
	
	
	$query = 'select estado from altas where codigo  = \'' . $codigo1 . '\''; 
	
	//echo "Consulta " . $query;
	
	$result = mysql_query($query, $conexion);

	if (!$result) {
	    $message  = 'Invalid query: ' . mysql_error() . "\n";
	    $message .= 'Whole query: ' . $query;
	    die($message);
	}
	
	$row = mysql_fetch_assoc($result);
	
	date_default_timezone_set('America/Argentina/Buenos_Aires');
	$fecha = date("YmdHis");
	$codigo2 = $fecha . (string)$cuil;
	
	
	$estado = $row["estado"]; 
	if(strcmp($estado,"1") == 0){
		$query = "update altas set estado = 2 , fechaConfirmacion = " . $fecha  . " , codigo= '" . $codigo2 . "' where codigo = '" . $codigo1 . "'";
		$result = mysql_query($query, $conexion);
		if (!$result) {
			$message  = 'Invalid query: ' . mysql_error() . "\n";
			$message .= 'Whole query: ' . $query;
			die($message);
		}else{
			//mandarMailConfirmacionDatosRecibida($mail);
			echo $mensajeOk = '<div style="margin= 0 auto;margin-top:100px;font-size:20pt;color:green;"><center><img src="imagenes/tilde2.png" style="width:200px;height:200px;"/></br>'
				 . 'Confirmacion Realizada<br/>Su pedido esta siendo analizado, cuando se tenga respuesta se enviara por mail.<font style="color:blue;">'  
				 . '</font><div style="font-size:15pt;"></div>';
		}
	}else
		echo '<div style="margin= 0 auto;margin-top:200px;font-size:25pt;color:red;"><center><img src="imagenes/alert.png" style="width:200px;height:200px;"/></br>
	La confirmacion ya ha sido realizada .</center></div>';

?>