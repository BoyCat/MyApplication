package com.example.lycan.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {
    RadioGroup rg;
    RadioButton radio1, radio2, radio3, radio4;

    String calc = "error";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button cal = (Button) findViewById(R.id.cal);
        rg = (RadioGroup) findViewById(R.id.radioG);
        radio1 = (RadioButton) findViewById(R.id.radio1);
        radio2 = (RadioButton) findViewById(R.id.radio2);
        radio3 = (RadioButton) findViewById(R.id.radio3);
        radio4 = (RadioButton) findViewById(R.id.radio4);

        radio1.setOnClickListener(this);
        radio2.setOnClickListener(this);
        radio3.setOnClickListener(this);
        radio4.setOnClickListener(this);

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edt1 = (EditText) findViewById(R.id.edt1);
                EditText edt2 = (EditText) findViewById(R.id.edt2);

                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);

                intent.putExtra("calc", calc);
                intent.putExtra("Num1", Integer.parseInt(edt1.getText().toString()));
                intent.putExtra("Num2", Integer.parseInt(edt2.getText().toString()));
                startActivityForResult(intent, 0);
            }
        });
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
        switch (rg.getCheckedRadioButtonId()) {
            case R.id.radio1:
                calc = radio1.getText().toString();
                break;
            case R.id.radio2:
                calc = radio2.getText().toString();
                break;
            case R.id.radio3:
                calc = radio3.getText().toString();
                break;
            case R.id.radio4:
                calc = radio4.getText().toString();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            int result = data.getIntExtra("result", 0);
            Toast.makeText(getApplicationContext(), "합계 : " + result, Toast.LENGTH_SHORT).show();
        }
    }
}
