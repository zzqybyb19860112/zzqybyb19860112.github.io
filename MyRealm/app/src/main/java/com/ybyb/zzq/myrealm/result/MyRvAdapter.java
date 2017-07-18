package com.ybyb.zzq.myrealm.result;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ybyb.zzq.myrealm.MainActivity;
import com.ybyb.zzq.myrealm.R;
import java.util.List;

/**
 * 作者：周正权 on 2017/5/24
 * 邮箱：zhouzhengquan@shixinyun.com
 */

public class MyRvAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<QueryResult> mList;

    public MyRvAdapter(Context mContext, List<QueryResult> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mContext);
        View view=inflater.inflate(R.layout.item_result_rv, parent, false);
        return new MyRvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        MyRvViewHolder holder= (MyRvViewHolder) viewHolder;
        QueryResult result=mList.get(position);
        holder.idTv.setText(result.getId()+"");
        holder.nameTv.setText(result.getName());
        holder.genderTv.setText(result.getGender());
        holder.orderTv.setText(result.getOrder()+"");
        holder.subjectTv1.setText(result.getSubject1());
        holder.scoreTv1.setText(result.getScore1()+"");
        holder.subjectTv2.setText(result.getSubject2());
        holder.scoreTv2.setText(result.getScore2()+"");
        holder.subjectTv3.setText(result.getSubject3());
        holder.scoreTv3.setText(result.getScore3()+"");
        holder.subjectTv4.setText(result.getSubject4());
        holder.scoreTv4.setText(result.getScore4()+"");
        holder.totalTv.setText(result.getTotal()+"");
        holder.averageTv.setText(MainActivity.doubleToString(result.getAverage()));
        TextView [] tvs={holder.scoreTv1,holder.scoreTv2,holder.scoreTv3};
        for (TextView tv:tvs){
          double score=Double.valueOf(tv.getText().toString());
            if (score>=135){
                tv.setTextColor(Color.parseColor("#00ff00"));
            }else if (score>=100){
                tv.setTextColor(Color.parseColor("#0000ff"));
            }else if (score>=80){
                tv.setTextColor(Color.parseColor("#00ffff"));
            }else {
                tv.setTextColor(Color.parseColor("#0000ff"));
            }
        }
        double score=Double.valueOf(holder.scoreTv4.getText().toString());
        if (score>=270){
            holder.scoreTv4.setTextColor(Color.parseColor("#00ff00"));
        }else if (score>=240){
            holder.scoreTv4.setTextColor(Color.parseColor("#0000ff"));
        }else if (score>=160){
            holder.scoreTv4.setTextColor(Color.parseColor("#00ffff"));
        }else {
            holder.scoreTv4.setTextColor(Color.parseColor("#0000ff"));
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    class MyRvViewHolder extends RecyclerView.ViewHolder{
        private TextView idTv;
        private TextView nameTv;
        private TextView genderTv;
        private TextView orderTv;
        private TextView subjectTv1;
        private TextView scoreTv1;
        private TextView subjectTv2;
        private TextView scoreTv2;
        private TextView subjectTv3;
        private TextView scoreTv3;
        private TextView subjectTv4;
        private TextView scoreTv4;
        private TextView totalTv;
        private TextView averageTv;
        public MyRvViewHolder(View itemView) {
            super(itemView);
            idTv= (TextView) itemView.findViewById(R.id.id_tv);
            nameTv= (TextView) itemView.findViewById(R.id.name_tv);
            genderTv= (TextView) itemView.findViewById(R.id.gender_tv);
            orderTv= (TextView) itemView.findViewById(R.id.order_tv);
            subjectTv1= (TextView) itemView.findViewById(R.id.sub_tv1);
            scoreTv1= (TextView) itemView.findViewById(R.id.score_tv1);
            subjectTv2= (TextView) itemView.findViewById(R.id.sub_tv2);
            scoreTv2= (TextView) itemView.findViewById(R.id.score_tv2);
            subjectTv3= (TextView) itemView.findViewById(R.id.sub_tv3);
            scoreTv3= (TextView) itemView.findViewById(R.id.score_tv3);
            subjectTv4= (TextView) itemView.findViewById(R.id.sub_tv4);
            scoreTv4= (TextView) itemView.findViewById(R.id.score_tv4);
            totalTv= (TextView) itemView.findViewById(R.id.total_tv);
            averageTv= (TextView) itemView.findViewById(R.id.average_tv);
        }
    }
}
