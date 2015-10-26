<?php
function isAdmin($user,$pass){
    
    htmlspecialchars_decode("utf-8");

    $response = false;
	
    //$db = new Database();
    //$dblink = $db->bdconnect();
	 if (file_exists($_SESSION["PATH_NAME"].'DataBase/XML_BDconf.xml')) {
            $dbconf = simplexml_load_file($_SESSION["PATH_NAME"].'DataBase/XML_BDconf.xml');
        } else if (file_exists($_SESSION["PATH_NAME"].'../DataBase/XML_BDconf.xml')) {
            $dbconf = simplexml_load_file($_SESSION["PATH_NAME"].'../DataBase/XML_BDconf.xml');
        } else {
            exit('Error abriendo configuracion de la base de datos ... ');
        }

	
	if(!($conexion = mysql_connect($dbconf->host, $dbconf->user,(string)$dbconf->pass))){echo'error putoo';exit;}
											if (!mysql_select_db($dbconf->name,$conexion)){exit();}
	
	
	
    $query = "select * from usuario_copado where usr_nick = '" . $user . "' and usr_contrasenia = '" . $pass."'";
	$Res = mysql_query($query,$conexion);
	
	//echo "<p>".$Res."</p>";
	//echo "<p>".$row."</p>";
	while ($row = mysql_fetch_assoc($Res) ) {
		$response = true;
	}
	
	

    return $response;
	}
	
	
	
	function encriptar($string, $key) {
		$result = '';
		for($i=0; $i<strlen($string); $i++) {
			$char = substr($string, $i, 1);
			$keychar = substr($key, ($i % strlen($key))-1, 1);
			$char = chr(ord($char)+ord($keychar));
			$result.=$char;
		}
	return base64_encode($result);
	}
	
	function desencriptar($string, $key) {
		$result = '';
		$string = base64_decode($string);
	for($i=0; $i<strlen($string); $i++) {
      $char = substr($string, $i, 1);
      $keychar = substr($key, ($i % strlen($key))-1, 1);
      $char = chr(ord($char)-ord($keychar));
      $result.=$char;
	}
	return $result;
	}
	
	?>