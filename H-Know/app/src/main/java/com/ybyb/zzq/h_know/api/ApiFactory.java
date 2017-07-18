package com.ybyb.zzq.h_know.api;

import com.ybyb.zzq.h_know.data.response.BigType;
import com.ybyb.zzq.h_know.data.response.ListInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * 作者：周正权 on 2017/6/16
 * 邮箱：zhouzhengquan@shixinyun.com
 */

public class ApiFactory {

    private ApiService mApiService;

    public ApiFactory() {
        this.mApiService = ApiManager.getInstance().getApiService();
    }

    /**
     * 查询健康知识分类
     *
     * @param key
     *
     * @return
     */
    public Observable<List<BigType>> queryTypes(String key) {
        Map<String, String> params = new HashMap<>();
        params.put("key", key);
        return this.mApiService.queryTypes(params).map(new ApiResultFunc<List<BigType>>()).subscribeOn(Schedulers.io());
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
        Map<String, String> params = new HashMap<>();
        params.put("key", key);
        params.put("id", id + "");
        return this.mApiService.queryInfoList(params).map(new ApiResultFunc<ListInfo>()).subscribeOn(Schedulers.io());
    }
}
