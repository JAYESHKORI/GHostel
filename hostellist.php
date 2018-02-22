<?php
require "init.php";

$res = mysqli_query($connection,"select * from hosteldata");
$result = array();
while($row = mysqli_fetch_array($res))
{
	array_push($result,array('id'=>$row[0],'name'=>$row[1],'description'=>$row[2],'type'=>$row[3]));
}
echo json_encode($result);
mysqli_close($connection);
?>
