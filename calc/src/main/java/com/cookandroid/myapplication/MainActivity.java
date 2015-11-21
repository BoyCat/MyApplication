package com.cookandroid.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{
    EditText num1, num2;
    Button btn1, btn2, btn3, btn4, btn5;
    TextView result;
    float a,b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = (EditText)findViewById(R.id.num1);
        num2 = (EditText)findViewById(R.id.num2);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);

        result=(TextView)findViewById(R.id.result);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
       if (num1.getText().toString().equals("") || num2.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(), "값을 입력하지 않음", Toast.LENGTH_SHORT).show();
       else {
           a = Float.parseFloat(num1.getText().toString());
           b = Float.parseFloat(num2.getText().toString());
           switch (v.getId()) {
                case R.id.btn1:
                    result.setText("계산 결과 : " + (a + b));
                    break;
                case R.id.btn2:
                    result.setText("계산 결과 : " + (a - b));
                    break;
                case R.id.btn3:
                    result.setText("계산 결과 : " + (a * b));
                    break;
                case R.id.btn4:
                    if (b == 0)
                        Toast.makeText(getApplicationContext(), "0으로 나눔", Toast.LENGTH_SHORT).show();
                    else result.setText("계산 결과 : " + (a / b));
                    break;
                case R.id.btn5:
                    result.setText("계산 결과 : " + (a % b));
                    break;
            }
        }
    }
}
