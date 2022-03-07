package com.qmxkaifa.entity;

public class User {
    private int ID;
    private String studentID;
    private String name;
    private String grade;
    private String target;
    private String major;
    private String password;
    private String mail;
    private String phone;
    private String result;
    private int score;
    private int rights;

    public User() {
    }

    public User(String studentID, String password) {
        this.studentID = studentID;
        this.password = password;
    }

    public User(String studentID, String name, String grade, String target, String major, String password, String mail, String phone) {
        this.studentID = studentID;
        this.name = name;
        this.grade = grade;
        this.target = target;
        this.major = major;
        this.password = password;
        this.mail = mail;
        this.phone = phone;
    }

    public User(int ID, String studentID, String name, String grade, String target, String major, String password, String mail, String phone) {
        this.ID = ID;
        this.studentID = studentID;
        this.name = name;
        this.grade = grade;
        this.target = target;
        this.major = major;
        this.password = password;
        this.mail = mail;
        this.phone = phone;
    }

    public int getRights() {
        return rights;
    }

    public void setRights(int rights) {
        this.rights = rights;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", studentID='" + studentID + '\'' +
                ", name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                ", target='" + target + '\'' +
                ", major='" + major + '\'' +
                ", password='" + password + '\'' +
                ", mail='" + mail + '\'' +
                ", phone='" + phone + '\'' +
                ", result='" + result + '\'' +
                ", score=" + score +
                ", rights=" + rights +
                '}';
    }
}
