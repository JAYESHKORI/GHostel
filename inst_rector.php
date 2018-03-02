<?php
require "init.php";

/*
$first_name="Ramanbhai";
$last_name="Chaganbhai";
$middle_name="Desai";
$dob="23-8-1998";
$email="Ramanbhai@gmail.com";
$contact="0987654321";
$address="2,narol,ahmedabad";
$em_contact="0987654321";
$hostelid="1";
$blockid="1";
$username=$email;
$password=$dob;
$dp="/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEB
                                                                AQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQH/2wBDAQEBAQEBAQEBAQEBAQEBAQEBAQEB
                                                                AQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQH/wAARCAJTAeADASIA
                                                                AhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQA
                                                                AAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3
                                                                ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWm
                                                                p6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEA
                                                                AwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSEx
                                                                BhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElK
                                                                U1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3
                                                                uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD+/iii
                                                                igAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKK
                                                                ACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooA
                                                                KKKKAK4P3sHK8c4+9y3ryNpX8c98V5D8cfibY/BX4L/Gb4x6hpd3rNj8H/hv43+JmoaJZ3kdjcat
                                                                Z+DfDeqeKrmzhumciFrmLSHjEsiybDI/+j3AZbVvYD1k+fA+Td8udv3tvf5t3PTp3r5K/b0XP7CX
                                                                7bXG/P7Kf7Qw25PzZ+D/AI6XZ0fPmZ248t/vHMcuSjZx0lbfVddNHU/uydt2/kvearN5w0bTd9Uv
                                                                L4px0tztKyuna6d95Ks3+Lv/AARU/wCDinQv+Cwnx9+KnwEg/ZL1b4Aap8O/hTefFqz1w/F6z+I+
                                                                karo1j428BeC5dBl/wCLa/D57LVEu/HdpeLIFngeCGWCCF5WSdv6WDnkHnb1P+8Tjj6Kf689f8z7
                                                                /gyYA/4eFftXZOM/sd6t264+OvwQ56c4zyM/LuHBLE1/pg5ypJ5Ix7bctj8dw+uPc80fzrXRR77N
                                                                1Env/dTut3dNuSnId9Z77R2e/vVOnpCNrK9pO7unIQEOMluB0468kH6fdP8Ankr/ALJOB3GM45Pf
                                                                OTnA79/Y1+OX/Baj/grLpP8AwR9/Zj8JfG+X4O6r8ZfFPxK+I9t8J/AfhWLW08P+HrfxHc+EvGfi
                                                                qPV/FWuC1vrmDTFg8LG1NlYW8up3L3Uctqoitr9h/Kz4z/4OT/8Ag4b0TwVq/wC0Bdf8EyvB/gn4
                                                                D6QY9R1Dxb4l/Zl/aX/4QbStBaWOES+I/GV58RbNbMTeYpa4MtqoEoMUTooy0n73/bie97OUl1lp
                                                                fTreV4/F7smRTV1fXS7111mlvvok7X001T5Zy/0NFAHTn0/2v9Znqflx79femrn1wF74zjJYdM55
                                                                /H73fbk/zyf8EHP+C7Wkf8Fg/B3xQ8J+NPhbafCb9ob4LaZ4Z1zxv4d8P6lqOr+BvFXhLXZ206z8
                                                                YeE7y8gkvtNk+3W88V94VvHuruyuJbZItQuraU3o+LP+CvH/AAccfGP/AIJl/wDBT34Yfsj2/wAI
                                                                fhTr/wCz9L4S+D3jn4xeONf0v4gap8T9K8O+M/GGqr4yvPCVp4V8bWOkPd+C/CWhT6lY2d7pl8dR
                                                                v5bhbm3ktxZ27pbNLVK3vJNt2k43Wrdrq7e+qu3fnBLXtaz2et5SXd9Yxvq7Ju923M/rwB2jg5A6
                                                                f7XL59SMEj65780zHXHIGOenU4HGc/5/Gv8AOy/ad/4PGf22dT8a+IfH37Gv7IXgHQP2UtE8Qnw9
                                                                oPj345+BPiZ4k8SeJr390iDxR4j8GfEXRvA3g2/uAVuLbwdp82qarHBNbzXGuXDObU/Y9r/web/D
                                                                DV/2O9A1bwl+zHrGuft7a54iTwND8Erd/FWofBuKb7KDbfFG38VWUF14p1zwtezGO0g+HVov/CxX
                                                                1MvaNfnTVHiWRpXvrezV9U0tZxW7ktlezvZSjJvWUg5Wlv0vu/hXN15tLpbPo1bWTm/7iM/LjqOe
                                                                fX5vTqPz/OnbueTj1GOnXv3zgfn7Gv8AO3/Zb/4PFv2u/Bn7RuneDP8Agon+zl8NND+Fd54j07QP
                                                                Gq/C7wV448F/Fj4YWtyyRvrw8K+N/iFq48UwWyZvL3T52064NssrWNw88thZSf6F+j6rpuu2FlrG
                                                                k31pqWmavZW+o6Ve2knmQX+mTRxyQ3cUmMPDItwkiEZAWVVJ3GQ0Wkr/APbvWWylNJbdUnda2Vl7
                                                                0nJtqLtJ3v8ADa12tJTsm/7yi2ld295XlL3nqr2GNxGcnOM8tjvxgD9PU8qR1Oc9MHH3uSD34xgf
                                                                XPsTXA/Evxg3gL4dfEbxvFYxahceC/BfiXxdHp80hjXUm8O6HqOsi1BjLyok5sfILhC6SSB1Uldz
                                                                fwbfs7f8HpXj24+GH7QepftM/sxfDvWPi3paeD7P9mfwR8E38ZeFtA8Ya1rB8XjxZN8T/EnjPx18
                                                                RTovh/wibLw9eLd6Fa/aL6zu7q0SOOZre5RqKs9d2v5lrffr2T97VvTWXNzSo+7ZW3SvZ/3knu2/
                                                                hbvv7z1vzRf+gOACOOf7p5G7lgeCeNu3HPXPfGacCoAAbkdDg+p7Z9Cfz9a/zjfAX/B4x/wUN+Fv
                                                                xzsbH9sL9kL4TWnw0vbzQNT8UeAfCvgP4pfD74taH4KuPtKT6x4Jk8dfEO9tJZL9UNxZjxpby20z
                                                                vaRW16luLq5P993gn9pj4IeOv2cfD37WukeONMs/gR4i+Elt8dx8QNaUaVpml/DSfwpF4vbxP4gW
                                                                WUy2UNj4bYXt7uMoghjLRySRm1Yii0m0rq0bt3VtZ2TTkmnFR0+1bRNyjMqMXr1Xuq9n1dTvK6b5
                                                                L9XqtZSUmfQK5Bz0HY9c8kHjqOp/Pv1p44PoF6jrjO/HPfOD9M89K/gH/aM/4O+f2qPjF8cdY+EP
                                                                /BLD9iW1+JPh+P7a3hXXPHvgz4mfEb4peO9KtA8cviiD4TfDzUNKm8MeHis0NyYL291W6jjlha/v
                                                                7QfaLY537Nf/AAeCftUfCb446R8Kv+Cov7HGieA/Dpu9J0/xxrHw88H/ABI+HnxX+H8F95UKeKZ/
                                                                hV8QNZ1h/EWgbN96NLs7mx1R4ldLHULuS4022ZRjZPXZq2jd3eS3inq+Xmim7y1+J2k1FK0rva1t
                                                                /es/d5f62snJyak/75/EPiXSPCeka14h8QahbaXoWg6Rqeva3qd2xW3sdJ0m3mur28lCkS7LeBDK
                                                                wRWOA4Bd2jWvgb9j/wD4Kt/8E9v2+vHHib4bfsj/ALTnhP4zeOPCPhyTxdrvhvR9D8aaFqVh4Wg1
                                                                zSPDt14kjj8X+E9C+1WcOp6/p1mWtvMbdPblozbTfa3/ADi/4L8/8FNvjF+yJ+w78KfiX+yV8FPD
                                                                P7THg79rKy1/wXrfiafSfiFrnh/Rfhl4u+GN/wCIfDXjixfwPdWEog1axv3nsZdWmjs2hkGQJHki
                                                                b/PV/wCCMP8AwUb/AGi/+CY/x/8AiX8bf2ZP2etG/aM8beOvhBrPwq1fwZqmg/EfVG0jw1N4t8Be
                                                                Nm8SqngW6jmUpN4HS3kWQyII5p2kA3QSBxS96+qko2d3ZvnlFK2qV2k00227+9GXtpy";
$dp_path="images/profile_pics/$first_name.png";
*/

$first_name=$_POST["first_name"];
$last_name=$_POST["last_name"];
$middle_name=$_POST["middle_name"];
$dob=$_POST["dob"];
$email=$_POST["email"];
$contact=$_POST["contact"];
$address=$_POST["address"];
$em_contact=$_POST["em_contact"];
$hostelid=$_POST["hostelid"];
$blockid=$_POST["blockid"];
$username=$email;
$password=$_POST["password"];
$dp=$_POST["dp"];
$extension=$_POST["extension"];
$dp_path="images/profile_pics/$first_name$extension";

$sql = mysqli_query($connection,"INSERT INTO `rectorData` (`rectorid`, `first_name`, `last_name`, `middle_name`, `dob`, `email`, `contact`, `address`, `em_contact`, `hostelid`, `blockid`, `username`, `password`, `dp_path`) VALUES 
(NULL, '$first_name', '$last_name', '$middle_name', '$dob', '$email', $contact, '$address', $em_contact, $hostelid, $blockid, '$username', '$dob', '$dp_path')");

if($sql)
{
	file_put_contents($dp_path,base64_decode($dp));
	echo json_encode(array('response'=>'New Rector Added Successfully'));
}
else
{
	echo json_encode(array('response'=>'Something went wrong'));
}

?>
