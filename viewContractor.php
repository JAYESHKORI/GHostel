<?php
require "init.php";
//$contractorid=15;
$contractorid=$_POST["contractorid"];


$res = mysqli_query($connection,"select c.*,h.name hostelname,b.name blockname from cntnContractorData c,hosteldata h,blockdata b WHERE c.hostelid=h.hostelid and c.blockid=b.blockid and contractorid=$contractorid");
$Cdetail = array();
while($row = mysqli_fetch_array($res))
{
array_push($Cdetail,array('fname'=>$row['first_name'],'lname'=>$row['last_name'],'mname'=>$row['middle_name'],
				'dob'=>$row['dob'],'email'=>$row['email'],'address'=>$row['address'],
				'contact'=>$row['contact'],'econtact'=>$row['em_contact'],'url'=>$row['dp_path'],
				'hostel'=>$row['hostelname'],'block'=>$row['blockname']));

}
echo json_encode($Cdetail);

mysqli_close($connection);
?>
