package com.qmxkaifa.service.winner;

import com.qmxkaifa.entity.User;
import com.qmxkaifa.entity.Winner;

import java.util.List;

public interface WinnerService {
    //用户登录
    Winner getWinner(String ID);

    boolean addWinner(Winner user);

    boolean isExistWinner(Winner user);

    List<Winner> getUserList();
}
