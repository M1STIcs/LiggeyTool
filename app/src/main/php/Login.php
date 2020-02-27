<?php

 if($_SERVER['REQUEST_METHOD']=='POST'){

     include 'DatabaseConfig.php';

     $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

     $email = $_POST['email'];
     $password = $_POST['password'];

     $Sql_Query = "select * from Users where user_email = '$email' and user_password = '$password' ";

     $check = mysqli_fetch_array(mysqli_query($con,$Sql_Query));

     if(isset($check)){

        echo "Connexion réussie !";
     }
     else{
        echo "Identifiant ou mot de passe invalide. Veuillez réessayer !";
     }
 }
 else{
    echo "Recommencez !";
 }
mysqli_close($con);

?>