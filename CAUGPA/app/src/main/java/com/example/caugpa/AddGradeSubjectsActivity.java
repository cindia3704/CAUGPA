package com.example.caugpa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddGradeSubjectsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private AddSubjectsMySubjectAdapter mRecyclerAdapter;
    private ArrayList<MySubjects> mySubjectList;

    private RecyclerView aRecyclerView;
    private NewSubjectsAdapter aRecyclerAdapter;

    private ArrayList<AllSubjects> allSubjectList;
    private ArrayList<MySubjects> addedSubjectList = new ArrayList<MySubjects>();

    private TextView title;
    private Button addButton;
    int currentSchoolYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grade_subjects);
        title = findViewById(R.id.addSubjects_title);
        currentSchoolYear = (Integer)getIntent().getIntExtra("year", 0);
        title.setText(""+currentSchoolYear+"학년 과목");
        addButton = findViewById(R.id.addSubjects);
        addButton.setOnClickListener(view -> {
            for(int i=0;i<addedSubjectList.size();i++){
                addSubjects(i);
            }
            Toast.makeText(this,"과목 추가 성공", Toast.LENGTH_SHORT).show();
        });

        mySubjectList = (ArrayList<MySubjects>)getIntent().getSerializableExtra("subject");
        allSubjectList = (ArrayList<AllSubjects>)getIntent().getSerializableExtra("all");

        mRecyclerView = (RecyclerView) findViewById(R.id.addSub_recyclerView_mySubs);
        aRecyclerView = (RecyclerView)findViewById(R.id.addSub_recyclerView_moreSubs);

        /* initiate adapter */
        mRecyclerAdapter = new AddSubjectsMySubjectAdapter();
        aRecyclerAdapter =  new NewSubjectsAdapter();

        /* initiate recyclerview */
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerAdapter.setMySubjectsList(mySubjectList);
        mRecyclerAdapter.setOnItemClickListener(
                new AddSubjectsMySubjectAdapter.onItemClickListener(){
                    @Override
                    public void onItemClick(View v, int pos) {
                        deleteSubjects(mySubjectList.get(pos).getId());
                    }
                }
        );

        aRecyclerView.setAdapter(aRecyclerAdapter);
        aRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        aRecyclerAdapter.setMySubjectsList(allSubjectList);
        aRecyclerAdapter.setOnItemClickListener(
                new NewSubjectsAdapter.onItemClickListener(){
                    @Override
                    public void onItemClick(View v, int pos, String value) {
                        Log.d("subject",value);
                        int year = allSubjectList.get(pos).getYear();
                        String subject = allSubjectList.get(pos).getSubject();
                        String score=value;
                        int weight=allSubjectList.get(pos).getWeight();
                        String major=allSubjectList.get(pos).getMajor();
                        String majorSpecific=allSubjectList.get(pos).getMajorSpecific();
                        MySubjects newSub = new MySubjects(year,subject,score,weight,major,majorSpecific,0);
                        addedSubjectList.add(newSub);

                    }
                }
        );
    }

    public void deleteSubjects(int id){
        Toast.makeText(this,"과목 제거 완료!",Toast.LENGTH_SHORT).show();
        myDBHelper myDBHelper = new myDBHelper(this);
        SQLiteDatabase sqlDB = myDBHelper.getReadableDatabase();
        sqlDB.delete("mySubjects","id=?",new String[]{""+id});
        sqlDB.close();
    }

    public void addSubjects(int id){
        myDBHelper myDBHelper = new myDBHelper(this);
        SQLiteDatabase sqlDB = myDBHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("sName", addedSubjectList.get(id).getSubject());
        cv.put("year", currentSchoolYear);
        cv.put("score", addedSubjectList.get(id).getScore());
        cv.put("weight", addedSubjectList.get(id).getWeight());
        cv.put("major", addedSubjectList.get(id).getMajor());
        cv.put("majorSpecific", addedSubjectList.get(id).getMajorSpecific());
//        Toast.makeText(this,"성적:"+addedSubjectList.get(id).getScore(), Toast.LENGTH_SHORT).show();
        long result = sqlDB.insert("mySubjects",null,cv);
        if(result == -1){
            Toast.makeText(this,"과목 추가 실패", Toast.LENGTH_SHORT).show();
        }
        sqlDB.close();
    }
}