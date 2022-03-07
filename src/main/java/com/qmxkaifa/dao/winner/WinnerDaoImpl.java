package com.qmxkaifa.dao.winner;

import com.qmxkaifa.dao.BaseDao;
import com.qmxkaifa.entity.Winner;
import com.qmxkaifa.service.user.UserServiceImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WinnerDaoImpl implements WinnerDao {
    @Override
    public Winner getWinner(Connection connection, String ID) {
        ResultSet resultSet = null;
        Winner winner = null;
        if (connection != null) {
            String sql = "select * from winner where ID=?";
            Object[] params = {ID};
            try {
                resultSet = BaseDao.execute(connection, sql, params, null, null);
                if (resultSet.next()) {
                    winner = new Winner();
                    winner.setName(resultSet.getString("name"));
                    winner.setPhone(resultSet.getString("studentID"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                BaseDao.closeResource(connection, null, resultSet);
            }
        }
        return winner;
    }

    @Override
    public int addWinner(Connection connection, Winner winner) throws Exception {
        int updateRows = 0;
        if (null != connection) {
            String sql = "insert into winner (ID,studentID) values(?,?)";
            Object[] params = {new UserServiceImpl().getUser(winner.getPhone()), winner.getPhone()};
            updateRows = BaseDao.execute(connection, sql, params, null);
            BaseDao.closeResource(null, null, null);
        }
        return updateRows;
    }

    @Override
    public Winner isExistWinner(Connection connection, String studentID) {
        ResultSet resultSet = null;
        Winner winner = null;
        if (connection != null) {
            String sql = "select * from winner where ID=?";
            Object[] params = {studentID};
            try {
                resultSet = BaseDao.execute(connection, sql, params, null, null);
                if (resultSet.next()) {
                    winner = new Winner();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                BaseDao.closeResource(connection, null, resultSet);
            }
        }
        return winner;
    }

    @Override
    public List<Winner> getUserList(Connection connection) throws SQLException {
        ResultSet resultSet;
        List<Winner> winnerList = new ArrayList<>();
        if (connection != null) {
            StringBuilder sql = new StringBuilder();
            sql.append("select user.* from user");
            Object[] params = {};
            System.out.println("sql ----> " + sql);
            resultSet = BaseDao.execute(connection, sql.toString(), params, null, null);
            while (resultSet.next()) {
                Winner winner = new Winner();
                winner.setName(resultSet.getString("name"));
                winner.setPhone(resultSet.getString("studentID"));
                winnerList.add(winner);
            }
            BaseDao.closeResource(null, null, resultSet);
        }
        return winnerList;
    }

}
