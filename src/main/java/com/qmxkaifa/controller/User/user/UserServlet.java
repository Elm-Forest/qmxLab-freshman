package com.qmxkaifa.controller.User.user;

import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import com.qmxkaifa.entity.User;
import com.qmxkaifa.service.user.UserService;
import com.qmxkaifa.service.user.UserServiceImpl;
import com.qmxkaifa.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.qmxkaifa.controller.Admin.admin.AdminServlet.sendMessage;
import static com.qmxkaifa.util.Constants.USER_SESSION;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        switch (method) {
            case "updatePWD":
                updatePwd(req, resp);
                break;
            case "getUserCount":
                getUserCount(req, resp);
                break;
            case "UserModify":
                userModify(req, resp);
                break;
            case "UserDelete":
                userDelete(req, resp);
                break;
            case "getResult":
                getResult(req, resp);
                break;
            case "getUserInfo":
                getUserInfo(req, resp);
                break;
            case "userModifySelf":
                userModifySelf(req, resp);
                break;
            case "userPwdModify":
                userPwdModify(req, resp);
                break;
            case "checkUserPwd":
                checkUserPwd(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Object attribute = req.getSession().getAttribute(USER_SESSION);
        String newPassword = req.getParameter("newPassword");
        PrintWriter writer = resp.getWriter();
        System.out.println("updateUserPassword" + newPassword);
        boolean flag;
        if (attribute != null && !StringUtils.isNullOrEmpty(newPassword)) {
            UserService userService = new UserServiceImpl();
            User user = (User) attribute;
            flag = userService.updatePwd(user.getStudentID(), newPassword);
            if (flag) {
                req.getSession().removeAttribute(USER_SESSION);
            } else {
                writer.write("服务端异常");
            }
        } else {
            writer.write("新密码格式有误，或登录超时");
        }
        writer.flush();
        writer.close();
    }

    public void getUserCount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String grade = req.getParameter("grade");
        String target = req.getParameter("target");
        UserService userService = new UserServiceImpl();
        int userCount = userService.getUserCount(name, grade, target);
        PrintWriter writer = resp.getWriter();
        System.out.println(userCount);
        writer.println(userCount);
        writer.close();
    }

    public static void userModify(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String ID = req.getParameter("ID");
        String studentID = req.getParameter("studentID");
        String name = req.getParameter("name");
        String grade = req.getParameter("grade");
        String target = req.getParameter("target");
        String major = req.getParameter("major");
        String mail = req.getParameter("mail");
        String phone = req.getParameter("phone");
        User user = new User();
        user.setID(Integer.parseInt(ID));
        user.setStudentID(studentID);
        user.setName(name);
        user.setTarget(target);
        user.setGrade(grade);
        user.setMajor(major);
        user.setMail(mail);
        user.setPhone(phone);
        boolean flag = new UserServiceImpl().modifyUser(user);
        PrintWriter writer = resp.getWriter();
        if (flag) {
            writer.write("success");
            System.out.println("modify success");
        } else {
            writer.write("error");
            System.out.println("modify error");
        }
        writer.close();
    }

    public void userDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Object sessionObject = getSessionObject(req, resp, USER_SESSION);
        if (sessionObject == null) {
            return;
        }
        User user = (User) sessionObject;
        boolean flag = new UserServiceImpl().deleteUserByID(String.valueOf(user.getID()));
        sendMessage(resp, flag);
    }

    public void getResult(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Object sessionObject = getSessionObject(req, resp, USER_SESSION);
        if (sessionObject == null) {
            return;
        }
        User user = (User) sessionObject;
        resp.setContentType("application/json");
        resp.getWriter().write(JSONArray.toJSONString(user));
        System.out.println(user);
    }

    public Object getSessionObject(HttpServletRequest req, HttpServletResponse resp, String session) throws IOException {
        Object attribute = req.getSession().getAttribute(session);
        if (attribute == null) {
            PrintWriter writer = resp.getWriter();
            writer.write("timeout");
            writer.close();
            return null;
        } else {
            return attribute;
        }
    }

    public void getUserInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Object sessionObject = new UserServlet().getSessionObject(req, resp, USER_SESSION);
        if (sessionObject == null) {
            return;
        }
        User user = (User) sessionObject;
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("ID", user.getID() + "");
        resultMap.put("name", user.getName());
        resultMap.put("studentID", user.getStudentID());
        resultMap.put("grade", user.getGrade());
        resultMap.put("target", user.getTarget());
        resultMap.put("major", user.getMajor());
        resultMap.put("mail", user.getMail());
        resultMap.put("phone", user.getPhone());
        resp.setContentType("application/json");
        PrintWriter writer = null;
        try {
            writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(resultMap));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }

    public void userModifySelf(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Object attribute = req.getSession().getAttribute(USER_SESSION);
        PrintWriter writer = resp.getWriter();
        if (attribute == null) {
            writer.write("登录超时或者用户不存在，请重新登录！");
            return;
        }
        int ID = ((User) attribute).getID();
        String studentID = req.getParameter("studentID");
        String name = req.getParameter("name");
        String grade = req.getParameter("grade");
        String target = req.getParameter("target");
        String major = req.getParameter("major");
        String mail = req.getParameter("mail");
        String phone = req.getParameter("phone");
        User user = new User();
        user.setID(ID);
        user.setStudentID(studentID);
        user.setName(name);
        user.setTarget(target);
        user.setGrade(grade);
        user.setMajor(major);
        user.setMail(mail);
        user.setPhone(phone);
        boolean flag = new UserServiceImpl().modifyUser(user);
        if (flag) {
            user.setPassword(((User) attribute).getPassword());
            user.setResult(((User) attribute).getResult());
            user.setScore(((User) attribute).getScore());
            req.getSession().removeAttribute(Constants.USER_SESSION);
            req.getSession().setAttribute(USER_SESSION, user);
            writer.write("success");
            System.out.println("modify success");
        } else {
            writer.write("error");
            System.out.println("modify error");
        }
        writer.close();
    }

    public void userPwdModify(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");
        String newPasswordTwo = req.getParameter("newPasswordTwo");
        User user = (User) req.getSession().getAttribute(USER_SESSION);
        PrintWriter writer = resp.getWriter();
        if (user == null) {
            writer.write("登录超时或者用户不存在，请重新登录！");
            return;
        }
        if (!Objects.equals(oldPassword, user.getPassword())) {
            writer.println("旧密码错误！");
            return;
        }
        if (!newPasswordTwo.equals(newPassword)) {
            writer.println("新旧密码不一致！");
            return;
        }
        boolean flag = new UserServiceImpl().updatePwd(new UserServiceImpl().getUser(user.getStudentID()).getStudentID(), newPassword);
        if (flag) {
            user.setPassword(newPassword);
            req.getSession().removeAttribute(Constants.USER_SESSION);
            req.getSession().setAttribute(USER_SESSION, user);
            writer.write("success");
        } else {
            writer.println("修改失败");
        }
    }

    public void checkUserPwd(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String oldPassword = req.getParameter("oldPassword");
        User user = (User) req.getSession().getAttribute(USER_SESSION);
        PrintWriter writer = resp.getWriter();
        if (user == null) {
            writer.write("登录超时或者用户不存在，请重新登录！");
            return;
        }
        if (!Objects.equals(oldPassword, user.getPassword())) {
            writer.println("旧密码错误！");
            return;
        }
        writer.write("success");
    }
}