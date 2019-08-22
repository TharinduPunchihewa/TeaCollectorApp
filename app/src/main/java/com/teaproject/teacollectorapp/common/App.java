package com.teaproject.teacollectorapp.common;

import android.app.Application;
import android.content.SharedPreferences;

public class App extends Application {

    private SharedPreferences mSharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public SharedPreferences getmSharedPreferences() {
        return mSharedPreferences;
    }

    public void setmSharedPreferences(SharedPreferences mSharedPreferences) {
        this.mSharedPreferences = mSharedPreferences;
    }
}
