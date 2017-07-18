package com.ybyb.zzq.rxjavastudy;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：周正权 on 2017/5/15
 * 邮箱：zhouzhengquan@shixinyun.com
 */

public class Student implements Serializable {
    private String name;
    private List<Course> courses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
