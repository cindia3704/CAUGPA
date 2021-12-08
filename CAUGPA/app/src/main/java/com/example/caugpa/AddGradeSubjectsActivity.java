package com.example.caugpa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class AddGradeSubjectsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MySubjectsAdapter mRecyclerAdapter;
    private ArrayList<MySubjects> mySubjectList;

    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grade_subjects);
        title = findViewById(R.id.addSubjects_title);
        title.setText(""+(Integer)getIntent().getIntExtra("year", 0)+"학년 과목");
        mySubjectList = (ArrayList<MySubjects>)getIntent().getSerializableExtra("subject");

        mRecyclerView = (RecyclerView) findViewById(R.id.addSub_recyclerView_mySubs);

        /* initiate adapter */
        mRecyclerAdapter = new MySubjectsAdapter();

        /* initiate recyclerview */
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerAdapter.setMySubjectsList(mySubjectList);
    }
}