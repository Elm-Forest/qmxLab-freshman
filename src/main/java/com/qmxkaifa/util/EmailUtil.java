package com.qmxkaifa.util;

import com.qmxkaifa.dao.BaseDao;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;


public class EmailUtil {
    private static String fromEmail;  // 发件人账号
    private static String fromEmailPw;  // 发件人密码
    private static Session session;  // 用于创建会话对象
    private String vCode;
    public static EmailUtil instance = new EmailUtil();

    // 获取验证码
    public String getVCode() {
        return vCode;
    }

    public EmailUtil() {
        InputStream resourceAsStream = BaseDao.class.getClassLoader().getResourceAsStream("mail.properties");
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fromEmail = properties.getProperty("mailAddress");
        fromEmailPw = properties.getProperty("mailPassword");
        // 发件邮箱服务器
        String myEmailSMTPHost = properties.getProperty("mailHost");
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");  // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);  // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");  // 需要请求认证

 /*       MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);
*/
        session = Session.getInstance(props);
        /*session.setDebug(true);*/  //debug
    }

    public MimeMessage createMailContent(String toEmail) throws Exception {
        MimeMessage message = new MimeMessage(session);
        // 发件人
        message.setFrom(new InternetAddress(fromEmail, "Java项目测试", "UTF-8"));
        // 收件人
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toEmail));
        // 邮件主题
        message.setSubject("验证码", "UTF-8");
        // 邮件正文
        vCode = VCCodeUtil.verifyCode(6);
        message.setContent("Java项目测试，您的验证码是：" + vCode + "\n若您不知道为什么收到此邮件，请勿回复，非常抱歉打扰您。", "text/html;charset=UTF-8");
        // 设置发件时间
        message.setSentDate(new Date());
        // 保存设置
        message.saveChanges();
        return message;
    }

    //发送邮件
    public void sendEmail(String toEmail) throws Exception {
        Transport transport = session.getTransport();
        transport.connect(fromEmail, fromEmailPw);
        MimeMessage message = createMailContent(toEmail);  // 邮件内容
        transport.sendMessage(message, message.getAllRecipients());
        System.out.println("验证码发送成功！");
        transport.close();
    }
}

