package com.jasonmg.salepoison.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by jasonmg_0302 on 2016-04-05.
 */
public class EditTextUtil {

    /**
     * 소프트 키보드 Show
     * @param editText
     */
    public static void show(EditText editText){
//        setEndSelection(editText);

        Context context = editText.getContext();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * 소프트 키보드 hide
     * @param editText
     */
    public static void hide(EditText editText){
        Context context = editText.getContext();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    /**
     * 커서위치를 에디트 텍스트 맨 끝으로 이동
     * @param editText
     */
    public static void setEndSelection(EditText editText){
        editText.clearFocus();
        editText.requestFocus();
        editText.setSelection(editText.getText().length());
    }

    public static void setSoftKeyboardListener(Activity activity, final OnSoftKeyboardListener listener){
        final View activityView = activity.getWindow().getDecorView();
        activityView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                Rect r = new Rect();
                activityView.getWindowVisibleDisplayFrame(r);
                int screenHeight = activityView.getRootView().getHeight();

                // r.bottom is the position above soft keypad or device button.
                // if keypad is shown, the r.bottom is smaller than that before.
                int keypadHeight = screenHeight - r.bottom;
                if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
                    // keyboard is opened
                    if(listener != null) listener.opened();
                } else {
                    // keyboard is closed
                    if(listener != null) listener.closed();
                }
            }
        });
    }

    public interface OnSoftKeyboardListener {
        void opened();
        void closed();
    }

}
