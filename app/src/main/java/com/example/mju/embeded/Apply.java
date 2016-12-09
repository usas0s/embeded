package com.example.mju.embeded;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Apply extends AppCompatActivity {
    String img; // 좌상단 이미지
    String name; // 우상단 이미지
    int number; // 글 번호
    ContentResolver cr; // cr

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);

        cr = getContentResolver();

        // intent 로부터 detail 의 정보를 일부 넘겨받아 설정.
        Intent intent = getIntent();
        img = intent.getExtras().getString("img");
        name = intent.getExtras().getString("name");
        number = intent.getExtras().getInt("number");
        System.out.println("★★ img,name,number = " + img +" / " + name + " / " + number);

        ImageView imageView = (ImageView)findViewById(R.id.applyIMG);
        imageView.setImageResource(getResources().getIdentifier(img+"0","drawable",getPackageName()));

        TextView textView = (TextView)findViewById(R.id.applyTitle);
        textView.setText(name);

        this.setTitle(name);
    }

    // 모임 참가 양식 작성 완료 후 참가 신청 버튼 터치 시 실행.
    protected void onClickApply(View view)
    {
        String name = ((EditText) findViewById(R.id.user_name)).getText().toString();
        String phone = ((EditText) findViewById(R.id.user_phone)).getText().toString();
        String email = ((EditText) findViewById(R.id.user_email)).getText().toString();

        if(name.equals("") | phone.equals("") | email.equals("")) // 이름 or 전화번호 or email 중 하나라도 비어있을 경우
        {
            Toast.makeText(getApplicationContext(), "필수 입력사항을 작성해주세요!", Toast.LENGTH_SHORT).show();
            
        }
        else // 모두 입력되어 있을 경우
        {
            if(((CheckBox)findViewById(R.id.cb_agree)).isChecked()) // 약관에 동의했으면 신청 완료
            {
                String mSelectionClauses = Apply_Contract.FeedEntry.COLUMN_NAME_POST_NUMBER + " like '" + number + "'";
                String[] mProjection = new String[] {
                        Post_Contract.FeedEntry.COLUMN_NAME_POST_NUMBER,
                        Post_Contract.FeedEntry.COLUMN_NAME_LIMIT,
                        Post_Contract.FeedEntry.COLUMN_NAME_CURRENT
                };
                Cursor d = cr.query(myContentProvider.CONTENT_URI_Post, mProjection, mSelectionClauses, null, null);
                d.moveToNext();
                int lim = d.getInt(d.getColumnIndex(Post_Contract.FeedEntry.COLUMN_NAME_LIMIT));
                int cur = d.getInt(d.getColumnIndex(Post_Contract.FeedEntry.COLUMN_NAME_CURRENT));
                if(cur > 0) {
                    String[] selection = {
                            Apply_Contract.FeedEntry.COLUMN_NAME_POST_NUMBER
                    };
                    ContentValues v = new ContentValues();
                    v.put(Apply_Contract.FeedEntry.COLUMN_NAME_POST_NUMBER, number);
                    v.put(Apply_Contract.FeedEntry.COLUMN_NAME_USERNAME, name);
                    v.put(Apply_Contract.FeedEntry.COLUMN_NAME_CALLNUMBER, phone);
                    v.put(Apply_Contract.FeedEntry.COLUMN_NAME_EMAIL, email);
                    cr.insert(myContentProvider.CONTENT_URI_Apply, v);

                    Cursor c = cr.query(myContentProvider.CONTENT_URI_Apply, selection , mSelectionClauses, null, null);
                    cur = lim - c.getCount();
                    System.out.println("current = " + cur);
                    ContentValues w = new ContentValues();
                    w.put(Post_Contract.FeedEntry.COLUMN_NAME_CURRENT, cur);
                    int m = cr.update(myContentProvider.CONTENT_URI_Post, w, mSelectionClauses, null);
                    Toast.makeText(getApplicationContext(), "참가신청 완료!" , Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "자리가 가득 찼습니다.", Toast.LENGTH_SHORT).show();
                }

            }
            else // 약관 미동의
            {
                Toast.makeText(getApplicationContext(), "약관에 동의해주세요.", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
