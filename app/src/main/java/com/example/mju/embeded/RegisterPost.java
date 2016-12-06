package com.example.mju.embeded;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterPost extends AppCompatActivity {

    ContentResolver cr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_post);
        cr = getContentResolver();
    }

    private void onRegisterPost()
    {
        String name = ((EditText) findViewById(R.id.register_post_name)).getText().toString();
        String period = ((EditText) findViewById(R.id.register_post_period)).getText().toString();
        String place = ((EditText) findViewById(R.id.register_post_place)).getText().toString();
        String description = ((EditText) findViewById(R.id.register_post_description)).getText().toString();
        String limit = ((EditText) findViewById(R.id.register_post_limit)).getText().toString();

        if(name.equals("") | period.equals("") | place.equals("") | description.equals("") | limit.equals("")) // 이름 or 전화번호 or email 중 하나라도 비어있을 경우
        {
            Toast.makeText(getApplicationContext(), "필수 입력사항을 작성해주세요!", Toast.LENGTH_SHORT).show();

        }
        else // 모두 입력되어 있을 경우
        {
            if(((CheckBox)findViewById(R.id.cb_agree)).isChecked())
            {
//                Toast.makeText(getApplicationContext(), "INSERT " + name + " / " + period + " / " + place + " / " + description + " / " + limit + " INTO DB", Toast.LENGTH_LONG).show();
                // TODO : 입력된 Post 데이터를 DB로 넣을 것. 내용 변경에 따른 수정 요망
                ContentValues v = new ContentValues();
                v.put(Post_Contract.FeedEntry.COLUMN_NAME_POST_NAME, name);
                v.put(Post_Contract.FeedEntry.COLUMN_NAME_PERIOD, period);
                v.put(Post_Contract.FeedEntry.COLUMN_NAME_PLACE, place);
                v.put(Post_Contract.FeedEntry.COLUMN_NAME_DESCRIPTION, description);
                int lim = Integer.parseInt(limit);
                v.put(Post_Contract.FeedEntry.COLUMN_NAME_LIMIT, lim);
                cr.insert(myContentProvider.CONTENT_URI_Post, v);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "capcha 에 체크해주세요.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
