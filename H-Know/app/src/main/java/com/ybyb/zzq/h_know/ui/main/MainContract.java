package com.ybyb.zzq.h_know.ui.main;

import android.content.Context;
import com.ybyb.zzq.h_know.base.BasePresenter;
import com.ybyb.zzq.h_know.base.BaseView;
import com.ybyb.zzq.h_know.data.response.Assortment;
import com.ybyb.zzq.h_know.data.response.BigType;
import java.util.List;

/**
 * 找回密码契约类，MVP规范
 *
 * 作者：周正权 on 2017/1/19
 * 邮箱：zhouzhengquan@shixinyun.com
 */

public interface MainContract {
    interface View extends BaseView {

        /**
         * 查询健康知识分类成功
         */
        void queryTypesSuccess(List<BigType> data);

        /**
         * 查询健康知识分类失败
         */
        void queryTypesError();
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
         * 查询健康知识分类
         */
        public abstract void queryTypes(String key);
    }
}
