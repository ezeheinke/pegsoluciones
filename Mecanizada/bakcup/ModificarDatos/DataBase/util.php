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
	if ($Res) {
		$response=true;
	}else
		$response=false;
	
	

    return $response;
	}
	
	function convert($str,$ky=''){
		if($ky=='')
			return $str;

		$ky = str_replace(chr(32),'',$ky);

		if(strlen($ky)<8)
			exit('key error');

		$kl = strlen($ky) <32 ?strlen($ky) : 32;

		$k = array();

		for($i=0;$i<$kl;$i++){
			$k[$i] = ord($ky{$i}) &0x1F;
		}

		$j=0;

		for($i=0;$i<strlen($str);$i++){

			$e=ord($str{$i});
			$str{$i} = $e & 0xE0 ? chr($e^$k[$j]) : chr($e);

			$j++;
			$j = $j== $kl ? 0 : $j;
		}
	return $str;
	}
	
	?>