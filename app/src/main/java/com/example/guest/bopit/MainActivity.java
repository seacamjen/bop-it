package com.example.guest.bopit;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v4.view.GestureDetectorCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends Activity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    @Bind(R.id.doubleTap) TextView mDoubleTap;
    @Bind(R.id.fling) TextView mFling;
    @Bind(R.id.longclick) TextView mLongClick;
    private MediaPlayer mHold;
    private MediaPlayer mDoubleClick;
    private MediaPlayer mFlingMusic;



    GestureDetector gestureDetector;

    private static final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

         mHold = MediaPlayer.create(MainActivity.this, R.raw.hold);
         mDoubleClick = MediaPlayer.create(MainActivity.this, R.raw.doubleclick);
         mFlingMusic = MediaPlayer.create(MainActivity.this, R.raw.fling);

        mDetector = new GestureDetectorCompat(this,this);
        mDetector.setOnDoubleTapListener(this);

//        mDoubleTap.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                mDetector.onTouchEvent(event);
//                return false;
//            }
//        });
        hideDoubleTap();
        hideFling();
        showLongClick();
    }

    public void hideDoubleTap() {
        mDoubleTap.setVisibility(View.GONE);
    }

    public void showDoubleTap() {
        mDoubleTap.setVisibility(View.VISIBLE);
    }
    public void hideFling() {
        mFling.setVisibility(View.GONE);
    }

    public void showFling() {
        mFling.setVisibility(View.VISIBLE);
    }
    public void hideLongClick() {
        mLongClick.setVisibility(View.GONE);
    }

    public void showLongClick() {
        mLongClick.setVisibility(View.VISIBLE);
    }

    public void randomGen() {
        Random Generator = new Random();
        int num1;
        num1 = Generator.nextInt(3);
        if ( num1 == 0) {
            showLongClick();
            mHold.start();
            hideDoubleTap();
            hideFling();
        }
        if (num1 == 1) {
            showDoubleTap();
            mDoubleClick.start();
            hideFling();
            hideLongClick();
        }
        if (num1 == 2) {
            showFling();
            mFlingMusic.start();
            hideDoubleTap();
            hideLongClick();
        }
    }



    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent event) {
//        Log.d(DEBUG_TAG,"onDown: " + event.toString());
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        Log.d(DEBUG_TAG, "onFling: " + event1.toString()+event2.toString());
//        Toast.makeText(MainActivity.this, "fling Time", Toast.LENGTH_LONG).show();
        hideFling();
        randomGen();
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
        Log.d(DEBUG_TAG, "onLongPress: " + event.toString());
//        Toast.makeText(MainActivity.this, "Long Press", Toast.LENGTH_LONG).show();
        hideLongClick();
        randomGen();
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
//        Log.d(DEBUG_TAG, "onScroll: " + e1.toString()+e2.toString());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
//        Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
//        Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDoubleTap: " + event.toString());
//        Toast.makeText(MainActivity.this, "Double Tap", Toast.LENGTH_LONG).show();
        hideDoubleTap();
        randomGen();
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
//        Log.d(DEBUG_TAG, "onDoubleTapEvent: " + event.toString());
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
//        Log.d(DEBUG_TAG, "onSingleTapConfirmed: " + event.toString());
        return true;
    }
}
