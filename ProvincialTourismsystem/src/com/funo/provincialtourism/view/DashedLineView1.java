package com.funo.provincialtourism.view;

import com.funo.antennatestsystem.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

public class DashedLineView1 extends View {    
      
    public DashedLineView1(Context context, AttributeSet attrs) {    
        super(context, attrs);              
            
    }    
    
    @SuppressLint("ResourceAsColor")
	@Override    
    protected void onDraw(Canvas canvas) {    
        // TODO Auto-generated method stub    
        super.onDraw(canvas);            
        Paint paint = new Paint();    
        paint.setStyle(Paint.Style.STROKE);  
        paint.setColor(R.color.notify_timebg);    
        Path path = new Path();         
        path.moveTo(2, 0);    
        path.lineTo(2,720);          
        PathEffect effects = new DashPathEffect(new float[]{3,3,3,3},1);    
        paint.setPathEffect(effects);    
        canvas.drawPath(path, paint);    
    } 
}