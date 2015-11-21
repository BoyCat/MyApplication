package com.example.lycan.myapplication;

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
    Button btn1, btn2, btn3, btn4;
    Button[] numBtn = new Button[10];
    Integer[] numBtnID = {R.id.n0, R.id.n1, R.id.n2, R.id.n3, R.id.n4, R.id.n5, R.id.n6, R.id.n7, R.id.n8, R.id.n9};
    int i;
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

        result=(TextView)findViewById(R.id.result);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        for(i=0; i< numBtnID.length; i++)
            numBtn[i] = (Button)findViewById(numBtnID[i]);

        for(i=0; i< numBtnID.length; i++){
            final int index;
            index = i;

            numBtn[index].setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    if(num1.isFocused()==true)
                        num1.setText(num1.getText().toString()+numBtn[index].getText().toString());
                    else if(num2.isFocused()==true)
                        num2.setText(num2.getText().toString()+numBtn[index].getText().toString());
                    else
                        Toast.makeText(getApplicationContext(), "숫자란 선택",Toast.LENGTH_SHORT).show();
                }
            });
        }
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
            }
        }
    }
}
