package com.jasonmg.salepoison.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by jasonmg_0302 on 2016-05-24.
 */
public class DisplayUtil {

    public static int dp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static int px2dp(Context context, int pixel) {

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) (pixel / (metrics.densityDpi / 160f));
    }

    /**
     * @param context
     * @param targetView      적용할 View
     * @param topMargin       상단 마진
     * @param bottomMargin    하단 마진
     * @param leftMargin      좌측 마진
     * @param rightMargin     우측 마진
     * @param useAddRule      RelativeLayout의 Rule을 적용
     * @param centerAlginType 사용안함 -1 : RelativeLayout.CENTER_VERTICAL, RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.CENTER_IN_PARENT ; 사용안함 -1
     * @param alginEdgeType   RelativeLayout.TO_LEFT, RelativeLayout.TO_RIGHT : 사용안함 -1
     * @param alginEdgeId     alginEdgeType를 적용할 뷰의 아이디 : 사용안함 -1
     */
    public static void setMarginAndRule(Context context, View targetView, int topMargin, int bottomMargin, int leftMargin, int rightMargin, boolean useAddRule, int centerAlginType, int alginEdgeType, int alginEdgeId) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) targetView.getLayoutParams();
        if (useAddRule)
            setAddRule(layoutParams, centerAlginType, alginEdgeType, alginEdgeId);
        layoutParams.topMargin = DisplayUtil.dp2px(context, topMargin);
        layoutParams.bottomMargin = DisplayUtil.dp2px(context, bottomMargin);
        layoutParams.leftMargin = DisplayUtil.dp2px(context, leftMargin);
        layoutParams.rightMargin = DisplayUtil.dp2px(context, rightMargin);
        setLayoutParams(layoutParams, targetView);
    }

    public static void setAddRule(RelativeLayout.LayoutParams layoutParams, int centerAlginType, int alginEdgeType, int alginEdgeId) {
        if (centerAlginType != -1)
            layoutParams.addRule(centerAlginType);
        if (alginEdgeType != -1)
            layoutParams.addRule(alginEdgeType, alginEdgeId);
    }

    public static void setLayoutParams(RelativeLayout.LayoutParams layoutParams, View targetView){
        targetView.setLayoutParams(layoutParams);
    }



}



