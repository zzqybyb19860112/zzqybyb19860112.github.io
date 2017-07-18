package com.ybyb.zzq.h_know.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.ybyb.zzq.h_know.AppConstants;
import com.ybyb.zzq.h_know.R;
import com.ybyb.zzq.h_know.data.response.Info;
import java.util.List;

/**
 * 作者：周正权 on 2017/6/19
 * 邮箱：zhouzhengquan@shixinyun.com
 */

public class RvListInfoAdapter extends RecyclerView.Adapter {
    List<Info> list;
    Context context;

    public RvListInfoAdapter(Context context, List<Info> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.infolist_item_rv, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ListViewHolder holder = (ListViewHolder) viewHolder;
        Info info = list.get(position);
        Picasso.with(context).load(info.getImg()).into(holder.desIv);
        holder.titleTv.setText(info.getTitle());
        holder.desTv.setText(info.getDescription());
        holder.timeTv.setText(AppConstants.SDF.format(info.getTime()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView desIv;
        TextView titleTv;
        TextView desTv;
        TextView timeTv;

        public ListViewHolder(View itemView) {
            super(itemView);
            desIv = (ImageView) itemView.findViewById(R.id.des_iv);
            titleTv = (TextView) itemView.findViewById(R.id.title_tv);
            desTv = (TextView) itemView.findViewById(R.id.des_tv);
            timeTv = (TextView) itemView.findViewById(R.id.time_tv);
        }
    }
}
