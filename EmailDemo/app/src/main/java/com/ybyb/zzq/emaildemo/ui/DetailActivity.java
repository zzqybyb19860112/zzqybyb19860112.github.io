package com.ybyb.zzq.emaildemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import com.libmailcore.IMAPMessage;
import com.libmailcore.IMAPMessageRenderingOperation;
import com.libmailcore.MailException;
import com.libmailcore.OperationCallback;
import com.ybyb.zzq.emaildemo.R;
import com.ybyb.zzq.emaildemo.api.EmailManager;

public class DetailActivity extends AppCompatActivity implements OperationCallback {
    WebView mDetailWv;
    private IMAPMessageRenderingOperation renderingOp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mDetailWv = (WebView) findViewById(R.id.detail_wv);
        IMAPMessage message= (IMAPMessage) getIntent().getSerializableExtra("data");
        renderingOp = EmailManager.getInstance().session.htmlRenderingOperation(message, "INBOX");
        renderingOp.start(this);
    }

    @Override
    public void succeeded() {
        String html = renderingOp.result();
        mDetailWv.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
    }

    @Override
    public void failed(MailException e) {

    }
}
