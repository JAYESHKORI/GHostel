<?php
require "init.php";

$res = mysqli_query($connection,
"SELECT b.blockid, b.name, b.hostelid, h.name, b.type, b.capacity FROM blockdata b,hosteldata h WHERE b.hostelid=h.hostelid");
$result = array();
while($row = mysqli_fetch_array($res))
{
	array_push($result,array('blockid'=>$row[0],'bname'=>$row[1],'hostelid'=>$row[2],'hname'=>$row[3],'type'=>$row[4],'capacity'=>$row[5]));
}
echo json_encode($result);
mysqli_close($connection);
?>
