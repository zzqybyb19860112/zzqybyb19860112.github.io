package com.ybyb.zzq.h_know.data.local;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * 作者：周正权 on 2017/6/19
 * 邮箱：zhouzhengquan@shixinyun.com
 */

public class InfoList extends RealmObject {
    private int total;
    private RealmList<InfoAssortment> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public RealmList<InfoAssortment> getData() {
        return data;
    }

    public void setData(RealmList<InfoAssortment> data) {
        this.data = data;
    }
}
