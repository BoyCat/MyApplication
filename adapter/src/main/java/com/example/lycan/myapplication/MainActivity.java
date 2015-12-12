package com.example.lycan.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       final GridView gv = (GridView) findViewById(R.id.gridView1);
        MyGridAdapter gAdapter = new MyGridAdapter(this);
       gv.setAdapter(gAdapter);

    }

    public class MyGridAdapter extends BaseAdapter{
        Context context;

        Integer[] posterID = {
                R.drawable.mov01, R.drawable.mov02, R.drawable.mov03,
                R.drawable.mov04, R.drawable.mov05, R.drawable.mov06,
                R.drawable.mov07, R.drawable.mov08, R.drawable.mov09,
                R.drawable.mov10, R.drawable.mov11, R.drawable.mov12,
                R.drawable.mov13, R.drawable.mov14, R.drawable.mov15,
                R.drawable.mov16, R.drawable.mov17, R.drawable.mov18,
                R.drawable.mov19, R.drawable.mov20, R.drawable.mov21,
                R.drawable.mov22, R.drawable.mov23, R.drawable.mov24,
                R.drawable.mov25, R.drawable.mov26, R.drawable.mov27,
                R.drawable.mov28
        };
        String[] posterTitle = {
                "신세계", "괴물", "하울링", "구르믈 버서난 달처럼",
                "수퍼맨 리턴즈", "감시자들", "Titanic", "파파로티",
                "완득이", "본레거시", "레미제라블", "설국열차",
                "마더", "여친소", "MATRIX", "더 울버린",
                "안녕, 형아", "전설의 주먹", "내 머릿속의 지우개", "해러포터:불의잔",
                "해운대", "킹메이커", "은밀하게 위대하게", "명량",
                "과속 스캔들", "지구가 멈추는 날", "군도", "her",
        };
        public MyGridAdapter(Context c){
            context = c;
        }

        @Override
        public int getCount() {
            return posterID.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View dv = (View)View.inflate(context, R.layout.dialog, null);

            ImageView iv = (ImageView)dv.findViewById(R.id.ivPoster);
            TextView tv = (TextView)dv.findViewById(R.id.tvPoster);

            iv.setLayoutParams(new LinearLayout.LayoutParams(100,150));
            iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
            iv.setPadding(5,5,5,0);
            tv.setPadding(5,0,5,5);

            iv.setImageResource(posterID[position]);
            tv.setText(posterTitle[position]);

            final int pos = position;

            dv.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    View dialogView = (View)View.inflate(MainActivity.this, R.layout.dialog, null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    ImageView ivPoster = (ImageView)dialogView.findViewById(R.id.ivPoster);
                    ivPoster.setImageResource(posterID[pos]);
                    dlg.setTitle(posterTitle[pos]);
                    dlg.setIcon(R.drawable.ic_launcher);
                    dlg.setView(dialogView);
                    dlg.setNegativeButton("닫기", null);
                    dlg.show();
                }
            });
            return dv;
        }
    }
}
