package com.qmxkaifa.service.admin;

import com.qmxkaifa.dao.BaseDao;
import com.qmxkaifa.dao.admin.AdminDao;
import com.qmxkaifa.dao.admin.AdminDaoImpl;
import com.qmxkaifa.entity.Admin;
import com.qmxkaifa.entity.User;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AdminServiceImpl implements AdminService {

    private final AdminDao adminDao;

    public AdminServiceImpl() {
        adminDao = new AdminDaoImpl();
    }

    @Override
    public Admin getAdmin(String adminUserName) {
        Connection connection = null;
        Admin admin = null;
        try {
            connection = BaseDao.getConnection();
            admin = adminDao.getAdmin(connection, adminUserName);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return admin;
    }

    @Override
    public boolean addAdmin(Admin admin) {
        if (isExistAdmin(admin.getAdminUserName())) {
            return false;
        }
        boolean flag = false;
        Connection connection = null;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            int updateRows = adminDao.addAdmin(connection, admin);
            connection.commit();
            if (updateRows > 0) {
                flag = true;
                System.out.println("add success!");
            } else {
                System.out.println("add failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    @Override
    public boolean isExistAdmin(String adminUserName) {
        Connection connection = null;
        Admin adminFlag = null;
        try {
            connection = BaseDao.getConnection();
            adminFlag = adminDao.isExistAdmin(connection, adminUserName);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return adminFlag != null;
    }

    @Override
    public int getAdminCount(String adminUserName) {
        Connection connection = null;
        int num = 0;
        try {
            connection = BaseDao.getConnection();
            num = adminDao.getAdminCount(connection, adminUserName);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return num;
    }

    @Override
    public List<Admin> getAdminList(String userName, int currentPageNum, int pageSize) {
        Connection connection = null;
        List<Admin> userList = null;
        System.out.println("userName ---- > " + userName);
        System.out.println("currentPageNum ---- > " + currentPageNum);
        System.out.println("pageSize ---- > " + pageSize);
        try {
            connection = BaseDao.getConnection();
            userList = adminDao.getAdminList(connection, userName, currentPageNum, pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return userList;
    }

    @Override
    public boolean deleteAdminByID(String ID) {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            int deleteRow = adminDao.deleteAdminByID(connection, ID);
            int updateRow = adminDao.updateAdminID(connection, ID);
            if (deleteRow > 0 && updateRow >= 0) {
                connection.commit();
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    @Override
    public boolean modifyAdmin(Admin admin) {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            if (adminDao.modifyAdmin(connection, admin) > 0) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    @Test
    public void test() {
        AdminServiceImpl userService = new AdminServiceImpl();
        System.out.println(userService.deleteAdminByID("13"));
    }
}
