<?php

$codigo2= 'asdada';
$codigo1= '1231313';
date_default_timezone_set('America/Argentina/Buenos_Aires');
$fecha = date("YmdHis");

$query = "update altas set estado = 2 , fechaConfirmacion = " . $fecha  . " , codigo= '" . $codigo2 . "' where codigo = '" . $codigo1 . "'";

echo $query;
		
?>