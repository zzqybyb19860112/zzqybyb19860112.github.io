package com.ybyb.zzq.h_know.api;

import com.ybyb.zzq.h_know.data.response.BigType;
import com.ybyb.zzq.h_know.data.response.ListInfo;
import com.ybyb.zzq.h_know.data.response.ResultData;
import java.util.List;
import java.util.Map;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 作者：周正权 on 2017/6/16
 * 邮箱：zhouzhengquan@shixinyun.com
 */

public interface ApiService {
    /**
     * 查询健康知识分类
     *
     * @param parms
     *
     * @return
     */
    @POST("categoryList")
    @FormUrlEncoded
    Observable<ResultData<List<BigType>>> queryTypes(@FieldMap Map<String, String> parms);

    /**
     * 查询健康知识信息列表
     *
     * @param params
     *
     * @return
     */
    @POST("infoList")
    @FormUrlEncoded
    Observable<ResultData<ListInfo>> queryInfoList(@FieldMap Map<String, String> params);
}
