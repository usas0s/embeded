package com.example.mju.embeded;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

/*
         ~ Copyright (C) 컴퓨터공학과 60132291 오지훈
*/

public class Login_Register extends AppCompatActivity {

    public static final Uri Content_uri = myContentProvider.CONTENT_URI_Login;
    private EditText _id;
    private EditText _pass;
    private EditText _pass2;
    private EditText _name;
    private EditText _depart;
    private EditText _phone;
    private EditText _email;
    private CheckBox ch1;
    private ContentResolver cr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
    }

    public void onCilckRegister(View view) {
        Cursor mCursor;
        cr = getContentResolver();

        _id =(EditText)findViewById(R.id.register_ID);
        String id = _id.getText().toString();
        _pass =(EditText)findViewById(R.id.register_PASS);
        String pass = _pass.getText().toString();
        _pass2 =(EditText)findViewById(R.id.register_PASS2);
        String pass2 = _pass2.getText().toString();
        _name =(EditText)findViewById(R.id.register_NAME);
        String name = _name.getText().toString();
        _depart =(EditText)findViewById(R.id.register_DEPART);
        String depart = _depart.getText().toString();
        _phone =(EditText)findViewById(R.id.register_PHONE);
        String phone = _phone.getText().toString();
        _email =(EditText)findViewById(R.id.register_EMAIL);
        String email = _email.getText().toString();
        ch1 = (CheckBox)findViewById(R.id.checkBox);

        //공백일때.
        if(id.equals("")) {
            Toast.makeText(this, "ID를 입력하지 않았습니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(pass.equals("")) {
            Toast.makeText(this," password를 입력하지 않았습니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(pass2.equals("")) {
            Toast.makeText(this, "retype password를 입력하지 않았습니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(name.equals("")) {
            Toast.makeText(this, "이름을 입력하지 않았습니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(depart.equals("")) {
            Toast.makeText(this, "학과를 입력하지 않았습니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(phone.equals("")) {
            Toast.makeText(this, "휴대폰번호를 입력하지 않았습니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(email.equals("")) {
            Toast.makeText(this, "email을 입력하지 않았습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        //id 형식 : 시작은 영문으로만, '_'를 제외한 특수문자 안되며 영문, 숫자, '_'으로만 이루어진 5 ~ 12자 이하
        if(!isId(id)){
            Toast.makeText(this, "올바른 ID형식이 아닙니다. \n다시 입력해주세요.", Toast.LENGTH_SHORT).show();
            _id.setText(null);
            _id.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            return;
        }

        //패스워드가 일치하지 않을때.
        if(!pass.equals(pass2)){
            Toast.makeText(this, "retype password가 일치하지 않습니다. \n다시 입력해주세요.", Toast.LENGTH_SHORT).show();
            _pass.setText(null);
            _pass2.setText(null);
            _pass.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            return;
        }

        //올바른 이메일형식이 아닐떄.
        if(!isEmail(email)){
            Toast.makeText(this, "올바른 email형식이 아닙니다.\n 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
            _email.setText(null);
            _email.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            return;
        }

        //이미 존재하는 id인지 확인.
        mCursor = cr.query(Content_uri, null, "owner_id =\""+id+"\"", null, null);
        if(mCursor.getCount()>0){
            Toast.makeText(this, "이미 존재하는 ID입니다.", Toast.LENGTH_SHORT).show();
            _id.setText(null);
            _id.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            return;
        }

        //이용약관 동의.
        if(!ch1.isChecked()){
            Toast.makeText(this, "이용약관에 동의해주십시오.", Toast.LENGTH_SHORT).show();
            return;
        }

        //insert
        ContentValues v = new ContentValues();
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_ID, id);
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_PASS, pass);
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_NAME, name);
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_DEPART, depart);
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_PHONE, phone);
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_EMAIL, email);
        cr.insert(Content_uri, v);

        Toast.makeText(this, "회원가입 완료. \n로그인창으로 돌아갑니다.", Toast.LENGTH_SHORT).show();

        Intent intent1 = new Intent(this, Service_SoundEffect.class);
        intent1.putExtra("sound", R.raw.water);
        startService(intent1);

        Intent intent2 = new Intent(this, Login.class);
        startActivity(intent2);
        finish();
    }
    public static boolean isId(String id) {
        if (id==null) return false;
        boolean b = Pattern.matches("^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$",id.trim());
        return b;
    }

    public static boolean isEmail(String email) {
        if (email==null) return false;
        boolean b = Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+",email.trim());
        return b;
    }
}
