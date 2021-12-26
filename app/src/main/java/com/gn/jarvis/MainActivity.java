package com.gn.jarvis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.*;

import android.graphics.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Bundle;
import android.content.Intent;
import android.animation.ObjectAnimator;
import android.widget.ListView;
import android.widget.Toast;



public class MainActivity extends  AppCompatActivity  {

    private Timer _timer = new Timer();







    private TimerTask time;
    private final Intent map = new Intent();
    private ObjectAnimator anim = new ObjectAnimator();
    private HashMap<String, Object> data = new HashMap<>();



    private ArrayList<HashMap<String, Object>> maps = new ArrayList<>();
    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);


        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);


        setContentView(R.layout.splash);
        initialize(_savedInstanceState);
        


        initializeLogic();


    }

    private void initialize(Bundle _savedInstanceState) {


        final MediaPlayer mp = MediaPlayer.create(this, R.raw.st);
        // webView.loadUrl("http://www.google.com");
        mp.start();

    }

    private void initializeLogic() {

        time = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        map.setClass(getApplicationContext(), MainActivity2.class);
                        startActivity(map);
                        finish();
                    }
                });
            }
        };
        _timer.schedule(time, (int)(5000));
    }

    @Override
    protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {

        super.onActivityResult(_requestCode, _resultCode, _data);

        switch (_requestCode) {

            default:
                break;
        }
    }

    @Deprecated
    public void showMessage(String _s) {
        Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
    }

    @Deprecated
    public int getLocationX(View _v) {
        int _location[] = new int[2];
        _v.getLocationInWindow(_location);
        return _location[0];
    }

    @Deprecated
    public int getLocationY(View _v) {
        int _location[] = new int[2];
        _v.getLocationInWindow(_location);
        return _location[1];
    }

    @Deprecated
    public int getRandom(int _min, int _max) {
        Random random = new Random();
        return random.nextInt(_max - _min + 1) + _min;
    }

    @Deprecated
    public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
        ArrayList<Double> _result = new ArrayList<Double>();
        SparseBooleanArray _arr = _list.getCheckedItemPositions();
        for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
            if (_arr.valueAt(_iIdx))
                _result.add((double)_arr.keyAt(_iIdx));
        }
        return _result;
    }

    @Deprecated
    public float getDip(int _input){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
    }

    @Deprecated
    public int getDisplayWidthPixels(){
        return getResources().getDisplayMetrics().widthPixels;
    }

    @Deprecated
    public int getDisplayHeightPixels(){
        return getResources().getDisplayMetrics().heightPixels;
    }

}


