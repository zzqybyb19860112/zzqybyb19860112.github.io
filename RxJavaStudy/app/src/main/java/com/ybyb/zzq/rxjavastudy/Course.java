package com.ybyb.zzq.rxjavastudy;

import java.io.Serializable;

/**
 * 作者：周正权 on 2017/5/15
 * 邮箱：zhouzhengquan@shixinyun.com
 */

public class Course implements Serializable {
    private String name;
    private double score;

    public Course(String name, double score) {
        this.name = name;
        this.score = score;
    }

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
}
