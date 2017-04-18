package jason.common.view;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;

/**
 * Created by jasonmg_0302 on 2016-03-14.
 */
public class AlertLayoutDialog extends AlertDialog {

    private View dialogView;

    public AlertLayoutDialog(Context context, int layoutResId) {
        super(context);
        dialogView = View.inflate(context, layoutResId, null);

        setView(dialogView);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

    }

    @Override
    public View findViewById(int id) {
        return dialogView.findViewById(id);
    }


}