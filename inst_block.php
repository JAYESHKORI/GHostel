<?php
require "init.php";
/*
$name="Block A";
$hostelid=6;
$type="Girls";
$capacity="300";
*/

$name=$_POST["blockName"];
$hostelid=$_POST["hostelId"];
$type=$_POST["type"];
$capacity=$_POST["capacity"];

$sql = mysqli_query($connection,"INSERT INTO `blockdata` (`blockid`, `name`, `hostelid`, `type`, `capacity`) VALUES (NULL, '$name', '$hostelid', '$type', '$capacity')");

if($sql)
{
	echo "Block Added Succesfully";
}
else
{
		echo "error";
}

?>
