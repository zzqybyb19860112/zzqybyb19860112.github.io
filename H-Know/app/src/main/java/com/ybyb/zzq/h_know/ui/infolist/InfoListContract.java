package com.ybyb.zzq.h_know.ui.infolist;

import android.content.Context;
import com.ybyb.zzq.h_know.base.BasePresenter;
import com.ybyb.zzq.h_know.base.BaseView;
import com.ybyb.zzq.h_know.data.response.BigType;
import com.ybyb.zzq.h_know.data.response.ListInfo;
import java.util.List;

/**
 * 找回密码契约类，MVP规范
 *
 * 作者：周正权 on 2017/1/19
 * 邮箱：zhouzhengquan@shixinyun.com
 */

public interface InfoListContract {
    interface View extends BaseView {

        /**
         * 查询健康知识信息列表成功
         */
        void queryInfoListSuccess(ListInfo data);

        /**
         * 查询健康知识信息列表失败
         */
        void queryInfoListError();
    }

    abstract class Presenter extends BasePresenter<View> {

        /**
         * 构造方法
         *
         * @param context
         * @param view
         */
        public Presenter(Context context, View view) {
            super(context, view);
        }

        /**
         * 查询健康知识信息列表
         */
        public abstract void queryInfoList(String key,int id);
    }
}
