<?php
require "init.php";
/*
$scancode="02-04-20183student2@gmail.comD";
*/
$scancode=$_POST["scancode"];

$response = array();

$result = mysqli_query($connection,"select * from messconsent where code='$scancode'");
$row = mysqli_fetch_assoc($result);
if($row)
{
	$result = mysqli_query($connection,"delete from messconsent where code='$scancode'");
	if($result)
	{
		array_push($response,array('response'=>1));
		echo json_encode($response);
	}
}
else
{
	array_push($response,array('response'=>0));
	echo json_encode($response);
}
mysqli_close($connection);
?>



