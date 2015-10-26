<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Carga Usuario</title>
	
	<style type="text/css" media="screen">
	</style>
	
 
	
</head>
<body style="margin:auto;">

	<?php
	
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
	
	
	
	
	
	/*Aca irian las validaciones*/
	
	
	
	//Errores
	$errorServidor = '<div style="margin= 0 auto;margin-top:200px;font-size:25pt;color:red;">
	<center><img src="imagenes/alert.png" style="width:200px;height:200px;"/></br>
	Error al conectar con el Servidor. Intente nuevamente mas tarde.</center></div>';
	
	
	$errorGeneral = '<div style="margin= 0 auto;margin-top:200px;font-size:25pt;color:red;">
	<center><img src="imagenes/alert.png" style="width:200px;height:200px;"/></br>
	No se ha podido completar la solicitur. Por favor, reintente mas tarde.</center></div>';
	
	$huboError = false ;
	
	
	
	//Guardo el registro
	include ("funcionesComunes.php");
	$conexion = conectarDB();
	 
	 
	 date_default_timezone_set('America/Argentina/Buenos_Aires');
	 $fecha = date("YmdHis");
	 $fecha2 = date("YmdHis");
	 $codigo = $fecha . (string)$usuario_cuil ;
	 	 
	 $Query = "INSERT INTO altas (idReg,
										   estado, 
										   col_nombre, 
										   col_dir,
										   col_localidad,
										   col_tel,
										   col_nro,
										   col_cuil,
										   sol_nombre, 
										   sol_apellido, 
										   sol_mail, 
										   sol_cargo, 
										   al_nombre, 
										   al_apellido,
										   al_cuil,
										   al_dni, 
										   al_tel, 
										   al_cel, 
										   al_mail, 
										   al_rol,
										   fechaAlta, 
										   codigo) VALUES (NULL," . 
														   "1," .
														   "'" . $col_nombre  . "'," .
														   "'" . $col_dir . "'," .
														   "'" . $col_localidad . "'," . 
														   $col_tel . "," .
														   $col_nroDipregep . "," .
														   $col_cuil . "," .
														   "'" . $sol_nom . "'," .
														   "'" . $sol_apellido . "'," .
														   "'" . $sol_mail . "'," .
														   $sol_cargo . "," .
														   "'" . $usuario_nom . "'," .
														   "'" . $usuario_apellido . "'," . 
														   $usuario_cuil . "," . 
														   $usuario_DNI . "," .
														   $usuario_TEL . "," .
														   $usuario_CEL . "," .
														   "'" . $usuario_MAIL . "'," .
														   $usuario_rol . "," .
														   $fecha2 . "," .
														   $codigo . ")";
			  
	
	 
	 
	$result = mysql_query($Query, $conexion);
	 if (!$result) {
			$message = '<div style="margin= 0 auto;margin-top:250px;font-size:20pt;color:red;"><center><img src="imagenes/alert.png" style="width:150px;height:150px;"/></br>'
					   . 'Consulta Invalida' . mysql_error() . '</center></div>';
    		echo($message);
			exit;
			$huboError = true;
		}
	
	
	
	
	
	//Envio Mail
	
	ini_set("include_path","php");
	require_once "Mail.php";

	$from = "Mecanizadas <movimientosonline@pegsoluciones.com>";
	$to = "<".$sol_mail.">";
	$subject = "Solicitud de alta - Confirmacion de datos y pedido de alta.";
	$body = "<html xmlns=\"http://www.w3.org/1999/xhtml\">
				<head>
					<title>Formulario</title>
					</head>
					<body style=\"background-color: #FFFFFF;margin:auto;font-size:15px;\">
						Hola " . $sol_nom . "," . "<br/>" .
						"Por favor, revise los datos y presione el link del final para la confirmación del Pedido" .
						"<br/><br/>" .
						"<b>Colegio</b> <br/>" .
						"<u>Nombre</u>" . " :<i>" . $col_nombre . "</i><br/>" .
						"<u>Dirección</u>" . " :<i>" . $col_dir . "</i><br/>" .
						"<u>Localidad</u>" . " :<i>" .  $col_localidad . "</i><br/>" .
						"<u>Teléfono</u>" . " :<i>" . $col_tel . "</i><br/>" .
						"<u>Número Dipregep</u>" . " :<i>" .  $col_nroDipregep . "</i><br/>" .
						"<u>CUIL</u>" . " :<i>" . $col_cuil . "</i><br/><br/><br/>" .
						
						"<b>Solicitante</b> <br/>" .
						"<u>Apellido</u>" . " :<i>" . $sol_apellido . "</i><br/>" .
						"<u>Nombre</u>" . " :<i>" . $sol_nom . "</i><br/>" .
						"<u>Mail</u>" . " :<i>" . $sol_mail . "</i><br/><br/>" .
						
						"<b>Usuario</b> <br/>" .
						"<u>Apellido</u>" . " :<i>" . $usuario_apellido . "</i><br/>" .
						"<u>Nombre</u>" . " :<i>" . $usuario_nom . "</i><br/>" .
						"<u>CUIL/CUIT</u>" . " :<i>" . $usuario_cuil . "</i><br/>" .
						"<u>DNI</u>" . " :<i>" . $usuario_DNI . "</i><br/>" .
						"<u>Teléfono</u>" . " :<i>" . $usuario_TEL . "</i><br/>" .
						"<u>CEL</u>" . " :<i>" . $usuario_CEL . "</i><br/>" .
						"<u>Mail</u>" . " :<i>" . $usuario_MAIL . "</i><br/><br/><br/>" .
						
						"<a href=\"http://www.pegsoluciones.com/Mecanizada/confirmacionDatosYPedidos.php?codigo=" . $codigo .
						"&mail=". $sol_mail . "&cuil=" . $usuario_cuil  .  "\">Confirme haciendo click aqui</a> 
					</body>
				</html>";

		$host = "mail.pegsoluciones.com";
		$username = "movimientosonline@pegsoluciones.com";
		$password = "gwwvmv9xgb1b";

		$headers = array ('From' => $from,
		   'To' => $to,
		   'Subject' => $subject,
		   'Content-type' => 'text/html');
		$smtp = Mail::factory('smtp',
		   array ('host' => $host,
		     'auth' => true,
		     'username' => $username,
		     'password' => $password));


		$mail = $smtp->send($to, $headers, $body);

		if (PEAR::isError($mail)) {
		   echo $errorGeneral ;exit;$huboError = true ;
		  } 
		

		
	
	$mensajeOk = '<div style="margin= 0 auto;margin-top:100px;font-size:20pt;color:green;"><center><img src="imagenes/tilde.png" style="width:200px;height:200px;"/></br>'
				 . 'Los datos han sido ingresados </br> Se enviara un mail con un pedido de confirmacion a la siguiente casilla : &nbsp <font style="color:blue;">' . $sol_mail   
				 . '</font></br></br><div style="font-size:15pt;">Aclaracion: En caso de no encotrar el mail verifique en su carpeta de correo no deseado</div></br></br></br><input type="button" style="width:150px;" onclick="location.href = ' . "'" . 'FormularioAlta.php' . "'" . '"'
				 . ' value="Ingresar Otro Usuario"/></center></div>';
	
	
	//Si envio el mail y guardo el registro muestro el mensaje de OK
	if(! $huboError) echo $mensajeOk;
	
	?>
</body>

</html>