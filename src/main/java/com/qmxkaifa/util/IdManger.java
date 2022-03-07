package com.qmxkaifa.util;

public class IdManger {
    public static int createUserID() {
        return new com.qmxkaifa.service.user.UserServiceImpl().getUserCount(null, null,null) + 1;
    }

    public static int createAdminID() {
        return new com.qmxkaifa.service.admin.AdminServiceImpl().getAdminCount(null) + 1;
    }
}