package com.example.mju.embeded;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


/*
        ~ Copyright (C) 컴퓨터공학과 60132291 오지훈
*/

public class Login extends AppCompatActivity {
    static Context Lcontext;
    private static final String URI = "content://com.example.mju.embeded/login_DB";
    private ContentResolver cr;
    private EditText editText1;
    private EditText editText2;
    public static boolean Login_State =false;
    private String current_Account_ID;
    private String current_Account_PASS;
    private String current_Account_NAME;
    private String current_Account_DEPART;
    private String current_Account_PHONE;
    private String current_Account_EMAIL;
    private Cursor current_Cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Lcontext = this.getApplicationContext();
}

    public void memberLeave(){
        cr = getContentResolver();
        cr.delete(Uri.parse(URI), "_id ="+current_Cursor.getInt(current_Cursor.getColumnIndex(Login_Contract.FeedEntry.COLUMN_NAME_ID)), null);
        logout();
    }

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

    public boolean getLoginState(){
        return Login_State;
    }

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
        cr =getContentResolver();
        mCursor = cr.query(Uri.parse(URI), null, "owner_id =\""+id+"\"", null, null);
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


            //TextView tv = (TextView)findViewById(R.id.nav_name);
            //tv.setText(current_Account_NAME);

            Toast.makeText(this, current_Account_NAME + "님 환영합니다.", Toast.LENGTH_SHORT).show();
        }
        else{
            editText1.setText(null);
            editText2.setText(null);
            Toast.makeText(this,"존재하지 않는 id 또는 password입니다.", Toast.LENGTH_SHORT ).show();
        }
    }

    public void onClickSignup(View view) {
        Intent intent = new Intent(this, Login_Register.class);
        startActivity(intent);
    }
}