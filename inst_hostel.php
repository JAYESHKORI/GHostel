<?php
require "init.php";
/*
$name="Girls Hostel";
$description="Only for Girls";
$type="Girls";
*/
$name=$_POST["hostelName"];
$description=$_POST["descrption"];
$type=$_POST["type"];

$sql = mysqli_query($connection,"INSERT INTO `hosteldata`(`hostelid`, `name`, `description`, `type`) VALUES (NULL,'$name','$description','$type')");

if($sql)
{
	echo "Hostel Added";
}
else
{
		echo "error";
}

?>
