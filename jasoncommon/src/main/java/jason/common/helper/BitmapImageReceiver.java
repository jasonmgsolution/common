package jason.common.helper;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.Glide;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jasonmg_0302 on 2017-04-03.
 */

public class BitmapImageReceiver {

    private String imageUrl;
    private int retryCount;
    private int retSumVal = 0;
    private int delay;


    public BitmapImageReceiver(String imageUrl, int retryCount, int delay) {
        this.imageUrl = imageUrl;
        this.retryCount = retryCount;
        this.delay = delay;

    }

    public void getBitmap(final Context context, final OnImageReceiveListener listener){

        class BitmapTask extends TimerTask {
            @Override
            public void run() {
                Bitmap bitmap = null;
                try {

                    bitmap = Glide.with(context).load(imageUrl).asBitmap().into(-1, -1).get();

                } catch (Exception e) {
                    bitmap = null;
                }

                if(bitmap == null && retSumVal++ < retryCount) {
                    new Timer().schedule(new BitmapTask(), delay);
                } else {
                    listener.onReceive(bitmap);
                }
            }
        }

        new BitmapTask().run();

    }

    public interface OnImageReceiveListener {
        void onReceive(Bitmap bitmap);
    }
}
