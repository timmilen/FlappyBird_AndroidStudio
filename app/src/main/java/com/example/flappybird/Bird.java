package com.example.flappybird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Bird {
    private final int width = 170;
    private final int height = 120;
    private Paint paint;
    private Bitmap bitmap;
    private double cordsX;
    private double cordsY;

    public Bird(Context context, double cordsX, double cordsY, int drawable) {
        this.bitmap = BitmapFactory.decodeResource(context.getResources(), drawable);
        bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
        this.cordsX = cordsX;
        this.cordsY = cordsY;
        paint = new Paint();
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, (float)cordsX, (float)cordsY, paint);
    }

    public void update() {

    }

}
