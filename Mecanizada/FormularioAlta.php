<html xmlns="http://www.w3.org/1999/xhtml">

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
<head runat="server">
    <title>Formulario</title>
	<link href="css/estilos.css" rel="stylesheet" type="text/css" />
    <script src="js/formularioAlta.js" type="text/javascript"></script>
	<style type="text/css" media="screen">
		
		
	*	{
		margin: auto;
		}
	
	.formularioAlta{
	    margin: auto;
		heigth: 500px;
		width:  900px;
		border: solid 3px #FFFFFF;
		-webkit-border-radius: 22px;
		-moz-border-radius: 22px;
		text-align: center;
		font-family: Tahoma, Verdana, Arial, sans-serif;
		font-size: 10pt;
		box-shadow: 5px 5px 5px #C0C0C0;
		-webkit-box-shadow: 5px 5px 5px #C0C0C0;
		-moz-box-shadow: 5px 5px 5px #C0C0C0;
		padding: 15px 10px 15px 10px;
		color:white;
		}
		
	
	.subForm
	{
		heigth:200px;
		padding-top:10px;
		text-align: center;
		font-size: 12pt;
		border: solid 2px #87CEFA;
		-webkit-border-radius: 22px;
		-moz-border-radius: 22px;
		font-family: Tahoma, Verdana, Arial, sans-serif;
		font-size: 10pt;
		box-shadow: 5px 5px 5px #C0C0C0;
		-webkit-box-shadow: 5px 5px 5px #C0C0C0;
		-moz-box-shadow: 5px 5px 5px #C0C0C0;
		color: #FFFFFF;
	}
	
	.leyenda
	{
	    text-align: center;
		font-size: 12pt;
		font-family: Arial, 'Lucida Sans Unicode', Helvetica, sans-serif;
		font-weight: bold;
		font-weight: bold;
		font-color:white;
	}
		
	.error
	   {
	    height:20px;
		visibility:hidden;
	   }
	   
	 .campo
	    {
		 float:left;margin-left:70px;margin:auto;width:200px;align:left;
		 color: white;
		 font-family: Arial, 'Lucida Sans Unicode', Helvetica, sans-serif;
		 font-weight: bold;
		 }
	
	 .textBox
		{
		  border: solid 1px black;
		}
	
	 .textBoxMarcado
	    {
		  border: solid 1px #D0A9F5;
		}
		
	</style>
	
	<script language="JavaScript"></script>  
	
	
	<?PHP
	include ("funcionesComunes.php");
	$conexion = conectarDB();
	?> 
	
	
</head>

<body style="background-color: #3D4751;margin:auto;margin-top:15px;">

<FORM name="bleh" METHOD="post" ACTION="ResultadoAltaUsuario.php" onsubmit ="return comprobar()"> 

<center>

  <fieldset class="formularioAlta" background= #15181C>
    <legend class="leyenda" style="color:#FFFFFF;">&nbsp&nbsp&nbsp Formulario pedido de nuevo usuario &nbsp&nbsp&nbsp  </legend>
	
	<fieldset class="subForm">
	 <legend class="leyenda"> &nbsp&nbsp&nbsp COLEGIO &nbsp&nbsp&nbsp </legend>
	  	</br>
			<div class="campo">
				Nombre</br><input id="col_nom" type="text" name="col_nom" value="" class="textBox" onfocus="posicionamiento('col_nom','col_nom_error',1);" onblur="salida('col_nom','col_nom_error',1);"></br>
				<div class="error" id="col_nom_error">a</div>
			</div>
			
			<div class="campo">
				Direccion</br><input id="col_dir" type="text" name="col_dir" value="" class="textBox" onfocus="posicionamiento('col_dir','col_dir_error',10);" onblur="salida('col_dir','col_dir_error',10);"></br>
				<div class="error" id="col_dir_error">a</div>
			</div>
			
			<div class="campo">
				Localidad</br><input id="col_localidad" type="text" name="col_localidad" value="" class="textBox" onfocus="posicionamiento('col_localidad','col_localidad_error',2);" onblur="salida('col_localidad','col_localidad_error',2);"></br>
				<div class="error" id="col_localidad_error">a</div>
			</div>
			
			
			<div class="campo">
				Telefono</br>
				<input id="col_tel_postal" type="text" style="width:40px;" maxlength="5" name="col_tel_postal" value="" class="textBox" onfocus="posicionamiento('usuario_cuil1','col_tel_error',8);" onblur="salida('usuario_cuil1','col_tel_error',8);"> - 
				<input id="col_tel" type="text" style="width:60px;" maxlength="8" name="col_tel" value="" class="textBox" onfocus="posicionamiento('col_tel','col_tel_error',8);" onblur="salida('col_tel','col_tel_error',8);"></br>
				<div class="error" id="col_tel_error">a</div>
			</div>
			
			
			<div class="campo" style="padding-top:15px;">
				Nro. Dipregep</br><input id="col_nroDipr" type="text" name="col_nroDipr" value="" class="textBox" onfocus="posicionamiento('col_nroDipr','col_nroDipr_error',3);" onblur="salida('col_nroDipr','col_nroDipr_error',3);"></br>
				<div class="error" id="col_nroDipr_error">a</div>
			</div>
			
			<div class="campo" style="padding-top:15px;">
				CUIT</br><input id="col_cuil1" type="text" style="width:20px;" maxlength="2" name="col_cuil1" value="" class="textBox" onfocus="posicionamiento('col_cuil1','col_cuil2_error',11);" onblur="salida('col_cuil1','col_cuil2_error',11);">&nbsp - 
						 <input id="col_cuil2" type="text" style="width:70px;" maxlength="8" name="col_cuil2" value="" class="textBox" onfocus="posicionamiento('col_cuil2','col_cuil2_error',12);" onblur="salida('col_cuil2','col_cuil2_error',12);">&nbsp - 
						 <input id="col_cuil3" type="text" style="width:15px;" maxlength="1" name="col_cuil3" value="" class="textBox" onfocus="posicionamiento('col_cuil3','col_cuil2_error',13);" onblur="salida('col_cuil3','col_cuil2_error',13);">
				</br><div class="error" id="col_cuil2_error">a</div>
			</div>	
			
			
	 </fieldset>
  
		
	</br>
	
	<fieldset  class="subForm" style="heigth:120px;">
	 <legend class="leyenda">&nbsp&nbsp&nbsp SOLICITANTE &nbsp&nbsp&nbsp </legend>
	    </br>
		<div class="solicitante">
			<div class="campo">
				Apellido</br><input id="sol_apellido" type="text" name="sol_apellido" value="" class="textBox" onfocus="posicionamiento('sol_apellido','sol_apellido_error',4);" onblur="salida('sol_apellido','sol_apellido_error',4);"></br>
				<div class="error" id="sol_apellido_error">a</div>
				
			</div>
			
			<div class="campo">
				Nombre</br><input id="sol_nom" type="text" name="sol_nom" value="" class="textBox" onfocus="posicionamiento('sol_nom','sol_nom_error',1);" onblur="salida('sol_nom','sol_nom_error',1);"></br>
				<div class="error" id="sol_nom_error">a</div>
			</div>
			
						
			<div class="campo">
				Mail</br><input id="sol_mail" type="text" name="sol_mail" value="" class="textBox" onfocus="posicionamiento('sol_mail','sol_mail_error',5);" onblur="salida('sol_mail','sol_mail_error',5);"></br>
				<div class="error" id="sol_mail_error">a</div>
			</div>
			
			
			
			<div class="campo">
				Cargo</br><select id="combo" name="cargo" style="width:150px;" name="Cargo">
											<option value="0">Seleccionar ---></option>
									 <?php
											$Query = "SELECT * FROM cargos"; 
											$Res = mysql_query($Query,$conexion);
											$opciones = '';
											while($row = mysql_fetch_array($Res))
											   {
												$opciones = $opciones . '<option value="' . $row['idCargo'] . '">' . $row['NombreCargo'] . '</option>';
												}
											$opciones = $opciones . '</select>'; 
											echo $opciones;
									  ?>
										
				<div class="error" id="sol_cargo_error">a</div>								 
			</div>
			
			
		  
		</div>
	  
	</fieldset>
	
	</br>
	
	<fieldset class="subForm">
	 <legend class="leyenda"> &nbsp&nbsp&nbsp USUARIO &nbsp&nbsp&nbsp </legend>
	  	</br>
			
			<div class="campo">
				Apellido</br><input id="usuario_apellido" type="text" name="usuario_apellido" value="" class="textBox" onfocus="posicionamiento('usuario_apellido','usuario_apellido_error',4);" onblur="salida('usuario_apellido','usuario_apellido_error',4);"></br>
				<div class="error" id="usuario_apellido_error">a</div>
			</div>
			
						
			<div class="campo">
				Nombre</br><input id="usuario_nom" type="text" name="usuario_nom" value="" class="textBox" onfocus="posicionamiento('usuario_nom','usuario_nom_error',1);" onblur="salida('usuario_nom','usuario_nom_error',1);"></br>
				<div class="error" id="usuario_nom_error">a</div>
			</div>
			
			<div class="campo">
				CUIL/CUIT</br><input id="usuario_cuil1" type="text" style="width:20px;" maxlength="2" name="usuario_cuil1" value="" class="textBox" onfocus="posicionamiento('usuario_cuil1','usuario_cuil2_error',14);" onblur="salida('usuario_cuil1','usuario_cuil2_error',14);">&nbsp - 
						 <input id="usuario_cuil2" type="text" style="width:70px;" maxlength="8" name="usuario_cuil2" value="" class="textBox" onfocus="posicionamiento('usuario_cuil2','usuario_cuil2_error',15);" onblur="salida('usuario_cuil2','usuario_cuil2_error',15);">&nbsp - 
						 <input id="usuario_cuil3" type="text" style="width:15px;" maxlength="1" name="usuario_cuil3" value="" class="textBox" onfocus="posicionamiento('usuario_cuil3','usuario_cuil2_error',16);" onblur="salida('usuario_cuil3','usuario_cuil2_error',16);">
				</br><div class="error" id="usuario_cuil2_error">a</div>
			</div>	
			
			
			<div class="campo">
				Mail</br><input id="usuario_MAIL" type="text" name="usuario_MAIL" class="textBox" value="" onfocus="posicionamiento('usuario_MAIL','usuario_MAIL_error',5);" onblur="salida('usuario_MAIL','usuario_MAIL_error',5);"></br>
				<div class="error" id="usuario_MAIL_error">a</div>
			</div>
			
			<div class="campo" style="padding-top:15px;">
				DNI</br><input id="usuario_DNI" type="text" maxlength="8" name="usuario_DNI" value="" class="textBox" onfocus="posicionamiento('usuario_DNI','usuario_DNI_error',7);" onblur="salida('usuario_DNI','usuario_DNI_error',7);"></br>
				<div class="error" id="usuario_DNI_error">a</div>
			</div>
			
			
			<div class="campo" style="padding-top:15px;">
				Teléfono</br>
				<input id="usuario_TEL_postal" type="text" style="width:40px;" maxlength="4" name="usuario_tel_postal" value="" class="textBox" onfocus="posicionamiento('usuario_TEL_postal','usuario_TEL_error',17);" onblur="salida('usuario_TEL_postal','usuario_TEL_error',17);">&nbsp - 
				<input id="usuario_TEL" type="text" style="width:60px;" name="usuario_TEL" value="" class="textBox" onfocus="posicionamiento('usuario_TEL','usuario_TEL_error',17);" onblur="salida('usuario_TEL','usuario_TEL_error',17);"></br>
				<div class="error" id="usuario_TEL_error">a</div>
			</div>
			
			
			<div class="campo" style="padding-top:15px;">
				Celular</br>
				<input id="usuario_CEL1" type="text" style="width:40px;" maxlength="5" name="usuario_CEL1" value="" class="textBox" onfocus="posicionamiento('usuario_CEL1','usuario_CEL_error',9);" onblur="salida('usuario_CEL1','usuario_CEL_error',9);">- 
				<label id="usuario_CEL2" >15-</label>
				<input id="usuario_CEL" type="text"  style="width:60px;" name="usuario_CEL" maxlength="8" value="" class="textBox" onfocus="posicionamiento('usuario_CEL','usuario_CEL_error',9);" onblur="salida('usuario_CEL','usuario_CEL_error',9);">
				<div class="error" id="usuario_CEL_error">a</div>
			</div>
			
			
			<div class="campo" style="padding-top:15px;">
				Rol</br><select id="comboRol" name="rol" style="width:150px;" name="rol">
											<option value="0">Seleccionar ---></option>
									 <?php
											$Query = "SELECT * FROM roles"; 
											$Res = mysql_query($Query,$conexion);
											$opciones = '';
											while($row = mysql_fetch_array($Res))
											   {
												$opciones = $opciones . '<option value="' . $row['idRol'] . '">' . $row['idRol'] . '- ' . $row['nombre'] . '</option>';
												}
											$opciones = $opciones . '</select>'; 
											echo $opciones;
									?>
										
				<div class="error" id="usuario_rol_error">a</div>								 
			</div>
			
			
			
	 </fieldset>
  
		
	</br>
	
	<input type="button" style="width:100px;" value="Cancelar" onclick="cancelar();">
	<input type="submit" style="width:100px;" value="Aceptar">  
	
	

	
</fieldset>
  
  
</center>

</body>
</html>