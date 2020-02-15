package com.example.registeration2;
import android.app.AlertDialog;
import android.net.Uri;

import android.os.AsyncTask;
import android.os.Bundle;

import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;

import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;

import android.widget.RadioGroup;

import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**

 * A simple {@link Fragment} subclass.

 * Activities that contain this fragment must implement the

 * {@link CourseFragment.OnFragmentInteractionListener} interface

 * to handle interaction events.

 * Use the {@link CourseFragment#newInstance} factory method to

 * create an instance of this fragment.

 */

public class CourseFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";

    private static final String ARG_PARAM2 = "param2";




    // TODO: Rename and change types of parameters

    private String mParam1;

    private String mParam2;




    private OnFragmentInteractionListener mListener;




    public CourseFragment() {

        // Required empty public constructor

    }




    /**

     * Use this factory method to create a new instance of

     * this fragment using the provided parameters.

     *

     * @param param1 Parameter 1.

     * @param param2 Parameter 2.

     * @return A new instance of fragment CourseFragment.

     */

    // TODO: Rename and change types and number of parameters

    public static CourseFragment newInstance(String param1, String param2) {

        CourseFragment fragment = new CourseFragment();

        Bundle args = new Bundle();

        args.putString(ARG_PARAM1, param1);

        args.putString(ARG_PARAM2, param2);

        fragment.setArguments(args);

        return fragment;

    }




    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

            mParam1 = getArguments().getString(ARG_PARAM1);

            mParam2 = getArguments().getString(ARG_PARAM2);

        }

    }




    //9강에 추가된 부분

    private ArrayAdapter yearAdapter;

    private Spinner yearSpinner;

    private ArrayAdapter termAdapter;

    private Spinner termSpinner;

    private ArrayAdapter areaAdapter;

    private Spinner areaSpinner;

    private ArrayAdapter majorAdapter;

    private Spinner majorSpinner;




    private String courseUniversity = "";



    private ListView courseListView;
    private CourseListAdapter adapter;
    private List<Course> courseList;







    //9강에 추가된 부분

    //Life Cycle 상 onCreate다음에 onActivityCreated가 실행됨

    //Activity의 모든 View가 만들어지고 난 다음이기 때문에 View를 변경하는 등의 작업을 할 수 있다.




    @Override

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        final RadioGroup courseUniversityGroup = (RadioGroup)getView().findViewById(R.id.courseUniversityGroup);

        yearSpinner = (Spinner)getView().findViewById(R.id.yearSpinner);

        termSpinner = (Spinner)getView().findViewById(R.id.termSpinner);

        areaSpinner = (Spinner)getView().findViewById(R.id.areaSpinner);

        majorSpinner = (Spinner)getView().findViewById(R.id.majorSpinner);


        //라디오 버튼에 따라서 달라짐

        courseUniversityGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override

            public void onCheckedChanged(RadioGroup group, int i) {

                //라디오 버튼 그룹 선언

                RadioButton courseButton = (RadioButton)getView().findViewById(i);




                //현재 라디오 버튼이 눌린 값의 text를 가져옴

                courseUniversity = courseButton.getText().toString();




                //arrays.xml의 내용을 이용해서 단순한 스피너를 만드는 부분

                yearAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.year, android.R.layout.simple_spinner_dropdown_item);

                yearSpinner.setAdapter(yearAdapter);//여기서 스피너뷰에 어댑터패턴을 이용해서 데이터를 연결해줌
               // yearSpinner.setSelection(1); 16,17,18,19 년도 지정 하는법


                //위와 동일

                termAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.term, android.R.layout.simple_spinner_dropdown_item);

                termSpinner.setAdapter(termAdapter);
                //tempSpinner.setSelection(0);  1학기,여름학기,2학기,겨울학기를 선택하지 않고 바로 지정 하는 법



                //라디오버튼의 상태에 따라서 학부와 대학원으로 나눔

                if(courseUniversity.equals("학부")){

                    areaAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.universityArea, android.R.layout.simple_spinner_dropdown_item);
                    areaSpinner.setAdapter(areaAdapter);
                    majorAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.universityRefinementMajor, android.R.layout.simple_spinner_dropdown_item);
                    majorSpinner.setAdapter(majorAdapter);

                }
                else if(courseUniversity.equals("대학원")){
                    areaAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.graduateArea, android.R.layout.simple_spinner_dropdown_item);
                    areaSpinner.setAdapter(areaAdapter);
                    majorAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.graduateMajor , android.R.layout.simple_spinner_dropdown_item);
                    majorSpinner.setAdapter(majorAdapter);

                }
            }

        });
        areaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override   //4분22초
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long I) {
                if(areaSpinner.getSelectedItem().equals("교양및기타"))
                {
                    majorAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.universityRefinementMajor, android.R.layout.simple_spinner_dropdown_item);
                    majorSpinner.setAdapter(majorAdapter);
                }
                if(areaSpinner.getSelectedItem().equals("전공"))
                {
                    majorAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.universityMajor, android.R.layout.simple_spinner_dropdown_item);
                    majorSpinner.setAdapter(majorAdapter);
                }
                if(areaSpinner.getSelectedItem().equals("일반대학원"))
                {
                    majorAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.graduateMajor , android.R.layout.simple_spinner_dropdown_item);
                    majorSpinner.setAdapter(majorAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        courseListView = (ListView) getView().findViewById(R.id.courseListView);
        courseList = new ArrayList<Course>();
        adapter = new CourseListAdapter(getContext().getApplicationContext(), courseList, this);
        courseListView.setAdapter(adapter);

        Button searchButton = (Button) getView().findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                new BackgroundTask().execute();
            }
        });
    }


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_course, container, false);

    }




    // TODO: Rename method, update argument and hook method into UI event

    public void onButtonPressed(Uri uri) {

        if (mListener != null) {

            mListener.onFragmentInteraction(uri);

        }

    }




    /*

    @Override

    public void onAttach(Context context) {

        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {

            mListener = (OnFragmentInteractionListener) context;

        } else {

            throw new RuntimeException(context.toString()

                    + " must implement OnFragmentInteractionListener");

        }

    }




강의에서 삭제함    */




    @Override

    public void onDetach() {

        super.onDetach();

        mListener = null;

    }




    /**

     * This interface must be implemented by activities that contain this

     * fragment to allow an interaction in this fragment to be communicated





     * to the activity and potentially other fragments contained in that

     * activity.

     * <p>

     * See the Android Training lesson <a href=

     * "http://developer.android.com/training/basics/fragments/communicating.html"

     * >Communicating with Other Fragments</a> for more information.

     */

    public interface OnFragmentInteractionListener {

        // TODO: Update argument type and name

        void onFragmentInteraction(Uri uri);

    }

    class BackgroundTask extends AsyncTask<Void,Void,String> {

        String target;

        @Override
        protected void onPreExecute() {
            try{
                target = "http://djatngus.cafe24.com/CourseList.php?courseUniversity="+ URLEncoder.encode(courseUniversity,"UTF-8") +
                        "&courseYear=" + URLEncoder.encode(yearSpinner.getSelectedItem().toString().substring(0,4), "UTF-8") + "&courseTerm=" + URLEncoder.encode(termSpinner.getSelectedItem().toString(),"UTF-8") +
                        "&courseArea=" + URLEncoder.encode(areaSpinner.getSelectedItem().toString(),"UTF-8") + "&courseMajor=" + URLEncoder.encode(majorSpinner.getSelectedItem().toString(),"UTF-8");

            }
            catch(Exception e)
            {
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
        public void onProgressUpdate(Void... values) { super.onProgressUpdate(); }
        @Override
        public void onPostExecute(String result) {
            try {
              courseList.clear();
              JSONObject jsonObject = new JSONObject(result);
              JSONArray jsonArray = jsonObject.getJSONArray("response");
              int count =0;
                int courseID; //강의 고유 번호
                String courseUniversity; //해당 년도
                int courseYear; //해당학기
                String courseTerm; //강의영역
                String courseArea;//해당학과
                String courseMajor;
                String courseGrade;//해당학년
                String courseTitle;//강의제목
                int courseCredit;//강의 학점
                int courseDivide;//강의 분반
                int coursePersonnel;//강의 제한 인원
                String courseProfessor; //강의 교슈
                String courseTime; // 강의 시간대
                String courseRoom;//강의실
                while(count < jsonArray.length())
                {
                    JSONObject object = jsonArray.getJSONObject(count);
                    courseID = object.getInt("courseID");
                    courseUniversity = object.getString("courseUniversity");
                    courseYear = object.getInt("courseYear");
                    courseTerm = object.getString("courseTerm");
                    courseArea = object.getString("courseArea");
                    courseMajor = object.getString("courseMajor");
                    courseGrade = object.getString("courseGrade");
                    courseTitle = object.getString("courseTitle");
                    courseCredit = object.getInt("courseCredit");
                    courseDivide = object.getInt("courseDivide");
                    coursePersonnel = object.getInt("coursePersonnel");
                    courseProfessor = object.getString("courseProfessor");
                    courseTime = object.getString("courseTime");
                    courseRoom = object.getString("courseRoom");
                   Course course = new  Course(courseID,courseUniversity,  courseYear,  courseTerm, courseArea,courseMajor, courseGrade, courseTitle, courseCredit,courseDivide,coursePersonnel,  courseProfessor, courseTime, courseRoom);
                   courseList.add(course);
                    count++;
                }
                if(count==0)
                {
                    AlertDialog dialog;
                    AlertDialog.Builder builder = new AlertDialog.Builder(CourseFragment.this.getActivity());
                    dialog = builder.setMessage("조회된 강의가 없습니다.\n날짜를 확인하세요.")
                            .setPositiveButton("확인", null)
                            .create();
                    dialog.show();
                }
                adapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
