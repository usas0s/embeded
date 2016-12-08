package com.example.mju.embeded;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegisterPost extends AppCompatActivity {

    ContentResolver cr;
    EditText input01;
    TextView txtMsg;
    int select = 0;
    String lats[];
    String lngs[];
    public static String defaultUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    Handler handler = new Handler();
    float Lat, Lng;

    private String request(String urlStr) {
        StringBuilder output = new StringBuilder();
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            if (conn != null) {
                conn.setConnectTimeout(10000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Accept-Charset","UTF-8");

                int resCode = conn.getResponseCode();

                Log.d("resCode", String.valueOf(resCode));
                if (resCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));

                    String line = null;
                    while(true) {
                        line = reader.readLine();
                        if (line == null) {
                            break;
                        }
                        output.append(line + "\n");
                    }

                    reader.close();
                    conn.disconnect();
                }
            }
        } catch(Exception ex) {
            Log.e("SampleHTTP", "Exception in processing response.", ex);
            ex.printStackTrace();
        }

        return output.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_post);
        cr = getContentResolver();

        //
        input01 = (EditText) findViewById(R.id.register_post_place);

        txtMsg = (TextView) findViewById(R.id.txtMsg);

        // 버튼 이벤트 처리
        Button requestBtn = (Button) findViewById(R.id.requestBtn);
        requestBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("★★request 됨 ");
                String userStr = input01.getText().toString();
                String urlStr = defaultUrl + userStr + "&key=AIzaSyCSp6s60ipmjadyhEF5gX5R1xr7_FgBEOc&language=ko";

                ConnectThread thread = new ConnectThread(urlStr);
                thread.start();
            }
        });

    }

    public void onRegisterPost(View view)
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
                v.put(Post_Contract.FeedEntry.COLUMN_NAME_CURRENT, 0);
                cr.insert(myContentProvider.CONTENT_URI_Post, v);
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "capcha 에 체크해주세요.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void findLatLng(String output) {
        Log.d("output", output);
        try {
            // 결과 값을 JsonObject로
            JSONObject jsonObject = new JSONObject(output);
            String status = jsonObject.getString("status");
            String condition = status.trim();

            if (condition.equals("OK")) {
                JSONArray jsonResultsArray = new JSONArray(jsonObject.getString("results"));
                int jsonResultsLength = jsonResultsArray.length();

                if (jsonResultsLength > 5) {
                    Toast.makeText(this, "검색된 결과 값이 너무 많습니다.", Toast.LENGTH_LONG).show();
                } else if (jsonResultsLength > 1) {
                    String addresses[] = new String[jsonResultsLength];
                    lats = new String[jsonResultsLength];
                    lngs = new String[jsonResultsLength];

                    for (int i = 0; i < jsonResultsLength; i++) {

                        String address = jsonResultsArray.getJSONObject(i).getString("formatted_address");

                        JSONObject geoObject = new JSONObject(jsonResultsArray.getJSONObject(i).getString("geometry"));
                        JSONObject locObject = new JSONObject(geoObject.getString("location"));
                        String lat = locObject.getString("lat");
                        String lng = locObject.getString("lng");

                        addresses[i] = address;
                        lats[i] = lat;
                        lngs[i] = lng;
                    }


                    AlertDialog.Builder ab = new AlertDialog.Builder(this);
                    ab.setTitle("아래에서 해당 주소를 선택하세요");
                    ab.setSingleChoiceItems(addresses, select, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            select = i;
                        }
                    }).setPositiveButton("선택", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            txtMsg.setText("lat : " + lats[select] + "\nlng : " + lngs[select]);
                            //Toast.makeText(MainActivity.this, "위도 : " + lats[select] + "경도 : " + lngs[select],Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton("취소", null);
                    ab.show();

                } else if (jsonResultsLength == 1) {
                    JSONObject geoObject = new JSONObject(jsonResultsArray.getJSONObject(0).getString("geometry"));
                    JSONObject locObject = new JSONObject(geoObject.getString("location"));
                    String lat = locObject.getString("lat");
                    String lng = locObject.getString("lng");

                    txtMsg.setText("lat : " + lat + "\nlng : " + lng);
                }

            } else {
                Toast.makeText(this, "해당 조회 결과 값이 없습니다.", Toast.LENGTH_LONG).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }class ConnectThread extends Thread {
        String urlStr;

        public ConnectThread(String inStr) {
            urlStr = inStr;
        }

        public void run() {

            try {
                final String output = request(urlStr);
                handler.post(new Runnable() {
                    public void run() {
                        // txtMsg.setText(output);
                        findLatLng(output);
                    }
                });

            } catch(Exception ex) {
                ex.printStackTrace();
            }

        }




    }
}
