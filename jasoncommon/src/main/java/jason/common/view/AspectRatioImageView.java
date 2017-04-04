package com.jasonmg.salepoison.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 *  sjy2746
 *  imageView 폭에 맞게
 **/

public class AspectRatioImageView extends ImageView {

    public AspectRatioImageView(final Context context) {
        super(context);
    }

    public AspectRatioImageView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public AspectRatioImageView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        try {
            Drawable drawable = getDrawable();

            if (drawable == null) {
                setMeasuredDimension(0, 0);
            } else {
                float imageSideRatio = (float) drawable.getIntrinsicWidth() / (float) drawable.getIntrinsicHeight();
                float viewSideRatio = (float) MeasureSpec.getSize(widthMeasureSpec) / (float) MeasureSpec.getSize(heightMeasureSpec);
                if (imageSideRatio >= viewSideRatio) {
                    // Image is wider than the display (ratio)
                    int width = MeasureSpec.getSize(widthMeasureSpec);
                    int height = (int) (width / imageSideRatio);
                    setMeasuredDimension(width, height);
                } else {
                    // Image is taller than the display (ratio)
                    int height = MeasureSpec.getSize(heightMeasureSpec);
                    int width = (int) (height * imageSideRatio);
                    setMeasuredDimension(width, height);
                }
            }
        } catch (Exception e) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}