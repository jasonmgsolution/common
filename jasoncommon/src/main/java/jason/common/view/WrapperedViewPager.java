package jason.common.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by jasonmg_0302 on 2016-05-27.
 */
public class WrapperedViewPager extends ViewPager {


    public WrapperedViewPager(Context context) {
        super(context);
    }

    public WrapperedViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        // 1020 x 780 의 비율
        // 180 x 100 의 비율
        int heightSize = widthSize * 5 / 9;
//        int heightSize = widthSize * 13 / 17;

        setMeasuredDimension(widthSize, heightSize);
    }


}
