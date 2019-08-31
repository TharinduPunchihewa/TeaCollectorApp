package com.teaproject.teacollectorapp.common;

import android.view.View;

public interface Listener {

    public interface ItemClickListener {
        void onClick(View view, int position);
    }
}
