package com.qmxkaifa.dao.admin;

import com.qmxkaifa.entity.Admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface AdminDao {
    Admin getAdmin(Connection connection, String adminUserName);

    int addAdmin(Connection connection, Admin admin) throws Exception;

    Admin isExistAdmin(Connection connection, String adminUserName);

    int getAdminCount(Connection connection, String userName) throws SQLException;

    List<Admin> getAdminList(Connection connection, String userName, int currentPageNum, int pageSize) throws SQLException;

    int deleteAdminByID(Connection connection, String ID) throws SQLException;

    int updateAdminID(Connection connection, String ID) throws SQLException;

    int modifyAdmin(Connection connection, Admin admin) throws Exception;

}
