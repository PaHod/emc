package com.emc.mvc;

import com.emc.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JDBCExample {


    private static final String IDUSER = "IDUSER";
    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";
    private static final String ENABLED = "ENABLED";
    @Autowired
    DataSource dataSource; //look to application-context.xml bean id='dataSource' definition

    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<User> queryAllUsers() {
        System.out.println("JDBCExample: queryAllUsers is called");

        final String QUERY_SQL = "SELECT * FROM USER ORDER BY IDUSER";
        final List<User> userlist = jdbcTemplate.query(QUERY_SQL, (resultSet, rowNum) -> {
            User user =new User();
            user.setIdUser(resultSet.getInt(IDUSER));
            user.setUsername(resultSet.getString(USERNAME));
            user.setPassword(resultSet.getString(PASSWORD));
            user.setEnabled(resultSet.getBoolean(ENABLED));
            return user;
        });
        return userlist;
    }
}
