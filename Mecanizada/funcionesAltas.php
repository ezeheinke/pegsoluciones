<?PHP

	function getConnectionDB(){
		if(!($conexion = mysql_connect("localhost", "pegsoluc_pablo", "123456pablo"))){
          exit();
		}
	    
		if (!mysql_select_db("pegsoluc_colegios",$conexion)){ 
	      exit(); 
	 	}
		return $conexion;
	}


	function cargarPedidoDeAlta($datos,$codigo){
	
		if(!($conexion = mysql_connect("localhost", "pegsoluc_pablo", "123456pablo"))){
          exit();
		}
	    
		if (!mysql_select_db("pegsoluc_colegios",$conexion)){ 
	      exit(); 
	 	}
		
		$dni = $datos["dni"];
		$mail = $datos["mail"];
		$rol = $datos["rol"];
		
		
		$pedidoAlta = "insert into altas(alta_dni,alta_mail,alta_rol,alta_status,alta_cod_tramite) values(";
		$pedidoAlta .= "'".$dni."','".$mail."','".$rol."','1','".$dni."')";
		
		echo $pedidoAlta;
		
		$result = mysql_query($pedidoAlta, $conexion);
		
	}
	
	function mandarMailConfirmacionDatos($datos){
	
		ini_set("include_path",".:/home/pegsoluc/php");

		require_once "Mail.php";


		$from = "Pablo <ppoza@pegsoluciones.com>";
		$to = "<".$datos["mail"].">";
		$subject = "Solicitud de alta - Confirmacion de datos y pedido de alta.";
		$body = "<html xmlns=\"http://www.w3.org/1999/xhtml\">
					<head>
						<title>Formulario</title>
					</head>
					<body style=\"background-color: #FFFFFF;margin:auto;\">  
						<a href=\"http://www.pegsoluciones.com/Dreamweaver/confirmacionDatosYPedidos.php?cod_pedido=".$datos["dni"]."&mail=".$datos["mail"]
						."\">Confirme haciendo click aqui</a> 
					</body>
				</html>";

		$host = "mail.pegsoluciones.com";
		$username = "ppoza@pegsoluciones.com";
		$password = "123456ppoza";

		$headers = array ('From' => $from,
		   'To' => $to,
		   'Subject' => $subject,
		   'Content-type' => 'text/html');
		$smtp = Mail::factory('smtp',
		   array ('host' => $host,
		     'auth' => true,
		     'username' => $username,
		     'password' => $password));


		echo "groso";

		$mail = $smtp->send($to, $headers, $body);

		if (PEAR::isError($mail)) {
		   echo("<p>" . $mail->getMessage() . "</p>");
		  } else {
		   echo("<p>Message successfully sent!</p>");
		}
	
	}
	
	function mandarMailConfirmacionDatosRecibida($mail){
		ini_set("include_path",".:/home/pegsoluc/php");
		require_once "Mail.php";

		$from = "Pablo <ppoza@pegsoluciones.com>";
		$to = "<".$mail.">";
		$subject = "Solicitud de alta en analisis - Datos y pedido  de alta confirmado.";
		$body = "Recibimos su confirmacion, su pedido esta siendo analizado, cuando tenga una respuesta se le enviara por mail";

		$host = "mail.pegsoluciones.com";
		$username = "ppoza@pegsoluciones.com";
		$password = "123456ppoza";

		$headers = array ('From' => $from,
		   'To' => $to,
		   'Subject' => $subject,
		   'Content-type' => 'text/html');
		$smtp = Mail::factory('smtp',
		   array ('host' => $host,
		     'auth' => true,
		     'username' => $username,
		     'password' => $password));


		echo "groso";

		$mail = $smtp->send($to, $headers, $body);

		if (PEAR::isError($mail)) {
		   echo("<p>" . $mail->getMessage() . "</p>");
		  } else {
		   echo("<p>Message successfully sent!</p>");
		}
	
	}

	function generarContrasena($largo){
		$str = "ABCDEFGHIJKLMNOPQRSTU$largoVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		$pas = "";
		for($i=0;$i<;$i++)
			$pas.= substr($str,rand(0,62),1);
		return $pas;
	}
	
	
	function darAlta($usuario){
		$con = getConnectionDB();
		
		$pass =generaraPass(8);
		$query ="update altas set alta_status = '3' , alta_pass = '".$pass."' where alta_mail = '".$usuario;
	}
	
	/*function mandarMailUserPass($mail,$user,$pass){
		ini_set("include_path","php");
		require_once "Mail.php";

		$from = "Pablo <ppoza@pegsoluciones.com>";
		$to = "<".$mail.">";
		$subject = "Alta CEC";
		$body = "Usted ya fu sido dado de alta en nuestro sistema.\n".
				"Le recordamos sus datos para poder iniciar session:\nUsuario: ".$user.
				"\nContraseña: ".$pass;

		$host = "mail.pegsoluciones.com";
		$username = "ppoza@pegsoluciones.com";
		$password = "123456ppoza";

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
		   echo("<p>" . $mail->getMessage() . "</p>");
		  } else {;
		   //echo("<p>Message successfully sent!</p>");
		}
	
	}*/
?>
