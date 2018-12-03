package com.example.vitaliy.sweethome;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.BufferedHttpEntity;

public class JSON_test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_test);
        final TextView time = (TextView) findViewById(R.id.time);
        final TextView time1 = (TextView) findViewById(R.id.time1);
        Button kit = (Button) findViewById(R.id.kitchen);
        Button bath = (Button) findViewById(R.id.bathroom);
        Button bed = (Button) findViewById(R.id.bedroom);
        Button liv = (Button) findViewById(R.id.livroom);
        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy ");
                DateFormat ds = new SimpleDateFormat("HH:mm:ss");
                String ss = ds.format(Calendar.getInstance().getTime());
                String date = df.format(Calendar.getInstance().getTime());
                time1.setText(ss);
                time.setText(date);
            }

            @Override
            public void onFinish() {
                start();
            }

        }.start();

kit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(JSON_test.this, Kitchen.class);
        startActivity(intent);
    }
});
liv.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(JSON_test.this, LivingRoom.class);
        startActivity(intent);
    }
});

bath.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(JSON_test.this, Bathroom.class);
        startActivity(intent);
    }
});
bed.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(JSON_test.this, Bedroom.class);
        startActivity(intent);
    }
});

    }


}