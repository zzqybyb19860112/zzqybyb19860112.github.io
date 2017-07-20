package com.example.zzqybyb1986.handlerdemo.typeD;

/**
 * Created by zzqybyb1986 on 2017/7/19.
 */

public class ThreadClass {
    //错误写法
    /*public static void doSomethings(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                //业务逻辑
            }
        }.start();
    }*/

    //正确写法，声明一个静态的内部类实现Runnable接口
    public static void doSomethings(){
        new Thread(new MyRunnable()).start();
    }
    static class MyRunnable implements Runnable{
        @Override
        public void run() {
            //业务逻辑
        }
    }
}
