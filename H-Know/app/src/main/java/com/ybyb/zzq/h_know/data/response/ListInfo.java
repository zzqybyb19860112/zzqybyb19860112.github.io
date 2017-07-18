package com.ybyb.zzq.h_know.data.response;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：周正权 on 2017/6/16
 * 邮箱：zhouzhengquan@shixinyun.com
 */

public class ListInfo implements Serializable {
    private int total;
    private List<Info> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Info> getData() {
        return data;
    }

    public void setData(List<Info> data) {
        this.data = data;
    }
}
