package jason.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import jason.common.R;

/**
 * Created by stadiko on 6/23/15.
 */
public class CountDownTimerView extends TextView {

    public interface TimerListener {
        void onTick(long millisUntilFinished);

        void onFinish();
    }

    private Context mContext;

    private CharSequence mPrefixText;
    private CharSequence mSuffixText;

    private long mDays = 0;
    private long mHours = 0;
    private long mMinutes = 0;
    private long mSeconds = 0;
    private long mMilliSeconds = 0;

    private TimerListener mListener;

    public void setOnTimerListener(TimerListener listener) {
        mListener = listener;
    }

    private CountDownTimer mCountDownTimer;

    public CountDownTimerView(Context context) {
        this(context, null);
    }

    public CountDownTimerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountDownTimerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.CountDownTimerView, defStyleAttr, 0);

        if (a != null) {
            mPrefixText = a.getString(R.styleable.CountDownTimerView_prefixText);
            mSuffixText = a.getString(R.styleable.CountDownTimerView_suffixText);

            String milliStr = a.getString(R.styleable.CountDownTimerView_timeMilliSeconds);
            if (!TextUtils.isEmpty(milliStr) && TextUtils.isDigitsOnly(milliStr)) {
                mMilliSeconds = Long.parseLong(a.getString(R.styleable.CountDownTimerView_timeMilliSeconds));
                setTime(mMilliSeconds);
                startCountDown();
            }

            a.recycle();
        }

        displayText();
    }

    private void initCounter() {
        mCountDownTimer = new CountDownTimer(mMilliSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                calculateTime(millisUntilFinished);
                if (mListener != null) {
                    mListener.onTick(millisUntilFinished);
                }
            }

            @Override
            public void onFinish() {
                calculateTime(0);
                if (mListener != null) {
                    mListener.onFinish();
                }
            }
        };
    }

    public void startCountDown() {
        if (mCountDownTimer != null) {
            mCountDownTimer.start();
        }
    }

    public void stopCountDown() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }

    public void setPrefixText(CharSequence text) {
        mPrefixText = text;
        displayText();
    }

    public void setSuffixText(CharSequence text) {
        mSuffixText = text;
        displayText();
    }

    public void setTime(long milliSeconds) {
        mMilliSeconds = milliSeconds;
        initCounter();
        calculateTime(milliSeconds);
    }

    private void calculateTime(long milliSeconds) {
        mSeconds = (milliSeconds / 1000);
        mMinutes = mSeconds / 60;
        mSeconds = mSeconds % 60;

        mDays = ( mMinutes / 60 ) / 24;
        mHours = ( mMinutes / 60 ) % 24;
        mMinutes = mMinutes % 60;

        displayText();
    }

    private void displayText() {
        StringBuffer buffer = new StringBuffer();
        if (!TextUtils.isEmpty(mPrefixText)) {
            buffer.append(mPrefixText);
            buffer.append(" ");
        }

        if( mDays > 0 ){
            buffer.append(mDays);
            buffer.append("일 ");
        }
        if( mHours > 0 ) {
            buffer.append(mHours);
            buffer.append(" : ");
        }
        buffer.append(getTwoDigitNumber(mMinutes));
        buffer.append(" : ");
        buffer.append(getTwoDigitNumber(mSeconds));

        if (!TextUtils.isEmpty(mSuffixText)) {
            buffer.append(" ");
            buffer.append(mSuffixText);
        }

        setText(buffer);
    }

    private String getTwoDigitNumberZero(long number) {
        if (number == 0) {
            return "00";
        }
        return String.valueOf(number);
    }

    private String getTwoDigitNumber(long number) {
        if (number >= 0 && number < 10) {
            return "0" + number;
        }

        return String.valueOf(number);
    }
}
