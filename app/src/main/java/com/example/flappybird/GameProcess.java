package com.example.flappybird;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameProcess extends Thread{
    private boolean isRunning = false;
    private final Game game;
    private final SurfaceHolder surfaceHolder;
    public static final double MAX_UPS = 60.0;
    private static final double UPS_PERIOD = 1E+3/MAX_UPS;
    private double averageUPS;
    private double averageFPS;

    public GameProcess(SurfaceHolder surfaceHolder, Game game) {
        this.surfaceHolder = surfaceHolder;
        this.game = game;
    }

    public void startLoop() {
        isRunning = true;
        start();
    }

    @Override
    public void run() {
        super.run();

        //declare time and cycle count variables
        int updateCount = 0;
        int frameCount = 0;

        long startTime;
        long elapsedTime;
        long sleepTime;

        //game loop
        Canvas canvas = null;
        startTime = System.currentTimeMillis();
        while (isRunning){

            //try to update and render game
            try{
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    try {
                        game.update();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    updateCount++;
                    game.draw(canvas);
                }
            }catch (IllegalArgumentException e){
                e.printStackTrace();
            }finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        frameCount++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            //pause the game Loop to not exceed target UPS
            elapsedTime = System.currentTimeMillis() - startTime;
            sleepTime = (long) (updateCount*UPS_PERIOD - elapsedTime);
            if(sleepTime > 0){
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //skip frames to keep up with UPS
            while(sleepTime < 0 && updateCount < MAX_UPS-1){
                try {
                    game.update();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                updateCount++;
                elapsedTime = System.currentTimeMillis() - startTime;
                sleepTime = (long) (updateCount*UPS_PERIOD - elapsedTime);
            }
//calculate avg UPS and FPS
            elapsedTime = System.currentTimeMillis() - startTime;
            if(elapsedTime >= 1000){
                averageUPS = updateCount / (1E-3 * elapsedTime);
                averageFPS = frameCount / (1E-3 * elapsedTime);
                updateCount = 0;
                frameCount = 0;
                startTime = System.currentTimeMillis();
            }
        }
    }
}
