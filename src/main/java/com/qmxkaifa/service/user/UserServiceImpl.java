package com.qmxkaifa.service.user;

import com.qmxkaifa.dao.BaseDao;
import com.qmxkaifa.dao.user.UserDao;
import com.qmxkaifa.dao.user.UserDaoImpl;
import com.qmxkaifa.entity.User;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    @Override
    public User getUser(String ID) {
        Connection connection = null;
        User user = null;
        try {
            connection = BaseDao.getConnection();
            user = userDao.getUser(connection, ID);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        System.out.println("调用UserService");
        return user;
    }

    @Override
    public boolean addUser(User user) {
        boolean flag = false;
        Connection connection = null;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            int updateRows = userDao.addUser(connection, user);
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
                System.out.println("事务失败，rollback");
                if (connection != null) {
                    connection.rollback();
                } else {
                    System.out.println("User.addUser.connection为空，检查连接配置是否正常");
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
    public boolean isExistUser(User user) {
        Connection connection = null;
        User userFlag = null;
        try {
            connection = BaseDao.getConnection();
            userFlag = userDao.isExistUser(connection, user.getStudentID());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        System.out.println("调用UserService.isExistUser");
        return userFlag != null;
    }

    @Override
    public boolean updatePwd(String studentID, String password) {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            if (userDao.updatePwd(connection, studentID, password) > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    @Override
    public int getUserCount(String name, String grade, String target) {
        Connection connection = null;
        int num = 0;
        try {
            connection = BaseDao.getConnection();
            num = userDao.getUserCount(connection, name, target, grade);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return num;
    }

    @Override
    public List<User> getUserList(String userName, String grade, String target, int currentPageNum, int pageSize) {
        Connection connection = null;
        List<User> userList = null;
        System.out.println("userName ---- > " + userName);
        System.out.println("grade ---- > " + grade);
        System.out.println("currentPageNum ---- > " + currentPageNum);
        System.out.println("pageSize ---- > " + pageSize);
        try {
            connection = BaseDao.getConnection();
            userList = userDao.getUserList(connection, userName, grade, target, currentPageNum, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return userList;
    }

    @Override
    public boolean deleteUserByID(String ID) {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            int deleteRow = userDao.deleteUserByID(connection, ID);
            int updateRow = userDao.updateUserID(connection, ID);
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
    public boolean modifyUser(User user) {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            if (userDao.modifyUser(connection, user) > 0) {
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
    public boolean setUserScore(int score, int id) {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            if (userDao.setUserScore(connection, score, id) > 0) {
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
    public boolean setUserResult(String result, int id) {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            if (userDao.setUserResult(connection, result, id) > 0) {
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
        //System.out.println(new UserServiceImpl().getUser("553781523@qq.com"));
        System.out.println(new UserServiceImpl().getUserCount("张", "", "开发"));
        System.out.println(new UserServiceImpl().getUserList("张", "", "开发", 1, 100));
    }
}
