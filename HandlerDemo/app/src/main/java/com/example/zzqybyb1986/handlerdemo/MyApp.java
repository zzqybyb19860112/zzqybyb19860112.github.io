package com.example.zzqybyb1986.handlerdemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by zzqybyb1986 on 2017/7/19.
 */

public class MyApp extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
    }
    public static Context getContext(){
        return context;
    }
}
