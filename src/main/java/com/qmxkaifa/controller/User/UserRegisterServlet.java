package com.qmxkaifa.controller.User;

import com.qmxkaifa.entity.User;
import com.qmxkaifa.service.user.UserServiceImpl;
import com.qmxkaifa.util.IdManger;
import com.qmxkaifa.util.SendMailThread;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.qmxkaifa.controller.Admin.admin.AdminServlet.sendMessage;

@WebServlet("/userRegister")
public class UserRegisterServlet extends HttpServlet {
    private String mailCode = null;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        System.out.println(mailCode);
        switch (method) {
            case "registerUser":
                registerUser(req, resp);
                return;
            case "isExistUser":
                isExistUser(req, resp);
                return;
            case "emailSend":
                mailCode = emailSend(req, resp);
                return;
            case "emailCheck":
                emailCheck(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void registerUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String studentID = req.getParameter("studentID");
        String name = req.getParameter("name");
        String grade = req.getParameter("grade");
        String target = req.getParameter("target");
        String major = req.getParameter("major");
        String password = req.getParameter("password");
        String mail = req.getParameter("mail");
        String phone = req.getParameter("phone");
        User user = new User();
        user.setStudentID(studentID);
        if (new UserServiceImpl().isExistUser(user)) {
            resp.getWriter().write("用户已存在");
        } else {
            user.setID(IdManger.createUserID());
            user.setName(name);
            user.setTarget(target);
            user.setGrade(grade);
            user.setPassword(password);
            user.setMajor(major);
            user.setMail(mail);
            user.setPhone(phone);
            boolean flag = new UserServiceImpl().addUser(user);
            sendMessage(resp, flag);
        }
    }

    protected void isExistUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentID = req.getParameter("studentID");
        User user = new User();
        user.setStudentID(studentID);
        boolean flag = new UserServiceImpl().isExistUser(user);
        PrintWriter writer = resp.getWriter();
        if (flag) {
            writer.write("error");
        } else {
            writer.write("success");
        }
        writer.close();
    }

    public String emailSend(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Runnable sendMailThread = new SendMailThread(req, resp);
        sendMailThread.run();
        String vCode = new SendMailThread().getVCode();
        System.out.println(vCode);
        return vCode;
    }

    protected void emailCheck(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String userMailCode = req.getParameter("mailCode");
        if (userMailCode.equals(mailCode)){
            registerUser(req,resp);
            System.out.println("ok");
        }else {
            resp.getWriter().write("error");
        }
    }
}
