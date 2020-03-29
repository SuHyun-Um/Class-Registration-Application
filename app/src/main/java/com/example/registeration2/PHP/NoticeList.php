<?php

      $con = mysqli_connect("localhost", "djatngus", "mint0502!!", "djatngus");

    //접속후에 테이블 내용 최신순서로 나오게 함

    $result = mysqli_query($con, "SELECT * FROM NOTICE ORDER BY noticeDate DESC;");

    $response = array();

    while($row = mysqli_fetch_array($result))

      array_push($response, array("noticeContent" => $row[0], "noticeName" => $row[1], "noticeDate" => $row[2]));



    //다음과 같이 출력함

    //"response":["noticeContent":"NOTICE NUMBER1","noticeName":"GAKARI","noticeDate":"2017-01-03",

    //"noticeContent":"NOTICE NUMBER1","noticeName":"GAKARI","noticeDate":"2017-01-02",





    //"noticeContent":"NOTICE NUMBER1","noticeName":"GAKARI","noticeDate":"2017-01-01"]

    echo json_encode(array("response" => $response));

   mysqli_close($con);

?>
