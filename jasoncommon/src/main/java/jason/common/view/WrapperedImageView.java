package jason.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by jasonmg_0302 on 2016-05-27.
 */
public class WrapperedImageView extends ImageView {


    public WrapperedImageView(Context context) {
        super(context);
    }

    public WrapperedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapperedImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        // 180 x 100 의 비율
        int heightSize = widthSize * 5 / 9;

        setMeasuredDimension(widthSize, heightSize);
    }

}
