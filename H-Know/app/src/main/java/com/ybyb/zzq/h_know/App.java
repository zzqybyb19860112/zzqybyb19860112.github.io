package com.ybyb.zzq.h_know;

import android.app.Application;
import android.content.Context;
import io.realm.Realm;

/**
 * 作者：周正权 on 2017/6/16
 * 邮箱：zhouzhengquan@shixinyun.com
 */

public class App extends Application {
    public static Realm mRealm;
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        this.mRealm=Realm.getDefaultInstance();
        this.mContext=this;
    }
    public static Context getContext(){
        return mContext;
    }
}
