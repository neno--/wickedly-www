package com.github.nenomm.wickedly.mvcxml.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.github.nenomm.wickedly.mvcxml.security.MyUser;

@Repository
public class BigDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public MyUser getUser(String username) {
		try {
			return jdbcTemplate.queryForObject(
					"select username, password, enabled from users where username = ?",
					new Object[] { username },
					new RowMapper<MyUser>() {
						public MyUser mapRow(ResultSet rs, int rowNum) throws SQLException {
							return new MyUser(rs.getString("username"), rs.getString("password"),
									rs.getBoolean("enabled"));
						}
					});
		}
		catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<String> getRolesForUser(MyUser user) {
		return jdbcTemplate.query(
				"select role from user_roles where username = ?",
				new Object[] { user.getUsername() },
				new RowMapper<String>() {
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getString("role");
					}
				});
	}
}
