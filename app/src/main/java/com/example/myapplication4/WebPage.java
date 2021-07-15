package com.example.myapplication4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.example.myapplication.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WebPage extends AppCompatActivity {
    private static final int REQUEST_PERMISSIONS = 1000;
    private static final int REQUEST_SELECT_FILE_CODE = 1001;
    //private static final String URL = "http://192.168.6.30:5000/";//wi-fi
    //private static final String URL = "http://127.0.0.1:5000/";//有線
    //private static final String URL = "http://10.0.2.2:5000/";//エミュレータ用
    private static final String URL = "http://27.106.242.66:5000/";//愛媛フリーwi-fi


    private ValueCallback<Uri[]> filePathCallback;
    private Uri mImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_web);
        this.setupWebView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setupWebView() {
        WebView webView = new WebView(this);
        setContentView(webView);
        webView.loadUrl(URL);
        // JavaScriptを有効にする
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {
            /**
             * 「ファイルを選択」ボタンが押された時
             * For Android > 5.0
             */
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                showFileChooser(filePathCallback, fileChooserParams);
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_SELECT_FILE_CODE) {
            if (filePathCallback != null) {
                if (resultCode == RESULT_OK) {
                    Uri[] result = WebChromeClient.FileChooserParams.parseResult(resultCode, data);
                    if (result == null) {
                        result = new Uri[]{mImageUri};
                    }
                    filePathCallback.onReceiveValue(result);
                } else {
                    filePathCallback.onReceiveValue(null);
                }
                filePathCallback = null;
            }
        }
    }

    private void showFileChooser(ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        // 完了していない処理があれば完了する
        if (this.filePathCallback != null) {
            this.filePathCallback.onReceiveValue(null);
        }
        this.filePathCallback = filePathCallback;

        // 権限がないときは、権限を要求する
        if (!CameraPermission.checkAndRequestPermissions(this, REQUEST_PERMISSIONS)) {
            this.filePathCallback.onReceiveValue(null);
            this.filePathCallback = null;
            return;
        }

        // カメラとファイルのインテントを作成する
        Intent chooserIntent = Intent.createChooser(fileChooserParams.createIntent(), "写真の選択");
        try {
            mImageUri = createImageFile();
            Intent imageCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            imageCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Parcelable[]{imageCaptureIntent});
        } catch (IOException ex) {
            mImageUri = null;
        }
        startActivityForResult(chooserIntent, REQUEST_SELECT_FILE_CODE);
    }

    private Uri createImageFile() throws IOException {
        File folder = getExternalFilesDir(Environment.DIRECTORY_DCIM);
        String date = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String fileName = String.format("MyApp_%s.jpg", date);
        File cameraFile = new File(folder, fileName);

        return FileProvider.getUriForFile(
                this,
                getApplicationContext().getPackageName() + ".fileprovider",
                cameraFile);
    }
}
