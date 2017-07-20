package com.example.zzqybyb1986.handlerdemo.typeC;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.zzqybyb1986.handlerdemo.R;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    private MyHandler mHandler=new MyHandler(this);
    private TextView mTv;
    private static final int HANDLE_DATA=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv= (TextView) findViewById(R.id.tv);
        loadData();
    }

    private void loadData() {
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               mHandler.sendEmptyMessage(HANDLE_DATA);
           }
       },6000);
    }

    private static class MyHandler extends Handler{
        private WeakReference<Context> reference;
        public MyHandler(Context context){
            reference=new WeakReference<Context>(context);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==HANDLE_DATA) {
                MainActivity activity = (MainActivity) reference.get();
                if (activity != null) {
                    activity.mTv.setText("测试WeakReference引用成功");
                }
            }
        }
    }
}
