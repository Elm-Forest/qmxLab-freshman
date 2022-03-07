package com.qmxkaifa.controller.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.qmxkaifa.entity.Admin;
import com.qmxkaifa.service.admin.*;

@WebServlet("/admin/adminRegister")
public class AdminRegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String adminUserName = req.getParameter("adminUserName");
        String adminPWD = req.getParameter("adminPWD");
        String method = req.getParameter("method");
        System.out.println("管理员注册:\nadminName : " + adminUserName + "\nadminPWD : " + adminPWD);
        Admin admin = new Admin();
        admin.setAdminUserName(adminUserName);
        admin.setAdminPWD(adminPWD);
        if (adminUserName.equals("")) {
            resp.getWriter().write("用户不能为空");
            return;
        }
        if (new AdminServiceImpl().isExistAdmin(adminUserName)) {
            resp.getWriter().write("用户已存在");
        } else {
            if (method.equals("Register")) {
                if (adminPWD == null || adminPWD.equals("")) {
                    resp.getWriter().write("密码不能为空");
                } else {
                    if (adminPWD.length() < 6 || adminPWD.length() > 20) {
                        resp.getWriter().write("密码长度应当在6位且20位之间");
                    } else {
                        new AdminServiceImpl().addAdmin(admin);
                        resp.getWriter().write("注册成功！");
                    }
                }
            } else {
                resp.getWriter().write("可以注册");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
