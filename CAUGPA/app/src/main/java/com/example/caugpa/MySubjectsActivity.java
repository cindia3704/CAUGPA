package com.example.CAUGPA;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.caugpa.MySubjects;
import com.example.caugpa.MySubjectsAdapter;
import com.example.caugpa.R;

import java.util.ArrayList;

public class MySubjectsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MySubjectsAdapter mRecyclerAdapter;
    private ArrayList<MySubjects> mySubjectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_subjects);
        mySubjectList = (ArrayList<MySubjects>)getIntent().getSerializableExtra("subject");

        mRecyclerView = (RecyclerView) findViewById(R.id.mySub_recyclerView);

        /* initiate adapter */
        mRecyclerAdapter = new MySubjectsAdapter();

        /* initiate recyclerview */
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerAdapter.setMySubjectsList(mySubjectList);
    }
}