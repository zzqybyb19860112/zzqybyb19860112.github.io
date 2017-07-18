package com.ybyb.zzq.myrealm.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * 作者：周正权 on 2017/5/23
 * 邮箱：zhouzhengquan@shixinyun.com
 */

public class Subject extends RealmObject {
    //@PrimaryKey//如果继承RealmObject的类中没有该字段，那么调用copyToRealmOrUpdate()方法使用该类的对象时会抛出异常
    private String name;//学科名字
    private double score;//得分
    private int fullScore;//满分多少

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getFullScore() {
        return fullScore;
    }

    public void setFullScore(int fullScore) {
        this.fullScore = fullScore;
    }
}
