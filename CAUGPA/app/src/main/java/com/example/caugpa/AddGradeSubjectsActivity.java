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
        mRecyclerAdapter = new AddSubjectsMySubjectAdapter();

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
    }

    public void deleteSubjects(int id){
        Toast.makeText(this,"과목 제거 완료!",Toast.LENGTH_SHORT).show();
        myDBHelper myDBHelper = new myDBHelper(this);
        SQLiteDatabase sqlDB = myDBHelper.getReadableDatabase();
        sqlDB.delete("mySubjects","id=?",new String[]{""+id});
        sqlDB.close();
    }
}