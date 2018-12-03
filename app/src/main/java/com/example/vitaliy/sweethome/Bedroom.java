package com.example.vitaliy.sweethome;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Arrays;

import cz.msebera.android.httpclient.Header;

public class Bedroom extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bedroom);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ToggleButton tgl = (ToggleButton) findViewById(R.id.tgl);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                AsyncHttpClient client = new AsyncHttpClient();
                final TextView txt = findViewById(R.id.tttt);
                client.get("http://smartdevgroup.hopto.org/service/temp_mob.php?api=123456789", new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        if (responseBody != null) {
                            try {
                                JSONObject json = new JSONObject(new String(responseBody));
                                Integer temp1 = json.optInt("bedroom_socket_1");
                                txt.setText("Temperature in room: " + temp1 + "\u2103");
                                Intent intent = new Intent(Bedroom.this, Home.class);
                                intent.putExtra("temp1", temp1);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                    }


                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });

            }

            @Override
            public void onFinish() {
                start();
            }

        }.start();


            gestureDetector = initGestureDetector();

        View view = findViewById(R.id.LinearLayout1);

        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
            }
        });


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        tgl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String on = "http://smartdevgroup.hopto.org/service/socket_mob.php?api=123456789&status=1";
                    AsyncHttpClient client = new AsyncHttpClient();
                    client.get(on, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            if (responseBody != null) {
                                Toast.makeText(getApplicationContext(), "Включен", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        }
                    });

                } else {
                    String off = "http://smartdevgroup.hopto.org/service/socket_mob.php?api=123456789&status=0";
                    AsyncHttpClient client = new AsyncHttpClient();
                    client.get(off, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            if (responseBody != null) {
                                Toast.makeText(getApplicationContext(), "Выключен", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        }
                    });
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Bedroom.this, Home.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bedroom, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.kitchen) {
            Intent intent = new Intent(Bedroom.this, Kitchen.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slidein, R.anim.slideout);
            finish();
        } else if (id == R.id.bathroom) {
            Intent intent = new Intent(Bedroom.this, Bathroom.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slidein, R.anim.slideout);
            finish();
        } else if (id == R.id.livroom) {
            Intent intent = new Intent(Bedroom.this, LivingRoom.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slidein, R.anim.slideout);
            finish();
        } else if (id == R.id.home) {
            Intent intent = new Intent(Bedroom.this, Home.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slidein, R.anim.slideout);
            finish();
        } else if (id == R.id.exit) {
            Intent intent = new Intent(Bedroom.this, Main.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slidein, R.anim.slideout);
            finish();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public class SwipeDetector {

        private int swipe_distance;
        private int swipe_velocity;
        private static final int SWIPE_MIN_DISTANCE = 120;
        private static final int SWIPE_THRESHOLD_VELOCITY = 200;

        public SwipeDetector(int distance, int velocity) {
            super();
            this.swipe_distance = distance;
            this.swipe_velocity = velocity;
        }

        public SwipeDetector() {
            super();
            this.swipe_distance = SWIPE_MIN_DISTANCE;
            this.swipe_velocity = SWIPE_THRESHOLD_VELOCITY;
        }

        public boolean isSwipeDown(MotionEvent e1, MotionEvent e2, float velocityY) {
            return isSwipe(e2.getY(), e1.getY(), velocityY);
        }

        public boolean isSwipeUp(MotionEvent e1, MotionEvent e2, float velocityY) {
            return isSwipe(e1.getY(), e2.getY(), velocityY);
        }

        public boolean isSwipeLeft(MotionEvent e1, MotionEvent e2, float velocityX) {
            return isSwipe(e1.getX(), e2.getX(), velocityX);
        }

        public boolean isSwipeRight(MotionEvent e1, MotionEvent e2, float velocityX) {
            return isSwipe(e2.getX(), e1.getX(), velocityX);
        }

        private boolean isSwipeDistance(float coordinateA, float coordinateB) {
            return (coordinateA - coordinateB) > this.swipe_distance;
        }

        private boolean isSwipeSpeed(float velocity) {
            return Math.abs(velocity) > this.swipe_velocity;
        }

        private boolean isSwipe(float coordinateA, float coordinateB, float velocity) {
            return isSwipeDistance(coordinateA, coordinateB)
                    && isSwipeSpeed(velocity);
        }
    }

    private GestureDetector initGestureDetector() {
        return new GestureDetector(new GestureDetector.SimpleOnGestureListener() {

            private Bedroom.SwipeDetector detector = new Bedroom.SwipeDetector();

            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                   float velocityY) {
                try {
                    if (detector.isSwipeLeft(e1, e2, velocityY)) {
                        Intent intent = new Intent(Bedroom.this, LivingRoom.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slideinleft, R.anim.slideoutleft);
                    } else if (detector.isSwipeRight(e1, e2, velocityX)) {
                        Intent intent = new Intent(Bedroom.this, Bathroom.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slideinright, R.anim.slideoutright);



                    }
                } catch (Exception e) {
                } //for now, ignore
                return false;
            }

        });
    }

    private GestureDetector gestureDetector;





}




