package com.gitlily.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.gitlily.domain.User;
import com.gitlily.utils.DataSourceUtils;

public class UserDao {

	public int regist(User user) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
		int update = runner.update(sql, user.getUid(), user.getUsername(), user.getPassword(), 
				user.getName(), user.getEmail(), user.getTelephone(),
				user.getBirthday(), user.getSex(), user.getState(),user.getCode());
		return update;
	}

}
