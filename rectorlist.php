<?php
require "init.php";

$res = mysqli_query($connection,"SELECT r.rectorid,r.first_name,r.last_name,h.hostelid,h.name,b.blockid,b.name from rectorData r, hosteldata h,blockdata b where r.hostelid=h.hostelid and r.blockid=b.blockid");
$result = array();
while($row = mysqli_fetch_array($res))
{
	array_push($result,array('rectorid'=>$row[0],'first_name'=>$row[1],'last_name'=>$row[2],
					'hostelid'=>$row[3],'hostelname'=>$row[4],
					'blockid'=>$row[5],'blockname'=>$row[6],));
}
echo json_encode($result);
mysqli_close($connection);
?>
