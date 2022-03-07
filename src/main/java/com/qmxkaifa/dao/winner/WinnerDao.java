package com.qmxkaifa.dao.winner;

import com.qmxkaifa.entity.Winner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface WinnerDao {
    Winner getWinner(Connection connection, String userCode);

    int addWinner(Connection connection, Winner user) throws Exception;

    Winner isExistWinner(Connection connection, String studentID);

    List<Winner> getUserList(Connection connection) throws SQLException;
}
