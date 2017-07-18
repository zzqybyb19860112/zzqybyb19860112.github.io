package com.ybyb.zzq.myrealm;

import android.app.Application;
import io.realm.Realm;

/**
 * 作者：周正权 on 2017/5/23
 * 邮箱：zhouzhengquan@shixinyun.com
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
