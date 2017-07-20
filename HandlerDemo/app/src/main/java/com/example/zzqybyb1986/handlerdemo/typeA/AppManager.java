package com.example.zzqybyb1986.handlerdemo.typeA;

import android.content.Context;

import com.example.zzqybyb1986.handlerdemo.MyApp;

/**
 * Created by zzqybyb1986 on 2017/7/19.
 */

public class AppManager {
    private Context context;

    /**
     * 错误写法，不宜在Activity中传入this进行该实例的调用
     * @param context
     */
    /*public AppManager(Context context){
        this.context=context;
    }*/
    public AppManager(){
        this.context= MyApp.getContext();
    }

    /**
     * 其他功能性方法
     */
    public  void doSomething(){
       // context.deleteDatabase();业务逻辑
    }
}
