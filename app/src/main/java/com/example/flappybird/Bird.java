package com.example.flappybird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Bird extends Thread {
    private final int WIDTH = 170;
    private final int HEIGHT = 120;
    private Paint paint;
    private Context context;
    private Bitmap bitmap;
    private double cordsX;
    private double cordsY;
    private boolean isRunning = true;
    private final double GRAVITY_ACCELARATION = 0.05;
    private final double JUMP_ACCELARATION = 10;
    private double gravitySpeed = 0.00;
    private boolean isPressed;

    //TODO: make hitboxe for bird

    public Bird(Context context, double cordsX, double cordsY, int drawable) {
        this.bitmap = BitmapFactory.decodeResource(context.getResources(), drawable);
        bitmap = Bitmap.createScaledBitmap(bitmap, WIDTH, HEIGHT, false);
        this.cordsX = cordsX;
        this.cordsY = cordsY;
        this.context = context;
        paint = new Paint();
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, (float) cordsX, (float) cordsY, paint);
    }

    public void update() {

    }

    public void run() {
        while (isRunning) {
            gravitySpeed += GRAVITY_ACCELARATION;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cordsY += gravitySpeed;


            if (isPressed) {
                setSprite(context, R.drawable.bluebird_downflap);
                for (int i = 0; i < 25; i++) {
                    gravitySpeed = 0;
                    gravitySpeed += JUMP_ACCELARATION;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    cordsY -= gravitySpeed;

                }

                setSprite(context, R.drawable.bluebird_midair);
                for (double i = gravitySpeed; i < 0; i--) {
                    gravitySpeed -= 0.1;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    cordsY += gravitySpeed;
                }

                setSprite(context, R.drawable.bluebird_upflap);
                gravitySpeed = 2.5;
                isPressed = false;
            }

        }
    }

    public void setPressed(boolean pressed) {
        isPressed = pressed;
    }

    public void setSprite(Context context, int drawable) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), drawable);
        bitmap = Bitmap.createScaledBitmap(bitmap, WIDTH, HEIGHT, false);
    }
}
