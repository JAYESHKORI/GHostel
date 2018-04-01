<?php
require "init.php";

$res = mysqli_query($connection,"SELECT s.studentid,s.first_name,s.last_name,h.hostelid,h.name,b.blockid,b.name from studentdata s, hosteldata h,blockdata b where s.hostelid=h.hostelid and s.blockid=b.blockid");
$result = array();
while($row = mysqli_fetch_array($res))
{
	array_push($result,array('studentid'=>$row[0],'first_name'=>$row[1],'last_name'=>$row[2],
					'hostelid'=>$row[3],'hostelname'=>$row[4],
					'blockid'=>$row[5],'blockname'=>$row[6],));
}
echo json_encode($result);
mysqli_close($connection);
?>

