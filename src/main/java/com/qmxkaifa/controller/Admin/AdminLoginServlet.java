package com.qmxkaifa.controller.Admin;

import com.qmxkaifa.entity.Admin;
import com.qmxkaifa.service.admin.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.qmxkaifa.util.Constants.ADMIN_SESSION;

@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("adminUserName");
        String userPWD = req.getParameter("adminPWD");
        AdminService adminService = new AdminServiceImpl();
        PrintWriter writer = resp.getWriter();
        System.out.println(userName+"||"+userPWD);
        if (adminService.isExistAdmin(userName)) {
            if (userPWD.equals(new AdminServiceImpl().getAdmin(userName).getAdminPWD())) {
                Admin admin = adminService.getAdmin(userName);
                if (admin.getRights()!=1){
                    writer.write("当前账号已被撤销管理员权限,联系其他管理者激活");
                    return;
                }
                req.getSession().setAttribute(ADMIN_SESSION, admin);
                writer.write("success");
            } else {
                writer.write("密码错误");
            }
        } else {
            writer.write("用户不存在");
        }
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}