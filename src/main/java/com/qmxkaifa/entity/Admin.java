package com.qmxkaifa.entity;

public class Admin {
    private int adminID;
    private String adminUserName;
    private String adminPWD;
    private int rights;

    public Admin() {
    }

    public int getRights() {
        return rights;
    }

    public void setRights(int rights) {
        this.rights = rights;
    }

    public Admin(int adminID, String adminUserName, String adminPWD) {
        this.adminID = adminID;
        this.adminUserName = adminUserName;
        this.adminPWD = adminPWD;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    public String getAdminPWD() {
        return adminPWD;
    }

    public void setAdminPWD(String adminPWD) {
        this.adminPWD = adminPWD;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminID=" + adminID +
                ", adminUserName='" + adminUserName + '\'' +
                ", adminPWD=" + adminPWD +
                '}';
    }
}
