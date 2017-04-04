package com.jasonmg.salepoison.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * http://stackoverflow.com/a/12931731
 */
public class NonScrollableListView extends ListView {


    public NonScrollableListView(Context context) {
        super(context);
    }

    public NonScrollableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NonScrollableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Do not use the highest two bits of Integer.MAX_VALUE because they are
        // reserved for the MeasureSpec mode
        int heightSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightSpec);
        getLayoutParams().height = getMeasuredHeight();
    }
}
