package com.victor.mycompass;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by victor on 2/16/17.
 */

public class MyCompassView extends View {

    private float direction = 0;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private boolean firstDraw;

    public MyCompassView(Context context) {
        super(context);
        init();
    }

    public MyCompassView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCompassView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setColor(Color.BLUE);
        paint.setTextSize(30);
        firstDraw = true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);

        int cxCompass = getMeasuredWidth()/2;
        int cyCompass = getMeasuredHeight()/2;
        float radiusCompass;

        if(cxCompass > cyCompass){
            radiusCompass = (float) (cyCompass * 0.9);
        }
        else{
            radiusCompass = (float) (cxCompass * 0.9);
        }
        canvas.drawCircle(cxCompass, cyCompass, radiusCompass, paint);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);

        if(!firstDraw){

            canvas.drawLine(cxCompass, cyCompass,
                    (float)(cxCompass + radiusCompass * Math.sin((double)(-direction) * 3.14/180)),
                    (float)(cyCompass - radiusCompass * Math.cos((double)(-direction) * 3.14/180)),
                    paint);

            canvas.drawText(String.valueOf(direction), cxCompass, cyCompass, paint);
        }

    }

    public void updateDirection(float dir)
    {
        firstDraw = false;
        direction = dir;
        invalidate();
    }
}
