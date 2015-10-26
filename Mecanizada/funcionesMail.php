<?PHP

		
	function mandarMailConfirmacionDatos($datos){
	
		ini_set("include_path","php");

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
		ini_set("include_path","php");
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


		$mail = $smtp->send($to, $headers, $body);

		if (PEAR::isError($mail)) {
		   echo("<p>" . $mail->getMessage() . "</p>");
		  } else {;
		   //echo("<p>Message successfully sent!</p>");
		}
	
	}

	
?>