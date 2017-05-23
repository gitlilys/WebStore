package com.gitlily.service;

import java.sql.SQLException;

import com.gitlily.dao.UserDao;
import com.gitlily.domain.User;

public class UserService {

	public boolean regist(User user) {
		UserDao dao = new UserDao();
		int row = 0;
		try {
			row = dao.regist(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return row > 0 ? true:false;
	}

	
}
