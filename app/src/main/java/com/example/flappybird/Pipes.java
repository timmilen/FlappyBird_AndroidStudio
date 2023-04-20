package com.example.flappybird;

import static java.lang.Thread.sleep;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

public class Pipes extends Thread {
    private final int width = 480;
    private final int height = 2088;
    private Paint paint;
    private Bitmap bitmap;
    private double cordsX;
    private double cordsY;
    private int count = 0;


    public Pipes(Context context, double cordsX, double cordsY, int drawable) {
        this.bitmap = BitmapFactory.decodeResource(context.getResources(), drawable);
        bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
        this.cordsX = cordsX;
        this.cordsY = cordsY;
        paint = new Paint();
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, (float) cordsX, (float) cordsY, paint);
    }

    public void update() throws InterruptedException {
            cordsX -= 5;
            count++;
            if(count == 400) {
                cordsY = Math.random()*600+300;
                cordsX = 1500;
                count = 0;
            }
    }
}