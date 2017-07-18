package com.ybyb.zzq.myrealm.data;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * 作者：周正权 on 2017/5/23
 * 邮箱：zhouzhengquan@shixinyun.com
 */

public class Student extends RealmObject {
    private String name;//名字
    private long id;//学号
    private int age;//年龄
    private String gender;//性别
    private RealmList<Subject> subjects;//学习情况
    private double totalScore;//总分
    private double averageScore;//平均分
    private int orderScore;//总排名

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public RealmList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(RealmList<Subject> subjects) {
        this.subjects = subjects;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public int getOrderScore() {
        return orderScore;
    }

    public void setOrderScore(int orderScore) {
        this.orderScore = orderScore;
    }
}
