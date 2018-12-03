package com.example.vitaliy.sweethome;


import android.content.Intent;

import android.os.Bundle;
import android.os.CountDownTimer;
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
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final TextView time = findViewById(R.id.time);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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



        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
                String date = df.format(Calendar.getInstance().getTime());
                time.setText(date);
                Intent intent = getIntent();
                String temp1 = intent.getStringExtra("temp1");

                TextView tempbed = (TextView) findViewById(R.id.tempbed);
                tempbed.setText("Tempreature in bedroom: " + temp1);
            }

            @Override
            public void onFinish() {
                start();
            }

        }.start();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }
    private static final long DOUBLE_PRESS_INTERVAL = 250; // in millis
    private long lastPressTime;
    private boolean mHasDoubleClicked = false;
    @Override
    public void onBackPressed() {
        long pressTime = System.currentTimeMillis();
        if (pressTime - lastPressTime <= DOUBLE_PRESS_INTERVAL) {
            finishAffinity();

            mHasDoubleClicked = true;

        } else {
            Toast.makeText(getApplicationContext(), "press twice to exit", Toast.LENGTH_SHORT).show();
        }
        lastPressTime = pressTime;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
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
            Intent intent = new Intent(Home.this, Kitchen.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.slidein, R.anim.slideout);
        } else if (id == R.id.bathroom) {
            Intent intent = new Intent(Home.this, Bathroom.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slidein, R.anim.slideout);finish();
        } else if (id == R.id.bedroom) {
            Intent intent = new Intent(Home.this, Bedroom.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slidein, R.anim.slideout);finish();
        } else if (id == R.id.livroom) {
            Intent intent = new Intent(Home.this, LivingRoom.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slidein, R.anim.slideout);finish();
        } else if (id == R.id.home) {

        } else if (id == R.id.exit){
            Intent intent = new Intent(Home.this, Main.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slidein, R.anim.slideout);finish();
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
            private SwipeDetector detector = new SwipeDetector();
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                   float velocityY) {
                try {
                    if (detector.isSwipeLeft(e1, e2, velocityX)) {
                        Intent intent = new Intent(Home.this, Kitchen.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slideinleft, R.anim.slideoutleft);
                    } else if(detector.isSwipeRight(e1, e2, velocityX)){
                        Intent intent = new Intent(Home.this, LivingRoom.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slideinright, R.anim.slideoutright);
                    }
                } catch (Exception e) {} //for now, ignore
                return false;
            }
        });
    }
    private GestureDetector gestureDetector;



}


