package com.qmxkaifa.controller.Admin.admin;

import com.alibaba.fastjson.JSONArray;
import com.qmxkaifa.controller.User.user.UserServlet;
import com.qmxkaifa.entity.Admin;
import com.qmxkaifa.entity.User;
import com.qmxkaifa.service.admin.AdminServiceImpl;
import com.qmxkaifa.service.user.UserService;
import com.qmxkaifa.service.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static com.qmxkaifa.util.Constants.ADMIN_SESSION;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        switch (method) {
            case "updatePWD":
                break;
            case "getUserCount":
                getUserCount(req, resp);
                break;
            case "getUserList":
                getUserList(req, resp);
                break;
            case "deleteUserByID":
                deleteUserByID(req, resp);
                break;
            case "adminModify":
                adminModify(req, resp);
                break;
            case "UserModify":
                UserServlet.userModify(req, resp);
                break;
            case "setUserScore":
                setUserScore(req, resp);
                break;
            case "getAdminList":
                getAdminList(req, resp);
                break;
            case "setUserResult":
                setUserResult(req, resp);
                break;
            case "getAdminName":
                getAdminName(req, resp);
                break;
            case "UserDelete":
                UserDelete(req, resp);
                break;
            case "getAdminCount":
                getAdminCount(req, resp);
                break;
            case "adminModifySelf":
                adminModifySelf(req, resp);
                break;
            case "getAdmin":
                getAdmin(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void getUserCount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String grade = req.getParameter("grade");
        String target = req.getParameter("target");
        UserService userService = new UserServiceImpl();
        int count = userService.getUserCount(name, grade, target);
        resp.getWriter().write(count + "");
    }

    public void getUserList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String grade = req.getParameter("grade");
        String target = req.getParameter("target");
        int currentPageNum = Integer.parseInt(req.getParameter("currentPageNum"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        UserService userService = new UserServiceImpl();
        List<User> list = userService.getUserList(name, grade, target, currentPageNum, pageSize);
        resp.setContentType("application/json");
        resp.getWriter().println(JSONArray.toJSONString(list));
    }

    public void deleteUserByID(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("ID");
        UserService userService = new UserServiceImpl();
        boolean flag = userService.deleteUserByID(id);
        sendMessage(resp, flag);
    }

    public void adminModify(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String adminUserName = req.getParameter("adminUserName");
        String adminPWD = req.getParameter("adminPWD");
        String rights = req.getParameter("rights");
        String adminID = req.getParameter("adminID");
        Admin admin = new Admin();
        admin.setAdminUserName(adminUserName);
        admin.setAdminPWD(adminPWD);
        admin.setRights(Integer.parseInt(rights));
        admin.setAdminID(Integer.parseInt(adminID));
        AdminServiceImpl adminService = new AdminServiceImpl();
        boolean flag = adminService.modifyAdmin(admin);
        sendMessage(resp, flag);
    }

    public void adminModifySelf(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String adminUserName = req.getParameter("adminUserName");
        String adminPWD = req.getParameter("adminPWD");
        Admin admin = new Admin();
        admin.setAdminUserName(adminUserName);
        admin.setAdminPWD(adminPWD);
        admin.setAdminID(((Admin) req.getSession().getAttribute(ADMIN_SESSION)).getAdminID());
        admin.setRights(1);
        AdminServiceImpl adminService = new AdminServiceImpl();
        boolean flag = adminService.modifyAdmin(admin);
        sendMessage(resp, flag);
    }

    public void getAdminCount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("adminUserName");
        AdminServiceImpl adminService = new AdminServiceImpl();
        int adminCount = adminService.getAdminCount(name);
        resp.getWriter().println(adminCount);
    }

    public void getAdminList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("adminUserName");
        int currentPageNum = Integer.parseInt(req.getParameter("currentPageNum"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        AdminServiceImpl adminService = new AdminServiceImpl();
        List<Admin> list = adminService.getAdminList(name, currentPageNum, pageSize);
        resp.setContentType("application/json");
        resp.getWriter().println(JSONArray.toJSONString(list));
    }

    public void setUserScore(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String score = req.getParameter("score");
        String id = req.getParameter("ID");
        UserService userService = new UserServiceImpl();
        boolean flag = userService.setUserScore(Integer.parseInt(score), Integer.parseInt(id));
        sendMessage(resp, flag);
    }

    public void setUserResult(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String score = req.getParameter("result");
        String id = req.getParameter("ID");
        UserService userService = new UserServiceImpl();
        boolean flag = userService.setUserResult(score, Integer.parseInt(id));
        sendMessage(resp, flag);
    }

    public void getAdminName(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Object attribute = req.getSession().getAttribute(ADMIN_SESSION);
        if (attribute == null) {
            resp.getWriter().write("会话已过期，请重新登录");
            return;
        }
        Admin admin = (Admin) attribute;
        resp.getWriter().write(admin.getAdminUserName());
    }

    public void getAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Object attribute = req.getSession().getAttribute(ADMIN_SESSION);
        if (attribute == null) {
            resp.getWriter().write("会话已过期，请重新登录");
            return;
        }
        Admin admin = (Admin) attribute;
        resp.setContentType("application/json");
        resp.getWriter().println(JSONArray.toJSONString(admin));
    }

    public static void sendMessage(HttpServletResponse resp, boolean flag) throws IOException {
        PrintWriter writer = resp.getWriter();
        if (flag) {
            writer.write("success");
        } else {
            writer.write("error");
        }
        writer.flush();
        writer.close();
    }

    public void UserDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        boolean flag = new UserServiceImpl().deleteUserByID(id);
        if (flag) {
            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("error");
        }
    }
}