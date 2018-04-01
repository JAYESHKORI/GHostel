

<?php
require "init.php";

$res = mysqli_query($connection,"SELECT c.contractorid,c.first_name,c.last_name,h.hostelid,h.name,b.blockid,b.name from cntnContractorData c, hosteldata h,blockdata b where c.hostelid=h.hostelid and c.blockid=b.blockid");
$result = array();
while($row = mysqli_fetch_array($res))
{
	array_push($result,array('contractorid'=>$row[0],'first_name'=>$row[1],'last_name'=>$row[2],
					'hostelid'=>$row[3],'hostelname'=>$row[4],
					'blockid'=>$row[5],'blockname'=>$row[6],));
}
echo json_encode($result);
mysqli_close($connection);
?>
