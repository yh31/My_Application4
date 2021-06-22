package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

import com.example.myapplication.R;


public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_his);

        Button returnButton = findViewById(R.id.return_button);
        //returnButton.setOnClickListener(new View.OnClickListener() {
        //@Override
        //public void onClick(View v) {
        //    finish();
        //}
        //});

        //lambda式
        returnButton.setOnClickListener(v -> finish());
    }

}
