<?php
require "init.php";


$dt=date("d/m/Y");
$tm=date("h:i:s a");
$code=$_POST["code"];

$sql = mysqli_query($connection,"INSERT INTO `messconsent` (`code`, `date`, `time`) VALUES ('$code', '$dt', '$tm')");

if($sql)
{
	echo "1";
}
else
{
		echo "0";
}
?>
