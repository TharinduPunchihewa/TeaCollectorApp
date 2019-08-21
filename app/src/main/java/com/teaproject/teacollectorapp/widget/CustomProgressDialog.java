package com.teaproject.teacollectorapp.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;

import com.teaproject.teacollectorapp.R;

import java.lang.ref.WeakReference;
import java.util.Timer;

public class CustomProgressDialog extends ProgressDialog {
    int timerCount = 0;
    private Context sContext = null;
    private Timer timer = null;
    private Handler mHandler = new ShowCustomProgressDialogHandler(this);


    public CustomProgressDialog(Context context) {
        super(context);
        this.setMessage(context.getResources().getString(R.string.progress_dialog_message));
        this.setCancelable(false);
        this.setIndeterminate(true);
        sContext = context;
    }
    public CustomProgressDialog(Context context,String message) {
        super(context);
        if(message!=null) {
            this.setMessage(message);
        }else{
            this.setMessage(context.getResources().getString(R.string.progress_dialog_message));
        }
        this.setCancelable(false);
        this.setIndeterminate(true);
        sContext = context;
    }

    @Override
    public void show() {
        super.show();
    }

    public void startTimer() {
        // Display the default dialog
        this.show();
    }

    public void stopTimer() {

        try {
            if ((this != null) && this.isShowing()) {
                this.dismiss();
            }
            // Dismiss the default dialog
            // this.dismiss();
        } catch (final IllegalArgumentException e) {
            Log.d(getClass().getSimpleName(), Log.getStackTraceString(e));
            // Handle or log or ignore
        }

        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);

    }

    private static class ShowCustomProgressDialogHandler extends Handler {
        private final WeakReference<CustomProgressDialog> mRef;

        public ShowCustomProgressDialogHandler(final CustomProgressDialog customProgressDialog) {
            mRef = new WeakReference<CustomProgressDialog>(customProgressDialog);
        }

        @Override
        public void handleMessage(Message msg) {
            final CustomProgressDialog customProgressDialog = mRef.get();
            if (customProgressDialog != null) {
                customProgressDialog.show();
            }
        }

    }
}