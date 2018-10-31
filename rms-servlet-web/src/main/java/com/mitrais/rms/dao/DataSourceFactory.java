package com.mitrais.rms.dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.io.IOException;
import java.io.InputStream;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This class provides MySQL datasource to be used to connect to database.
 * It implements singleton pattern 
 * See <a href="http://www.oodesign.com/singleton-pattern.html">Singleton Pattern</a>
 */
public class DataSourceFactory {
    private final Properties prop;
    private final DataSource dataSource;

    DataSourceFactory() {
        MysqlDataSource dataSource = new MysqlDataSource();
        prop = this.getDbConfig();
        dataSource.setDatabaseName(prop.getProperty("databaseName"));
        dataSource.setServerName(prop.getProperty("serverName"));
        dataSource.setPort(Integer.parseInt(prop.getProperty("port")));
        dataSource.setUser(prop.getProperty("user"));
        dataSource.setPassword(prop.getProperty("password"));
        dataSource.setUseSSL(Boolean.valueOf(prop.getProperty("useSSL")));
        this.dataSource = dataSource;
    }

    /**
     * Get a data source to database
     *
     * @return DataSource object
     * @throws java.sql.SQLException any SQL Exception
     */
    public static Connection getConnection() throws SQLException {
        return SingletonHelper.INSTANCE.dataSource.getConnection();
    }

    private static class SingletonHelper {
        private static final DataSourceFactory INSTANCE = new DataSourceFactory();
    }
    
    private Properties getDbConfig() {
        // TODO: remove explicit usage of 'target/classes' folder
        Properties rtn = new Properties();
//        Path path = Paths.get("target/classes/database.properties"); 
//        try (FileInputStream in = new FileInputStream(path.toFile())) {
//            rtn.load(in);
//        }
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream input = classLoader.getResourceAsStream("database.properties")) {
            rtn.load(input);
        } catch (IOException ex) {
            System.err.println("getDbConfig() - " + ex.getMessage());
        }
        return rtn;
    }
    
    public static Properties getDbProperties() {
        return SingletonHelper.INSTANCE.prop;
    }
}
