package jason.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 *  sjy2746
 *  imageView 정사각형으로 표시되게 하는 함수
 **/

public class WrapperedSquareImageView extends ImageView {

    public WrapperedSquareImageView(final Context context) {
        super(context);
    }

    public WrapperedSquareImageView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapperedSquareImageView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int width, int height) {
      //  super.onMeasure(width, height);

        final int widthSize = MeasureSpec.getSize(width);
        setMeasuredDimension(widthSize, widthSize);
        //int measuredWidth = getMeasuredWidth();
       // setMeasuredDimension(measuredWidth, measuredWidth);

        /*
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (measuredWidth > measuredHeight) {
            setMeasuredDimension(measuredHeight, measuredHeight);
        } else {
            setMeasuredDimension(measuredWidth, measuredWidth);

        }
        */

    }

//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//
//        int width = getMeasuredWidth();
//        setMeasuredDimension(width, width);
//    }

}