package com.gn.jarvis;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.suke.widget.SwitchButton;

import java.util.HashMap;

import me.ibrahimsn.lib.Speedometer;
import com.ramijemli.percentagechartview.PercentageChartView;




public class MainActivity2 extends AppCompatActivity {


    private static final String LOG_TAG = "not connected";
    private String ip_address = "";
    private HashMap<String, Object> Jarvi = new HashMap<>();

    private ImageView front;
    private ImageView back;
    private ImageView Left;
    private ImageView Right;
    private ImageView hn;
    private TextView ipadd;
    private String wifi ="Connecting";
    private String camip ="Connecting";
    private WebView webView;
    private WebView jarvis;
    private WebView cam;
    private SeekBar speed;
    private String done;
    private PercentageChartView battey ;
    LottieAnimationView b;
    LottieAnimationView f;
    LottieAnimationView r;
    LottieAnimationView l;
    private ImageView wheel;
    private double mCurrAngle = 0;
    private double mPrevAngle = 0;
    private TextView textView;
    private String value;
    private TextView dat;



    private double S;
    private Speedometer speedometer;

    // creating a variable for
    // our Firebase Database.
    FirebaseDatabase firebaseDatabase;
    FirebaseDatabase firebaseDatabase1;

    // creating a variable for our
    // Database Reference for Firebase.
    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;







    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

        setContentView(R.layout.main);
        initialize(_savedInstanceState);

        initializeLogic();

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.jarvis_connected);
        // webView.loadUrl("http://www.google.com");
        mp.start();
        cam.setVisibility(View.GONE);
        speedometer.setVisibility(View.VISIBLE);
      //  wheel.setVisibility(View.INVISIBLE);


        // below line is used to get the instance
        // of our Firebase database.
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase1 = FirebaseDatabase.getInstance();

        // below line is used to get
        // reference for our database.
        databaseReference = firebaseDatabase.getReference("Jarvis/jarvis/ip");
        databaseReference1 = firebaseDatabase.getReference("Jarvis/jarvis/camip");

        // initializing our object class variable.


        // calling method
        // for getting data.
        getdata();



    }




//
//    private void animate(double fromDegrees, double toDegrees, long durationMillis) {
//        final RotateAnimation rotate = new RotateAnimation((float) fromDegrees, (float) toDegrees, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
//        rotate.setDuration(durationMillis);
//        rotate.setFillEnabled(true);
//        rotate.setFillAfter(true);
//
//        if(Right.getVisibility()==View.VISIBLE){
//            wheel.setVisibility(View.INVISIBLE);
//
//        }
//
//        if (mCurrAngle <= 90 && mCurrAngle > -90) // 180 degree
//        {
//            wheel.startAnimation(rotate);
//         //   System.out.println(mCurrAngle);
//
//        }
//
//        //  if(mCurrAngle==0){
//        //       textView.setText("forword");
//        //   }
//
//
//    }






    private void initializeLogic() {




    }


    private void getdata() {


        // calling add value event listener method
        // for getting the values from database.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // this method is call to get the realtime
                // updates in the data.
                // this method is called when the data is
                // changed in our Firebase console.
                // below line is for getting the data from
                // snapshot of our database.
                wifi = snapshot.getValue(String.class);

                // after getting the value we are setting
                // our value to our text view in below line.
               ipadd.setText(wifi);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(getApplicationContext(),"Not Connected Check Network",Toast.LENGTH_SHORT).show();
                ipadd.setText("Check FIREBASE");
            }
        });
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // this method is call to get the realtime
                // updates in the data.
                // this method is called when the data is
                // changed in our Firebase console.
                // below line is for getting the data from
                // snapshot of our database.
                camip = snapshot.getValue(String.class);

                // after getting the value we are setting
                // our value to our text view in below line.

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(getApplicationContext(),"Not Connected Check Network",Toast.LENGTH_SHORT).show();
                ipadd.setText("Check FIREBASE");
            }
        });
    }
    private void initialize(Bundle _savedInstanceState) {

        front=(ImageView) findViewById(R.id.F);
        back=(ImageView) findViewById(R.id.B);
        Left=(ImageView) findViewById(R.id.left);
        Right=(ImageView) findViewById(R.id.right);
        hn = (ImageView) findViewById(R.id.hn);
        ipadd=(TextView) findViewById(R.id.ipadd);
        webView =(WebView) findViewById(R.id.web1);
        webView.setWebViewClient(new WebViewClient());
        jarvis =(WebView) findViewById(R.id.jarvis);
        jarvis.setWebViewClient(new WebViewClient());
        cam =(WebView) findViewById(R.id.cam);
        cam.setWebViewClient(new WebViewClient());
        //  webView.loadUrl("http://www.google.com");
        speed=(SeekBar) findViewById(R.id.speed);
        battey =(PercentageChartView) findViewById(R.id.battey);

        speedometer=(Speedometer) findViewById(R.id.speedometer);

        speedometer.setBorderSize(30);

        speedometer.setMetricText("Speed");
        b = (LottieAnimationView) findViewById(R.id.bc);
        f = (LottieAnimationView) findViewById(R.id.fr2);
        r = (LottieAnimationView) findViewById(R.id.rt);
        l = (LottieAnimationView) findViewById(R.id.lf);
        wheel = (ImageView) findViewById(R.id.wheel);
        dat=(TextView) findViewById(R.id.dad);


        speedometer.setSpeed(51,1000L,null);
        battey.setProgress(50,true);


        com.suke.widget.SwitchButton switchButton = (com.suke.widget.SwitchButton)
                findViewById(R.id.switch_button);
        com.suke.widget.SwitchButton switchButton2 = (com.suke.widget.SwitchButton)
                findViewById(R.id.switch_button2);

        com.suke.widget.SwitchButton Line = (com.suke.widget.SwitchButton)
                findViewById(R.id.line);
        com.suke.widget.SwitchButton camon = (com.suke.widget.SwitchButton)
                findViewById(R.id.camon);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Jarvis/jarvis/data");




//        camon.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
//                //TODO do your job
//
//                if(camon.isChecked())
//                    webView.loadUrl("http://".concat(camip.concat(":8081")));
//
//
//                else
//                    ;
////                    webView.loadUrl("http://".concat(camip.concat("/?State=S")));
//
//
//            }
//
//
//
//        });


        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                //TODO do your job

                if(switchButton.isChecked())
                {// webView.loadUrl("http://".concat(wifi.concat("/?State=obs")));
                    cam.loadUrl("http://".concat(camip.concat(":8081")));
                    cam.setVisibility(View.VISIBLE);


                    }


                else

                {cam.setVisibility(View.GONE);



                    myRef.setValue("disconnect");}


            }



        });

        switchButton2.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                //TODO do your job

                if(switchButton2.isChecked())
                    webView.loadUrl("http://".concat(wifi.concat("/?State=line")));


                else
                    webView.loadUrl("http://".concat(wifi.concat("/?State=S")));


            }



        });



        Line.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                //TODO do your job


                if(Line.isChecked()){

                    webView.loadUrl("http://".concat(wifi.concat("/?State=line")));


                }

                else {
                    webView.loadUrl("http://".concat(wifi.concat("/?State=S")));


                }

            }




        });












        final Vibrator vibe = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
//replace yourActivity.this with your own activity or if you declared a context you can write context.getSystemService(Context.VIBRATOR_SERVICE);




        Toast.makeText(getApplicationContext(),"Welcome To Jarvis",Toast.LENGTH_SHORT).show();


        front.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                       // Toast.makeText(getApplicationContext(),"Forward",Toast.LENGTH_SHORT).show();

                        vibe.vibrate(30);



                        webView.loadUrl("http://".concat(wifi.concat("/?State=F")));


                        done="front";
                        f.playAnimation();



                        return true; // if you want to handle the touch event
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        webView.loadUrl("http://".concat(wifi.concat("/?State=S")));

                        done="stop";

                       // Toast.makeText(getApplicationContext(),"Stop",Toast.LENGTH_SHORT).show();
                        return true; // if you want to handle the touch event
                }
                return false;
            }
        });





        back.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        //Toast.makeText(getApplicationContext(),"Backward",Toast.LENGTH_SHORT).show();

                        vibe.vibrate(30);

                        webView.loadUrl("http://".concat(wifi.concat("/?State=B")));

                        done="back";
                        b.playAnimation();


                        return true; // if you want to handle the touch event
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        webView.loadUrl("http://".concat(wifi.concat("/?State=S")));

                        done="stop";

                       // Toast.makeText(getApplicationContext(),"Stop",Toast.LENGTH_SHORT).show();
                        return true; // if you want to handle the touch event
                }
                return false;
            }
        });


        Right.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        // Toast.makeText(getApplicationContext(),"Forward",Toast.LENGTH_SHORT).show();

                        vibe.vibrate(30);

                        r.playAnimation();
                        webView.loadUrl("http://".concat(wifi.concat("/?State=R")));







                        return true; // if you want to handle the touch event
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        if(done=="front"){
                            front.performLongClick();
                            webView.loadUrl("http://".concat(wifi.concat("/?State=F")));

                        }
                        else if(done=="back"){
                            back.performLongClick();
                            webView.loadUrl("http://".concat(wifi.concat("/?State=B")));}
                        else {
                            // RELEASED
                            webView.loadUrl("http://".concat(wifi.concat("/?State=S")));

                            //    Toast.makeText(getApplicationContext(), "Stop", Toast.LENGTH_SHORT).show();


                            return true; // if you want to handle the touch event
                        }

                        // Toast.makeText(getApplicationContext(),"Stop",Toast.LENGTH_SHORT).show();
                        return true ;// if you want to handle the touch event
                }
                return false;
            }
        });


        Left.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        // Toast.makeText(getApplicationContext(),"Forward",Toast.LENGTH_SHORT).show();

                        vibe.vibrate(30);
                        l.playAnimation();


                        webView.loadUrl("http://".concat(wifi.concat("/?State=L")));







                        return true; // if you want to handle the touch event
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        if(done=="front"){
                            front.performLongClick();
                            webView.loadUrl("http://".concat(wifi.concat("/?State=F")));

                        }
                        else if(done=="back"){
                            back.performLongClick();
                            webView.loadUrl("http://".concat(wifi.concat("/?State=B")));}
                        else {
                            // RELEASED
                            webView.loadUrl("http://".concat(wifi.concat("/?State=S")));

                            //    Toast.makeText(getApplicationContext(), "Stop", Toast.LENGTH_SHORT).show();


                            return true; // if you want to handle the touch event
                        }

                        // Toast.makeText(getApplicationContext(),"Stop",Toast.LENGTH_SHORT).show();
                        return true; // if you want to handle the touch event
                }
                return false;
            }
        });



        hn.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        // Toast.makeText(getApplicationContext(),"Forward",Toast.LENGTH_SHORT).show();

                        vibe.vibrate(60);
                        jarvis.loadUrl("http://".concat(camip.concat(":3000/moveBackward")));


                        myRef.setValue("hornon");



                        webView.loadUrl("http://".concat(wifi.concat("/?State=HN")));





                        return true; // if you want to handle the touch event
                    case MotionEvent.ACTION_UP:
                        // RELEASED

                        webView.loadUrl("http://".concat(wifi.concat("/?State=S")));
                        if(done=="front"){
                            front.performLongClick();
                            webView.loadUrl("http://".concat(wifi.concat("/?State=F")));

                        }
                        else if(done=="back"){
                            back.performLongClick();
                            webView.loadUrl("http://".concat(wifi.concat("/?State=B")));}
                        else {
                            // RELEASED
                            webView.loadUrl("http://".concat(wifi.concat("/?State=S")));

                            //    Toast.makeText(getApplicationContext(), "Stop", Toast.LENGTH_SHORT).show();


                            return true; // if you want to handle the touch event
                        }





                        // Toast.makeText(getApplicationContext(),"Stop",Toast.LENGTH_SHORT).show();
                        return true; // if you want to handle the touch event
                }
                return false;
            }
        });


        battey.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        // Toast.makeText(getApplicationContext(),"Forward",Toast.LENGTH_SHORT).show();

                        vibe.vibrate(60);
                        jarvis.loadUrl("http://".concat(camip.concat(":3000/moveForward")));


                        myRef.setValue("ala");



                        webView.loadUrl("http://".concat(wifi.concat("/?State=HN")));





                        return true; // if you want to handle the touch event
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        if(done=="front"){
                            front.performLongClick();
                            webView.loadUrl("http://".concat(wifi.concat("/?State=F")));

                        }
                        else if(done=="back"){
                            back.performLongClick();
                            webView.loadUrl("http://".concat(wifi.concat("/?State=B")));}
                        else {
                            // RELEASED
                            webView.loadUrl("http://".concat(wifi.concat("/?State=S")));

                            //    Toast.makeText(getApplicationContext(), "Stop", Toast.LENGTH_SHORT).show();


                            return true; // if you want to handle the touch event
                        }



                        // Toast.makeText(getApplicationContext(),"Stop",Toast.LENGTH_SHORT).show();
                        return true; // if you want to handle the touch event
                }
                return false;
            }
        });








       /* wheel.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch ( final View v, MotionEvent event){
                final float xc = wheel.getWidth() / 2;
                final float yc = wheel.getHeight() / 2;

                final float x = event.getX();
                final float y = event.getY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        wheel.clearAnimation();
                        mCurrAngle = Math.toDegrees(Math.atan2(x - xc, yc - y));

                        break;
                    }
                    case MotionEvent.ACTION_MOVE: {
                        mPrevAngle = mCurrAngle;
                        mCurrAngle = Math.toDegrees(Math.atan2(x - xc, yc - y));
                        animate(mPrevAngle, mCurrAngle, 0);
                     //   System.out.println(mCurrAngle);

                       S=mCurrAngle;




                         if (S >= 0 && S <= 45){

                             webView.loadUrl("http://".concat(wifi.concat("/?State=I")));
                         }
                        if (S >= 46 && S <= 90){

                            webView.loadUrl("http://".concat(wifi.concat("/?State=R")));
                        }
                        if (S <= 0 && S >= -45){

                            webView.loadUrl("http://".concat(wifi.concat("/?State=G")));
                        }
                        if (S <= -46 && S >= -90){

                            webView.loadUrl("http://".concat(wifi.concat("/?State=L")));
                        }









                            break;
                    }
                    case MotionEvent.ACTION_UP: {
                        mPrevAngle = mCurrAngle = 0;
                        animate(mPrevAngle, mCurrAngle, 1000);

                        if(Right.getVisibility()==View.VISIBLE){
                            wheel.setVisibility(View.INVISIBLE);

                        }


                        if(done=="front"){
                            front.performLongClick();
                            webView.loadUrl("http://".concat(wifi.concat("/?State=F")));

                        }
                        else if(done=="back"){
                            back.performLongClick();
                            webView.loadUrl("http://".concat(wifi.concat("/?State=B")));}
                        else {
                            // RELEASED
                            webView.loadUrl("http://".concat(wifi.concat("/?State=S")));

                            //    Toast.makeText(getApplicationContext(), "Stop", Toast.LENGTH_SHORT).show();


                            return true; // if you want to handle the touch event
                        }
                        return true; // if you want to handle the touch event

                    }

                }
                return true;
            }
        });**/






        speed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 1;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
             //   Toast.makeText(getApplicationContext(), "Car speed is :" + progressChangedValue,
                    //    Toast.LENGTH_SHORT).show();

                    S=progressChangedValue;
                if(progressChangedValue == 0){
                    speedometer.setSpeed(6,1000L,null);



                    battey.setProgress(1,true);

                    webView.loadUrl("http://".concat(wifi.concat("/?State=0")));

                }
               else if(progressChangedValue == 1){
                    speedometer.setSpeed(11,1000L,null);

                    battey.setProgress(10,true);

                    webView.loadUrl("http://".concat(wifi.concat("/?State=1")));

                }
                else if(progressChangedValue == 2){
                    speedometer.setSpeed(21,1000L,null);

                    battey.setProgress(20,true);

                    webView.loadUrl("http://".concat(wifi.concat("/?State=2")));

                }
                else if(progressChangedValue == 3){
                    speedometer.setSpeed(31,1000L,null);

                    battey.setProgress(30,true);

                    webView.loadUrl("http://".concat(wifi.concat("/?State=3")));

                }
                else if(progressChangedValue == 4){
                    speedometer.setSpeed(41,1000L,null);

                    battey.setProgress(40,true);

                    webView.loadUrl("http://".concat(wifi.concat("/?State=4")));

                }
                else if(progressChangedValue == 5){
                    speedometer.setSpeed(51,1000L,null);

                    battey.setProgress(50,true);

                    webView.loadUrl("http://".concat(wifi.concat("/?State=5")));

                }
                else if(progressChangedValue == 6){
                    speedometer.setSpeed(61,1000L,null);


                    battey.setProgress(60,true);

                    webView.loadUrl("http://".concat(wifi.concat("/?State=6")));

                }
                else if(progressChangedValue == 7){
                    speedometer.setSpeed(71,1000L,null);

                    battey.setProgress(70,true);

                    webView.loadUrl("http://".concat(wifi.concat("/?State=7")));

                }
                else if(progressChangedValue == 8){
                    speedometer.setSpeed(81,1000L,null);

                    battey.setProgress(80,true);

                    webView.loadUrl("http://".concat(wifi.concat("/?State=8")));

                }
                else if(progressChangedValue == 9){
                    speedometer.setSpeed(91,1000L,null);

                    battey.setProgress(90,true);

                    webView.loadUrl("http://".concat(wifi.concat("/?State=9")));

                }

                else if(progressChangedValue == 10){
                    speedometer.setSpeed(101,1000L,null);

                    battey.setProgress(100,true);

                    webView.loadUrl("http://".concat(wifi.concat("/?State=10")));

                }




            }
        });












    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }




}