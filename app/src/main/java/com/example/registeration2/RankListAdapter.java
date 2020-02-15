package com.example.registeration2;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.List;

import static com.example.registeration2.MainActivity.userID;

public class RankListAdapter extends BaseAdapter {

    private Context context;
    private List<Course> courseList;
    private Fragment parent;



    public RankListAdapter(Context context, List<Course> courseList, Fragment parent) {
        this.context = context;
        this.courseList = courseList;
        this.parent = parent;
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
        View v = View.inflate(context, R.layout.rank, null);
        TextView rankTextview = (TextView)v.findViewById(R.id.rankTextView);
        TextView courseGrade = (TextView) v.findViewById(R.id.courseGrade)  ;
        TextView courseTitle = (TextView) v.findViewById(R.id.courseTitle);
        TextView courseProfessor = (TextView) v.findViewById(R.id.courseProfessor);
        TextView courseCredit = (TextView) v.findViewById(R.id.courseCredit);
        TextView courseDivide = (TextView) v.findViewById(R.id.courseDivide);
        TextView coursePersonnel = (TextView) v.findViewById(R.id.coursePersonnel);
        TextView courseTime = (TextView) v.findViewById(R.id.courseTime);

        rankTextview.setText((i+1) + "위");
        if(i != 0)
        {
            rankTextview.setBackgroundColor(parent.getResources().getColor(R.color.colorPrimary));
        }

        if (courseList.get(i).getCourseGrade().equals("제한없음") || courseList.get(i).getCourseGrade().equals("")) {
            courseGrade.setText("모든학년");
        }
        else {
            courseGrade.setText(courseList.get(i).getCourseGrade() + "학년");
        }

        courseTitle.setText(courseList.get(i).getCourseTitle());
        courseCredit.setText(courseList.get(i).getCourseCredit() + "학점");
        courseDivide.setText(courseList.get(i).getCourseDivide() + "분반");


        if (courseList.get(i).getCoursePersonnel() == 0) {
            coursePersonnel.setText("인원 제한 없음");
        }
        else {
            coursePersonnel.setText("제한인원 :" + courseList.get(i).getCoursePersonnel() + "명");
        }
        if( courseList.get(i).getCourseProfessor().equals(""))
        {
                    courseProfessor.setText("개인 연구");
        }
       else
        {
            courseProfessor.setText(courseList.get(i).getCourseProfessor()+ "교수님");
        }
        courseTime.setText(courseList.get(i).getCourseTime() + "");
        v.setTag(courseList.get(i).getCourseID());
        return v;
    }
}