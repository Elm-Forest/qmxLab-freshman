package com.qmxkaifa.service.winner;

import com.qmxkaifa.dao.BaseDao;
import com.qmxkaifa.dao.winner.WinnerDao;
import com.qmxkaifa.dao.winner.WinnerDaoImpl;
import com.qmxkaifa.entity.Winner;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class WinnerServiceImpl implements WinnerService {
    private final WinnerDao winnerDao;

    public WinnerServiceImpl() {
        winnerDao = new WinnerDaoImpl();
    }

    @Override
    public Winner getWinner(String ID) {
        Connection connection = null;
        Winner winner = null;
        try {
            connection = BaseDao.getConnection();
            winner = winnerDao.getWinner(connection, ID);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        System.out.println("调用UserService");
        return winner;
    }

    @Override
    public boolean addWinner(Winner winner) {
        boolean flag = false;
        Connection connection = null;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);    //开启JDBC事务管理
            int updateRows = winnerDao.addWinner(connection, winner);
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
                System.out.println("rollback==================");
                assert connection != null;
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            //在service层进行connection连接的关闭
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    @Override
    public boolean isExistWinner(Winner winner) {
        Connection connection = null;
        Winner userFlag = null;
        try {
            connection = BaseDao.getConnection();
            userFlag = winnerDao.isExistWinner(connection, winner.getPhone());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        System.out.println("调用UserService.isExistUser");
        return userFlag != null;
    }

    @Override
    public List<Winner> getUserList() {
        Connection connection = null;
        List<Winner> userList = null;
        try {
            connection = BaseDao.getConnection();
            userList = winnerDao.getUserList(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return userList;
    }

    @Test
    public void test() {
        WinnerServiceImpl userService = new WinnerServiceImpl();
        /*Winner user = new Winner(1, 111, "2020111112");*/
        System.out.println(userService.getUserList());
    }
}
