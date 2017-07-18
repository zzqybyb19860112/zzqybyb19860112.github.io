package com.ybyb.zzq.emaildemo.api;

import com.libmailcore.ConnectionType;
import com.libmailcore.MessageBuilder;
import com.libmailcore.SMTPSession;

/**
 * Created by 2nd on 2017/6/23.
 */

public class EmailBuilder extends MessageBuilder{
    public SMTPSession session;

    static private EmailBuilder theSingleton;

    public static EmailBuilder getInstance() {
        if (theSingleton == null) {
            theSingleton = new EmailBuilder();
        }
        return theSingleton;
    }

    private EmailBuilder() {
        session = new SMTPSession();
        session.setUsername("zzqybyb1986@163.com");
        session.setPassword("ybyb19860112");
        session.setHostname("smtp.163.com");
        //session.setUsername("zzqybyb@sina.com");
        //session.setPassword("zzqybyb19860112@");
        //session.setHostname("smtp.sina.com");
        session.setPort(25);
        session.setConnectionType(ConnectionType.ConnectionTypeStartTLS);
    }
}
