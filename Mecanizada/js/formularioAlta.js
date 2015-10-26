	
	var imagenError = '<img id="imgError" src="imagenes/error.png" style="width:15px;height:15px;">';
	var imagenInfo = '<img id="imgError" src="imagenes/info.png" style="width:15px;height:15px;">';
	var imagenOk = '<img id="imgError" src="imagenes/ok.png" style="width:15px;height:15px;">';
	var imagenFalta = '<img id="imgError" src="imagenes/falta.png" style="width:15px;height:15px;">';
	
	function posicionamiento(idObjeto,idObjetoError,valor)
	{
	 var texto;
	 document.getElementById(idObjeto).className = 'textBoxMarcado';
	 switch(valor)
	  { 
		case 1 : texto = 'Ingrese Nombre';break;
		case 2 : texto = 'Ingrese Localidad';break;
		case 3 : texto = 'Ingrese Nro Dipregep';break;
		case 4 : texto = 'Ingrese Apellido';break;
		case 5 : texto = 'Ingrese Mail';break;
		case 6 : texto = 'Ingrese Cargo';break;
		case 7 : texto = 'Ingrese DNI';break;
		case 17:
		case 8 : texto = 'Ingrese TEL';break;
		case 9 : texto = 'Ingrese CEL';break;
		case 10: texto = 'Ingrese Direccion';break;
		case 11:
		case 12:
		case 13: texto = 'Ingrese Cuit';break;
		case 14:
		case 15:
		case 16: texto = 'Ingrese Cuil/Cuit';break;
		
	
		
	  }
	
	switch(valor)
	{ case 1:
	  case 2:
	  case 3:
	  case 4:
	  case 5:
	  case 6:
	  case 7:
	  case 8:
	  case 9:
	  case 10: 
	  case 17: if(document.getElementById(idObjeto).value == "")
				{document.getElementById(idObjetoError).innerHTML = imagenInfo + '<font size=1>'+texto+'</font>';
				 document.getElementById(idObjetoError).style.visibility = 'visible';break;}
	  case 11:
	  case 12:
	  case 13: if(document.getElementById('col_cuil1').value == "" || 
				 document.getElementById('col_cuil2').value == "" ||
				 document.getElementById('col_cuil3').value == "")
				{document.getElementById(idObjetoError).innerHTML = imagenInfo + '<font size=1>'+texto+'</font>';
				 document.getElementById(idObjetoError).style.visibility = 'visible';break;}
	  case 14:
	  case 15:
	  case 16: if(document.getElementById('usuario_cuil1').value == "" ||
				  document.getElementById('usuario_cuil2').value == "" || 
				  document.getElementById('usuario_cuil3').value == "")
				  {document.getElementById(idObjetoError).innerHTML = imagenInfo + '<font size=1>'+texto+'</font>';
				  document.getElementById(idObjetoError).style.visibility = 'visible';break;}
	    
	}
	
	}
	
	
	function salida(idObjeto,idObjetoError,valor)
	{
		
	document.getElementById(idObjeto).className = 'textBox';
	if(Trim(document.getElementById(idObjeto).value) == "")
		{
		 document.getElementById(idObjetoError).innerHTML = 'a';
		 document.getElementById(idObjetoError).style.visibility = 'hidden';
		}
		
	
	
	 else 
	    switch(valor)
		{
		case 4 : if(SonLetras(document.getElementById(idObjeto).value))
				 {document.getElementById(idObjeto).value = ucWords(Trim(document.getElementById(idObjeto).value));
				 document.getElementById(idObjetoError).innerHTML = imagenOk + '<font size=1>'+'OK'+'</font>';
				 document.getElementById(idObjetoError).style.visibility = 'visible';}
				 else{
				 document.getElementById(idObjeto).value = '';
				 document.getElementById(idObjetoError).innerHTML = imagenError + '<font size=1>'+'Ingrese Solo Letras'+'</font>';
				 document.getElementById(idObjetoError).style.visibility = 'visible';
				 }break;
		case 7 : if(EsNumerico(Trim(document.getElementById(idObjeto).value)) && (Trim(document.getElementById(idObjeto).value)).length > 5){
				 document.getElementById(idObjeto).value = limpiarCadena(Trim(document.getElementById(idObjeto).value));
				 document.getElementById(idObjetoError).innerHTML = imagenOk + '<font size=1>'+'OK'+'</font>';
				 document.getElementById(idObjetoError).style.visibility = 'visible';break;}
				 else{
					if(!EsNumerico(Trim(document.getElementById(idObjeto).value)))
						{document.getElementById(idObjeto).value = '';
						document.getElementById(idObjetoError).innerHTML = imagenError + '<font size=1>'+'Ingrese Solo Numeros'+'</font>';
						document.getElementById(idObjetoError).style.visibility = 'visible';break;}
				    else
						{document.getElementById(idObjeto).value = '';
						document.getElementById(idObjetoError).innerHTML = imagenError + '<font size=1>'+'Pocos Digitos'+'</font>';
						document.getElementById(idObjetoError).style.visibility = 'visible';break;}
				 }break;
		
		
		
		
		
		case 8 : if((EsNumerico(Trim(document.getElementById('col_tel').value))) && document.getElementById('col_tel').value != "")
		         {
					if((EsNumerico(Trim(document.getElementById('col_tel_postal').value))) && document.getElementById('col_tel_postal').value != "")
					   {document.getElementById(idObjetoError).innerHTML = imagenOk + '<font size=1>'+'OK'+'</font>';
					   document.getElementById(idObjetoError).style.visibility = 'visible';break;}
					else
					 {document.getElementById(idObjetoError).innerHTML = imagenError + '<font size=1>'+'Formato Incorrecto'+'</font>';
					  document.getElementById(idObjetoError).style.visibility = 'visible';break;}
					 
				 } 
				 else
				 {document.getElementById(idObjetoError).innerHTML = imagenError + '<font size=1>'+'Formato Incorrecto'+'</font>';
				  document.getElementById(idObjetoError).style.visibility = 'visible';break;}
				
		
		case 9 : 
				 if(EsNumerico(Trim(document.getElementById('usuario_CEL').value)) && (Trim(document.getElementById('usuario_CEL').value)).length > 5)
				     if(EsNumerico(Trim(document.getElementById('usuario_CEL1').value)) && document.getElementById('usuario_CEL1').value != '' )
						{document.getElementById('usuario_CEL1').value = limpiarCadena(Trim(document.getElementById('usuario_CEL1').value));
						 document.getElementById(idObjetoError).innerHTML = imagenOk + '<font size=1>'+'OK'+'</font>';
						 document.getElementById(idObjetoError).style.visibility = 'visible';break;}
					 else
					 {document.getElementById('usuario_CEL1').value = '';
					  document.getElementById(idObjetoError).innerHTML = imagenError + '<font size=1>'+'Formato Incorrecto'+'</font>';
					  document.getElementById(idObjetoError).style.visibility = 'visible';break;}
				else{
				    document.getElementById('usuario_CEL').value = '';
					document.getElementById(idObjetoError).innerHTML = imagenError + '<font size=1>'+'Formato Incorrecto'+'</font>';
					document.getElementById(idObjetoError).style.visibility = 'visible';break;}
							   
				   
				 break;
				 
		case 3 : var len = (Trim(document.getElementById(idObjeto).value)).length;
				 if(EsNumerico(Trim(document.getElementById(idObjeto).value)) && (len < 5 ) &&  (len > 0))
					    {document.getElementById(idObjeto).value = limpiarCadena(Trim(document.getElementById(idObjeto).value));
				      	 document.getElementById(idObjetoError).innerHTML = imagenOk + '<font size=1>'+'OK'+'</font>';
						 document.getElementById(idObjetoError).style.visibility = 'visible';break;}
				else{document.getElementById(idObjeto).value = '';
					 document.getElementById(idObjetoError).innerHTML = imagenError + '<font size=1>'+'Numero Incorrecto'+'</font>';
					 document.getElementById(idObjetoError).style.visibility = 'visible';break;}
					 
		case 5 : if(validarEmail(document.getElementById(idObjeto).value)){
				 document.getElementById(idObjetoError).innerHTML = imagenOk + '<font size=1>'+'OK'+'</font>';
				 document.getElementById(idObjetoError).style.visibility = 'visible';}
				 else{
				 document.getElementById(idObjetoError).innerHTML = imagenError + '<font size=1>'+'Formato Incorrecto'+'</font>';
				 document.getElementById(idObjetoError).style.visibility = 'visible';break;}
		


        case 1:
        case 2:		
		case 10: if((document.getElementById(idObjeto).value).length > 2)
				{document.getElementById(idObjetoError).innerHTML = imagenOk + '<font size=1>'+'OK'+'</font>';
				 document.getElementById(idObjetoError).style.visibility = 'visible';break;}
		case 11:
		case 12:
		case 13:
		        if((EsNumerico(Trim(document.getElementById('col_cuil1').value))) && document.getElementById('col_cuil1').value != "" && (Trim(document.getElementById('col_cuil1').value)).length == 2)
				{    
					if((EsNumerico(Trim(document.getElementById('col_cuil2').value))) && document.getElementById('col_cuil2').value != "" && (Trim(document.getElementById('col_cuil2').value)).length == 8)
					{ 		
							if((EsNumerico(Trim(document.getElementById('col_cuil3').value))) && document.getElementById('col_cuil3').value != "" && (Trim(document.getElementById('col_cuil3').value)).length == 1)
							     {document.getElementById(idObjetoError).innerHTML = imagenOk + '<font size=1>'+'OK'+'</font>';
								 document.getElementById(idObjetoError).style.visibility = 'visible';break;}
							else
								{document.getElementById(idObjetoError).innerHTML = imagenError + '<font size=1>'+'Formato Incorrecto'+'</font>';
								 document.getElementById(idObjetoError).style.visibility = 'visible';break;}
					}
					else 
					{document.getElementById(idObjetoError).innerHTML = imagenError + '<font size=1>'+'Formato Incorrecto'+'</font>';
					 document.getElementById(idObjetoError).style.visibility = 'visible';break;}
			    }
			    else
				{document.getElementById(idObjetoError).innerHTML = imagenError + '<font size=1>'+'Formato Incorrecto'+'</font>';
				 document.getElementById(idObjetoError).style.visibility = 'visible';break;}
				 break;	 
				
		case 14:
		case 15:
		case 16: if((EsNumerico(Trim(document.getElementById('usuario_cuil1').value))) && document.getElementById('usuario_cuil1').value != "" && (Trim(document.getElementById('usuario_cuil1').value)).length == 2)
				{    
					if((EsNumerico(Trim(document.getElementById('usuario_cuil2').value))) && document.getElementById('usuario_cuil2').value != "" && (Trim(document.getElementById('usuario_cuil2').value)).length == 8)
					{ 		
							if((EsNumerico(Trim(document.getElementById('usuario_cuil3').value))) && document.getElementById('usuario_cuil3').value != "" && (Trim(document.getElementById('usuario_cuil3').value)).length == 1)
							     {document.getElementById(idObjetoError).innerHTML = imagenOk + '<font size=1>'+'OK'+'</font>';
								 document.getElementById(idObjetoError).style.visibility = 'visible';break;}
							else
								{document.getElementById(idObjetoError).innerHTML = imagenError + '<font size=1>'+'Formato Incorrecto'+'</font>';
								 document.getElementById(idObjetoError).style.visibility = 'visible';break;}
					}
					else 
					{document.getElementById(idObjetoError).innerHTML = imagenError + '<font size=1>'+'Formato Incorrecto'+'</font>';
					 document.getElementById(idObjetoError).style.visibility = 'visible';break;}
			    }
			    else
				{document.getElementById(idObjetoError).innerHTML = imagenError + '<font size=1>'+'Formato Incorrecto'+'</font>';
				 document.getElementById(idObjetoError).style.visibility = 'visible';break;}
				 break;
		
		case 17: if((EsNumerico(Trim(document.getElementById('usuario_TEL').value))) && document.getElementById('usuario_TEL').value != "")
		         {
					if((EsNumerico(Trim(document.getElementById('usuario_TEL_postal').value))) && document.getElementById('usuario_TEL_postal').value != "")
					   {document.getElementById(idObjetoError).innerHTML = imagenOk + '<font size=1>'+'OK'+'</font>';
					   document.getElementById(idObjetoError).style.visibility = 'visible';break;}
					else
					 {document.getElementById(idObjetoError).innerHTML = imagenError + '<font size=1>'+'Formato Incorrecto'+'</font>';
					  document.getElementById(idObjetoError).style.visibility = 'visible';break;}
					 
				 } 
				 else
				 {document.getElementById(idObjetoError).innerHTML = imagenError + '<font size=1>'+'Formato Incorrecto'+'</font>';
				  document.getElementById(idObjetoError).style.visibility = 'visible';break;}
		}
		
	}
	
	
	
	function comprobar(){
	comprobado = true;
	if((document.getElementById('col_nom_error').innerHTML).indexOf('OK') < 0 )
		{document.getElementById('col_nom_error').innerHTML = imagenError + '<font size="1">'+'Campo Requerido'+'</font>';
		 document.getElementById('col_nom_error').style.visibility = 'visible';
		 comprobado = false;}
		
		
	if((document.getElementById('col_dir_error').innerHTML).indexOf('OK') < 0 )
		{document.getElementById('col_dir_error').innerHTML = imagenError + '<font size="1">'+'Campo Requerido'+'</font>';
		 document.getElementById('col_dir_error').style.visibility = 'visible';
		 comprobado = false;}
	
	
	if((document.getElementById('col_localidad_error').innerHTML).indexOf('OK') < 0 )
		{document.getElementById('col_localidad_error').innerHTML = imagenError + '<font size="1">'+'Campo Requerido'+'</font>';
		 document.getElementById('col_localidad_error').style.visibility = 'visible';
		 comprobado = false;}
		 
	
	
	
	if((document.getElementById('col_tel_error').innerHTML).indexOf('OK') < 0 )
		{document.getElementById('col_tel_error').innerHTML = imagenError + '<font size="1">'+'Campo Requerido'+'</font>';
		 document.getElementById('col_tel_error').style.visibility = 'visible';
		 comprobado = false;}
		 
		 
	if( EsNumerico(Trim(document.getElementById('col_cuil1').value)) && (Trim(document.getElementById('col_cuil1').value)).length > 0 &&
		EsNumerico(Trim(document.getElementById('col_cuil2').value)) && (Trim(document.getElementById('col_cuil2').value)).length > 0 &&
		EsNumerico(Trim(document.getElementById('col_cuil3').value)) && (Trim(document.getElementById('col_cuil3').value)).length > 0) 
		{;}
	else	
		{document.getElementById('col_cuil2_error').innerHTML = imagenError + '<font size="1">'+'Campo Requerido'+'</font>';
		 document.getElementById('col_cuil2_error').style.visibility = 'visible';
		 comprobado = false;}
		
	    
		 
		 
	if((document.getElementById('col_nroDipr_error').innerHTML).indexOf('OK') < 0 )
		{document.getElementById('col_nroDipr_error').innerHTML = imagenError + '<font size="1">'+'Campo Requerido'+'</font>';
		 document.getElementById('col_nroDipr_error').style.visibility = 'visible';
		 comprobado = false;}
	
	
	if((document.getElementById('sol_nom_error').innerHTML).indexOf('OK') < 0 )
		{document.getElementById('sol_nom_error').innerHTML = imagenError + '<font size="1">'+'Campo Requerido'+'</font>';
		 document.getElementById('sol_nom_error').style.visibility = 'visible';
		 comprobado = false;} 

	if((document.getElementById('sol_apellido_error').innerHTML).indexOf('OK') < 0 )
		{document.getElementById('sol_apellido_error').innerHTML = imagenError + '<font size="1">'+'Campo Requerido'+'</font>';
		  document.getElementById('sol_apellido_error').style.visibility = 'visible';
		 comprobado = false;}

		 
	if((document.getElementById('sol_mail_error').innerHTML).indexOf('OK') < 0 )
		{document.getElementById('sol_mail_error').innerHTML = imagenError + '<font size="1">'+'Campo Requerido'+'</font>';
		 document.getElementById('sol_mail_error').style.visibility = 'visible';
		 comprobado = false;}
		 
		 
	 
	if((document.getElementById('usuario_apellido_error').innerHTML).indexOf('OK') < 0 )
		{document.getElementById('usuario_apellido_error').innerHTML = imagenError + '<font size="1">'+'Campo Requerido'+'</font>';
		 document.getElementById('usuario_apellido_error').style.visibility = 'visible';
		 comprobado = false;}
	
	
	if((document.getElementById('usuario_nom_error').innerHTML).indexOf('OK') < 0 )
		{document.getElementById('usuario_nom_error').innerHTML = imagenError + '<font size="1">'+'Campo Requerido'+'</font>';
		 document.getElementById('usuario_nom_error').style.visibility = 'visible';
		 comprobado = false;}
	
		 
	if( EsNumerico(Trim(document.getElementById('usuario_cuil1').value)) && (Trim(document.getElementById('usuario_cuil1').value)).length > 0 &&
		EsNumerico(Trim(document.getElementById('usuario_cuil1').value)) && (Trim(document.getElementById('usuario_cuil1').value)).length > 0 &&
		EsNumerico(Trim(document.getElementById('usuario_cuil1').value)) && (Trim(document.getElementById('usuario_cuil1').value)).length > 0) 
		{;}
	else	
		{document.getElementById('usuario_cuil2_error').innerHTML = imagenError + '<font size="1">'+'Campo Requerido'+'</font>';
		 document.getElementById('usuario_cuil2_error').style.visibility = 'visible';
		 comprobado = false;}
		 
	
	
	if((document.getElementById('usuario_DNI_error').innerHTML).indexOf('OK') < 0 )
		{document.getElementById('usuario_DNI_error').innerHTML = imagenError + '<font size="1">'+'Campo Requerido'+'</font>';
		 document.getElementById('usuario_DNI_error').style.visibility = 'visible';
		 comprobado = false;}
	
	if((document.getElementById('usuario_TEL_error').innerHTML).indexOf('OK') < 0 )
		{document.getElementById('usuario_TEL_error').innerHTML = imagenError + '<font size="1">'+'Campo Requerido'+'</font>';
		 document.getElementById('usuario_TEL_error').style.visibility = 'visible';
		 comprobado = false;}
	
	if((document.getElementById('usuario_CEL_error').innerHTML).indexOf('OK') < 0 )
		{document.getElementById('usuario_CEL_error').innerHTML = imagenError + '<font size="1">'+'Campo Requerido'+'</font>';
		 document.getElementById('usuario_CEL_error').style.visibility = 'visible';
		 comprobado = false;}
		 
	if((document.getElementById('usuario_MAIL_error').innerHTML).indexOf('OK') < 0 )
		{document.getElementById('usuario_MAIL_error').innerHTML = imagenError + '<font size="1">'+'Campo Requerido'+'</font>';
		 document.getElementById('usuario_MAIL_error').style.visibility = 'visible';
		 comprobado = false;}
	 
	 
	if(document.getElementById('combo').value == '0' )
		{document.getElementById('sol_cargo_error').innerHTML = imagenError + '<font size="1">'+'Seleccione Cargo'+'</font>';
	  document.getElementById('sol_cargo_error').style.visibility = 'visible';
		 comprobado = false;} 
	
	if(document.getElementById('comboRol').value == '0')
	 {document.getElementById('usuario_rol_error').innerHTML = imagenError + '<font size="1">'+'Seleccion Rol'+'</font>';
	  document.getElementById('usuario_rol_error').style.visibility = 'visible';
		 comprobado = false;}
	
	return comprobado;
	
	
	}
	
	
	
	
	function Trim( str ) {
		var resultStr = "";
		resultStr = TrimLeft(str);
		resultStr = TrimRight(resultStr);
		return resultStr;
	}

	function TrimLeft( str ) {
		var resultStr = "";
		var i = len = 0;
		if (str+"" == "undefined" || str == null)	
		return "";
		str += "";

		if (str.length == 0) 
		resultStr = "";
		else {	
		len = str.length;
		while ((i <= len) && (str.charAt(i) == " "))
		i++;
		resultStr = str.substring(i, len);
		}
		return resultStr;
	}

	function TrimRight( str ) {
		var resultStr = "";
		var i = 0;
		if (str+"" == "undefined" || str == null)	
		return "";
		str += "";
		if (str.length == 0) 
		resultStr = "";
		else {
		i = str.length - 1;
		while ((i >= 0) && (str.charAt(i) == " "))
		i--;
		resultStr = str.substring(0, i + 1);
			}

		return resultStr; 
	}
	

	function ucWords(string){
		 var arrayWords;
		 var returnString = "";
		 var len;
		 arrayWords = string.split(" ");
		 len = arrayWords.length;
		 for(i=0;i < len ;i++){
		  if(i != (len-1)){
		   returnString = returnString+ucFirst(arrayWords[i])+" ";
		  }
		  else{
		   returnString = returnString+ucFirst(arrayWords[i]);
		  }
		 }
		 return returnString;
	}
	
	function ucFirst(string){
	 return string.substr(0,1).toUpperCase()+string.substr(1,string.length).toLowerCase();
	}
	
	
	function EsNumerico(texto)

	{
	   var caracteresValidos = "0123456789.-";
	   var EsNumero=true;
	   var Char;
 
    if(texto == "")
       EsNumero = false;
	else
	   for (i = 0; i < texto.length && EsNumero == true; i++) 
		  { 
		  Char = texto.charAt(i); 
		  if (caracteresValidos.indexOf(Char) == -1) 
			 {
			 EsNumero = false;
			 }
		  }
		  
	   return EsNumero;
   
   }
   
   /*
   	function SonLetrasG(texto)

	{
	   var caracteresValidos = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ";
	  
	   var valido =true;
	   var Char;

	 
	   for (i = 0; i < texto.length && valido == true; i++) 
		  { 
		  Char = texto.charAt(i); 
		  if (caracteresValidos.indexOf(Char) == -1 ) 
			 {
			 valido = false;
			 }
		  }
	   return valido;
   
   }
   */
   /*UNICODE
   a -z -> 97 a 122
A- Z -> 65 a 90
á 225
é 223
í´237
ó 243
ú 250
  32
Á 193
É 201
Í  205
Ó  211
Ú  218
ñ  241
Ñ 209
   
   */
   
   	function SonLetras(texto) 
	{
	
		var Char;
		var valido = true;
		
		for (i = 0; i < texto.length ; i++) {
			Char = texto.charCodeAt(i);
			if (  (Char >= 97 && Char <= 122 ) || (Char >= 65 && Char <= 90)  || Char == 225 || Char == 223 || Char ==   237 || Char ==  243 || Char ==  250 || Char == 32 || Char == 193 || Char ==  201 || Char == 205 || Char == 211 || Char ==  218 || Char == 241 || Char == 209  ){
				valido = true;
			}
			else {
				valido = false;
				break;
				}
		}
		
		return valido;
   
   }
   
	
	function limpiarCadena (cadena,caracteres) {
	 
		 especiales = new Array('á','é','í','ó','ú','ñ',' ','´',':',',',';','.','-');
		 i=0;
		 while (i<especiales.length) {
		  cadena = cadena.split(especiales[i]).join('');
		  i++
		 }

		 return cadena;

	}
	
	
	function validarEmail(original) {
		 //comprobar @
		 arroba = original.indexOf('@', 0);
		 valida = true;
		 if(arroba > 0)
			if(original.indexOf('@',arroba + 1) > 0)
			     return false; //mas de 2
			else valida = true;
		else return false; //ni 1
		 
		 //comprobar dominio
		arroba2 = original.indexOf('.',arroba + 3);
		if( arroba2 < 0)
		     return false;
		else valida = true;
		
		/*mailDspPunto = original.substring(arroba2 +1,original.length);
		
		if(!SonLetras(mailDspPunto))
		     return false;
		else valida = true;*/
		 
    	return valida;
	}
	

	function cancelar()
	{
	  if(confirm('Se perderan todos sus datos y se lo redireccionara a la pantalla principal. ¿Desea Continuar?'))
			location.href = 'index.php';
	
	}
	