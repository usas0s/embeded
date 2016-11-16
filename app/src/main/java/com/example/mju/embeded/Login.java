package com.example.mju.embeded;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends AppCompatActivity {
    private static final String URI = "content://com.example.mju.embeded/login_DB";
    private SQLiteDatabase mDB;
    public final static String ID ="hello";
    public final static String PASSWORD ="1234";
    private ContentResolver cr;
    private EditText editText1;
    private EditText editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
}
    //입력된 id, password가 db상에 존재하는지 탐색하는 메소드.
    //return :일치하는 db행의 Cursor
    public Cursor search(String id, String password){
        Cursor mCursor;
        cr =getContentResolver();
        mCursor = cr.query(Uri.parse(URI), null, "id =\""+id+"\"", null, null);
        if(mCursor.getCount()!=0) {
            while(mCursor.moveToNext()){
                if(mCursor.getString(mCursor.getColumnIndex("pass")).equals(password)){
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

        //TODO 로그인동작 추가하기
        if((mCursor =search(id, password))!=null)
            //후에 추가할 동작에서 다음과 같이 mCursor이용.
            Toast.makeText(this, mCursor.getString(mCursor.getColumnIndex("name"))+"님 환영합니다.", Toast.LENGTH_SHORT).show();
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