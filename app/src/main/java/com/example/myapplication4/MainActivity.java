package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //カレンダーへの移動
        Button calender_button = findViewById(R.id.calender_button);
        calender_button.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Calender.class);
                startActivity(intent);
            }
        });
        //カメラ機能への移動
        Button camera_button = findViewById(R.id.camera_button);
        camera_button.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Camera.class);
                startActivity(intent);
            }
        });
        //画像判定機能への移動
        Button send_button = findViewById(R.id.send_button);
        send_button.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SubActivity.class);
                startActivity(intent);
            }
        });
        //履歴への移動
        Button history_button = findViewById(R.id.history_button);
        history_button.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), History.class);
                startActivity(intent);
            }
        });
    }
}