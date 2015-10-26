<?php
    if(!isset($_SESSION))
    {
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
<style>
body {
	background-color: #3D4751;
	}
</style>
<link href="login-box.css" rel="stylesheet" type="text/css" />
</head>

<body>

             <?php
                echo '<form name="loginform" action="'. $_SESSION["PATH_NAME"] .'sesiones.php" method="post">';?>
                <style type="text/css">
				table{
				border:1px solid black;
				}
				</style>

				<table >
					<tr>
						<td> Nick : </td><td> <input name="nick" size=15 type="text" /> </td>
					</tr>

                    <tr>
                        <td> Contraseña  : </td><td> <input name="contrasenia" size=15 type="password" /> </td>
                    </tr>

                </table>
                <input name="login"type="submit" value="login" /><?php
                                    if($error=="nouser"){
                                        echo '<a style="color:red;">  Usuario o contrase&ntilde;a incorrectos</a>';
                                    }
                                ?>
            <?php echo '</form>';?>



</body>
</html>
