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
import android.widget.EditText;
import android.widget.Toast;


/*
        ~ Copyright (C) 컴퓨터공학과 60132291 오지훈
*/

// 로그인 처리
public class Login extends AppCompatActivity {
    public static Context Lcontext;
    public static final Uri Content_URI = myContentProvider.CONTENT_URI_Login;
    private ContentResolver cr;
    private EditText editText1;
    private EditText editText2;
    public static boolean Login_State =false;
    public static String current_Account_ID;
    public static String current_Account_PASS;
    public static String current_Account_NAME;
    public static String current_Account_DEPART;
    public static String current_Account_PHONE;
    public static String current_Account_EMAIL;
    private Cursor current_Cursor;

    //외부 Activity에서 메소드 호출 가능하도록 context
    //contentResolver 초기화.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Lcontext = this;
        cr =getContentResolver();
    }

    // 멤버 탈퇴 처리
    public void memberLeave(){
        //cr = getContentResolver();
        cr.delete(Content_URI, "_id = "+current_Cursor.getInt(current_Cursor.getColumnIndex(Login_Contract.FeedEntry._ID)), null);
    }

    // 모두 null로 채워 로그아웃 처리
    public void logout(){
        current_Account_ID = null;
        current_Account_PASS = null;
        current_Account_NAME = null;
        current_Account_DEPART = null;
        current_Account_PHONE = null;
        current_Account_EMAIL = null;
        current_Cursor = null;
        Login_State = false;
    }

    //현재 로그인여부 반환.
    public boolean getLoginState(){
        return Login_State;
    }

    //현재 로그인된 계정 values 반환.
    public ContentValues getLoginAccount(){
        if(!Login_State) return null;
        ContentValues v = new ContentValues();
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_ID, current_Account_ID);
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_PASS, current_Account_PASS);
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_NAME, current_Account_NAME);
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_DEPART, current_Account_DEPART);
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_PHONE, current_Account_PHONE);
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_EMAIL, current_Account_EMAIL);
        return v;
    }


    //입력된 id, password가 db상에 존재하는지 탐색하는 메소드.
    //return :일치하는 db행의 Cursor
    public Cursor search(String id, String password){
        Cursor mCursor;
        //cr =getContentResolver();
        mCursor = cr.query(Content_URI, null, "owner_id =\""+id+"\"", null, null);
        if(mCursor.getCount()!=0) {
            while(mCursor.moveToNext()){
                if(mCursor.getString(mCursor.getColumnIndex(Login_Contract.FeedEntry.COLUMN_NAME_PASS)).equals(password)){
                    return mCursor;
                }
            }
        }
        return null;
    }

    //로그인
    public void onCilckLogin(View view) {
        Cursor mCursor;
        editText1 =(EditText)findViewById(R.id.login_ID);
        editText2 =(EditText)findViewById(R.id.login_PASS);
        String id =editText1.getText().toString();
        String password = editText2.getText().toString();

        //로그인작업.
        if((mCursor =search(id, password))!=null) {

            Login_State = true;
            current_Account_ID =
                    mCursor.getString(mCursor.getColumnIndex(Login_Contract.FeedEntry.COLUMN_NAME_ID));
            current_Account_PASS =
                    mCursor.getString(mCursor.getColumnIndex(Login_Contract.FeedEntry.COLUMN_NAME_PASS));
            current_Account_NAME =
                    mCursor.getString(mCursor.getColumnIndex(Login_Contract.FeedEntry.COLUMN_NAME_NAME));
            current_Account_DEPART =
                    mCursor.getString(mCursor.getColumnIndex(Login_Contract.FeedEntry.COLUMN_NAME_DEPART));
            current_Account_PHONE =
                    mCursor.getString(mCursor.getColumnIndex(Login_Contract.FeedEntry.COLUMN_NAME_PHONE));
            current_Account_EMAIL =
                    mCursor.getString(mCursor.getColumnIndex(Login_Contract.FeedEntry.COLUMN_NAME_EMAIL));
            current_Cursor = mCursor;

            Toast.makeText(this, current_Account_NAME + "님 환영합니다.", Toast.LENGTH_SHORT).show();

            Intent intent1 = new Intent(this, Service_SoundEffect.class);
            intent1.putExtra("sound", R.raw.water);
            startService(intent1);

            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            intent.putExtra("login", Login_State);
            startActivity(intent);
            finish();
        }
        else{
            editText1.setText(null);
            editText2.setText(null);
            Toast.makeText(this,"존재하지 않는 id 또는 password입니다.", Toast.LENGTH_SHORT ).show();
        }
    }

    //회원가입.
    public void onClickSignup(View view) {
        Intent intent = new Intent(this, Login_Register.class);
        startActivity(intent);
    }
}