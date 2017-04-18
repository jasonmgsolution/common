package jason.common.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.TextView;

import jason.common.R;


public class DeleteDialog extends Dialog implements View.OnClickListener {
    public Activity activity;

    public TextView cancel_tv, confirm_tv;
    public CheckBox delete_cb;

    private OnDeleteDlgListener mDeleteDlgListener;

    public interface OnDeleteDlgListener {
        public void onDeleteAllSet(Boolean delete_all);

        public void onCancel();
    }

    public DeleteDialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_delete);
        confirm_tv = (TextView) findViewById(R.id.confirm_tv);
        cancel_tv = (TextView) findViewById(R.id.cancel_tv);
        delete_cb = (CheckBox) findViewById(R.id.delete_cb);

        confirm_tv.setOnClickListener(this);
        cancel_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == confirm_tv) {

            if (delete_cb.isChecked()) {
                mDeleteDlgListener.onDeleteAllSet(true);
            } else {
                mDeleteDlgListener.onDeleteAllSet(false);
            }
            dismiss();
        }

        if (v == cancel_tv) {
            mDeleteDlgListener.onCancel();
            dismiss();
        }
    }

    public void setOnDeleteDlgListener(OnDeleteDlgListener listener) {
        mDeleteDlgListener = listener;
    }
}