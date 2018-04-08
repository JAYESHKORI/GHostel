<?php
require "init.php";
//$studentid=15;
$studentid=$_POST["studentid"];


$res = mysqli_query($connection,"select s.*,h.name hostelname,b.name blockname from studentdata s,hosteldata h,blockdata b WHERE s.hostelid=h.hostelid and s.blockid=b.blockid and studentid=$studentid");
$Sdetail = array();
while($row = mysqli_fetch_array($res))
{
	array_push($Sdetail,array('enroll'=>$row['enroll_no'],'fname'=>$row['first_name'],'lname'=>$row['last_name'],
				'mname'=>$row['middle_name'],'dob'=>$row['dob'],'email'=>$row['email'],'address'=>$row['address'],
				'contact'=>$row['contact'],'pcontact'=>$row['p_contact'],'econtact'=>$row['em_contact'],
				'college'=>$row['college'],'url'=>$row['dp_path'],'hostel'=>$row['hostelname'],'block'=>$row['blockname']));
}
echo json_encode($Sdetail);

mysqli_close($connection);
?>
