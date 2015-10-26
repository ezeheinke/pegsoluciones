<?php
    
	function generarContrasena($largo){
		$str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		$pas = "";
		for($i=0;$i<$largo;$i++)
			$pas.= substr($str,rand(0,62),1);
		return $pas;
	}
	
	function mandarMailUserPass($mail,$user,$pass){
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
		   'Content-type' => 'text/plain');
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
	
	if(!($conexion = mysql_connect("localhost", "pegsoluc_altaCEC", "gwwvmv9xgb1b"))){
          exit();
	}
    
	
	if (!mysql_select_db("pegsoluc_Mecanizada",$conexion)){ 
      exit(); 
 	}
	
	
	$usuariosParaAvisarQuery = "select * from altas where estado = 3";
	$resultParaAvisar = mysql_query($usuariosParaAvisarQuery,$conexion);
	
	
	$deleteQuery = "update altas set estado = 4 where estado = 3";
	mysql_query($deleteQuery, $conexion);


	$i=0;

	while($row = mysql_fetch_array($resultParaAvisar)){
		$mail = $row["al_mail"];		
		$user = strval($row["idReg"]).strval($row["al_dni"]);
		$pass = generarContrasena(8);
		
		
		$migrarUsuariosQuery = 	"INSERT INTO usuarios_finales (idReg, col_nombre, col_nro"
							.", al_nick, al_nombre,al_apellido, al_contrasenia, al_dni,"
							."al_tel, al_cel, al_mail, al_rol, fechaAlta)" 
							."values (".strval($row["idReg"]).", '".$row["col_nombre"]."', ".strval($row["col_nro"])
							.", '".$nick."', '".$row["al_nombre"]."', '".$row["al_apellido"]."', '"
							.$row["al_contrasenia"]."', ".strval($row["al_dni"]).", ".strval($row["al_tel"])
							.strval($row["al_cel"])." ,'".$row["al_mail"]."', ".$row["al_rol"]." ,12/12/12)";
		mysql_query($migrarUsuariosQuery, $conexion);
		
		mandarMailUserPass($mail,$user,$pass);
		$i = $i +1;
	}

	echo "Usuarios notificados: ".strval($i);
?>