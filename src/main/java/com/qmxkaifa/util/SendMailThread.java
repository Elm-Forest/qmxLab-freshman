package com.qmxkaifa.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.qmxkaifa.util.isMail.isEmail;

public class SendMailThread implements Runnable {
    private HttpServletRequest req;
    private HttpServletResponse resp;
    public static String code = null;
    EmailUtil emailUtil = new EmailUtil();

    public SendMailThread() {
    }

    public SendMailThread(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
    }

    @Override
    public void run() {
        String mail = req.getParameter("mail");
        PrintWriter writer = null;
        try {
            writer = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!isEmail(mail)) {
            if (writer != null) {
                writer.write("error");
            }
            return;
        }
        try {
            emailUtil.sendEmail(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        code = emailUtil.getVCode();
    }

    public String getVCode() {
        return code;
    }
}
