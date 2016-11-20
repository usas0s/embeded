package com.example.mju.embeded;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Apply extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);
    }

    // 모임 참가 양식 작성 완료 후 참가 신청 버튼 터치 시 실행.
    protected void onClickApply(View view)
    {
//        EditText tv_name = (EditText) findViewById(R.id.user_name);
//        String name = tv_name.getText().toString();
        String name = ((EditText) findViewById(R.id.user_name)).getText().toString();
        String phone = ((EditText) findViewById(R.id.user_phone)).getText().toString();
        String email = ((EditText) findViewById(R.id.user_email)).getText().toString();

        if(name.equals("") | phone.equals("") | email.equals("")) // 이름 or 전화번호 or email 중 하나라도 비어있을 경우
        {
            Toast.makeText(getApplicationContext(), "필수 입력사항을 작성해주세요!", Toast.LENGTH_SHORT).show();
            
        }
        else // 모두 입력되어 있을 경우
        {
            if(((CheckBox)findViewById(R.id.cb_agree)).isChecked())
            {
                Toast.makeText(getApplicationContext(), "INSERT " + name + " / " + phone + " / " + email + " INTO DB", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "약관에 동의해주세요.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
