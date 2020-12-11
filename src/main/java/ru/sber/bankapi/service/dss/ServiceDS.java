package ru.sber.bankapi.service.dss;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ServiceDS {
    private static DataSource dataSource;
    private static HikariConfig config = new HikariConfig();
    private static final String DB_URL = "jdbc:h2:~/test;AUTO_SERVER=TRUE;Mode=Oracle;" +
            "INIT=runscript from 'src/main/resources/sql/schema.sql'\\;runscript from 'src/main/resources/sql/set_data.sql'";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "";

    static {
        //config.setUsername(DB_USERNAME);
        //config.setPassword(DB_PASSWORD);
        config.setJdbcUrl(DB_URL);

        dataSource = new HikariDataSource(config);
    }

    private ServiceDS() {}

    public static Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }
}
