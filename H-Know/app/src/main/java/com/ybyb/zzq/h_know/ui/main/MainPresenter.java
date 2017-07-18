package com.ybyb.zzq.h_know.ui.main;

import android.content.Context;
import com.ybyb.zzq.h_know.api.ApiSubscriber;
import com.ybyb.zzq.h_know.api.RxSchedulers;
import com.ybyb.zzq.h_know.api.UserRepository;
import com.ybyb.zzq.h_know.data.response.Assortment;
import com.ybyb.zzq.h_know.data.response.BigType;
import java.util.List;
import rx.Subscription;
/**
 * DetailPresenter
 *
 * 作者：周正权 on 2017/1/19
 * 邮箱：zhouzhengquan@shixinyun.com
 */

public class MainPresenter extends MainContract.Presenter {
    /**
     * 构造方法
     *
     * @param context
     * @param view
     */
    public MainPresenter(Context context, MainContract.View view) {
        super(context, view);
    }

    /**
     * 查询健康知识分类
     * @param key
     */
    @Override
    public void queryTypes(String key) {
        Subscription subscription = UserRepository.getInstance().queryTypes(key).compose(RxSchedulers.<List<BigType>>io_main()).subscribe(new ApiSubscriber<List<BigType>>(mContext) {
            @Override
            protected void _onNext(List<BigType> data) {
                mView.queryTypesSuccess(data);
            }

            @Override
            protected void _onError(String message) {
                mView.queryTypesError();
            }

            @Override
            protected void _onCompleted() {

            }
        });
        super.addSubscribe(subscription);
    }
}

