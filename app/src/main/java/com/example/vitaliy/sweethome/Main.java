package com.example.vitaliy.sweethome;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.Arrays;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;

public class Main extends AppCompatActivity implements View.OnClickListener {

    private EditText username;
    private EditText password;
    private TextView log;
    private Button btn;

    public String api_key;
    // Число для подсчета попыток залогиниться:
    int numberOfRemainingLoginAttempts = 3;
//    String urls = "http://smartdevgroup.hopto.org/service/mobile.php?login=";
//    String urlc = "http://192.168.0.98/service/mobile.php?login=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Связываемся с элементами нашего интерфейса:
        username = findViewById(R.id.edit_user);
        password = findViewById(R.id.edit_password);
        log = findViewById(R.id.log);

        Button btn = findViewById(R.id.button_login);
        assert btn != null;
        btn.setOnClickListener(this);
    }

    public void onBackPressed() {
        finish();
    }


    public void onClick(final View v) {
        v.setEnabled(false);
        AsyncHttpClient client = new AsyncHttpClient();
        String log = username.getText().toString();
        String pass = password.getText().toString();
        final TextView txt = findViewById(R.id.txt);
        client.get("http://smartdevgroup.hopto.org/service/mobile.php?login=" + log + "&pass=" + pass, new AsyncHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (responseBody != null) {


                    api_key = new String(responseBody);
                    txt.setText(api_key);

                    if (api_key.equals("false_login")) {
                        txt.setText("False login");
                    }

                    if (api_key.equals("false_pass")){
                        txt.setText("False password");
                    }

                    if (api_key.equals("false_login") && api_key.equals("false_pass")){
                        txt.setText("Failed data");
                    }

                    if(!api_key.equals("false_login") && !api_key.equals("false_pass")){
                        Intent intent = new Intent(Main.this, JSON_test.class);
                        startActivity(intent);
                    }

                    v.setEnabled(true);
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

        });


////        Intent intent = new Intent(this, Home.class);
////        intent.putExtra("api_key", api_key);

    }

public void OnCLickbb(View v){
    Intent intent = new Intent(Main.this, JSON_test.class);
    startActivity(intent);finish();
}





}




