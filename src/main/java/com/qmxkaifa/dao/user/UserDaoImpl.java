package com.qmxkaifa.dao.user;

import com.qmxkaifa.dao.BaseDao;
import com.qmxkaifa.dao.admin.AdminDaoImpl;
import com.qmxkaifa.entity.User;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public User getUser(Connection connection, String ID) {
        ResultSet resultSet = null;
        User user = null;
        if (connection != null) {
            String sql1 = "select * from user where ID=?";
            String sql2 = "select * from user where studentID=?";
            String sql3 = "select * from user where mail=?";
            Object[] params = {ID};
            try {
                resultSet = BaseDao.execute(connection, sql2, params, null, null);
                if (resultSet.next()) {
                    user = new User();
                    setUser(user, resultSet);
                    System.out.println("使用studentID来获取User对象");
                    resultSet.close();
                    resultSet = null;
                } else {
                    resultSet = BaseDao.execute(connection, sql1, params, resultSet, null);
                    if (resultSet.next()) {
                        user = new User();
                        setUser(user, resultSet);
                        System.out.println("使用ID来获取User对象");
                    } else {
                        resultSet = BaseDao.execute(connection, sql3, params, resultSet, null);
                        if (resultSet.next()) {
                            user = new User();
                            setUser(user, resultSet);
                            System.out.println("使用mail来获取User对象");
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                BaseDao.closeResource(connection, null, resultSet);
            }
        }
        return user;
    }

    @Override
    public int addUser(Connection connection, User user) throws Exception {
        int updateRows = 0;
        if (null != connection) {
            String sql = "insert into user (ID,studentID,name,grade,target,major,password,mail,phone) values(?,?,?,?,?,?,?,?,?)";
            Object[] params = {user.getID(), user.getStudentID(), user.getName(), user.getGrade(), user.getTarget(), user.getMajor(), user.getPassword(), user.getMail(), user.getPhone()};
            updateRows = BaseDao.execute(connection, sql, params, null);
            BaseDao.closeResource(null, null, null);
        }
        return updateRows;
    }

    @Override
    public int updatePwd(Connection connection, String studentID, String password) {
        int execute = 0;
        try {
            if (connection != null) {
                String sql = "update user set password = ? where studentID = ?";
                Object[] params = {password, studentID};
                execute = BaseDao.execute(connection, sql, params, null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return execute;
    }

    @Override
    public User isExistUser(Connection connection, String ID) {
        ResultSet resultSet = null;
        User user = null;
        if (connection != null) {
            String sql1 = "select * from user where ID=?";
            String sql2 = "select * from user where studentID=?";
            String sql3 = "select * from user where mail=?";
            Object[] params = {ID};
            try {
                resultSet = BaseDao.execute(connection, sql2, params, null, null);
                if (resultSet.next()) {
                    user = new User();
                    System.out.println("使用studentID来获取User对象");
                    resultSet.close();
                    resultSet = null;
                } else {
                    resultSet = BaseDao.execute(connection, sql1, params, resultSet, null);
                    if (resultSet.next()) {
                        user = new User();
                        System.out.println("使用ID来获取User对象");
                    } else {
                        resultSet = BaseDao.execute(connection, sql3, params, resultSet, null);
                        if (resultSet.next()) {
                            user = new User();
                            System.out.println("使用mail来获取User对象");
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                BaseDao.closeResource(connection, null, resultSet);
            }
        }
        return user;
    }

    @Override
    public int getUserCount(Connection connection, String userName, String target, String grade) throws SQLException {
        int count = 0;
        List<Object> list = new ArrayList<>();
        if (connection != null) {
            StringBuilder sql = new StringBuilder();
            sql.append("select count(1) as count from user where 1=1 ");    //存放sql参数
            if (userName != null) {
                sql.append("and user.name like ? ");
                list.add("%" + userName + "%");
            }
            if (grade != null) {
                sql.append("and user.grade like ? ");
                list.add("%" + grade + "%");
            }
            if (target != null) {
                sql.append("and user.target like ? ");
                list.add("%" + target + "%");
            }
            count = AdminDaoImpl.getCount(connection, count, sql, list);
        }
        return count;
    }

    @Override
    public List<User> getUserList(Connection connection, String userName, String grade, String target, int currentPageNum, int pageSize) throws SQLException {
        ResultSet resultSet;
        List<User> userList = new ArrayList<>();
        if (connection != null) {
            StringBuilder sql = new StringBuilder();
            sql.append("select user.* from user where 1=1 ");
            List<Object> list = new ArrayList<>();
            if (userName != null) {
                sql.append("and user.name like ? ");
                list.add("%" + userName + "%");
            }
            if (grade != null) {
                sql.append("and user.grade like ? ");
                list.add("%" + grade + "%");
            }
            if (target != null) {
                sql.append("and user.target like ? ");
                list.add("%" + target + "%");
            }
            sql.append(" order by ID ASC limit ?,?");
            int currentIndex = (currentPageNum - 1) * pageSize;
            list.add(currentIndex);
            list.add(pageSize);
            Object[] params = list.toArray();
            System.out.println("sql ----> " + sql);
            resultSet = BaseDao.execute(connection, sql.toString(), params, null, null);
            while (resultSet.next()) {
                User user = new User();
                setUser(user, resultSet);
                userList.add(user);
            }
            BaseDao.closeResource(null, null, resultSet);
        }
        return userList;
    }

    @Override
    public int deleteUserByID(Connection connection, String ID) throws SQLException {
        int flag = -1;
        if (null != connection) {
            String sql = "delete from user where id=?";
            Object[] params = {ID};
            flag = BaseDao.execute(connection, sql, params, null);
            BaseDao.closeResource(null, null, null);
        }
        return flag;
    }

    @Override
    public int updateUserID(Connection connection, String ID) throws SQLException {
        int flag = 0;
        if (null != connection) {
            String sql = "update user set ID = id-1 where id > ?";
            Object[] params = {ID};
            flag = BaseDao.execute(connection, sql, params, null);
            BaseDao.closeResource(null, null, null);
        }
        return flag;
    }

    @Override
    public int modifyUser(Connection connection, User user) throws SQLException {
        int flag = 0;
        if (null != connection) {
            String sql = "update user set " +
                    "studentID=?,name=?,grade=?,target=?,major=?,mail=?,phone=? " +
                    "where id = ? ";
            Object[] params = {
                    user.getStudentID(), user.getName(), user.getGrade(),
                    user.getTarget(), user.getMajor(), user.getMail(),
                    user.getPhone(), user.getID()
            };
            flag = BaseDao.execute(connection, sql, params, null);
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    @Override
    public int setUserScore(Connection connection, int score, int id) throws SQLException {
        int flag = 0;
        if (null != connection) {
            String sql = "update user set score = ? where id = ?";
            Object[] params = {score, id};
            flag = BaseDao.execute(connection, sql, params, null);
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    @Override
    public int setUserResult(Connection connection, String result, int id) throws SQLException {
        int flag = 0;
        if (null != connection) {
            String sql = "update user set " +
                    "result = ?" +
                    "where id = ? ";
            Object[] params = {result, id};
            flag = BaseDao.execute(connection, sql, params, null);
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    protected void setUser(User user, ResultSet resultSet) throws SQLException {
        user.setID(resultSet.getInt("ID"));
        user.setName(resultSet.getString("name"));
        user.setStudentID(resultSet.getString("studentID"));
        user.setTarget(resultSet.getString("target"));
        user.setGrade(resultSet.getString("grade"));
        user.setPassword(resultSet.getString("password"));
        user.setMajor(resultSet.getString("major"));
        user.setMail(resultSet.getString("mail"));
        user.setPhone(resultSet.getString("phone"));
        user.setRights(resultSet.getInt("rights"));
        user.setScore(resultSet.getInt("score"));
        user.setResult(resultSet.getString("result"));
    }
}
