package com.ybyb.zzq.h_know.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ybyb.zzq.h_know.R;
import com.ybyb.zzq.h_know.data.response.BigType;
import com.ybyb.zzq.h_know.ui.infolist.InfoListActivity;
import java.util.List;

/**
 * 作者：周正权 on 2017/6/16
 * 邮箱：zhouzhengquan@shixinyun.com
 */

public class RvTypeAdapter extends RecyclerView.Adapter {
    List<BigType> types;
    Context context;

    public RvTypeAdapter(Context context,List<BigType> types) {
        this.types = types;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.main_item_rv, parent, false);//item显示不正常的错误原因
        return new RvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        RvViewHolder holder= (RvViewHolder) viewHolder;
        final BigType type=types.get(position);
        holder.nameTv.setText(type.getName());
        holder.desTv.setText(type.getDescription());
        holder.desTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoListActivity.start(context,type.getName(),type.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return types.size();
    }
    class RvViewHolder extends RecyclerView.ViewHolder{
        TextView nameTv;
        TextView desTv;
        public RvViewHolder(View itemView) {
            super(itemView);
            nameTv= (TextView) itemView.findViewById(R.id.name_tv);
            desTv= (TextView) itemView.findViewById(R.id.des_tv);
        }
    }
}
