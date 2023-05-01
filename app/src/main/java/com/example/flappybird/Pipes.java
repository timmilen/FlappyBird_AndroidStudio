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
    private double cordsX;
    private double cordsY;
    private boolean isRunning = true;

    //TODO: make hitboxes for pipes

    public Pipes(Context context, double cordsX, double cordsY, int drawable) {
        this.bitmap = BitmapFactory.decodeResource(context.getResources(), drawable);
        bitmap = Bitmap.createScaledBitmap(bitmap, WIDTH, HEIGHT, false);
        this.cordsX = cordsX;
        this.cordsY = cordsY;
        paint = new Paint();
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, (float) cordsX, (float) cordsY, paint);
    }

    public void update() {

    }

    public void run() {
        while (isRunning) {
            cordsX -= 0.5;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (cordsX < -740) {
                cordsX = screenWidth;
                cordsY = -screenHeight/3 + random.nextInt(screenHeight/3);
            }

        }
    }

}