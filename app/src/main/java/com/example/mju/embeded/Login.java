package com.example.mju.embeded;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends AppCompatActivity {

    public final static String ID ="hello";
    public final static String PASSWORD ="1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(String id, String password){
        Toast toast2 = Toast.makeText(this, id+"님 환영합니다.", Toast.LENGTH_SHORT);
        toast2.show();
    }

        //로그인 ID, 비밀번호가 일치하는지 확인하는 메소드.
    public void onCilckLogin(View view) {
        String id;
        String password;
        EditText _id =(EditText)findViewById(R.id.login_ID);
        EditText _password =(EditText)findViewById(R.id.login_PASS);

        id = _id.getText().toString();
        password = _password.getText().toString();

        if(id.equals(ID)){
            if (password.equals(PASSWORD)){
                login(id, password);
                return;
            }
        }
        Toast toast = Toast.makeText(this, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT);
        toast.show();
    }
}
