<?php

$db_name="gHostel";
$mysql_user="root";
$server_name="localhost";
$connection=mysqli_connect($server_name,$mysql_user,"",$db_name);
if($connection)
{
}
else
{
	echo "Not Connected";
}


?>
