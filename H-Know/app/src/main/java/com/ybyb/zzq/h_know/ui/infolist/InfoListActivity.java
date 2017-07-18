package com.ybyb.zzq.h_know.ui.infolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;
import com.ybyb.zzq.h_know.App;
import com.ybyb.zzq.h_know.AppConstants;
import com.ybyb.zzq.h_know.R;
import com.ybyb.zzq.h_know.base.BaseActivity;
import com.ybyb.zzq.h_know.data.local.InfoAssortment;
import com.ybyb.zzq.h_know.data.local.InfoList;
import com.ybyb.zzq.h_know.data.response.Info;
import com.ybyb.zzq.h_know.data.response.ListInfo;
import com.ybyb.zzq.h_know.ui.adapter.RvListInfoAdapter;
import io.realm.Realm;
import java.util.List;

public class InfoListActivity extends BaseActivity<InfoListPresenter> implements InfoListContract.View {
    private String mType;
    private int mId;
    private Realm mRealm;
    private RecyclerView mInfoListRv;
    private TextView mTypeTv;

    /**
     * 启动InfoListActivity
     */
    public static void start(Context context, String type, int id) {
        Intent intent = new Intent(context, InfoListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putInt("id", id);
        intent.putExtra("data", bundle);
        context.startActivity(intent);
    }
    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        this.mRealm = App.mRealm;
        this.mType = getIntent().getBundleExtra("data").getString("type");
        this.mId = getIntent().getBundleExtra("data").getInt("id");
        super.mPresenter.queryInfoList(AppConstants.APP_KEY, this.mId);
    }

    @Override
    protected void bindViews() {
        this.mInfoListRv = (RecyclerView) findViewById(R.id.info_list_rv);
        this.mTypeTv = (TextView) findViewById(R.id.type_tv);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_info_list;
    }

    @Override
    protected InfoListPresenter createPresenter() {
        return new InfoListPresenter(this, this);
    }

    @Override
    public void queryInfoListSuccess(ListInfo data) {
        this.mTypeTv.setText(this.mType);
        List<Info> list = data.getData();
        saveData(data);
        setRvAdapter(list);
    }

    private void saveData(ListInfo data) {
        this.mRealm.beginTransaction();
        InfoList list=this.mRealm.createObject(InfoList.class);
        list.setTotal(data.getTotal());
        for (Info info:data.getData()){
            InfoAssortment infoment=this.mRealm.createObject(InfoAssortment.class);
            infoment.setDescription(info.getDescription());
            infoment.setId(info.getId());
            infoment.setCount(info.getCount());
            infoment.setFcount(info.getFcount());
            infoment.setImg(info.getImg());
            infoment.setKeywords(info.getKeywords());
            infoment.setLoreclass(info.getLoreclass());
            infoment.setRcount(info.getRcount());
            infoment.setTime(info.getTime());
            infoment.setTitle(info.getTitle());
            list.getData().add(infoment);
        }
        this.mRealm.commitTransaction();
    }

    private void setRvAdapter(List<Info> list) {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        this.mInfoListRv.setLayoutManager(manager);
        RvListInfoAdapter adapter = new RvListInfoAdapter(this, list);
        this.mInfoListRv.setAdapter(adapter);
    }

    @Override
    public void queryInfoListError() {
        Toast.makeText(this, "网络错误，请稍候重试！", Toast.LENGTH_LONG).show();
    }
}
