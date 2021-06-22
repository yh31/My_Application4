package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

import com.example.myapplication.R;

import java.util.Calendar;


public class Calender extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cal);
        //月取得表示
        Calendar cal = Calendar.getInstance();       //カレンダーを取得
        int mon = cal.get(Calendar.MONTH);           //月取得
        TextView tv = (TextView)findViewById(R.id.textView_mon);
        tv.setText(mon+"月の旬の魚");

        //戻るボタン
        Button returnButton = findViewById(R.id.return_button);
//        returnButton.setOnClickListener(new View.OnClickListener() {
//            //@Override
//            public void onClick(View v) {finish();}
//        });

        // lambda式
        returnButton.setOnClickListener(v -> finish());
    }

}