<?php
require "init.php";
/*
$username="contractor5@gmail.com";
$password="welcome";
*/
$username=$_POST["username"];
$password=$_POST["password"];

$id=0;
$response = array();

$result = mysqli_query($connection,"select adminid from adminData where username='$username' and password='$password'");
$row = mysqli_fetch_assoc($result);
if($row)
{
	array_push($response,array('id'=>$row['adminid'],'usertype'=>'A'));
	echo json_encode($response);
	exit();
}
$result = mysqli_query($connection,"select studentid from studentdata where username='$username' and password='$password'");
$row = mysqli_fetch_assoc($result);
if($row)
{
	array_push($response,array('id'=>$row['studentid'],'usertype'=>'S'));
	echo json_encode($response);
	exit();
}
$result = mysqli_query($connection,"select rectorid from rectorData where username='$username' and password='$password'");
$row = mysqli_fetch_assoc($result);
if($row)
{
	array_push($response,array('id'=>$row['rectorid'],'usertype'=>'R'));
	echo json_encode($response);
	exit();
}
$result = mysqli_query($connection,"select contractorid from cntnContractorData where username='$username' and password='$password'");
$row = mysqli_fetch_assoc($result);
if($row)
{
	array_push($response,array('id'=>$row['contractorid'],'usertype'=>'C'));
	echo json_encode($response);
	exit();
}
else
{
	array_push($response,array('id'=>'-1','usertype'=>'X'));
	echo json_encode($response);
}
mysqli_close($connection);
?>



