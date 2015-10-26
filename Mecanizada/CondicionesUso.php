<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html xmlns="http://www.w3.org/1999/xhtml"><script type="text/javascript" src="js/jquery-1.6.js"></script>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>-->
<head runat="server">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 

    <title>Movimientos On-line</title>
	<style>		* {margin: 0 auto;}
	body {
	background-color: #3D4751;	margin: 0;	}
	.scrolleable	{	width: 650px;	height:350px;	margin-top:30px;	background-color:#ffffff;	text-align:left;	overflow: scroll;	border: thin solid #1F2933;	padding-left: 10px;	padding-bottom: 10px;	padding-top: 10px;	padding-right: 5px;	}	
	.titulo	{		color: black;		font-family: Arial, 'Lucida Sans Unicode', Helvetica, sans-serif;		font-weight: bold;		text-decoration: underline;		font-size: 20px;	}		.scrolleable p	{		color: black;		font-family: Arial, 'Lucida Sans Unicode', Helvetica, sans-serif;		font-size: 16px;	}

	.textoCondiciones {color: #FFFFFF; colo-size: 10px;}		#peg:hover	{	cursor: pointer;	}
    </style>
	
	<script language="JavaScript">
	function checkboxControl(control){
	if(document.getElementById(control).checked  == true)
		document.getElementById('aceptar').disabled = false;
     else
	    document.getElementById('aceptar').disabled = true;
	}			$(document).ready(function() {		document.getElementById('aceptar').disabled = true;		document.getElementById('check').checked = false;	});	
	
	</script>
	
</head>

<body>
<div style="background-image:url('imagenes/encabezado.jpg');width:939px;height:100px;margin: 0 auto"></div>

<div class="scrolleable">
<div class="titulo"><center>SOLICITUD DE ADHESIÓN Y ACCESO AL SISTEMA</center></div><br/><p>El sistema de Movimientos On Line permite la confección de la liquidación de mecanizadasde forma simple y desde cualquier PC conectada a Internet. El sistema prevé la confección del movimientopropiamente dicho, almacenamiento de datos, seguimiento de la presentación ante Dipregep y un asistentepara la guía del trámite. Permite confeccionar movimientos de alta, baja y modificación y tiene incorporadointeligencia de procedimiento que impide al usuario cometer algunas de las faltas más comunes por nuestraexperiencia detectadas, evitando errores que provocan el rechazo de las presentaciones. Esto facilita imprimirla planilla de movimientos de forma directa del sistema y a su vez permite guardarla localmente en dondeUd. lo desee (su PC, PenDrive, Cd, etc.) en un archivo formato pdf para su uso posterior para impresión,consulta, control, envío por mail, etc. Ud. puede probar por internet este nuevo sistema en modo demo en el linkcorrespondiente.</p><br/><p> Para utilizar el sistema principal Ud. deberá contar con nombres de usuarios y contraseñasde acceso habilitados para operar sobre su colegio. Cada usuario que Ud. solicite estará habilitado para operarexclusivamente con los datos de su colegio, identificado por su número de Dipregep. Si una misma personadesea operar el sistema con distintos colegios, contará con un usuario distinto e independiente para cada unode los colegios.</p><br/><p> El sistema Movimientos On Line es un aplicativo de uso gratuito exclusivo para los colegiosdependientes del Consejo de Educación Católica de la Provincia de Buenos Aires. Si su colegio tiene el aporteal CEC regularizado, solo deberá abonar una cuota equivalente mensual, por usuario, para el mantenimientotécnico operativo de $ 45. Los colegios adheridos al débito automático tendrán una bonificación de $ 15durante el primer semestre. Las claves otorgadas a los usuarios de su colegio serán de periodicidad anualy de renovación automática. No se le requerirá tener que efectuar un nuevo pedido de clave para manteneruna continuidad de uso. Cada usuario será identificado por su número de documento personal y número deDipregep del colegio. </p><br/><p>Se le permitirá al usuario el acceso al aplicativo para movimientos de su colegio. Ud.podrá solicitar más de un usuario para su colegio. El ingreso de datos al sistema así como el resguardo de lainformación resultante es de exclusiva responsabilidad del usuario, por tal motivo Ud. deberá implementar ymantener los controles que considere pertinentes.</p><br/><p>Sí desea adherir el colegio que representa y acepta las condiciones de uso aquí indicadas manifieste suconformidad seleccionando abajo el botón de Aceptar y proceder a ingresar los datos correspondientes a sucolegio.</p>

</div>	<center>
		<div style="padding-top:5px;">			<input id="check" type="checkbox" onClick="checkboxControl('check');">			<span class="textoCondiciones">&nbsp He Leído y acepto las condiciones de uso </span>		</div>
		<div style="padding-top:5px;">			<input type="button" id="cancelar" class="Cancelar" style="display:inline-block" onClick="location.href = 'index.php'" value=" Cancelar">&nbsp&nbsp&nbsp			<input type="button" id="aceptar"  class="Aceptar" style="display:inline-block" onClick="location.href = 'FormularioAlta.php'" disabled="true" value="Aceptar ">		</div>	</center>
		<div id="peg" class="peg" style="background:url('imagenes/piePeg.jpg') no-repeat;width:939px;height:50px;margin-top:50px;" onclick="window.open('http://www.pegsoluciones.com');"></div>
</body>
</html>