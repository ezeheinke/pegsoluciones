<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<head runat="server">
    <title>Formulario</title>
	
<body>
<form>


<table id="myTable" class="tablesorter">
<thead>
<tr>
	<th class="header">Last Name</th>
	<th>First Name</th>
	<th>Email</th>
	<th>Due</th>
	<th>Web Site</th>
</tr>
</thead>
<tbody>
<tr>
	<td>Smith</td>
	<td>John</td>
	<td>jsmith@gmail.com</td>
	<td>$50.00</td>
	<td>http://www.jsmith.com</td>
</tr>
<tr>
	<td>Bach</td>
	<td>Frank</td>
	<td>fbach@yahoo.com</td>
	<td>$50.00</td>
	<td>http://www.frank.com</td>
</tr>
<tr>
	<td>Doe</td>
	<td>Jason</td>
	<td>jdoe@hotmail.com</td>
	<td>$100.00</td>
	<td>http://www.jdoe.com</td>
</tr>
<tr>
	<td>Conway</td>
	<td>Tim</td>
	<td>tconway@earthlink.net</td>
	<td>$50.00</td>
	<td>http://www.timconway.com</td>
</tr>
</tbody>
</table>


<?php

if(!($conexion = mysql_connect('localhost', 'gabi','gabi'))){echo'error';exit;}
if (!mysql_select_db("colegios",$conexion)){exit();}
$Query = "SELECT * FROM roles"; 
$Res = mysql_query($Query,$conexion);

print '<table>';
print '<tr class="titulo"><td>Roles</td></tr></table>';
print '</table>';

print '<table id="mytable" class=""tablesorter>';
print '<thead>';
print '<tr>';
print '<th>idRol</th>';
print '<th>Nombre</th>';
print '</tr></thead>';

print '<tbody>';

while($row = mysql_fetch_array($Res))
{
	print '<tr>';
	print '<td>' . $row['idRol'] . '</td>';
	print '<td>' . $row['nombre'] . '</td>';
	print '<td><input type="checkbox"' . 'id="' . $row['idRol'] . '" onclick="agregar(' . $row['idRol'] .  ');"/></td>';
	print '</tr>';
	
}

print '</tbody>';
print '</table>';


?>

</form>
</body>
<html>