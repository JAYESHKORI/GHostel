<?php
require "init.php";
//$rectorid=65;
$rectorid=$_POST["rectorid"];


$res = mysqli_query($connection,"select r.*,h.name hostelname,b.name blockname from rectorData r,hosteldata h,blockdata b WHERE r.hostelid=h.hostelid and r.blockid=b.blockid and rectorid=$rectorid");
$Rdetail = array();
while($row = mysqli_fetch_array($res))
{
	array_push($Rdetail,array('fname'=>$row['first_name'],'lname'=>$row['last_name'],'mname'=>$row['middle_name'],
				'dob'=>$row['dob'],'email'=>$row['email'],'address'=>$row['address'],
				'contact'=>$row['contact'],'econtact'=>$row['em_contact'],'url'=>$row['dp_path'],
				'hostel'=>$row['hostelname'],'block'=>$row['blockname']));
}
echo json_encode($Rdetail);

mysqli_close($connection);
?>
