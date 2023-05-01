package com.example.flappybird;

import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import java.util.Random;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private GameProcess gameProcess;
    private Bird bird;
    private Pipes pipe;
    private Pipes pipe1;
    private Random random = new Random();
    public static int screenHeight;
    public static int screenWidth;

    public Game(Context context) {
        super(context);
        //get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        //getting the display height & width
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;

        gameProcess = new GameProcess(surfaceHolder, this);
        setFocusable(true);

        bird = new Bird(getContext(), screenWidth/12, screenHeight/2, R.drawable.bluebird_midair);

        bird.start();


        pipe = new Pipes(getContext(), screenWidth, -screenHeight/3 + random.nextInt(screenHeight/3), R.drawable.pipe);
        pipe1 = new Pipes(getContext(), screenWidth+screenWidth/4*3,   -screenHeight/3 + random.nextInt(screenHeight/3), R.drawable.pipe);

        pipe.start();
        pipe1.start();


    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        gameProcess.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    public void update() throws InterruptedException {
        bird.update();

        pipe.update();
        pipe1.update();

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        bird.draw(canvas);

        pipe.draw(canvas);
        pipe1.draw(canvas);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                bird.setPressed(true);
                return true;

            case MotionEvent.ACTION_UP:
                bird.setPressed(false);
                return false;
        }

        return super.onTouchEvent(event);
    }
}
