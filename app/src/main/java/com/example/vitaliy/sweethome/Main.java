package com.example.vitaliy.sweethome;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;



import cz.msebera.android.httpclient.Header;

public class Main extends AppCompatActivity implements View.OnClickListener {

    private EditText username;
    private EditText password;
    private TextView log;

    public String api_key;


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

                    if (api_key.equals("false_login")) {
                        Toast.makeText(getApplicationContext(), "Неправильный логин", Toast.LENGTH_SHORT).show();
                    }

                    if (api_key.equals("false_pass")){
                        Toast.makeText(getApplicationContext(), "Неправильный пароль", Toast.LENGTH_SHORT).show();
                    }

                    if(!api_key.equals("false_login") && !api_key.equals("false_pass")){
                        Intent intent = new Intent(Main.this, JSON_test.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Вход выполнен", Toast.LENGTH_SHORT).show();
                    }

                    v.setEnabled(true);
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), "Not connection into server", Toast.LENGTH_SHORT).show();
            }

        });

    }

public void OnCLickbb(View v){
    Intent intent = new Intent(Main.this, JSON_test.class);
    startActivity(intent);finish();
}





}




