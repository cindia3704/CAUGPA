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



public class MainActivity extends AppCompatActivity {
    private Button initializeDB;
    private Button seeAllSub;
    private Button grade1Add;
    private Button grade2Add;
    private Button grade3Add;
    private Button grade4Add;

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
            startActivity(intentToMySubjects);
        });
        grade1Add.setOnClickListener(view -> {
            Intent intentToAddSubjects = new Intent(getApplicationContext(),AddGradeSubjectsActivity.class);

        });

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

        totalScore.setText(calculateGPA(sqlTotal)+TotalGPA);
        majorScore.setText(calculateGPA(sqlMajor)+TotalGPA);
        grade1Score.setText(calculateGPA(sqlGrade1)+TotalGPA);
        grade2Score.setText(calculateGPA(sqlGrade2)+TotalGPA);
        grade3Score.setText(calculateGPA(sqlGrade3)+TotalGPA);
        grade4Score.setText(calculateGPA(sqlGrade4)+TotalGPA);
    }


    public class myDBHelper extends SQLiteOpenHelper{
        public myDBHelper(Context context){
            super(context,"groupDB",null,1);
        }
        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL("create table mySubjects(id integer, sName char(100), year integer, score char(10), weight integer, major char(20), majorSpecific char(100));");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists mySubjects;");
            onCreate(db);
        }
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
        int count = 0;
        while(cursor.moveToNext()){
            count++;
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
        Toast.makeText(this,count+"!!",Toast.LENGTH_SHORT).show();
        // 아무것도 없을때 예외 처리
        if(totalWeights>0) {
            totalGPAScore = totalGPAWeight / totalWeights;
        }

        cursor.close();
        sqlDB.close();
        return totalGPAScore;
    }
}