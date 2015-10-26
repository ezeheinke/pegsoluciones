<?php
if(!isset($_SESSION))
	session_start();
if (!isset ($_SESSION["PATH_NAME"]))
    $_SESSION["PATH_NAME"] = "";
if (isset ($_SESSION["user_capo"]))
    require_once("paraConvalidar.php");
else
    require_once("login.php");
?>