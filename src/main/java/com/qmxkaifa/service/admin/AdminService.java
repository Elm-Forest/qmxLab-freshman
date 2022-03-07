package com.qmxkaifa.service.admin;

import com.qmxkaifa.entity.Admin;

import java.sql.SQLException;
import java.util.List;

public interface AdminService {

    Admin getAdmin(String adminUserName);

    boolean addAdmin(Admin admin);

    boolean isExistAdmin(String adminUserName);

    int getAdminCount(String adminUserName);

    List<Admin> getAdminList(String userName, int currentPageNum, int pageSize);

    boolean deleteAdminByID(String ID) throws SQLException;

    boolean modifyAdmin(Admin admin) throws Exception;
}
