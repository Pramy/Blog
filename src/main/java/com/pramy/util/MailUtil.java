package com.pramy.util;

import java.util.Date;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtil
{
    private static final String myEmailAccount = "pramy_ccl@163.com";
    private static final String myEmailPassword = "19940118liu";
    private static final String myEmailSMTPHost = "smtp.163.com";

    public static void sendMail(String receiveMailAccount, String userName, String content)
    {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", "smtp.163.com");
        props.setProperty("mail.smtp.auth", "true");
        try
        {
            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);

            MimeMessage message = createMimeMessage(session, userName, receiveMailAccount, content);

            Transport transport = session.getTransport();

            transport.connect("pramy_ccl@163.com", "19940118liu");

            transport.sendMessage(message, message.getAllRecipients());

            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MimeMessage createMimeMessage(Session session, String userName, String receiveMailAccount, String content)
            throws Exception
    {
        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress("pramy_ccl@163.com", "部落格", "UTF-8"));

        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMailAccount, userName, "UTF-8"));

        message.setSubject("验证消息：" + new Date(), "UTF-8");

        if (content == null) {
            content = "";
        }

        message.setContent(content, "text/html;charset=UTF-8");

        message.setSentDate(new Date());

        message.saveChanges();

        return message;
    }

    public void setQQMailProp(Properties props)
    {
        String smtpPort = "465";
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", "465");
    }
}