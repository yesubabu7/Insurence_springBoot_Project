package com.example.task.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.task.models.UserData;

public class UsersDataRowMapper implements RowMapper<UserData> {
    @Override
    public UserData mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Long userId = resultSet.getLong("user_id");
        Long custId = resultSet.getLong("cust_id");
        String userName = resultSet.getString("user_name");
        java.util.Date userCDate = resultSet.getDate("user_cdate");
        String userPwd = resultSet.getString("user_pwd");
        String userType = resultSet.getString("user_type");
        String userStatus = resultSet.getString("user_status");

        UserData user = new UserData();
        user.setUserId(userId);
        user.setCustId(custId);
        user.setUserName(userName);
        user.setUserCDate(userCDate);
        user.setUserPwd(userPwd);
        user.setUserType(userType);
        user.setUserStatus(userStatus);

        return user;
    }
}
