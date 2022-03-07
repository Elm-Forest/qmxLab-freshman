package com.qmxkaifa.controller.Winner;

import com.alibaba.fastjson.JSONArray;
import com.qmxkaifa.entity.Winner;
import com.qmxkaifa.service.winner.WinnerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/winner")
public class WinnerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Winner> list = new WinnerServiceImpl().getUserList();
        resp.setContentType("application/json");
        resp.getWriter().println(JSONArray.toJSONString(list));
    }
}