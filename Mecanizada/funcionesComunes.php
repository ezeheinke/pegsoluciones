<?PHP

        
        function conectarDB(){
                
	if(!($conexion = mysql_connect("localhost", "pegsoluc_altaCEC", "gwwvmv9xgb1b"))){
          exit();
		}
	    
	if (!mysql_select_db('pegsoluc_Mecanizada',$conexion)){ 
	      exit(); 
	 	}
	return $conexion;
	}
        
 	
	
	
	
?>