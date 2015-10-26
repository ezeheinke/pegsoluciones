<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<head runat="server">
    <title>PEG-Soluciones</title>
	<link runat="server" href="../css/estilos.css" rel="stylesheet" type="text/css" />
	
	<style>
	.scrolleable
	{
		width: 550px;
		height:400px;
		margin-top:100px;
		background-color:#ffffff; 
		text-align:left;
		overflow: scroll;"
	}
	
	.titulo
	{
		color: black;
		font-family: Arial, 'Lucida Sans Unicode', Helvetica, sans-serif;
		font-weight: bold;
		text-decoration: underline;
	
	}
	
	</style>
	
	<script language="JavaScript">
	function checkboxControl(control){
	if(document.getElementById(control).checked  == true)
		document.getElementById('aceptar').disabled = false;
     else
	    document.getElementById('aceptar').disabled = true;
	}
	
	</script>
	
</head>

<body>

<center>
<div class="scrolleable">
<div class="titulo"><center>SOLICITUD DE ADHESION Y ACCESO AL SISTEMA</center></div>
<p>Condiciones de uso: el sistema de Movimientos On Line permite la confecci�n de la liquidaci�n 
de mecanizadas de forma simple y desde cualquier PC conectada a Internet.</p>
<p> El sistema prev� la confecci�n del movimiento propiamente dicho, almacenamiento de datos, 
seguimiento de la presentaci�n ante Dipregep y un asistente para la gu�a del tr�mite.</p>
Permite confeccionar movimientos de alta, baja y modificaci�n y tiene incorporado inteligencia de 
procedimiento que impide al usuario cometer algunas de las faltas m�s comunes por nuestra experiencia detectadas,
evitando errores que provocan el rechazo de las presentaciones. Esto facilita imprimir la planilla de movimientos
de forma directa del sistema y a su vez permite guardarla localmente en donde Ud. lo desee (su PC, PenDrive, Cd, etc.)
en un archivo formato pdf para su uso posterior para impresi�n, consulta, control, env�o por mail, etc. </p>
<p>Ud. puede probar por internet este nuevo sistema en modo demo en: www.cecba-docs.com.ar/demo. 
Para utilizar el sistema deber� contar con nombres de usuarios y contrase�as de acceso habilitados para 
operar sobre su colegio. El sistema Movimientos On Line es un aplicativo de uso gratuito exclusivo para los 
colegios dependientes del Consejo de Educaci�n Cat�lica de la Provincia de Buenos Aires.  Si su colegio tiene el 
pago al CEC al d�a, solo deber� abonar una cuota equivalente mensual por usuario para mantenimiento t�cnico 
operativo de $ 45.-. Los colegios adheridos al d�bito autom�tico tendr�n una bonificaci�n de $ 15 durante el 
semestre. Las claves otorgadas a los usuarios de los colegios ser�n de periodicidad anual y de renovaci�n autom�tica. 
No se requerir� tener que efectuar un nuevo pedido de clave para mantener una continuidad de servicio. 
Cada usuario ser� identificado por su n�mero de documento y tendr� acceso al aplicativo para movimientos vinculados 
a su colegio, que ser� identificado por el correspondiente n�mero de Dipregep. Se podr� solicitar m�s de un usuario 
por colegio. 
<p>El ingreso de datos al sistema es de exclusiva responsabilidad del usuario que lo realiza, por tal motivo toda 
liquidaci�n de movimientos deber� seguir manteniendo los controles pertinentes.
S� desea adherir el colegio que representa y acepta las condiciones de uso aqu� indicadas manifieste su conformidad 
seleccionando abajo el bot�n de Aceptar y proceder a ingresar los datos correspondientes a su colegio.</p>

</div>


</br></br>

<div>
<input id="check" type="checkbox" onclick="checkboxControl('check');">&nbsp He Leido y acepto las condiciones de uso
</div>

</br>
<div>
<input type="button" id="cancelar" class="Cancelar" style="display:inline-block" value="� Cancelar">&nbsp&nbsp&nbsp
<input type="button" id="aceptar"  class="Aceptar" style="display:inline-block" onclick="location.href = 'FormularioAlta.php'" disabled="true" value="Aceptar �">
</div>
</center>


</body>
</html>