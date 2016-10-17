package com.example.mju.embeded;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

//이 어플리케이션은 명지대학교 학생들이 모임을 쉽게 만들도록 도와주는 어플리케이션이다.

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickDetail(View view){
        Intent detail_intent = new Intent(this,Details.class);
        startActivity(detail_intent);
    }
}
