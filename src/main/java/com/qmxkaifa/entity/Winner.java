package com.qmxkaifa.entity;

public class Winner {
    private String phone;
    private String name;
    public Winner() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Winner{" +
                "studentID='" + phone + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
