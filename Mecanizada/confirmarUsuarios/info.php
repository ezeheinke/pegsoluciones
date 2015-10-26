<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Info</title>
	
	<style type="text/css" media="screen">
	*	{
		margin: 0 auto;
		}
	
	.formularioAlta{
	    margin: auto;
		heigth: 500px;
		width:  400px;
		border: solid 3px black;
		-webkit-border-radius: 22px;
		-moz-border-radius: 22px;
		text-align: center;
		font-family: Tahoma, Verdana, Arial, sans-serif;
		font-size: 10pt;
		box-shadow: 5px 5px 5px #C0C0C0;
		-webkit-box-shadow: 5px 5px 5px #C0C0C0;
		-moz-box-shadow: 5px 5px 5px #C0C0C0;
		padding: 20px 10px 20px 10px; 
		}
		
	.tabla
	{
		
		width:300px;
	}
	
	.tipo{width:150px;}
	
	.dato{width:150px;}

	
	#info{
	margin-top:30px;
	font: font-family: 'Tangerine', serif;
	color: green;
	font-size: 25;
	}
	
	</style>
	
			
	
		
	<script language="JavaScript">
	</script>  
	
</head>
<body>

<?php

if(!($conexion = mysql_connect('localhost', 'pegsoluc_altaCEC','gwwvmv9xgb1b'))){echo'error';exit;}
if (!mysql_select_db("pegsoluc_Mecanizada",$conexion)){exit();}
$Query = "SELECT A.*, C.NombreCargo, R.nombre FROM altas A inner join cargos C on  C.idCargo = A.sol_cargo " .
		 "inner join roles R on R.idRol = A.al_rol "  .
		 "WHERE idReg = " . $_GET["id"]; 
$Res = mysql_query($Query,$conexion);
$row = mysql_fetch_array($Res)
?>

<br/>
<table class="tabla">
	<tr><td><H3>Colegio<H3></td></tr>
	 <tr>
		<td><label class="tipo">Nombre =</label></td>
		<td><label class="dato"><?php echo $row['col_nombre']; ?></label></td>
	 </tr>
	  <tr>
		<td><label class="tipo">Dirección =</label></td>
		<td><label class="dato"><?php echo $row['col_dir'] . ", " . $row['col_localidad']; ?></label></td>
	 </tr>
	 <tr>
		<td><label class="tipo">Teléfono =</label></td>
		<td><label class="dato"><?php echo $row['col_tel']; ?></label></td>
	 </tr>
	 <tr>
		<td><label class="tipo">Nro Dipr =</label></td>
		<td><label class="dato"><?php echo $row['col_nro']; ?></label></td>
	 </tr>
	 <tr>
		<td><label class="tipo">CUIT =</label></td>
		<td><label class="dato"><?php echo $row['col_cuil']; ?></label></td>
	 </tr>
	 <tr><td></td></tr>
	 <tr><td></td></tr>
	 <tr><td></td></tr>
	 <tr><td><H3>Solicitante<H3></td></tr>
	 <tr>
		<td><label class="tipo">Nombre =</label></td>
		<td><label class="dato"><?php echo $row['sol_apellido'] . ", " .  $row['sol_nombre']; ?></label></td>
	 </tr>
	 <tr>
		<td><label class="tipo">Mail =</label></td>
		<td><label class="dato"><?php echo $row['sol_mail']; ?></label></td>
	 </tr>
	 <tr>
		<td><label class="tipo">Cargo =</label></td>
		<td><label class="dato"><?php echo $row['NombreCargo']; ?></label></td>
	 </tr>
	 <tr><td></td></tr>
	 <tr><td></td></tr>
	 <tr><td></td></tr>
	 <tr><td><H3>Usuario<H3></td></tr>
	  <tr>
		<td><label class="tipo">Nombre =</label></td>
		<td><label class="dato"><?php echo $row['al_apellido'] . ", " .  $row['al_nombre']; ?></label></td>
	 </tr>
	 <tr>
		<td><label class="tipo">Cuil/Cuit =</label></td>
		<td><label class="dato"><?php echo $row['al_cuil']; ?></label></td>
	 </tr>
	 <tr>
		<td><label class="tipo">DNI =</label></td>
		<td><label class="dato"><?php echo $row['al_dni']; ?></label></td>
	 </tr>
	  <tr>
		<td><label class="tipo">Teléfono =</label></td>
		<td><label class="dato"><?php echo $row['al_tel']; ?></label></td>
	 </tr>
	  <tr>
		<td><label class="tipo">Cel =</label></td>
		<td><label class="dato"><?php echo $row['al_cel']; ?></label></td>
	 </tr>
	 <tr>
		<td><label class="tipo">Mail =</label></td>
		<td><label class="dato"><?php echo $row['al_mail']; ?></label></td>
	 </tr>
	 <tr>
		<td><label class="tipo">Rol =</label></td>
		<td><label class="dato"><?php echo $row['nombre']; ?></label></td>
	 </tr>
	</table>
	
</fieldset>

</br></br>
<center>
	<div>
		<input type="button" id="cancelar" class="Cancelar" style="display:inline-block" onclick="window.close();" value="Salir">
	</div>
</center>
	
</body>
</html>