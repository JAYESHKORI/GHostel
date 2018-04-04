<?php
require "init.php";
//$hostelid=10;
$hostelid=$_POST["hostelid"];


$res = mysqli_query($connection,"select * from hosteldata where hostelid=$hostelid");
$Hdetail = array();
while($row = mysqli_fetch_array($res))
{
	array_push($Hdetail,array('hostelid'=>$row[0],'name'=>$row[1],'description'=>$row[2],'type'=>$row[3]));
}
//echo json_encode($Hdetail);

$res = mysqli_query($connection,"select * from blockdata where hostelid=$hostelid");
$BList = array();
while($row = mysqli_fetch_array($res))
{
	array_push($BList,array('blockid'=>$row[0],'blockname'=>$row[1],'capacity'=>$row[4],'type'=>$row[3]));
}
//echo json_encode($BList);

$response = array();
array_push($response,array('hostelDetails'=>$Hdetail,'blockList'=>$BList));
echo json_encode($response);

mysqli_close($connection);
?>
