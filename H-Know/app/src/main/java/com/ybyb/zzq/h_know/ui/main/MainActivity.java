package com.ybyb.zzq.h_know.ui.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import com.ybyb.zzq.h_know.App;
import com.ybyb.zzq.h_know.AppConstants;
import com.ybyb.zzq.h_know.R;
import com.ybyb.zzq.h_know.base.BaseActivity;
import com.ybyb.zzq.h_know.data.local.HKnowledge;
import com.ybyb.zzq.h_know.data.local.Type;
import com.ybyb.zzq.h_know.data.response.Assortment;
import com.ybyb.zzq.h_know.data.response.BigType;
import com.ybyb.zzq.h_know.ui.adapter.RvTypeAdapter;
import io.realm.Realm;
import io.realm.RealmList;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {
    private RecyclerView mTypeRv;
    private Realm mRealm;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        this.mRealm= App.mRealm;
        HKnowledge knowledge=this.mRealm.where(HKnowledge.class).findFirst();
        if (knowledge==null){
            super.mPresenter.queryTypes(AppConstants.APP_KEY);
            Log.i("tag","网络获取分类信息");
        }else {
            RealmList<Type> data = knowledge.getDatas();
            List<BigType> list=new ArrayList<>();
            for (Type type:data){
                BigType info=new BigType();
                info.setId(type.getId());
                info.setName(type.getName());
                info.setDescription(type.getDescription());
                list.add(info);
            }
            showDatas(list);
            Log.i("tag","本地获取分类信息");
        }
    }

    @Override
    protected void bindViews() {
        this.mTypeRv = (RecyclerView) findViewById(R.id.type_rv);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this, this);
    }

    @Override
    public void queryTypesSuccess(List<BigType> data) {
        showDatas(data);
        this.mRealm.beginTransaction();
        HKnowledge hKnowledge=this.mRealm.createObject(HKnowledge.class);
        RealmList<Type> types=new RealmList<>();
        for (BigType type:data){
            Type type1=this.mRealm.createObject(Type.class);
            type1.setId(type.getId());
            type1.setName(type.getName());
            type1.setDescription(type.getDescription());
            types.add(type1);
        }
        hKnowledge.setDatas(types);
        this.mRealm.commitTransaction();
    }

    @Override
    public void queryTypesError() {
        Toast.makeText(this,"网络出错，请稍后重试!",Toast.LENGTH_LONG).show();
    }
    private void showDatas(List<BigType> list){
        RvTypeAdapter adapter=new RvTypeAdapter(this,list);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(this);
        this.mTypeRv.setLayoutManager(manager);
        this.mTypeRv.setAdapter(adapter);
    }
}
