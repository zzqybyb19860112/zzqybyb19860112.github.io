package com.ybyb.zzq.h_know.api;

import com.ybyb.zzq.h_know.data.response.BigType;
import com.ybyb.zzq.h_know.data.response.ListInfo;
import java.util.List;
import rx.Observable;

/**
 * 作者：周正权 on 2017/6/16
 * 邮箱：zhouzhengquan@shixinyun.com
 */

public class UserRepository {
    private static volatile UserRepository mInstance;

    private ApiFactory mApiFactory;

    private UserRepository() {
        this.mApiFactory = new ApiFactory();
    }

    public static UserRepository getInstance() {
        if (null == mInstance) {
            synchronized (UserRepository.class) {
                if (null == mInstance) {
                    mInstance = new UserRepository();
                }
            }
        }
        return mInstance;
    }

    /**
     * 查询健康知识分类
     */
    public Observable<List<BigType>> queryTypes(String key) {
        return this.mApiFactory.queryTypes(key);
    }

    /**
     * 查询健康知识信息列表
     *
     * @param key
     * @param id
     *
     * @return
     */
    public Observable<ListInfo> queryInfoList(String key, int id) {
        return this.mApiFactory.queryInfoList(key, id);
    }
}
