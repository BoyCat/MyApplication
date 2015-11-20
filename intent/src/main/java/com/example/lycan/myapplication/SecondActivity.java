package com.example.lycan.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends Activity {
    int result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        Intent intent = getIntent();

        switch(intent.getStringExtra("calc")) {
            case "더하기" :
                result = intent.getIntExtra("Num1", 0) + intent.getIntExtra("Num2", 0);
                break;
            case "빼기" :
                result = intent.getIntExtra("Num1", 0) - intent.getIntExtra("Num2", 0);
                break;
            case "곱하기" :
                result = intent.getIntExtra("Num1", 0) * intent.getIntExtra("Num2", 0);
                break;
            case "나누기" :
                result = intent.getIntExtra("Num1", 0) / intent.getIntExtra("Num2", 0);
                break;
        }

        Button cal = (Button) findViewById(R.id.back);
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent(getApplicationContext(), MainActivity.class);
                outIntent.putExtra("result", result);
                setResult(RESULT_OK, outIntent);
                finish();
            }
        });
    }

}
