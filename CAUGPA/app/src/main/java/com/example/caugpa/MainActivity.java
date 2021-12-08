package com.example.caugpa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.caugpa.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
//    private ActivityMainBinding binding;
////    static final int SERVICE_ID = 1001;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding= ActivityMainBinding.inflate(getLayoutInflater());
//        View view = binding.getRoot();
//        setContentView(view);
//    }

//    public void buttonClick(View view){
//        Intent intent = new Intent(this, MyService.class);
//        Toast.makeText(this,"hi",Toast.LENGTH_LONG).show();
//        startService(intent);
//    }
}