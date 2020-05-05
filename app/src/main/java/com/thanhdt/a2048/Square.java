package com.thanhdt.a2048;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class Square extends TextView {
    public Square(Context context) {
        super(context);
    }

    public Square(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Square(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Square(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        setMeasuredDimension(width,width);
    }
    public void setValue(int number, int mode){
//setTextSize
        setTextSize(150/mode);
        if(number>=8){
            setTextColor(Color.WHITE);
        }else{
            setTextColor(Color.BLACK);
        }
        GradientDrawable drawable = (GradientDrawable) this.getBackground();
        drawable.setColor(GamePlay.getGamePlay().color(number));
        setBackgroundDrawable(drawable);

        if(number==0){
            setText("");
        }else {
            setText(number+"");
        }
    }
}
