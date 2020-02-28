<?php
     include 'DatabaseConfig.php';

   /* disable cache in browser */

   header('Cache-Control: no-cache, must-revalidate');
  // header('Expires: Mon, 26 Jul 1997 05:00:00 GMT');

   /* encode an sql query */

   function encodequery($query) {
   //Define your host here.
   $HostName = "localhost";

   //Define your database username here.
   $HostUser = "root";

   //Define your database password here.
   $HostPass = "";

   //Define your database name here.
   $DatabaseName = "liggeytool";
      /* sql connnection link - fill your details*/
      $link = mysql_connect($HostName,$HostUser,$HostPass)
      or die('connection to the DB failed.');
      mysql_select_db($DatabaseName,$link) or die('Cannot find DB.');
      $result = mysql_query($query,$link) or die('Error in query: '.$query);
      /* create one master array of the records */
      $posts = array();
      if(mysql_num_rows($result)) {
         while($post = mysql_fetch_assoc($result)) {
            $posts[] = array('posts'=>$posts);
         }
      }
      encodearray($posts);
   }

   /* Encode array to JSON string */
   function encodearray($posts) {
      header('Content-type: application/json');
      echo json_encode(array('posts'=>$posts));
   }


   /* grab the posts from the db */
   $query = "SELECT * FROM users";
   encodequery($query);
?>