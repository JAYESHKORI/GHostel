<?php
require "init.php";
//$blockid=2;
$blockid=$_POST["blockid"];

$Bdetail = array();
$rname="";
$cname="";
$res1 = mysqli_query($connection,"select b.blockid,b.name as blockname,b.type,b.capacity,h.name as hostelname from blockdata b,hosteldata h 					where b.hostelid=h.hostelid and blockid=$blockid");
$row1 = mysqli_fetch_assoc($res1);

$res2 = mysqli_query($connection,"SELECT * from ( 
select COALESCE(r.blockid, 'NA') b1, COALESCE(r.first_name, 'NA') as rfn,COALESCE(r.last_name, 'NA') as rln, COALESCE(c.blockid, 'NA') b2,COALESCE(c.first_name, 'NA') as cfn,COALESCE(c.last_name, 'NA') as cln from rectorData r LEFT JOIN cntnContractorData c ON r.blockid=c.blockid 
UNION 
select COALESCE(r.blockid, 'NA') b1, COALESCE(r.first_name, 'NA') as rfn,COALESCE(r.last_name, 'NA') as rln, COALESCE(c.blockid, 'NA') b2,COALESCE(c.first_name, 'NA') as cfn,COALESCE(c.last_name, 'NA') as cln from rectorData r RIGHT JOIN cntnContractorData c ON r.blockid=c.blockid) temp where b1=$blockid or b2=$blockid");
$row2 = mysqli_fetch_assoc($res2);

$res3 = mysqli_query($connection,"SELECT count(*) as totalStudents FROM studentdata WHERE blockid=$blockid");
$row3 = mysqli_fetch_assoc($res3);
if($row1&&$row2&&$row3)
{
	if($row2['rfn']=='NA'||$row2['rln']=='NA') $rname='NA';
	else $rname=$row2['rfn'].' '.$row2['rln'];
	if($row2['cfn']=='NA'||$row2['cln']=='NA') $cname='NA';
	else $cname=$row2['cfn'].' '.$row2['cln'];
	array_push($Bdetail,array('response'=>1,'blocklid'=>$row1['blockid'],'blockname'=>$row1['blockname'],'blocktype'=>$row1['type'],
			'blockcapacity'=>$row1['capacity'],'hostelname'=>$row1['hostelname'],
			'rname'=>$rname,'cname'=>$cname,'totalStudent'=>$row3['totalStudents']));
}
else
{
	array_push($Bdetail,array('response'=>0));
}
echo json_encode($Bdetail);
mysqli_close($connection);
?>
