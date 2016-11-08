package com.example.mju.embeded;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.HashMap;


public class Login extends AppCompatActivity {
    private SQLiteDatabase mDB;
    public final static String ID ="hello";
    public final static String PASSWORD ="1234";
    private Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // create DB and table
        mDB = openOrCreateDatabase("myDB.db",MODE_PRIVATE,null);
        //mDB.execSQL("drop table if exists my_table");
        mDB.execSQL("create table my_table (_id integer primary key autoincrement, "
                + "id text not null, pass int not null);");
        // insert data into DB
        mDB.execSQL("insert into my_table(id, pass) values ('hello', 1234);");
        /*
        ContentValues v = new ContentValues();
        v.put("name", "seung");
        v.put("age", 20);
        mDB.insert("my_table", null, v);
        */
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

        /*
        if(id.equals(ID)){
            if (password.equals(PASSWORD)){
                login(id, password);
                return;
            }
        }
        */
        mCursor = mDB.query("my_table",
                new String[] {"id","pass"},
                null,   // selection
                null,   // selection args
                null,   // groupby
                null,  // having
                "_id", // oderBy
                "5");

        if(mCursor != null) {
            if (mCursor.moveToFirst()) {
                HashMap<String,String> item = new HashMap<String,String>();
                do {
                    if(mCursor.getString(0).equals(id)){
                        if(mCursor.getString(1).equals(password)){
                            login(id,password);
                            return;
                        }
                    }
                } while (mCursor.moveToNext());
            }
        }

        Toast toast = Toast.makeText(this, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onClickSignup(View view) {
        EditText id = (EditText)findViewById(R.id.login_ID);
        EditText pass = (EditText)findViewById(R.id.login_PASS);

        ContentValues v = new ContentValues();
        v.put("id", id.getText().toString());
        v.put("pass", pass.getText().toString());
        mDB.insert("my_table", null, v);

    }
}