package com.ybyb.zzq.emaildemo.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.libmailcore.Address;
import com.libmailcore.IMAPFetchFoldersOperation;
import com.libmailcore.IMAPFetchMessagesOperation;
import com.libmailcore.IMAPFolder;
import com.libmailcore.IMAPFolderFlags;
import com.libmailcore.IMAPFolderInfo;
import com.libmailcore.IMAPFolderInfoOperation;
import com.libmailcore.IMAPMessage;
import com.libmailcore.IMAPMessagesRequestKind;
import com.libmailcore.IMAPOperation;
import com.libmailcore.IMAPSession;
import com.libmailcore.IndexSet;
import com.libmailcore.MailException;
import com.libmailcore.MessageHeader;
import com.libmailcore.OperationCallback;
import com.libmailcore.Range;
import com.libmailcore.SMTPLoginOperation;
import com.libmailcore.SMTPSendWithDataOperation;
import com.ybyb.zzq.emaildemo.EmailAdapter;
import com.ybyb.zzq.emaildemo.R;
import com.ybyb.zzq.emaildemo.api.EmailBuilder;
import com.ybyb.zzq.emaildemo.api.EmailManager;
import java.util.ArrayList;
import java.util.List;

import static com.libmailcore.IMAPMessagesRequestKind.IMAPMessagesRequestKindExtraHeaders;
import static com.libmailcore.IMAPMessagesRequestKind.IMAPMessagesRequestKindFlags;
import static com.libmailcore.IMAPMessagesRequestKind.IMAPMessagesRequestKindFullHeaders;
import static com.libmailcore.IMAPMessagesRequestKind.IMAPMessagesRequestKindHeaderSubject;
import static com.libmailcore.IMAPMessagesRequestKind.IMAPMessagesRequestKindHeaders;
import static com.libmailcore.IMAPMessagesRequestKind.IMAPMessagesRequestKindInternalDate;
import static com.libmailcore.IMAPMessagesRequestKind.IMAPMessagesRequestKindStructure;

public class MainActivity extends AppCompatActivity implements OperationCallback, View.OnClickListener {
    private IMAPFetchMessagesOperation fetchMessagesOp;
    private List<IMAPMessage> messages;
    private RecyclerView messageRv;
    private ProgressDialog waitingDialog;
    private Button sendBtn;
    private Button acceptBtn;
    private Button openFolderBtn;
    private EditText inputEdt;

    private SMTPLoginOperation loginOp;
    private SMTPSendWithDataOperation sendOp;
    private EmailBuilder builder;
    private IMAPSession imapSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initListener() {
        sendBtn.setOnClickListener(this);
        acceptBtn.setOnClickListener(this);
        openFolderBtn.setOnClickListener(this);
    }

    private void initView() {
        inputEdt = (EditText) findViewById(R.id.send_email_address);
        sendBtn = (Button) findViewById(R.id.send_email);
        acceptBtn = (Button) findViewById(R.id.accept_email);
        openFolderBtn = (Button) findViewById(R.id.open_folder);
        messageRv = (RecyclerView) findViewById(R.id.email_message_rv);
    }

    private void initData() {
        builder = EmailBuilder.getInstance();
        // 经过一次测试不用下面登陆直接设置发送邮件也可以发送邮件成功
        loginOp = (SMTPLoginOperation) builder.session.loginOperation();
        loginOp.start(new OperationCallback() {
            @Override
            public void succeeded() {
                Log.e("=====login==", "succeeded: ");
                MessageHeader header = new MessageHeader();
                Address sendAds = new Address();
                sendAds.setMailbox("928902646@qq.com");// 发送人

                List<Address> adsList = new ArrayList<>();
                Address acceptAds = new Address();
                acceptAds.setMailbox(inputEdt.getText().toString().trim());// 收件人
                adsList.add(acceptAds);

                header.setFrom(sendAds);
                header.setTo(adsList);
                header.setSubject("测试邮件"); // 邮件标题
                builder.setHeader(header);
                builder.setTextBody("hello boy,今天天气真好！"); // 邮件正文

                sendOp = (SMTPSendWithDataOperation) builder.session.sendMessageOperation(builder.data());
                sendOp.start(new OperationCallback() {
                    @Override
                    public void succeeded() {
                        Log.e("=====send==", "succeeded: ");
                    }

                    @Override
                    public void failed(MailException e) {
                        Log.e("=====send==", "failed: " + e.getMessage());
                    }
                });
            }

            @Override
            public void failed(MailException e) {
                Log.e("=====login===", "failed: " + e.getMessage());
            }
        });
    }

    private void showWaitingDialog() {
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle("正在从网络获取数据");
        waitingDialog.setMessage("等待中...");
        waitingDialog.setIndeterminate(true);
        waitingDialog.setCancelable(false);
        waitingDialog.show();
    }

    @Override
    public void succeeded() {
        waitingDialog.dismiss();
        messages = fetchMessagesOp.messages();
        IMAPMessage msg = messages.get(0);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        messageRv.setLayoutManager(manager);
        EmailAdapter adapter = new EmailAdapter(this, messages);
        messageRv.setAdapter(adapter);
        Log.i("tag", "ok");
        Log.i("tag", "name----" + msg.toString());
    }

    @Override
    public void failed(MailException e) {
        waitingDialog.dismiss();
        Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        Log.i("tag", "error" + e.toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.open_folder:
                openFolder();
                //openFolders();
                break;
            case R.id.send_email:
                initData();
                break;
            case R.id.accept_email:
                fetchMessagesOp = EmailManager.getInstance().session.fetchMessagesByNumberOperation("INBOX", IMAPMessagesRequestKindHeaders | IMAPMessagesRequestKindStructure,
                                                                                                    IndexSet.indexSetWithRange(new Range(1, 10)));
                fetchMessagesOp.start(this);
                showWaitingDialog();
                break;
        }
    }

    private void openFolders() {
        IMAPSession mGetSession = EmailManager.getInstance().session;
        final IMAPFetchMessagesOperation mFetchMessagesOp = mGetSession.fetchMessagesByNumberOperation("", IMAPMessagesRequestKind.IMAPMessagesRequestKindFlags | IMAPMessagesRequestKindStructure | IMAPMessagesRequestKindInternalDate | IMAPMessagesRequestKindFullHeaders | IMAPMessagesRequestKindHeaderSubject | IMAPMessagesRequestKindExtraHeaders, IndexSet.indexSetWithRange(new Range(1, Range.RangeMax)));
        mFetchMessagesOp.start(new OperationCallback() {
            @Override
            public void succeeded() {
                List<IMAPMessage> messages = mFetchMessagesOp.messages();
                Log.e("tag","result----------->"+messages.size());
            }

            @Override
            public void failed(MailException e) {
                Log.e("tag","error----------->"+e.getMessage());
            }
        });
    }

    private void openFolder() {
        imapSession = EmailManager.getInstance().session;
        IMAPOperation op = imapSession.checkAccountOperation();
        op.start(new OperationCallback() {
            @Override
            public void succeeded() {
                Log.e("tag", "1");
                final IMAPFetchFoldersOperation oop = imapSession.fetchAllFoldersOperation();
                oop.start(new OperationCallback() {
                    @Override
                    public void succeeded() {
                        Log.e("tag", "2");
                        List<IMAPFolder> list = oop.folders();
                        nextStep(list);
                    }

                    @Override
                    public void failed(MailException e) {
                        Log.e("tag", "2--->" + e.getMessage());
                    }
                });
            }

            @Override
            public void failed(MailException e) {
                Log.e("tag", "1--->" + e.getMessage());
            }
        });
    }

    private void nextStep(List<IMAPFolder> list) {
        for (IMAPFolder f : list) {
            if (f.flags() == IMAPFolderFlags.IMAPFolderFlagSentMail) {
                //163邮箱：1、收件箱；2、草稿箱；3、发件箱；4、已删除；5、垃圾邮件；6、病毒文件夹；7、订阅邮件
                //163邮箱：1、INBOX；2、&g0l6P3ux-；3、&XfJT0ZAB-；4、&XfJSIJZk-；5、&V4NXPpCuTvY-；6、&dcVr0mWHTvZZOQ-；7、&i6KWBZCuTvY-
                //qq邮箱：1、其他文件夹；2、INBOX；3、Sent Messages；4、Drafts；5、Deleted Messages；6、Junk；7、&UXZO1mWHTvZZOQ-/QQ&kK5O9ouilgU-；8、&UXZO1mWHTvZZOQ-/&kK5O9l9SaGM-
                //qq邮箱：1、其他文件夹；2、INBOX；3、Sent Messages；4、Drafts；5、Deleted Messages；6、Junk；7、其他文件夹+qq邮件订阅；8、其他文件夹+文件归档,
                List<String> ls = imapSession.defaultNamespace().componentsFromPath(f.path());
                //final String path = imapSession.defaultNamespace().pathForComponentsAndPrefix(ls, imapSession.defaultNamespace().mainPrefix());
                //final String folderName = "Drafts";
                final String folderName = ls.get(0);
                final int requestKind = (IMAPMessagesRequestKindHeaders
                    | IMAPMessagesRequestKindStructure
                    | IMAPMessagesRequestKindInternalDate
                    | IMAPMessagesRequestKindHeaderSubject
                    | IMAPMessagesRequestKindFlags);
                final IMAPFolderInfoOperation op = imapSession.folderInfoOperation("Sent Messages");
                op.start(new OperationCallback() {
                    @Override
                    public void succeeded() {
                        Log.e("tag", "3");
                        IMAPFolderInfo info = op.info();
                        int count = info.messageCount();
                        IndexSet numbers = IndexSet.indexSetWithRange(new Range(1, count));
                        final IMAPFetchMessagesOperation oop = imapSession.fetchMessagesByNumberOperation("Sent Messages", requestKind, numbers);
                        oop.start(new OperationCallback() {
                            @Override
                            public void succeeded() {
                                Log.e("tag", "4");
                                List<IMAPMessage> result = oop.messages();
                                Log.e("tag", "ok---->" + result.size());
                            }

                            @Override
                            public void failed(MailException e) {
                                Log.e("tag", "4--->" + e.getMessage());
                            }
                        });
                    }

                    @Override
                    public void failed(MailException e) {
                        Log.e("tag", "3--->" + e.getMessage());
                    }
                });
            }
        }
    }
}
