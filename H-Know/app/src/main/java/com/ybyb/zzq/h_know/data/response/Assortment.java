package com.ybyb.zzq.h_know.data.response;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：周正权 on 2017/6/16
 * 邮箱：zhouzhengquan@shixinyun.com
 */

public class Assortment implements Serializable {
    private List<BigType> bigTypeList;

    public List<BigType> getBigTypeList() {
        return bigTypeList;
    }

    public void setBigTypeList(List<BigType> bigTypeList) {
        this.bigTypeList = bigTypeList;
    }
}
