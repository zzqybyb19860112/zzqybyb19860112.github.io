package com.ybyb.zzq.h_know.data.local;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * 作者：周正权 on 2017/6/16
 * 邮箱：zhouzhengquan@shixinyun.com
 */

public class HKnowledge extends RealmObject {
    private RealmList<Type> datas;

    public RealmList<Type> getDatas() {
        return datas;
    }

    public void setDatas(RealmList<Type> datas) {
        this.datas = datas;
    }
}
