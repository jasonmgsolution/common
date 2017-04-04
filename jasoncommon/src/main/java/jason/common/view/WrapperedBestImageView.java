package com.jasonmg.salepoison.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by jasonmg_0302 on 2016-05-27.
 */
public class WrapperedBestImageView extends ImageView {


    public WrapperedBestImageView(Context context) {
        super(context);
    }

    public WrapperedBestImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapperedBestImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        // 360 x 140 의 비율
        int heightSize = widthSize * 4 / 11;

        setMeasuredDimension(widthSize, heightSize);
    }

}
