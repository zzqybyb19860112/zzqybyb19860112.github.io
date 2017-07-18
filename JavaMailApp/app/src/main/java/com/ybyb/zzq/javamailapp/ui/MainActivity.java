package com.ybyb.zzq.javamailapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import com.sun.mail.imap.IMAPMessage;
import com.sun.mail.imap.IMAPSSLStore;
import com.sun.mail.pop3.POP3Message;
import com.sun.mail.pop3.POP3SSLStore;
import com.ybyb.zzq.javamailapp.AppConstants;
import com.ybyb.zzq.javamailapp.R;
import com.ybyb.zzq.javamailapp.StringUtil;
import com.ybyb.zzq.javamailapp.manager.MailManager;
import javax.mail.Folder;
import javax.mail.Store;
/**
*主页面
*/
public class MainActivity extends AppCompatActivity {
    private EditText mEmailAccountEt;
    private EditText mEmailPasswordEt;
    private RadioButton mIMAPRb;
    private RadioButton mPOPRb;
    private RadioButton mSMTPRb;
    private Button mConfirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        setListener();
    }

    private void setListener() {
        this.mEmailAccountEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (StringUtil.isEmailLegal(s.toString())) {
                    boolean isEmpty=TextUtils.isEmpty(mEmailPasswordEt.getText().toString());
                    mConfirmBtn.setEnabled(!isEmpty);
                }
            }
        });
        this.mEmailPasswordEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())) {
                    boolean isRight=StringUtil.isEmailLegal(mEmailAccountEt.getText().toString());
                    mConfirmBtn.setEnabled(isRight);
                }
            }
        });

        this.mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToView();
            }
        });
    }

    private void jumpToView() {
        String account = this.mEmailAccountEt.getText().toString();
        String password = this.mEmailPasswordEt.getText().toString();
        int type = 0;
        if (this.mIMAPRb.isChecked()) {
            type = AppConstants.TYPE_IMAP;
        }
        else if (this.mPOPRb.isChecked()) {
            type = AppConstants.TYPE_POP;
        }
        else if (this.mSMTPRb.isChecked()) {
            type = AppConstants.TYPE_STMP;
        }
        Store store = MailManager.getStore(account, password, type);
        if (store == null) {
            Toast.makeText(this, "邮件服务器配置错误，请检查设置！", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this, MailActivity.class);
            if (type == AppConstants.TYPE_IMAP) {
                IMAPSSLStore s = (IMAPSSLStore) store;
                try {
                    Folder inbox = s.getFolder("INBOX");
                    inbox.open(Folder.READ_ONLY);
                    IMAPMessage[] messages = (IMAPMessage[]) inbox.getMessages();
                    intent.putExtra("data", messages);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if (type == AppConstants.TYPE_POP) {
                POP3SSLStore s = (POP3SSLStore) store;
                try {
                    Folder inbox = s.getFolder("INBOX");
                    inbox.open(Folder.READ_ONLY);
                    POP3Message[] messages = (POP3Message[]) inbox.getMessages();
                    intent.putExtra("data", messages);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            startActivity(intent);
        }
    }

    private void bindViews() {
        this.mEmailAccountEt = (EditText) findViewById(R.id.email_account_et);
        this.mEmailPasswordEt = (EditText) findViewById(R.id.email_password_et);
        this.mIMAPRb = (RadioButton) findViewById(R.id.imap_rb);
        this.mPOPRb = (RadioButton) findViewById(R.id.pop_rb);
        this.mSMTPRb = (RadioButton) findViewById(R.id.smtp_rb);
        this.mConfirmBtn = (Button) findViewById(R.id.confirm_btn);
    }
}
