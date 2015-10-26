<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<head runat="server">
    <title>Formulario</title>
	
<body>

<?php

if(!($conexion = mysql_connect('localhost', 'gabi','gabi'))){echo'error';exit;}
if (!mysql_select_db("colegios",$conexion)){exit();}

$columna = '';
$dir = '';
if(isset($_GET['columna']) && isset($_GET['dir'])) 
	{
	$columna = $_GET['columna'];
	$dir = $_GET['dir'];
	$dirAnt = $_GET['dir'];
	$Query = "SELECT * FROM roles ORDER BY " . $columna . " " . $dir ; 
	$dir = ($dir == "ASC") ? "DESC" : "ASC";
	//echo $Query;
	}
else
	{
	$dir= 'DESC';
	$columna = '';
	$Query = "SELECT * FROM roles"; 
	}
	

$Res = mysql_query($Query,$conexion);



print '<table><tr class="titulo"><td>Roles</td></tr></table>';
print '<table>';
print '<tr class="header">';


if($columna == '')
{
print '<td><div class="link2" onclick="' .'$(\'#grilla\').load(' . '\'grilla.php?columna=idRol&' . 'dir=' . $dir . '\');">' . 'Rol' . '</div></td>';
print '<td><div class="link2" onclick="' .'$(\'#grilla\').load(' . '\'grilla.php?columna=nombre&' . 'dir=' . $dir . '\');">' . 'Nombre' . '</div></td>';

}


if($columna == 'idRol')
{
print '<td><div class="link2" onclick="' .'$(\'#grilla\').load(' . '\'grilla.php?columna=idRol&' . 'dir=' . $dir . '\');">' . 'Rol' . 
	  '<img src="imagenes/' . $dirAnt .'.gif"' . ' style="width:10px;height:10px;">' . '</div></td>';
print '<td><div class="link2" onclick="' .'$(\'#grilla\').load(' . '\'grilla.php?columna=nombre&' . 'dir=' . $dir . '\');">' . 'Nombre' . '</div></td>';

}

if($columna == 'nombre')
{
print '<td><div class="link2" onclick="' .'$(\'#grilla\').load(' . '\'grilla.php?columna=idRol&' . 'dir=' . $dir . '\');">' . 'Rol' . '</div></td>';
print '<td><div class="link2" onclick="' .'$(\'#grilla\').load(' . '\'grilla.php?columna=nombre&' . 'dir=' . $dir . '\');">' . 'Nombre' . 
	  '<img src="imagenes/' . $dirAnt . '.gif"' . ' style="width:10px;height:10px;">' . '</div></td>';


}


print '<td></td>';
print '</tr>';



while($row = mysql_fetch_array($Res))
{
	print '<tr>';
	print '<td>' . $row['idRol'] . '</td>';
	print '<td>' . $row['nombre'] . '</td>';
	print '<td><input type="checkbox"' . 'id="' . $row['idRol'] . '" onclick="agregar(' . $row['idRol'] .  ');"/></td>';
	print '<tr>';
	
}


print '</table>';



?>


</body>
<html>