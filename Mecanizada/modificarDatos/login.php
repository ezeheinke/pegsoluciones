<?php
    if(!isset($_SESSION)) {
    session_start();
    }
if (!isset ($_SESSION["PATH_NAME"]))
        $_SESSION["PATH_NAME"] = "";

    if (isset ($_SESSION["user"]))
        require_once("modificarDatos.php");
    else
        require_once("index.php");

    if(isset ($_COOKIE["error"])){
        $error = $_COOKIE["error"];
        setcookie("error","");
    }

?>

<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Movimientos on-line</title>
<link href="login-box.css" rel="stylesheet" type="text/css" />

<style>
body {
	background-color: #3D4751;
	}
</style>

</head>

<body>

		
	<div style="padding: 100px 0 0 250px;">
	<div id="login-box">
    
			<?php
                echo '<form name="loginform" action="'. $_SESSION["PATH_NAME"] .'sesiones.php" method="post">';?>
                <style type="text/css">
				table{
				border:1px solid black;
				}
				</style>
				<H2>Login</H2>
				A continuación ingrese su nick (nro. Dipregep + DNI) y contraseña 
				<!-- <table >
					<tr> -->
						<div id="login-box-name" style="margin-top:20px;">Nick:</div><div id="login-box-field" style="margin-top:20px;"><input name="nick" class="form-login" title="Nick" value="" size="30" maxlength="2048" /></div>
						<!-- <td> Nick : </td><td> <input name="nick" size=15 type="text" /> </td>  -->
					<!-- </tr>

                    <tr> -->
						<div id="login-box-name">Contraseña:</div><div id="login-box-field"><input name="contrasenia" type="password" class="form-login" title="Contraseña" value="" size="30" maxlength="2048" /></div>
                       <!-- <td> Contraseña  : </td><td> <input name="contrasenia" size=15 type="password" /> </td> -->
                   <!-- </tr>

                </table> -->
				
                <INPUT TYPE="image" SRC="images/login-btn.png" width="103" height="42" style="margin-left:90px;" BORDER="0" ALT="Submit Form"><?php
                                    if($_SESSION['error']=="nouser"){
                                        echo '<a style="color:red;">  Usuario o contrase&ntilde;a incorrectos</a>';
                                    }
                                ?>
				
            <?php echo '</form>';?>



</body>
</html>
