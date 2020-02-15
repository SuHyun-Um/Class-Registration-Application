package com.example.registeration2;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static com.example.registeration2.MainActivity.userID;

public class CourseListAdapter extends BaseAdapter {

    private Context context;
    private List<Course> courseList;
    private Fragment parent;
    private  String UserID = MainActivity.userID;  //여기수정
    private  Schedule schedule = new Schedule();
    private  List<Integer> courseIDList;
    public static int totalCredit = 0;

    public CourseListAdapter(Context context, List<Course> courseList, Fragment parent) {
        this.context = context;
        this.courseList = courseList;
        this.parent = parent;
        schedule = new Schedule();
        courseIDList = new ArrayList<Integer>();
        new BackgroundTask().execute();
        totalCredit = 0;
    }

    @Override
    public int getCount() {
        return courseList.size();
    }

    @Override
    public Object getItem(int i) {
        return courseList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, final ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.course, null);
        TextView courseGrade = (TextView) v.findViewById(R.id.courseGrade);
        TextView courseTitle = (TextView) v.findViewById(R.id.courseTitle);
        TextView courseCredit = (TextView) v.findViewById(R.id.courseCredit);
        TextView courseDivide = (TextView) v.findViewById(R.id.courseDivide);
        TextView coursePersonnel = (TextView) v.findViewById(R.id.coursePersonnel);
        TextView courseProfessor = (TextView) v.findViewById(R.id.courseProfessor);
        TextView courseTime = (TextView) v.findViewById(R.id.courseTime);

        if (courseList.get(i).getCourseGrade().equals("제한없음") || courseList.get(i).getCourseGrade().equals("")) {
            courseGrade.setText("모든학년");
        } else {
            courseGrade.setText(courseList.get(i).getCourseGrade() + "학년");
        }

        courseTitle.setText(courseList.get(i).getCourseTitle());
        courseCredit.setText(courseList.get(i).getCourseCredit() + "학점");
        courseDivide.setText(courseList.get(i).getCourseDivide() + "분반");
        if (courseList.get(i).getCoursePersonnel() == 0) {
            coursePersonnel.setText("인원 제한 없음");
        } else {
            coursePersonnel.setText("제한 인원 :" + courseList.get(i).getCoursePersonnel() + "명");

        }
        if(courseList.get(i).getCourseProfessor().equals("")) {
            courseProfessor.setText("개인 연구");
        }
        else
        {
            courseProfessor.setText(courseList.get(i).getCourseProfessor()+"교수님");
        }
        courseTime.setText(courseList.get(i).getCourseTime() + "");

        v.setTag(courseList.get(i).getCourseID()); //여기도 잘봐

        Button addButton = (Button)v.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validate = false;
                validate = schedule.validate(courseList.get(i).getCourseTime());
              if(!alreadyIn(courseIDList,courseList.get(i).getCourseID()))
              {
                  AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                  AlertDialog dialog = builder.setMessage("이미 추가한 강의 입니다.")
                          .setPositiveButton("다시 시도 ", null)
                          .create();
                  dialog.show();
              }
              else if(totalCredit + courseList.get(i).getCourseCredit() > 24)
              {
                  AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                  AlertDialog dialog = builder.setMessage("24학점을 초과할 수 없습니다.")
                          .setPositiveButton("다시 시도", null)
                          .create();
                  dialog.show();
              }
              else if(validate == false)
              {
                  AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                  AlertDialog dialog = builder.setMessage("시간표가 중복됩니다")
                          .setPositiveButton("다시 시도", null)
                          .create();
                  dialog.show();
              }
              else
              {
                  //검증시작
                  Response.Listener<String> responseListener = new Response.Listener<String>(){
                      @Override
                      public void onResponse(String response) {

                          try{

                              //Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_LONG).show();

                              JSONObject jsonResponse = new JSONObject(response);

                              boolean success = jsonResponse.getBoolean("success");

                              if(success){//사용할 수 있는 아이디라면

                                  AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                                  AlertDialog dialog = builder.setMessage("강의가 추가되었습니다")
                                          .setPositiveButton("확인", null)
                                          .create();
                                  dialog.show();
                                  courseIDList.add(courseList.get(i).getCourseID());
                                  schedule.addSchedule(courseList.get(i).getCourseTime());
                                  totalCredit += courseList.get(i).getCourseCredit();

                              }
                              else{//사용할 수 없는 아이디라면

                                  AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                                  AlertDialog dialog = builder.setMessage("강의 추가에 실패하였습니다")
                                          .setNegativeButton("확인", null)
                                          .create();
                                  dialog.show();

                              }

                          }
                          catch(Exception e){
                              e.printStackTrace();
                          }

                      }

                  };//Response.Listener 완료

                  //Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분

                  AddRequest addRequest = new AddRequest(userID, courseList.get(i).getCourseID()+"",responseListener);

                  RequestQueue queue = Volley.newRequestQueue(parent.getActivity());

                  queue.add(addRequest);

              }
              }

        });
        return v;

    }

    class BackgroundTask extends AsyncTask<Void,Void,String> {

        String target;

        @Override
        protected void onPreExecute() {
            try {
                target = "http://djatngus.cafe24.com/ScheduleList.php?userID="+ URLEncoder.encode(userID,"UTF-8");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {

                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine()) != null) {

                    stringBuilder.append(temp + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate();
        }

        @Override
        public void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                String courseProfessor;
                String courseTime;
                int courseID;
                totalCredit =0;
                while (count < jsonArray.length()) {
                    JSONObject object = jsonArray.getJSONObject(count);
                    courseID = object.getInt("courseID");
                    courseProfessor = object.getString("courseProfessor");
                    courseTime = object.getString("courseTime");
                    totalCredit += object.getInt("courseCredit");
                    courseIDList.add(courseID);
                    schedule.addSchedule(courseTime);
                    count++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public  boolean alreadyIn(List<Integer> courseIDList,int item)
    {
        for(int i=0; i <courseIDList.size();i++)
        {
            if(courseIDList.get(i) == item)
            {
                return false;
            }
        }
        return true;
    }
}