package com.qmxkaifa.dao.user;

import com.qmxkaifa.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    User getUser(Connection connection, String ID);

    int addUser(Connection connection, User user) throws Exception;

    int updatePwd(Connection connection, String studentID, String password);

    User isExistUser(Connection connection, String studentID);

    int getUserCount(Connection connection, String userName, String target, String grade) throws SQLException;

    List<User> getUserList(Connection connection, String userName, String grade, String target, int currentPageNum, int pageSize) throws SQLException;

    int deleteUserByID(Connection connection, String ID) throws SQLException;

    int updateUserID(Connection connection, String ID) throws SQLException;

    int modifyUser(Connection connection, User user) throws SQLException;

    int setUserScore(Connection connection, int score, int id) throws SQLException;

    int setUserResult(Connection connection, String result, int id) throws SQLException;


}
