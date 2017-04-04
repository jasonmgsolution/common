package com.jasonmg.salepoison.view;

import android.content.Context;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.flyco.animation.FadeEnter.FadeEnter;
import com.flyco.animation.FadeExit.FadeExit;
import com.flyco.dialog.widget.base.BaseDialog;
import com.jasonmg.salepoison.util.EditTextUtil;

/**
 * Created by jasonmg_0302 on 2016-03-14.
 */
public class LayoutDialog extends BaseDialog<LayoutDialog> {

    private View dialogView;

    public LayoutDialog(Context context, int layoutResId) {
        super(context);
        dialogView = View.inflate(mContext, layoutResId, null);
    }

    @Override
    public View onCreateView() {
        setCanceledOnTouchOutside(false);
        widthScale(0.85f);
        showAnim(new FadeEnter());
        dismissAnim(new FadeExit());
        return dialogView;
    }

    @Override
    public void setUiBeforShow() {}

    @Override
    public View findViewById(int id) {
        return dialogView.findViewById(id);
    }

    public void setOnLayoutDialogClickListener(int id, final OnLayoutDialogClickListener listener){
        findViewById(id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) listener.onClick(LayoutDialog.this, v);
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Rect dialogBounds = new Rect();
        dialogView.getGlobalVisibleRect(dialogBounds);

        if (!dialogBounds.contains((int) ev.getX(), (int) ev.getY())) {

            View currentFocus = getCurrentFocus();
            if(currentFocus != null && currentFocus instanceof EditText){
                EditTextUtil.hide((EditText) currentFocus);
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}