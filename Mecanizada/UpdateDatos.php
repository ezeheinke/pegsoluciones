<?php 
	
	if(!($conexion = mysql_connect("localhost", "pegsoluc_altaCEC", "gwwvmv9xgb1b"))){
          exit();
	}
	    
	if (!mysql_select_db("pegsoluc_Mecanizada",$conexion)){ 
	      exit(); 
	}
	
	$col_nombre= '';
	$col_dire = '';
	$col_localidad= '';
	$col_tel = '';
	$col_nroDipregep = 0;
	$col_cuil = 0;
		
	$sol_apellido= '';
	$sol_nom= '';
	$sol_mail= '';
	$sol_cargo = 0;
	
	$usuario_apellido= '';
	$usuario_nom= '';
	$usuario_cuil= 0;
	$usuario_MAIL = '';
	$usuario_DNI = 0;
	$usuario_TEL = 0;
	$usuario_CEL = 0;
	$usuario_rol = 0;
	
		
	$col_nombre = $_POST["col_nom"];
	$col_dir = $_POST["col_dir"];
	$col_localidad = $_POST["col_localidad"];
	$col_tel = $_POST["col_tel"];
	$col_nroDipregep = $_POST["col_nroDipr"];
	$col_cuil = $_POST["col_cuil1"] . $_POST["col_cuil2"] . $_POST["col_cuil3"];
	
	
	$sol_apellido = $_POST["sol_apellido"];
	$sol_nom = $_POST["sol_nom"];
	$sol_mail = $_POST["sol_mail"];
	$sol_cargo = $_POST["cargo"];
	
	$usuario_apellido = $_POST["usuario_apellido"];
	$usuario_nom = $_POST["usuario_nom"];
	$usuario_cuil= $_POST["usuario_cuil1"] . $_POST["usuario_cuil2"] . $_POST["usuario_cuil3"];
	$usuario_MAIL = $_POST["usuario_MAIL"];
	$usuario_DNI = $_POST["usuario_DNI"];
	$usuario_TEL = $_POST["usuario_TEL"];
	$usuario_CEL = $_POST["usuario_CEL"];
	$usuario_rol = $_POST["rol"];
	
	 date_default_timezone_set('America/Argentina/Buenos_Aires');
	 $fecha = date("YmdHis");
	 $fecha2 = date("YmdHis");
	 $codigo = $fecha . (string)$usuario_cuil ;
	 	 
	 $query = "update altas set ".
					   "col_nombre = '" . $col_nombre  . "'," . 
					   "col_dir = '" . $col_dir . "'," .
					   "col_localidad='" . $col_localidad . "'," . 
					   "col_tel =". $col_tel . "," .
					   "col_nro =".$col_nroDipregep . "," .
					   "col_cuil =".$col_cuil . "," .
					   "sol_nombre='" . $sol_nom . "'," .
					   "sol_apellido = '" . $sol_apellido . "'," .
					   "sol_mail = '" . $sol_mail . "'," .
					   "sol_cargo =". $sol_cargo . "," . 
					   "al_nombre ='" . $usuario_nom . "'," .
					   "al_apellido='" . $usuario_apellido . "'," . 
					   "al_cuil =".$usuario_cuil . "," . 
					   "al_dni =".$usuario_DNI . ",".
					   "al_tel =". $usuario_TEL . ",".
					   "al_cel =".$usuario_CEL . "," .
					   "al_mail ='" . $usuario_MAIL . "'," .
					   "al_rol = ".$usuario_rol . "," .
					   "fechaAlta = ".$fecha2 . "".
					   " where codigo = ".$codigo;
	echo $query;
	$result = mysql_query($query, $conexion);													   

?>