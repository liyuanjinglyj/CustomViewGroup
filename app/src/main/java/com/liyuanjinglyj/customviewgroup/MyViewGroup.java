package com.liyuanjinglyj.customviewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class MyViewGroup extends ViewGroup {

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth=MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight=MeasureSpec.getSize(heightMeasureSpec);
        int measureWidhtMode=MeasureSpec.getMode(widthMeasureSpec);
        int measureHeightMode=MeasureSpec.getMode(heightMeasureSpec);

        //这里计算width,height
        int height=0;
        int width=0;
        int count=getChildCount();
        for(int i=0;i<count;i++){
            //测量子控件
            View child=getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            int childWidth=child.getMeasuredWidth();
            int childHeight=child.getMeasuredHeight();
            height+=childHeight;//高度叠加
            width=Math.max(width,childWidth);//宽度取最大
        }


        setMeasuredDimension((measureWidhtMode==MeasureSpec.EXACTLY)?measureWidth:width,(measureHeightMode==MeasureSpec.EXACTLY)?measureHeight:height);
    }

    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int top=0;
        int count=getChildCount();
        for(int i=0;i<count;i++){
            View child=getChildAt(i);
            int childWidth=child.getMeasuredWidth();
            int childHeight=child.getMeasuredHeight();
            child.layout(0,top,childWidth,top+childHeight);
            top+=childHeight;
        }
    }
}
