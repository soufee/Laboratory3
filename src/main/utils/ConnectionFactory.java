package main.utils;

import listeners.MySessionListener;
import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Shoma on 18.04.2017.
 */
public class ConnectionFactory {
 private static Logger userLogger = Logger.getLogger(MySessionListener.class);
    public static Connection conn = null;

    public static Connection getConnection() {
        try {

            DataSource datasource = new DataSource();
            userLogger.debug("Trying to make connection in ConnectionFactory ");
            PoolProperties p = new PoolProperties();
            p.setUrl("jdbc:postgresql://localhost/ashamaz");
            p.setDriverClassName("org.postgresql.Driver");
            p.setUsername("postgres");
            p.setPassword("mutanabbi1");
            p.setJmxEnabled(true);
            p.setTestWhileIdle(false);
            p.setTestOnBorrow(true);
            p.setValidationQuery("SELECT 1");
            p.setTestOnReturn(false);
            p.setValidationInterval(30000);
            p.setTimeBetweenEvictionRunsMillis(30000);
            p.setMaxActive(100);
            p.setInitialSize(1);
            p.setMaxWait(10000);
            p.setRemoveAbandonedTimeout(60);
            p.setMinEvictableIdleTimeMillis(30000);
            p.setMinIdle(10);
            p.setLogAbandoned(true);
            p.setRemoveAbandoned(true);
            p.setJdbcInterceptors(
                    "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" +
                            "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
            datasource.setPoolProperties(p);
            userLogger.debug("Setting properties to datasource "+datasource);

            conn = datasource.getConnection();
            userLogger.debug("Getting connection with properties "+conn);

        } catch (SQLException e) {
           userLogger.debug("A mistake with SQL "+e.getMessage());
        }

        return conn;
    }

//
//    public static DataSource getMyPGDataSource() {
//        PGPoolingDataSource source = new PGPoolingDataSource();
//        source.setDataSourceName(DATASOURCE_NAME);
//        source.setServerName("localhost");
//        source.setDatabaseName("ashamaz");
//        source.setUser("postgres");
//        source.setPassword("mutanabbi1");
//        source.setMaxConnections(100);
//        return source;
}


