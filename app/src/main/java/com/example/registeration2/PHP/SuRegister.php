<?php

    $con = mysqli_connect("localhost", "djatngus", "mint0502!!", "djatngus");


     //안드로이드 앱으로부터 아래 값들을 받음

     $userID = $_POST["userID"];

     $userPassword = $_POST["userPassword"];

     $userGender =  $_POST["userGender"];

     $userMajor = $_POST["userMajor"];

     $userEmail = $_POST["userEmail"];



     //insert 쿼리문을 실행함

     $statement = mysqli_prepare($con, "INSERT INTO SU VALUES (?, ?, ?, ?, ?)");

     mysqli_stmt_bind_param($statement, "sssss", $userID, $userPassword, $userGender, $userMajor, $userEmail);

     mysqli_stmt_execute($statement);



     $response = array();

     $response["success"] = true;

     //회원 가입 성공을 알려주기 위한 부분임

     echo json_encode($response);

?>
