package com.ybyb.zzq.myrealm.data;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * 作者：周正权 on 2017/5/23
 * 邮箱：zhouzhengquan@shixinyun.com
 */

public class SimpleClass extends RealmObject {
    private RealmList<Student> students;//全班所有学生
    private String className;//班级名字

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public RealmList<Student> getStudents() {
        return students;
    }

    public void setStudents(RealmList<Student> students) {
        this.students = students;
    }
}
