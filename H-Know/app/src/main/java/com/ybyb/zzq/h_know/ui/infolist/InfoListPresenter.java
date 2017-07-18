package com.ybyb.zzq.h_know.ui.infolist;

import android.content.Context;
import com.ybyb.zzq.h_know.api.ApiSubscriber;
import com.ybyb.zzq.h_know.api.RxSchedulers;
import com.ybyb.zzq.h_know.api.UserRepository;
import com.ybyb.zzq.h_know.data.response.ListInfo;
import rx.Subscription;

/**
 * DetailPresenter
 *
 * 作者：周正权 on 2017/1/19
 * 邮箱：zhouzhengquan@shixinyun.com
 */

public class InfoListPresenter extends InfoListContract.Presenter {
    /**
     * 构造方法
     *
     * @param context
     * @param view
     */
    public InfoListPresenter(Context context, InfoListContract.View view) {
        super(context, view);
    }

    /**
     * 查询健康知识信息列表
     * @param key
     * @param id
     */
    @Override
    public void queryInfoList(String key, int id) {
        Subscription subscription = UserRepository.getInstance().queryInfoList(key,id).compose(RxSchedulers.<ListInfo>io_main()).subscribe(new ApiSubscriber<ListInfo>(mContext) {
            @Override
            protected void _onNext(ListInfo data) {
                mView.queryInfoListSuccess(data);
            }

            @Override
            protected void _onError(String message) {
                mView.queryInfoListError();
            }

            @Override
            protected void _onCompleted() {

            }
        });
        super.addSubscribe(subscription);
    }
}

