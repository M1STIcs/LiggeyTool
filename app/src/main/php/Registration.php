<?php
if($_SERVER['REQUEST_METHOD']=='POST'){

    include 'DatabaseConfig.php';

    $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

    $f_name = $_POST['f_name'];
    $l_name = $_POST['l_name'];
    $email = $_POST['email'];
    $password = $_POST['password'];
    $t_compte = $_POST['t_compte'];

    $CheckSQL = "SELECT * FROM Users WHERE user_email='$email'";

    $check = mysqli_fetch_array(mysqli_query($con,$CheckSQL));

    if(isset($check)){
        echo 'Cet E-mail existe déjà dans notre base de données !';
    }
    else{
        $Sql_Query = "INSERT INTO Users (user_firstname, user_lastname, user_email, user_password, user_type) values ('$f_name','$l_name','$email','$password', '$t_compte')";

        if(mysqli_query($con,$Sql_Query))
        {
            echo 'Inscription réussie !';
        }
        else
        {
            echo 'Echec Inscription, Recommencez !';
        }
     }
}
 mysqli_close($con);
?>