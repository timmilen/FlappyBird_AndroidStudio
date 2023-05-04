package com.example.flappybird;

import static com.example.flappybird.Game.screenHeight;
import static com.example.flappybird.Game.screenWidth;
import static java.lang.Thread.sleep;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.*;

public class Pipes extends Thread {
    private final int WIDTH = 740;
    private final int HEIGHT = 4000;
    private Paint paint;
    private Bitmap bitmap;
    private Random random = new Random();
    public static double pipeCordsX;
    public static double pipeCordsY;
    public static boolean isRunningPipes = true;

    //TODO: make hitboxes for pipes

    public Pipes(Context context, double cordsX, double cordsY, int drawable) {
        this.bitmap = BitmapFactory.decodeResource(context.getResources(), drawable);
        bitmap = Bitmap.createScaledBitmap(bitmap, WIDTH, HEIGHT, false);
        this.pipeCordsX = cordsX;
        this.pipeCordsY = cordsY;
        paint = new Paint();
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, (float) pipeCordsX, (float) pipeCordsY, paint);
    }

    public void update() {

    }

    public void run() {
        while (isRunningPipes) {
            pipeCordsX -= 0.5;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (pipeCordsX < -740) {
                pipeCordsX = screenWidth;
                pipeCordsY = -screenHeight/3 + random.nextInt(screenHeight/3);
                System.out.println(pipeCordsY);
            }

        }
    }


    public void setRunningPipes(boolean runningPipes) {
        isRunningPipes = runningPipes;
    }


}