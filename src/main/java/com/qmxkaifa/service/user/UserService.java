package com.qmxkaifa.service.user;

import com.qmxkaifa.entity.User;

import java.util.List;

public interface UserService {
    //用户登录
    User getUser(String userCode);

    //用户注册
    boolean addUser(User user);

    //检查用户是否存在
    boolean isExistUser(User user);

    //修改密码
    boolean updatePwd(String studentID, String password);

    //获取用户总数
    int getUserCount(String name, String grade, String target);

    //获取用户列表
    List<User> getUserList(String userName, String grade, String target, int currentPageNum, int pageSize);

    //根据ID删除用户
    boolean deleteUserByID(String ID);

    boolean modifyUser(User user);

    boolean setUserScore(int score, int id);

    boolean setUserResult(String result, int id);
}
