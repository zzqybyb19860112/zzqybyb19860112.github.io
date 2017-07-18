package com.ybyb.zzq.emaildemo.api;

import android.util.Log;
import com.libmailcore.AuthType;
import com.libmailcore.ConnectionLogger;
import com.libmailcore.ConnectionType;
import com.libmailcore.IMAPSession;
import com.libmailcore.SMTPSession;
import java.io.UnsupportedEncodingException;

/**
 * 作者：周正权 on 2017/6/21
 * 邮箱：zhouzhengquan@shixinyun.com
 */

public class EmailManager {
    public IMAPSession session;

    static private EmailManager theSingleton;

    public static EmailManager getInstance() {
        if (theSingleton == null) {
            theSingleton = new EmailManager();
        }
        return theSingleton;
    }

    private EmailManager() {
        session = new IMAPSession();
        //session.setUsername("928902646@qq.com");
        //session.setPassword("Tulip9289");
        //session.setHostname("imap.qq.com");
        //session.setOAuth2Token("");
        //session.setAuthType(AuthType.AuthTypeXOAuth2);
        //session.setUsername("zzqybyb@sina.com");
        //session.setPassword("zzqybyb19860112@");
        //session.setHostname("imap.sina.com");
        session.setUsername("zzqybyb1986@163.com");
        session.setPassword("ybyb19860112");
        session.setHostname("imap.163.com");
        session.setPort(993);
        //session.setCheckCertificateEnabled(false);
        //session.setAllowsFolderConcurrentAccessEnabled(true);
        session.setConnectionType(ConnectionType.ConnectionTypeTLS);
    }
}
