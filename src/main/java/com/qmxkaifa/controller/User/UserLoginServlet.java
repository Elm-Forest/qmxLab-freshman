package com.qmxkaifa.controller.User;

import com.qmxkaifa.entity.User;
import com.qmxkaifa.service.user.UserService;
import com.qmxkaifa.service.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.qmxkaifa.util.Constants.USER_SESSION;

@WebServlet("/userLogin")
public class UserLoginServlet extends HttpServlet {
    private String mailCode = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String method = req.getParameter("method");
        System.out.println(mailCode);
        switch (method) {
            case "UserLogin":
                UserLogin(req, resp);
                return;
            case "PwdBack":
                PwdBack(req, resp);
                return;
            case "emailSend":
                sendEmail(req, resp);
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

    protected void PwdBack(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String mail = req.getParameter("mail");
        String password = req.getParameter("password");
        String mailCode2 = req.getParameter("mailCode");
        PrintWriter writer = resp.getWriter();
        if (!mailCode.equals(mailCode2)) {
            writer.write("error");
            writer.close();
            return;
        }
        User user = new UserServiceImpl().getUser(mail);
        boolean flag = new UserServiceImpl().updatePwd(user.getStudentID(), password);
        if (flag) {
            writer.write("success");
        } else {
            writer.write("error");
            mailCode = null;
        }
        writer.close();
    }

    protected void UserLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String studentID = req.getParameter("studentID");
        String userPWD = req.getParameter("password");
        UserService userService = new UserServiceImpl();
        if (userService.isExistUser(new User(studentID, userPWD))) {
            if (userPWD.equals(userService.getUser(studentID).getPassword())) {
                User user = userService.getUser(studentID);
                req.getSession().setAttribute(USER_SESSION, user);
                resp.getWriter().write("success");
            } else {
                resp.getWriter().write("密码错误");
            }
        } else {
            resp.getWriter().write("用户不存在");
        }
    }

    protected void emailCheck(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userMailCode = req.getParameter("mailCode");
        if (userMailCode.equals(mailCode)) {
            resp.getWriter().write("success");
            System.out.println("ok");
        } else {
            resp.getWriter().write("error");
        }
    }

    protected void sendEmail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String mail = req.getParameter("mail");
        User user = new UserServiceImpl().getUser(mail);
        boolean flag = user != null;
        PrintWriter writer = resp.getWriter();
        if (flag) {
            writer.write("success");
            mailCode = new UserRegisterServlet().emailSend(req, resp);

        } else {
            writer.write("error");
        }
        writer.close();
    }
}

