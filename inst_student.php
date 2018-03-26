<?php
require "init.php";

$enroll_no=$_POST["enroll_no"];
$first_name=$_POST["first_name"];
$last_name=$_POST["last_name"];
$middle_name=$_POST["middle_name"];
$dob=$_POST["dob"];
$email=$_POST["email"];
$contact=$_POST["contact"];
$address=$_POST["address"];
$p_contact=$_POST["p_contact"];
$em_contact=$_POST["em_contact"];
$college=$_POST["college"];
$hostelid=$_POST["hostelid"];
$blockid=$_POST["blockid"];
$username=$email;
$password=$_POST["password"];
$dp=$_POST["dp"];
$extension=$_POST["extension"];
$dp_path="images/profile_pics/$first_name$extension";

$sql = mysqli_query($connection,"INSERT INTO `studentdata` (`studentid`,`enroll_no`, `first_name`, `last_name`, `middle_name`, `dob`, `email`, `contact`, `address`, `p_contact`, `em_contact`, `college`,`hostelid`, `blockid`, `username`, `password`, `dp_path`) VALUES 
(NULL,$enroll_no, '$first_name', '$last_name', '$middle_name', '$dob', '$email', $contact, '$address',$p_contact, $em_contact,'$college', $hostelid, $blockid, '$username', '$dob', '$dp_path')");

if($sql)
{
	file_put_contents($dp_path,base64_decode($dp));
	echo json_encode(array('response'=>'New Student Added Successfully'));
}
else
{
	echo json_encode(array('response'=>'Something went wrong'));
}

?>
