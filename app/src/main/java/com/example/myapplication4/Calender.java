package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.content.Intent;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Calender extends AppCompatActivity {

    // 表示する画像の名前（拡張子無し）
    private String[] members = new String[4];
    private String[] memberen = new String[4];

    // Resource IDを格納するarray
    private List<Integer> imgList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cal);
        //月取得表示
        Calendar cal = Calendar.getInstance();       //カレンダーを取得
        int mon = cal.get(cal.MONTH)+1;//月取得
        int day = cal.get(cal.DATE);//日付取得
        TextView tv = (TextView)findViewById(R.id.textView_mon);
        tv.setText(mon+"月"+day+"日の旬の魚");
        //画像表示
        if(3<= mon&&mon <=5){//春
            members = new String[]{"サヨリ","マダイ","鰆","メバル"};
            memberen = new String[]{"sayori","madai","sawara","mebaru"};
        }else if(6<=mon&&mon<=8){//夏
            members = new String[]{"アジ","イワシ","カマス","スズキ"};
            memberen = new String[]{"azi","iwasi","kamasu","suzuki"};
        }else if(9<=mon&&mon<=11){//秋
            members = new String[]{"サバ","太刀魚","サンマ","カツオ"};
            memberen = new String[]{"saba","tatiuo","sannma","katuo"};
        }else{//冬
            members = new String[]{"ヒラメ","ブリ","マダイ","アンコウ"};
            memberen = new String[]{"hirame","buri","madai","annkou"};
        }
        // for-each member名をR.drawable.名前としてintに変換してarrayに登録
        for (String member: memberen){
            int imageId = getResources().getIdentifier(
                    member,"drawable", getPackageName());
            imgList.add(imageId);
        }

        // GridViewのインスタンスを生成
        GridView gridview = findViewById(R.id.gridview);
        // BaseAdapter を継承したGridAdapterのインスタンスを生成
        // 子要素のレイアウトファイル grid_items.xml を
        // activity_main.xml に inflate するためにGridAdapterに引数として渡す
        GridAdapter adapter = new GridAdapter(this.getApplicationContext(),
                R.layout.grid_items,
                imgList,
                members
        );

        // gridViewにadapterをセット
        gridview.setAdapter(adapter);

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