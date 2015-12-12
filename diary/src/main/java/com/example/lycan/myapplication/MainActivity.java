package com.example.lycan.myapplication;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DatePicker dp;
    EditText edt;
    Button bw;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dp=(DatePicker)findViewById(R.id.datePicker1);
        edt=(EditText)findViewById(R.id.edtDiary);
        bw=(Button)findViewById(R.id.btnWrite);

        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        name = Integer.toString(cYear) + "_" + Integer.toString(cMonth) + "_" + Integer.toString(cDay) + ".txt";
        String str = readDiary(name);
        edt.setText(str);
        bw.setEnabled(true);

        dp.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                name = Integer.toString(year) + "_" + Integer.toString(monthOfYear) + "_" + Integer.toString(dayOfMonth) + ".txt";
                String str = readDiary(name);
                edt.setText(str);
                bw.setEnabled(true);
            }
        });

        bw.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try{
                    FileOutputStream outFs = openFileOutput(name, Context.MODE_WORLD_WRITEABLE);
                    String str  = edt.getText().toString();
                    outFs.write(str.getBytes());
                    outFs.close();
                    Toast.makeText(getApplicationContext(), name+"이 저장됨", Toast.LENGTH_SHORT).show();
                }catch(IOException e){}
            }
        });
    }

    String readDiary(String name){
        String diaryStr=null;
        FileInputStream inFs;
        try{
            inFs = openFileInput(name);
            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();
            diaryStr=(new String(txt)).trim();
            bw.setText("수정하기");
        }catch (IOException e){
            edt.setHint("일기 없음");
            bw.setText("새로 저장");
        }
        return diaryStr;
    }
}
