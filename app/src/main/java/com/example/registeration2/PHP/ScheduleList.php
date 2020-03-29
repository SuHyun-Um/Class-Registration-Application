<?php

  header("Content-Type: text/html; charset=UTF-8");
  $con = mysqli_connect("localhost", "djatngus", "mint0502!!", "djatngus");

  $userID = $_GET["userID"];

  $result = mysqli_query($con, "SELECT COURSE.courseID, COURSE.courseTime, COURSE.courseProfessor, COURSE.courseTitle, COURSE.courseCredit
      FROM SU , COURSE , SCHEDULE WHERE SU.userID = '$userID' AND SU.userID = SCHEDULE.userID AND SCHEDULE.courseID = COURSE.courseID ");

    $response = array();
    while($row = mysqli_fetch_array($result)){
      array_push($response, array("courseID" =>$row[0], "courseTime" =>$row[1], "courseProfessor" =>$row[2], "courseTitle" =>$row[3],
      "courseCredit" =>$row[4]));
    }
    echo json_encode(array("response" =>$response), JSON_UNESCAPED_UNICODE);
    mysqli_close($con);
 ?>
