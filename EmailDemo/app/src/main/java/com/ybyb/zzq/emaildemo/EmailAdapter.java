package com.ybyb.zzq.emaildemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.libmailcore.IMAPMessage;
import com.ybyb.zzq.emaildemo.ui.DetailActivity;
import java.util.List;

/**
 * 作者：周正权 on 2017/6/21
 * 邮箱：zhouzhengquan@shixinyun.com
 */

public class EmailAdapter extends RecyclerView.Adapter {
    Context context;
    List<IMAPMessage> messages;

    public EmailAdapter(Context context, List<IMAPMessage> messages) {
        this.context = context;
        this.messages = messages;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.item_rv,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder= (MyViewHolder) holder;
        final IMAPMessage message=messages.get(position);
        viewHolder.messageTv.setText(""+(position+1));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailActivity.class);
                intent.putExtra("data",message);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        TextView messageTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            messageTv = (TextView) itemView.findViewById(R.id.message_tv);
        }
    }
}
