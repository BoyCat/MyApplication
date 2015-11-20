package com.cookandroid.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener{
    Switch switchbtn;
    RadioGroup rg;
    RadioButton radio1, radio2, radio3;
    Button btnExit, btnHome;
    ImageView img;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchbtn=(Switch)findViewById(R.id.switchBtn);

        tv=(TextView)findViewById(R.id.q2);
        img=(ImageView)findViewById(R.id.imgView);

        rg=(RadioGroup)findViewById(R.id.radioG);
        radio1=(RadioButton)findViewById(R.id.radio1);
        radio2=(RadioButton)findViewById(R.id.radio2);
        radio3=(RadioButton)findViewById(R.id.radio3);

        btnExit=(Button)findViewById(R.id.button);
        btnHome=(Button)findViewById(R.id.button2);

        tv.setVisibility(View.GONE);
        rg.setVisibility(View.GONE);
        img.setVisibility(View.GONE);

        switchbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(switchbtn.isChecked()==true){
                    tv.setVisibility(View.VISIBLE);
                    rg.setVisibility(View.VISIBLE);
                }
                else{
                    tv.setVisibility(View.GONE);
                    rg.setVisibility(View.GONE);
                    img.setVisibility(View.GONE);
                }
            }
        });

        radio1.setOnClickListener(this);
        radio2.setOnClickListener(this);
        radio3.setOnClickListener(this);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchbtn.setChecked(false);
                rg.clearCheck();
                img.setVisibility(View.GONE);
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
        img.setVisibility(View.VISIBLE);

        switch(rg.getCheckedRadioButtonId()){
            case R.id.radio1:
                img.setImageResource(R.mipmap.jelly);
                break;
            case R.id.radio2:
                img.setImageResource(R.mipmap.kit);
                break;
            case R.id.radio3:
                img.setImageResource(R.mipmap.lolli);
                break;
        }
    }
}
