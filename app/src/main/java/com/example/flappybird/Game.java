package com.example.flappybird;

import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import androidx.annotation.NonNull;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private GameProcess gameProcess;
    private Bird bird;
    private Pipes lower_pipe;
    private Pipes upper_pipe;

    public Game(Context context) {
        super(context);
        //get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        //getting the display height & width
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int screenHeight = displayMetrics.heightPixels;
        int screenWidth = displayMetrics.widthPixels;

        gameProcess = new GameProcess( surfaceHolder, this);
        setFocusable(true);

        bird = new Bird(getContext(), screenWidth/12, screenHeight/2, R.drawable.bluebird_2);
        upper_pipe = new Pipes(getContext(), 1500, 0, R.drawable.pipe_upper);
        lower_pipe = new Pipes(getContext(), 1500, 0+2200, R.drawable.pipe_lower);


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
        lower_pipe.update();
        upper_pipe.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        bird.draw(canvas);
        lower_pipe.draw(canvas);
        upper_pipe.draw(canvas);
    }


}
