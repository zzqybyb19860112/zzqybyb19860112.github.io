package com.ybyb.zzq.h_know.data.local;

import io.realm.RealmObject;

/**
 * 作者：周正权 on 2017/6/16
 * 邮箱：zhouzhengquan@shixinyun.com
 */

public class Type extends RealmObject {
    private int id;
    private String name;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
