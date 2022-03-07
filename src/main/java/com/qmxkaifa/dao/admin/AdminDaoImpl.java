package com.qmxkaifa.dao.admin;

import com.qmxkaifa.dao.BaseDao;
import com.qmxkaifa.entity.Admin;
import com.qmxkaifa.util.IdManger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {
    @Override
    public Admin getAdmin(Connection connection, String adminUserName) {
        ResultSet resultSet = null;
        Admin admin = null;
        if (connection != null) {
            String sql = "select * from admin where adminUserName=?";
            Object[] params = {adminUserName};
            try {
                resultSet = BaseDao.execute(connection, sql, params, null, null);
                if (resultSet.next()) {
                    admin = new Admin();
                    setAdmin(admin, resultSet);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("AdminDaoImpl.getAdmin Error:" + e.getMessage());
            } finally {
                BaseDao.closeResource(connection, null, resultSet);
            }
        }
        return admin;
    }

    @Override
    public int addAdmin(Connection connection, Admin admin) throws Exception {
        int updateRows = 0;
        if (null != connection) {
            String sql = "insert into admin (adminID,adminUserName,adminPWD) values(?,?,?)";
            Object[] params = {IdManger.createAdminID(), admin.getAdminUserName(), admin.getAdminPWD()};
            updateRows = BaseDao.execute(connection, sql, params, null);
            BaseDao.closeResource(null, null, null);
        }
        return updateRows;
    }

    @Override
    public Admin isExistAdmin(Connection connection, String adminUserName) {
        ResultSet resultSet = null;
        Admin admin = null;
        if (connection != null) {
            String sql = "select * from admin where adminUserName=?";
            Object[] params = {adminUserName};
            try {
                resultSet = BaseDao.execute(connection, sql, params, null, null);
                if (resultSet.next()) {
                    admin = new Admin();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                BaseDao.closeResource(connection, null, resultSet);
            }
        }
        return admin;
    }

    @Override
    public int getAdminCount(Connection connection, String adminUserName) throws SQLException {
        int count = 0;
        if (connection != null) {
            StringBuilder sql = new StringBuilder();
            sql.append("select count(1) as count from admin where 1=1 ");    //存放sql参数
            List<Object> list = new ArrayList<>();
            if (adminUserName != null) {
                sql.append("and admin.adminUserName like ? ");
                list.add("%" + adminUserName + "%");
            }
            count = getCount(connection, count, sql, list);
        }
        return count;
    }

    public static int getCount(Connection connection, int count, StringBuilder sql, List<Object> list) throws SQLException {
        ResultSet resultSet;
        Object[] params = list.toArray();
        System.out.println("sql ----> " + sql);
        resultSet = BaseDao.execute(connection, sql.toString(), params, null, null);
        if (resultSet.next()) {
            count = resultSet.getInt("count");
        }
        BaseDao.closeResource(null, null, resultSet);
        return count;
    }

    @Override
    public List<Admin> getAdminList(Connection connection, String userName, int currentPageNum, int pageSize) throws SQLException {
        ResultSet resultSet;
        List<Admin> userList = new ArrayList<>();
        if (connection != null) {
            StringBuilder sql = new StringBuilder();
            sql.append("select admin.* from admin where 1=1 ");
            List<Object> list = new ArrayList<>();
            if (userName != null) {
                sql.append("and admin.adminUserName like ? ");
                list.add("%" + userName + "%");
            }
            sql.append(" order by adminID ASC limit ?,?");
            int currentIndex = (currentPageNum - 1) * pageSize;
            list.add(currentIndex);
            list.add(pageSize);
            Object[] params = list.toArray();
            System.out.println("admin.sql ----> " + sql);
            resultSet = BaseDao.execute(connection, sql.toString(), params, null, null);
            while (resultSet.next()) {
                Admin admin = new Admin();
                setAdmin(admin, resultSet);
                userList.add(admin);
            }
            BaseDao.closeResource(null, null, resultSet);
        }
        return userList;
    }

    @Override
    public int deleteAdminByID(Connection connection, String ID) throws SQLException {
        int flag = 0;
        if (null != connection) {
            String sql = "delete from admin where adminID=?";
            Object[] params = {ID};
            flag = BaseDao.execute(connection, sql, params, null);
            BaseDao.closeResource(null, null, null);
        }
        return flag;
    }

    @Override
    public int updateAdminID(Connection connection, String ID) throws SQLException {
        int flag = -1;
        if (null != connection) {
            String sql = "update admin set adminID = adminID-1 where adminID > ?";
            Object[] params = {ID};
            flag = BaseDao.execute(connection, sql, params, null);
            BaseDao.closeResource(null, null, null);
        }
        return flag;
    }

    @Override
    public int modifyAdmin(Connection connection, Admin admin) throws Exception {
        int flag = 0;
        if (null != connection) {
            String sql = "update admin set " +
                    "adminUserName=?,adminPWD=?,rights=? where adminID = ? ";
            Object[] params = {admin.getAdminUserName(), admin.getAdminPWD(), admin.getRights(), admin.getAdminID()};
            flag = BaseDao.execute(connection, sql, params, null);
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    protected void setAdmin(Admin admin, ResultSet resultSet) throws SQLException {
        admin.setAdminID(resultSet.getInt("adminID"));
        admin.setAdminUserName(resultSet.getString("adminUserName"));
        admin.setAdminPWD(resultSet.getString("adminPWD"));
        admin.setRights(resultSet.getInt("rights"));
    }
}
