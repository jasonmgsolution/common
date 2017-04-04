package com.jasonmg.salepoison.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * Created by sjy2746 on 2016-06-29.
 */
public class RefreshLayoutExIntercepter extends SwipeRefreshLayout {

    public RefreshLayoutExIntercepter(Context ctx) {
        super(ctx);
        mTouchSlop = ViewConfiguration.get(ctx).getScaledTouchSlop();
    }

    public RefreshLayoutExIntercepter(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
        mTouchSlop = ViewConfiguration.get(ctx).getScaledTouchSlop();
    }

    private int mTouchSlop;
    private float mPrevX;
    private float mPrevY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPrevX = event.getX();
                mPrevY = event.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                final float eventX = event.getX();
                final float eventY = event.getY();
                float xDiff = Math.abs(eventX - mPrevX);
                float YDiff = Math.abs(eventY - mPrevY);

                if (xDiff > mTouchSlop && YDiff < dp2px(250) ) {
                    return false;
                }
        }

        return super.onInterceptTouchEvent(event);
    }

    private float dp2px(float dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}