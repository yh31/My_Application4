package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Camera extends AppCompatActivity {
    private Uri _imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cam);
        //戻るボタン
        Button returnButton = findViewById(R.id.return_button);
//        returnButton.setOnClickListener(new View.OnClickListener() {
//            //@Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

        // lambda式
        returnButton.setOnClickListener(v -> finish());
    }
    //カメラ機能部分
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 200 && resultCode == RESULT_OK) {
            ImageView ivCamera = findViewById(R.id.ivCamera);
            ivCamera.setImageURI(_imageUri);
        }
    }
    public void onCameraImageClick(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,200);
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date now = new Date(System.currentTimeMillis());
        String nowStr = dataFormat.format(now);
        String fileName = "CameraIntentSamplePhoto_" + nowStr + ".jpg";
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, fileName);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        ContentResolver resolver = getContentResolver();
        _imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
        intent = new Intent(MediaStore.EXTRA_OUTPUT, _imageUri);
        startActivityForResult(intent,200);
    }

}
