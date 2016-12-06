package com.example.mju.embeded;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterPost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_post);
    }

    private void onRegisterPost()
    {
        String name = ((EditText) findViewById(R.id.register_post_name)).getText().toString();
        String period = ((EditText) findViewById(R.id.register_post_period)).getText().toString();
        String place = ((EditText) findViewById(R.id.register_post_place)).getText().toString();
        String description = ((EditText) findViewById(R.id.register_post_description)).getText().toString();

        if(name.equals("") | period.equals("") | place.equals("") | description.equals("")) // 이름 or 전화번호 or email 중 하나라도 비어있을 경우
        {
            Toast.makeText(getApplicationContext(), "필수 입력사항을 작성해주세요!", Toast.LENGTH_SHORT).show();

        }
        else // 모두 입력되어 있을 경우
        {
            if(((CheckBox)findViewById(R.id.cb_agree)).isChecked())
            {
                Toast.makeText(getApplicationContext(), "INSERT " + name + " / " + period + " / " + place + " / " + description + " INTO DB", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "capcha 에 체크해주세요.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
