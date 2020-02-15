package com.example.registeration2;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


/**

 * Created by kch on 2018. 5. 14..

 */




public class DeleteRequest extends StringRequest {




    final static private  String URL = "http://djatngus.cafe24.com/ScheduleDelete.php";

    private Map<String, String> parameters;




    public DeleteRequest(String userID, String courseID, Response.Listener<String> listener){

        super(Method.POST, URL, listener, null);//해당 URL에 POST방식으로 파마미터들을 전송함

        parameters = new HashMap<>();

        parameters.put("userID", userID);

        parameters.put("courseID", courseID);

    }

    @Override

    protected Map<String, String> getParams() throws AuthFailureError { return parameters;}

}
