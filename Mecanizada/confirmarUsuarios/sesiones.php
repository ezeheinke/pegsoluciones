<?php
session_start();

// Normal user section - Not logged ------
require_once( $_SESSION["PATH_NAME"] . "DataBase/util.php");

    if(isset($_REQUEST['nick']) && isset($_REQUEST['contrasenia']))
		{
                // Section for logging process -----------
                $user = trim($_REQUEST['nick']);
                $pass = trim($_REQUEST['contrasenia']);
                //if($user == USER && $pass == PASS)
                if(isAdmin($user,$pass))
                    {
                        // Successful login ------------------
                        
                        // Setting Session
                        $_SESSION['user_capo'] = $user;
                        $_SESSION['key'] = encriptar($pass,"eze");


                        // Redirecting to the logged page.
                        require_once( $_SESSION["PATH_NAME"] . "paraConvalidar.php");
                    }
                else
                    {
                        // Wrong nick or contrasenia. Show logging again.
                    //session_unset();
                    //session_destroy();
                    $_SESSION['error']='nouser';
                    require_once( $_SESSION["PATH_NAME"] . "index.php");

                    }

            }
			
		

?>
