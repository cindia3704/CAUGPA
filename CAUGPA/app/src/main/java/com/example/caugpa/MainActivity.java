package com.example.caugpa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    boolean noBasic = false;
    private Button initializeDB;
    private Button seeAllSub;
    private Button grade1Add;
    private Button grade2Add;
    private Button grade3Add;
    private Button grade4Add;
    private Button basicMajorFlag;

    private TextView totalScore;
    private TextView majorScore;
    private TextView grade1Score;
    private TextView grade2Score;
    private TextView grade3Score;
    private TextView grade4Score;
    private final String TotalGPA = "/4.5";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initializeDB = findViewById(R.id.main_initialize);
        seeAllSub = findViewById(R.id.main_seeAllSubjects);
        grade1Add = findViewById(R.id.grade1AddSubject);
        grade2Add = findViewById(R.id.grade2AddSubject);
        grade3Add = findViewById(R.id.grade3AddSubject);
        grade4Add = findViewById(R.id.grade4AddSubject);
        basicMajorFlag = findViewById(R.id.noBasicMajor);

        totalScore = findViewById(R.id.totalGPAScore);
        majorScore = findViewById(R.id.majorGPAScore);
        grade1Score = findViewById(R.id.grade1GPAScore);
        grade2Score = findViewById(R.id.grade2GPAScore);
        grade3Score = findViewById(R.id.grade3GPAScore);
        grade4Score = findViewById(R.id.grade4GPAScore);

        initializeDB.setOnClickListener(view -> {
           initializeDB(view);
        });

        seeAllSub.setOnClickListener(view -> {
            Intent intentToMySubjects = new Intent(getApplicationContext(), com.example.CAUGPA.MySubjectsActivity.class);
            String sql = "select * from mySubjects";
            ArrayList<MySubjects> subjects = getSubjects(sql);
            intentToMySubjects.putExtra("subject", subjects);
            startActivity(intentToMySubjects);
        });

        grade1Add.setOnClickListener(view -> {
            Intent intentToAddSubjects = new Intent(getApplicationContext(),AddGradeSubjectsActivity.class);
            String sql = "select * from mySubjects where year=1";
            ArrayList<MySubjects> subjects = getSubjects(sql);
            intentToAddSubjects.putExtra("subject", subjects);
            intentToAddSubjects.putExtra("year", 1);

            String sqlAll = "select * from allSubjects";
            ArrayList<AllSubjects> allSubjects = getAllSubjects(sqlAll);
            intentToAddSubjects.putExtra("all", allSubjects);
            startActivity(intentToAddSubjects);
        });

        grade2Add.setOnClickListener(view -> {
            Intent intentToAddSubjects2 = new Intent(getApplicationContext(),AddGradeSubjectsActivity.class);
            String sql = "select * from mySubjects where year=2";
            ArrayList<MySubjects> subjects = getSubjects(sql);
            intentToAddSubjects2.putExtra("subject", subjects);
            intentToAddSubjects2.putExtra("year", 2);

            String sqlAll = "select * from allSubjects";
            ArrayList<AllSubjects> allSubjects = getAllSubjects(sqlAll);
            intentToAddSubjects2.putExtra("all", allSubjects);
            startActivity(intentToAddSubjects2);
        });

        grade3Add.setOnClickListener(view -> {
            Intent intentToAddSubjects3 = new Intent(getApplicationContext(),AddGradeSubjectsActivity.class);
            String sql = "select * from mySubjects where year=3";
            ArrayList<MySubjects> subjects = getSubjects(sql);
            intentToAddSubjects3.putExtra("subject", subjects);
            intentToAddSubjects3.putExtra("year", 3);

            String sqlAll = "select * from allSubjects";
            ArrayList<AllSubjects> allSubjects = getAllSubjects(sqlAll);
            intentToAddSubjects3.putExtra("all", allSubjects);
            startActivity(intentToAddSubjects3);
        });

        grade4Add.setOnClickListener(view -> {
            Intent intentToAddSubjects4 = new Intent(getApplicationContext(),AddGradeSubjectsActivity.class);
            String sql = "select * from mySubjects where year=4";
            ArrayList<MySubjects> subjects = getSubjects(sql);
            intentToAddSubjects4.putExtra("subject", subjects);
            intentToAddSubjects4.putExtra("year", 4);

            String sqlAll = "select * from allSubjects";
            ArrayList<AllSubjects> allSubjects = getAllSubjects(sqlAll);
            intentToAddSubjects4.putExtra("all", allSubjects);
            startActivity(intentToAddSubjects4);
        });
        initTextViews();
        basicMajorFlag.setOnClickListener(view -> {
            noBasic = !noBasic;
            String sqlNoBasic ="select * from mySubjects where majorSpecific='ADV' or majorSpecific='REQ'";
            String sqlMajor ="select * from mySubjects where major='Y'";

            if(noBasic){
                majorScore.setText(String.format("%.2f",calculateGPA(sqlNoBasic))+TotalGPA);
            }else{
                majorScore.setText(String.format("%.2f",calculateGPA(sqlMajor))+TotalGPA);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initTextViews();
    }

    // 모든 textview 평점 계산하기
    private void initTextViews(){
        String sqlTotal ="select * from mySubjects";
        String sqlMajor ="select * from mySubjects where major='Y'";
        String sqlGrade1 ="select * from mySubjects where year=1";
        String sqlGrade2 ="select * from mySubjects where year=2";
        String sqlGrade3 ="select * from mySubjects where year=3";
        String sqlGrade4 ="select * from mySubjects where year=4";

        totalScore.setText(String.format("%.2f",calculateGPA(sqlTotal))+TotalGPA);
        majorScore.setText(String.format("%.2f",calculateGPA(sqlMajor))+TotalGPA);
        grade1Score.setText(String.format("%.2f",calculateGPA(sqlGrade1))+TotalGPA);
        grade2Score.setText(String.format("%.2f",calculateGPA(sqlGrade2))+TotalGPA);
        grade3Score.setText(String.format("%.2f",calculateGPA(sqlGrade3))+TotalGPA);
        grade4Score.setText(String.format("%.2f",calculateGPA(sqlGrade4))+TotalGPA);
    }

    public void initializeDB(View view){
        myDBHelper myDBHelper = new myDBHelper(this);
        SQLiteDatabase sqlDB = myDBHelper.getWritableDatabase();
        myDBHelper.onUpgrade(sqlDB,1,2);
        sqlDB.close();
        Toast.makeText(getApplicationContext(),"초기화 완료!",Toast.LENGTH_SHORT).show();
    }

    public double calculateGPA(String sql){
        myDBHelper myDBHelper = new myDBHelper(this);
        SQLiteDatabase sqlDB = myDBHelper.getReadableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery(sql,null);

        double totalGPAScore = 0.00;
        double totalGPAWeight = 0.00;
        double totalWeights = 0.00;
        while(cursor.moveToNext()){
            String score = cursor.getString(3);
            Integer weights = cursor.getInt(4);
            double scoreDouble = 0.0;
            switch (score){
                case "A+":
                    scoreDouble = 4.5;
                    break;
                case "P":
                    scoreDouble = 4.5;
                    break;
                case "A":
                    scoreDouble = 4.0;
                    break;
                case "B+":
                    scoreDouble = 3.5;
                    break;
                case "B":
                    scoreDouble = 3.0;
                    break;
                case "C+":
                    scoreDouble = 2.5;
                    break;
                case "C":
                    scoreDouble = 2.0;
                    break;
                case "D+":
                    scoreDouble = 1.5;
                    break;
                case "D":
                    scoreDouble = 1.0;
                    break;
                default:
                    scoreDouble = 0.0;
            }
            totalGPAWeight+=scoreDouble*(double)weights;
            totalWeights +=(double)weights;
        }
        // 아무것도 없을때 예외 처리
        if(totalWeights>0) {
            totalGPAScore = totalGPAWeight / totalWeights;
        }

        cursor.close();
        sqlDB.close();
        return totalGPAScore;
    }

    public ArrayList<MySubjects> getSubjects(String sql){
        myDBHelper myDBHelper = new myDBHelper(this);
        SQLiteDatabase sqlDB = myDBHelper.getReadableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery(sql,null);

        ArrayList<MySubjects> tempList = new ArrayList<>();

        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String subject = cursor.getString(1);
            int year = cursor.getInt(2);
            String score=cursor.getString(3);
            int weight=cursor.getInt(4);
            String major=cursor.getString(5);
            String majorSpecific=cursor.getString(6);
            MySubjects ms = new MySubjects(year,subject,score,weight,major,majorSpecific,id);
            tempList.add(ms);
        }
        cursor.close();
        sqlDB.close();
        return tempList;
    }
    public ArrayList<AllSubjects> getAllSubjects(String sql){
        myDBHelper myDBHelper = new myDBHelper(this);
        SQLiteDatabase sqlDB = myDBHelper.getReadableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery(sql,null);

        ArrayList<AllSubjects> tempList = new ArrayList<>();

        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String subject = cursor.getString(1);
            int year = cursor.getInt(2);
            int weight=cursor.getInt(3);
            String major=cursor.getString(4);
            String majorSpecific=cursor.getString(5);
            AllSubjects as = new AllSubjects(year,subject,weight,major,majorSpecific,id);
            tempList.add(as);
        }
        cursor.close();
        sqlDB.close();
        return tempList;
    }
}