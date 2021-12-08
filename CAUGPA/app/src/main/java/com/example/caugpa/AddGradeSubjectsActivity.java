package com.example.caugpa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
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

    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grade_subjects);
        title = findViewById(R.id.addSubjects_title);
        title.setText(""+(Integer)getIntent().getIntExtra("year", 0)+"학년 과목");

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
                    public void onItemClick(View v, int pos) {
                        addSubjects(allSubjectList.get(pos).getId());
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

    }
}