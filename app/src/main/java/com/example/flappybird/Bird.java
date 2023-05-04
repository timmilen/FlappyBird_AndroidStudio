package com.example.flappybird;

import static com.example.flappybird.Game.screenHeight;
import static com.example.flappybird.Pipes.isRunningPipes;
import static com.example.flappybird.Pipes.pipeCordsX;
import static com.example.flappybird.Pipes.pipeCordsY;

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
    public static double birdCordsX;
    public static double birdCordsY;
    public static boolean isRunningBird = true;
    private final double GRAVITY_ACCELARATION = 0.05;
    private final double JUMP_ACCELARATION = 10;
    private double gravitySpeed = 0.00;
    private boolean isPressed;

    //TODO: make hitbox for bird

    public Bird(Context context, double cordsX, double cordsY, int drawable) {
        this.bitmap = BitmapFactory.decodeResource(context.getResources(), drawable);
        bitmap = Bitmap.createScaledBitmap(bitmap, WIDTH, HEIGHT, false);
        this.birdCordsX = cordsX;
        this.birdCordsY = cordsY;
        this.context = context;
        paint = new Paint();
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, (float) birdCordsX, (float) birdCordsY, paint);
    }

    public void update() {

    }

    public void run() {
        while (isRunningBird) {
            gravitySpeed += GRAVITY_ACCELARATION;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            birdCordsY += gravitySpeed;


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
                    birdCordsY -= gravitySpeed;

                }

                setSprite(context, R.drawable.bluebird_midair);
                for (double i = gravitySpeed; i < 0; i--) {
                    gravitySpeed -= 0.1;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    birdCordsY += gravitySpeed;
                }

                setSprite(context, R.drawable.bluebird_upflap);
                gravitySpeed = 2.5;
                isPressed = false;
            }

            if (Hitboxes.hitCheck(birdCordsX, birdCordsY, pipeCordsX, pipeCordsY)) {
                isRunningPipes = false;
                isRunningBird = false;
                setSprite(context, R.drawable.bluebird_death);
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
