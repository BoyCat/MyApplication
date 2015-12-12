package com.example.lycan.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    myDBHelper myHelper;
    EditText edtName, edtNumber, edtNameResult, edtNumberResult;
    Button btnInit, btnInsert, btnSelect, btnUpdate, btnDelete;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = (EditText)findViewById(R.id.edtName);
        edtNumber = (EditText)findViewById(R.id.edtNumber);
        edtNameResult = (EditText)findViewById(R.id.edtNameResult);
        edtNumberResult = (EditText)findViewById(R.id.edtNumberResult);
        btnInit = (Button)findViewById(R.id.btnInit);
        btnInsert = (Button)findViewById(R.id.btnInsert);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        btnSelect = (Button)findViewById(R.id.btnSelect);

        myHelper = new myDBHelper(this);

        btnInit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                sqlDB = myHelper.getWritableDatabase();
                myHelper.onUpgrade(sqlDB, 1, 2);
                sqlDB.close();
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO groupTBL VALUES ( '" + edtName.getText().toString() + "' , " + edtNumber.getText().toString() + ");");
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "입력 됨", Toast.LENGTH_SHORT).show();
                btnSelect.callOnClick();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("UPDATE groupTBL SET gNumber =  '" + edtNumber.getText().toString() + "' WHERE gName = '" + edtName.getText().toString() + "';");
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "수정 됨", Toast.LENGTH_SHORT).show();
                btnSelect.callOnClick();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("DELETE FROM groupTBL WHERE gName = '" + edtName.getText().toString() + "';");
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "삭제 됨", Toast.LENGTH_SHORT).show();
                btnSelect.callOnClick();
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM groupTBL;", null);

                String strNames = "그룹 이름" + "\r\n" + "--------------"+ "\r\n";
                String strNumbers = "인원" + "\r\n" + "-------" + "\r\n";

                while(cursor.moveToNext()){
                    strNames += cursor.getString(0) + "\r\n";
                    strNumbers += cursor.getString(1) + "\r\n";
                }

                edtNameResult.setText(strNames);
                edtNumberResult.setText(strNumbers);

                cursor.close();
                sqlDB.close();
            }
        });
    }
    public class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context){
            super(context, "groupDB", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE  groupTBL ( gName CHAR(20) PRIMARY KEY, gNumber INTEGER);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(db);
        }
    }
}
