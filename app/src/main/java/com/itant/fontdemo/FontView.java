package com.itant.fontdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class FontView extends View {
 
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Typeface mTypeface;
	private String text;
 
    public FontView(Context context, AttributeSet attrs){
        super(context, attrs);
        mTypeface = Typeface.createFromAsset(getContext().getAssets(), "king.ttf");
        mPaint.setTextSize(52);
        
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FontView);
		text = typedArray.getString(R.styleable.FontView_text);
		
		
		typedArray.recycle();
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    	//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    	
    	// 可抽取为方法
    	int height = 300;
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);

        switch (specMode) {
        case MeasureSpec.AT_MOST:
        	if (specSize < height) {
        		height = specSize;
        	}
        	break;
        case MeasureSpec.EXACTLY:
        	height = specSize;
            break;
        }
    	
    	setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec), height);
    }
    
    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawColor(Color.GRAY);
        mPaint.setTypeface(mTypeface);
        canvas.drawText(text, 10, 50, mPaint);
    }
}