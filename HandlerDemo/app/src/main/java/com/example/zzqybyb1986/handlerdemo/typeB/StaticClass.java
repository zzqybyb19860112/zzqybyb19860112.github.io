package com.example.zzqybyb1986.handlerdemo.typeB;

/**
 * Created by zzqybyb1986 on 2017/7/19.
 */

public class StaticClass {
    private static InnerClass innerClass = null;

    /*class InnerClass {///////////////////未加static
//        doSomethings();
    }*/
    static class InnerClass{
        //        doSomethings();//////////加了static，或者将该内部类写成单例的外部类，向外提供方法进行内部调用
    }
}
